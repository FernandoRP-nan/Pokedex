package mx.nancrow.pokedex.presentation.screens.home

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import mx.nancrow.pokedex.presentation.viewmodel.BaseViewModel
import mx.nancrow.pokedex.domain.model.network.response.ListPokemonResponse
import mx.nancrow.pokedex.domain.model.network.response.PokemonResponse
import mx.nancrow.pokedex.domain.model.network.response.PokemonSearchResponse
import mx.nancrow.pokedex.domain.use_case.ListAllPokemonUseCase
import mx.nancrow.pokedex.domain.use_case.ListPokemonUseCase
import mx.nancrow.pokedex.domain.use_case.PokemonByNameUseCase
import mx.nancrow.pokedex.domain.use_case.PokemonSearchUseCase
import mx.nancrow.pokedex.util.Resource
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val listPokemonUseCase: ListPokemonUseCase,
    private val pokemonByNameUseCase: PokemonByNameUseCase,
    private val listAllPokemonUseCase: ListAllPokemonUseCase,
    private val pokemonSearchUseCase: PokemonSearchUseCase
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
            HomeViewEvent.LoadMoreData -> loadMoreData()
            HomeViewEvent.LoadSearchData -> loadSearchData()
            is HomeViewEvent.UpdateFilter -> createFilterList(event.searchQuery)
        }
    }

    private var pokemon by mutableStateOf(state.listPokemon ?: emptyList())
    private var offset = 0 // Contador que sirve para aumentar la lista principal

    private fun fetchListPokemon() {
        viewModelScope.launch {
            try {
                when (val response = listPokemonUseCase.invoke(20, offset)) {
                    is Resource.Error -> {
                        _uiEvent.send(HomeUiEvent.ShowSnackBar(response.message ?: ""))
                        println("$response hubo error")
                    }

                    is Resource.Success -> {
                        val newPokemon = convertToListPokemon(response.data)
                        val updatedPokemonList =
                            (pokemon + newPokemon).distinct() // Crea una nueva lista combinando los datos existentes y los nuevos
                        state = state.copy(listPokemon = updatedPokemonList)
                        offset += 20 // Incrementa el offset para la próxima carga
                    }
                }
                fetchListPokemonWithImage()
            } catch (e: Exception) {
                println("error: $e")
            }
        }
    }

    private fun loadMoreData() {
        if (!state.isLoading) {
            viewModelScope.launch {
                try {
                    state = state.copy(isLoading = true)// Marcar como cargando

                    // Llamar a la función en tu caso de uso para obtener más datos de la API
                    when (val response = listPokemonUseCase.invoke(20, offset)) {
                        is Resource.Error -> {
                            // Manejar el error
                            println("$response hubo error")
                        }

                        is Resource.Success -> {
                            val newPokemon = convertToListPokemon(response.data)
                            offset += 20 // Incrementa el offset para la próxima carga
                            pokemon = newPokemon
                            // Agregar los nuevos datos a la lista existente
                            val updatedPokemonList = state.listPokemon?.plus(newPokemon)
                            state = state.copy(listPokemon = updatedPokemonList)
                        }
                    }
                    fetchListNewPokemonWithImage(pokemon)
                } catch (e: Exception) {
                    // Manejar la excepción
                    println("error: $e")
                } finally {
                    state = state.copy(isLoading = false) // Marcar como no cargando
                }
            }
        }
    }

    private fun loadSearchData() {
        if (state.listAllPokemonSearch?.isEmpty() == true) {
            viewModelScope.launch {
                try {
                    when (val response = listAllPokemonUseCase.invoke()) {
                        is Resource.Error -> {
                            _uiEvent.send(HomeUiEvent.ShowSnackBar(response.message ?: ""))
                            println("$response hubo error")
                        }

                        is Resource.Success -> {
                            val newPokemon =
                                mapListPokemonResponseToPokemonSearchResponse(response.data!!)
                            state = state.copy(listAllPokemonSearch = newPokemon)
                            fetchListPokemonSearch()
                        }
                    }
                } catch (e: Exception) {
                    println("error: $e")
                }
            }
        }
    }

    private fun fetchListPokemonSearch() {
        state.listAllPokemonSearch?.forEach {
            fetchPokemonSearch(it.name)
        }
        println("la lista con los nombres e imagenes ${state.listPokemon}")
    }

    private fun fetchPokemonSearch(pokemonName: String) {
        viewModelScope.launch {
            try {
                when (val response = pokemonSearchUseCase.invoke(pokemonName)) {
                    is Resource.Error -> {
                        //uiState = uiState.copy(isLoading = false)
                        _uiEvent.send(HomeUiEvent.ShowSnackBar(response.message ?: ""))
                    }

                    is Resource.Success -> {
                        val mutablePokemonList: MutableList<PokemonSearchResponse> = mutableListOf()
                        state.listAllPokemonSearch?.let { pokemonList ->
                            mutablePokemonList.addAll(pokemonList)
                        }
                        val newPokemon = addPokemonToList(
                            pokemonList = mutablePokemonList,
                            newPokemon = response.data!!
                        )
                        state = state.copy(listAllPokemonSearch = newPokemon)
                    }
                }
            } catch (e: Exception) {
                println("error: $e")
            }
        }
    }

    private fun fetchListPokemonWithImage() {
        state.listPokemon?.forEach {
            fetchPokemonByName(it.name)
        }
        println("la lista con los nombres e imagenes ${state.listPokemon}")
    }

    private fun fetchListNewPokemonWithImage(newPokemon: List<PokemonResponse>) {
        newPokemon.forEach {
            fetchPokemonByName(it.name)
        }
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
                        pokemon = state.listPokemon!!
                        pokemon = mergeSpritesWithNames(pokemon, response.data!!)
                        state = state.copy(listPokemon = pokemon)
                    }
                }
            } catch (e: Exception) {
                println("error: $e")
            }
        }
    }

    private fun createFilterList(searchQuery: String) {
        state = state.copy(
            searchQuery = searchQuery,
            listFilterPokemonSearch = filterPokemonList(
                state.listAllPokemonSearch!!,
                searchQuery
            )
        )
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

fun mapListPokemonResponseToPokemonSearchResponse(listPokemonResponse: ListPokemonResponse): List<PokemonSearchResponse> {
    return listPokemonResponse.results.map { namedAPIResource ->
        // Crea un nuevo objeto PokemonSearchResponse con el nombre del recurso y un ID arbitrario
        PokemonSearchResponse(
            name = namedAPIResource.name
        )
    }
}


fun mergeSpritesWithNames(
    pokemonList: List<PokemonResponse>,
    singlePokemon: PokemonResponse
): List<PokemonResponse> {
    val updatedList = pokemonList.map { pokemon ->
        if (pokemon.name == singlePokemon.name) {
            pokemon.copy(sprites = singlePokemon.sprites, id = singlePokemon.id)
        } else {
            pokemon
        }
    }
    return updatedList
}

fun addPokemonToList(
    pokemonList: MutableList<PokemonSearchResponse>,
    newPokemon: PokemonSearchResponse
): List<PokemonSearchResponse> {
    // Verifica si el id del nuevo Pokémon es mayor a 1302
    if (newPokemon.id > 1302) {
        // Si el id es mayor a 1302, no hagas ningún cambio en la lista y devuélvela tal como está
        return pokemonList
    }

    // Verifica si ya existe un Pokémon con el mismo nombre en la lista
    val existingPokemonIndex = pokemonList.indexOfFirst { it.name == newPokemon.name }

    // Si no existe, agrega el nuevo Pokémon a la lista
    if (existingPokemonIndex == -1) {
        pokemonList.add(newPokemon)
    } else {
        // Si ya existe, actualiza los valores del Pokémon existente con los del nuevo Pokémon
        val existingPokemon = pokemonList[existingPokemonIndex]
        existingPokemon.id = newPokemon.id
        existingPokemon.types =
            newPokemon.types // Puedes elegir cómo manejar la actualización de los tipos
    }

    // Retorna la lista actualizada
    return pokemonList
}


fun filterPokemonList(
    pokemonList: List<PokemonSearchResponse>,
    searchQuery: String
): List<PokemonSearchResponse> {
    return pokemonList.filter { pokemon ->
        // Filtra por nombre si el nombre contiene la cadena de búsqueda
        val nameMatch = pokemon.name.contains(searchQuery, ignoreCase = true)

        // Filtra por tipo si alguno de los tipos contiene la cadena de búsqueda
        val typeMatch = pokemon.types.any { type ->
            type.type.name.contains(searchQuery, ignoreCase = true)
        }

        // Filtra por ID si el ID coincide
        val idMatch = pokemon.id.toString().contains(searchQuery, ignoreCase = true)

        // Devuelve true si el nombre, algún tipo o el ID coinciden con la cadena de búsqueda
        nameMatch || typeMatch || idMatch
    }
}



