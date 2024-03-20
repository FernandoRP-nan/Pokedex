package mx.nancrow.pokedex.presentation.screens.act1

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mx.nancrow.pokedex.presentation.viewmodel.BaseViewModel
import mx.nancrow.pokedex.R
import mx.nancrow.pokedex.data.local.entity.FavoritePokemonEntity
import mx.nancrow.pokedex.domain.model.network.response.PokemonResponse
import mx.nancrow.pokedex.domain.model.network.response.PokemonSpeciesResponse
import mx.nancrow.pokedex.domain.use_case.LocalDeletePokemonUseCase
import mx.nancrow.pokedex.domain.use_case.LocalGetAllPokemonUseCase
import mx.nancrow.pokedex.domain.use_case.LocalGetPokemonUseCase
import mx.nancrow.pokedex.domain.use_case.LocalInsertPokemonUseCase
import mx.nancrow.pokedex.domain.use_case.PokemonSpeciesUseCase
import mx.nancrow.pokedex.domain.use_case.PokemonUseCase
import mx.nancrow.pokedex.util.Resource
import javax.inject.Inject

@HiltViewModel
class Act1ViewModel @Inject constructor(
    application: Application,
    private val pokemonUseCase: PokemonUseCase,
    private val pokemonSpeciesUseCase: PokemonSpeciesUseCase,
    private val localInsertPokemonUseCase: LocalInsertPokemonUseCase,
    private val localDeletePokemonUseCase: LocalDeletePokemonUseCase,
    private val getPokemonUseCase: LocalGetPokemonUseCase
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
            is Act1ViewEvent.SavePokemon -> savePokemon(event.pokemon)
            is Act1ViewEvent.FavoritePokemon -> favoritePokemon(event.pokemon)
        }
    }

    private fun checkFavoritePokemon() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                state.pokemon?.let {
                    getPokemonUseCase.invoke(it.id).collect {
                        if (it == null) {
                            state = state.copy(exist = false)
                        } else {
                            state = state.copy(exist = true)
                        }
                    }
                }
            }
        }
    }

    private fun favoritePokemon(pokemon: PokemonResponse) {
        if (!state.exist) {
            savePokemon(pokemon)
        } else {
            deletePokemon(pokemon)
        }
    }

    private fun deletePokemon(pokemon: PokemonResponse) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                localDeletePokemonUseCase.invoke(pokemon.toFavoritePokemonEntity())
            }
        }
    }

    private fun savePokemon(pokemon: PokemonResponse) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                localInsertPokemonUseCase.invoke(pokemon.toFavoritePokemonEntity())
            }
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
                            pokemon.types = it.types
                            pokemon.abilities = it.abilities
                            pokemon.stats = it.stats
                            pokemon.weight = it.weight
                            pokemon.height = it.height
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
                val pokemonResponse: PokemonResponse = pokemon.let {
                    PokemonResponse(
                        id = it.id,
                        name = it.name,
                        sprites = it.sprites,
                        height = it.height,
                        weight = it.weight,
                        types = it.types,
                        description = it.description,
                        abilities = it.abilities,
                        stats = it.stats
                    )
                }
                state = state.copy(
                    pokemon = pokemonResponse
                )
                checkFavoritePokemon()
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

fun PokemonResponse.toFavoritePokemonEntity(): FavoritePokemonEntity {
    return FavoritePokemonEntity(
        id = this.id,
        name = this.name,
        sprites = this.sprites.frontDefault // O cualquier otra imagen que desees utilizar
    )
}



