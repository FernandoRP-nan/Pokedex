package mx.nancrow.pokedex.presentation.screens.act1

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import mx.com.satoritech.journeys.presentation.viewmodel.BaseViewModel
import mx.nancrow.pokedex.R
import mx.nancrow.pokedex.domain.model.network.response.PokemonResponse
import mx.nancrow.pokedex.domain.model.network.response.PokemonSpeciesResponse
import mx.nancrow.pokedex.domain.use_case.PokemonByNameUseCase
import mx.nancrow.pokedex.domain.use_case.PokemonSpeciesUseCase
import mx.nancrow.pokedex.domain.use_case.PokemonUseCase
import mx.nancrow.pokedex.presentation.screens.home.HomeUiEvent
import mx.nancrow.pokedex.presentation.screens.home.mergeSpritesWithNames
import mx.nancrow.pokedex.util.Resource
import javax.inject.Inject

@HiltViewModel
class Act1ViewModel @Inject constructor(
    application: Application,
    private val pokemonUseCase: PokemonUseCase,
    private val pokemonSpeciesUseCase: PokemonSpeciesUseCase,
    private val pokemonByNameUseCase: PokemonByNameUseCase,
    ) : BaseViewModel(application) {

    var state by mutableStateOf(Act1ViewState())
        private set

    private val _uiEvent = Channel<PokemonUiEvent>()


    init {
        initViewState(Act1ViewState())
    }

    fun onEvent(event: Act1ViewEvent) {
        when (event) {
            is Act1ViewEvent.OnEmailChange -> onTextChange(event.newText)
            is Act1ViewEvent.UpdateResult -> updateResult(event.newText)
            Act1ViewEvent.OnHiddenDialogError -> showDialogLogout(false)
            Act1ViewEvent.OnShowDialogError -> showDialogLogout(true)
            is Act1ViewEvent.Login -> loginUser()
            is Act1ViewEvent.GetPokemon -> fetchRandomPokemon(event.pokemonId)
        }
    }

    private fun fetchRandomPokemon(id: Int) {
        viewModelScope.launch {
            try {
                var pokemon by mutableStateOf(PokemonDataClass())

                when (val response = pokemonUseCase.invoke(id)) {
                    is Resource.Error -> {
                        //uiState = uiState.copy(isLoading = false)
                        _uiEvent.send(PokemonUiEvent.ShowSnackBar(response.message ?: ""))
                    }

                    is Resource.Success -> {
                        response.data.also {
                            pokemon.id = it!!.id
                            pokemon.name = it.name
                            pokemon.sprites = it.sprites
                        }
                    }
                }
                when (val response = pokemonSpeciesUseCase.invoke(id)) {
                    is Resource.Error -> {
                        _uiEvent.send(PokemonUiEvent.ShowSnackBar(response.message ?: ""))
                    }

                    is Resource.Success -> {
                        val pokemonDescription: String = response.data?.let {
                            extractEnglishDescription(
                                it
                            )
                        }!!
                        pokemon = pokemon.copy(
                            description = pokemonDescription
                        )
                    }
                }
                val pokemonResponse: PokemonResponse? = pokemon.let {
                    PokemonResponse(
                        id = it.id,
                        name = it.name,
                        sprites = it.sprites,
                        height = it.height,
                        weight = it.weight,
                        description = it.description
                    )
                }
                state = state.copy(
                    pokemon = pokemonResponse
                )

            } catch (e: Exception) {
                println("error: $e")
            }
        }
    }

    private fun extractEnglishDescription(pokemonSpecies: PokemonSpeciesResponse): String {
        val englishFlavorTextEntry = pokemonSpecies.flavorTextEntries.find { entry ->
            entry.language.name == "es"
        }
        return englishFlavorTextEntry?.flavorText ?: "Descripción no disponible"
    }

    private fun loginUser() {
        val result = validateForm()
        if (!result)
            state = state.copy(
                result = "",
            )
        state = state.copy(
            isLoginSuccess = result,
        )
    }

    private fun onTextChange(newText: String) {
        state = state.copy(
            input = newText
        )
    }

    private fun updateResult(newText: String) {
        state = state.copy(
            result = newText
        )
    }

    private fun validate(input: String): Int? {
        var error: Int? = null
        val num = input.toIntOrNull()

        if (num == null) {
            error = R.string.act1_error_required
        } else if (num < 1 || num > 100) {
            error = R.string.act1_error_format
        }
        return error
    }

    private fun validateForm(): Boolean {
        val error: Int? = validate(state.input)
        state = state.copy(
            emailError = error,
        )
        return error == null
    }

    private fun showDialogLogout(it: Boolean) {
        state = state.copy(
            showDialogError = it
        )
    }
}



