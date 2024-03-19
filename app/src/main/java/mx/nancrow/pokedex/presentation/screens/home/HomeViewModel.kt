package mx.nancrow.pokedex.presentation.screens.home

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
import mx.nancrow.pokedex.domain.model.network.response.ListPokemonResponse
import mx.nancrow.pokedex.domain.model.network.response.NamedAPIResource
import mx.nancrow.pokedex.domain.model.network.response.PokemonResponse
import mx.nancrow.pokedex.domain.use_case.ListPokemonUseCase
import mx.nancrow.pokedex.domain.use_case.PokemonByNameUseCase
import mx.nancrow.pokedex.presentation.screens.act1.PokemonDataClass
import mx.nancrow.pokedex.presentation.screens.act1.PokemonUiEvent
import mx.nancrow.pokedex.util.Resource
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val listPokemonUseCase: ListPokemonUseCase,
    private val pokemonByNameUseCase: PokemonByNameUseCase,
) : BaseViewModel(application) {

    var state by mutableStateOf(HomeViewState())
        private set

    private val _uiEvent = Channel<HomeUiEvent>()


    init {
        initViewState(HomeViewState())
        fetchListPokemon()
    }

    fun onEvent(event: HomeViewEvent) {
        when (event) {
            is HomeViewEvent.OnEmailChange -> onTextChange(event.newText)
            HomeViewEvent.OnHiddenDialogError -> showDialogLogout(false)
            HomeViewEvent.OnShowDialogError -> showDialogLogout(true)
        }
    }

    private var pokemon by mutableStateOf(state.listPokemon)

    private fun fetchListPokemon() {
        viewModelScope.launch {
            try {
                when (val response = listPokemonUseCase.invoke(20, 0)) {
                    is Resource.Error -> {
                        //uiState = uiState.copy(isLoading = false)
                        _uiEvent.send(HomeUiEvent.ShowSnackBar(response.message ?: ""))
                        println("$response hubo error")
                    }
                    is Resource.Success -> {
                        pokemon = convertToListPokemon(response.data)
                        println("${response.data}" )
                        println("${convertToListPokemon(response.data)} aqui la lista convertida" )
                        state = state.copy(listPokemon = pokemon)
                    }
                }
                println("la lista con únicamente los nombres $pokemon")
                fetchListPokemonWithImage()
            } catch (e: Exception) {
                println("error: $e")
            }
        }
    }

    private fun fetchListPokemonWithImage() {
        state.listPokemon?.forEach{
            fetchPokemonByName(it.name)
        }
        println("la lista con los nombres e imagenes ${state.listPokemon}")
    }

        private fun fetchPokemonByName(pokemonName: String) {
        viewModelScope.launch {
            try {
                when (val response = pokemonByNameUseCase.invoke(pokemonName)) {
                    is Resource.Error -> {
                        //uiState = uiState.copy(isLoading = false)
                        _uiEvent.send(HomeUiEvent.ShowSnackBar(response.message ?: ""))
                    }

                    is Resource.Success -> {
                        pokemon = mergeSpritesWithNames(pokemon!!, response.data!!)
                        state = state.copy(listPokemon = pokemon)
                    }
                }
            } catch (e: Exception) {
                println("error: $e")
            }
        }
    }

    private fun onTextChange(newText: String) {
        state = state.copy(
            input = newText
        )
    }

    private fun showDialogLogout(it: Boolean) {
        state = state.copy(
            showDialogError = it
        )
    }
}

fun convertToListPokemon(listPokemonResponse: ListPokemonResponse?): List<PokemonResponse> {
    val pokemonList: MutableList<PokemonResponse> = mutableListOf()
    if (listPokemonResponse == null) {
        return pokemonList
    }

    val nameMap = listPokemonResponse.results.associateBy { it.name }
    pokemonList.addAll(listPokemonResponse.results.map { namedAPIResource ->
        val namedPokemon = nameMap[namedAPIResource.name]
        if (namedPokemon != null) {
            PokemonResponse(name = namedPokemon.name)
        } else {
            null
        }
    }.filterNotNull())

    return pokemonList
}

fun mergeSpritesWithNames(pokemonList: List<PokemonResponse>, singlePokemon: PokemonResponse): List<PokemonResponse> {
    val updatedList = pokemonList.map { pokemon ->
        if (pokemon.name == singlePokemon.name) {
            pokemon.copy(sprites = singlePokemon.sprites, id = singlePokemon.id)
        } else {
            pokemon
        }
    }
    return updatedList
}



