package mx.nancrow.pokedex.presentation.screens.act2

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mx.nancrow.pokedex.presentation.viewmodel.BaseViewModel
import mx.nancrow.pokedex.domain.use_case.LocalGetAllPokemonUseCase
import javax.inject.Inject

@HiltViewModel
class Act2ViewModel @Inject constructor(
    application: Application,
    private val getLocalGetAllPokemonUseCase: LocalGetAllPokemonUseCase
) : BaseViewModel(application) {

    var state by mutableStateOf(Act2ViewState())
        private set

    init {
        initViewState(Act2ViewState())
        getFavoritePokemon()
    }

    fun onEvent(event: Act2ViewEvent) {
        when (event) {
            is Act2ViewEvent.OnInput1Change -> {}
            is Act2ViewEvent.OnInput2Change -> {}
            is Act2ViewEvent.OnInput3Change -> {}
            is Act2ViewEvent.OnInput4Change -> {}
            is Act2ViewEvent.UpdateResult -> {}
            is Act2ViewEvent.Calculate -> {}
        }
    }

    private fun getFavoritePokemon() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getLocalGetAllPokemonUseCase.invoke().collect {
                    state = state.copy(listPokemon = it)

                }
            }
        }
    }


}



