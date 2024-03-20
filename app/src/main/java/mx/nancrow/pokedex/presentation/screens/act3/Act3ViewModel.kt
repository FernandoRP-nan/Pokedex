package mx.nancrow.pokedex.presentation.screens.act3

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.hilt.android.lifecycle.HiltViewModel
import mx.nancrow.pokedex.R
import mx.nancrow.pokedex.domain.preferences.Preferences
import mx.nancrow.pokedex.presentation.viewmodel.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class Act3ViewModel @Inject constructor(
    application: Application,
    private val preferences: Preferences
) : BaseViewModel(application) {

    var state by mutableStateOf(Act3ViewState())
        private set

    init {
        initViewState(Act3ViewState())
        setSettings()
    }

    fun onEvent(event: Act3ViewEvent) {
        when (event) {
            is Act3ViewEvent.OnClickSettings -> onClickSettings(event.label)
        }
    }

    private fun setSettings() {
        val itemsSettings = listOf<SettingsType>(
            SettingsType(
                label = R.string.change_mode,
                icon = if (preferences.loadDarkTheme()) {
                    R.drawable.toggle_on
                } else {
                    R.drawable.toggle_off
                }
            )
        )
        state = state.copy(
            itemsSettings = itemsSettings,
            isDarkTheme = preferences.loadDarkTheme()
        )
    }

    private fun onClickSettings(label: Int) {
        when (label) {
            R.string.change_mode -> {
                println("el color es: ${preferences.loadDarkTheme()}")
                preferences.isDarkTheme(!preferences.loadDarkTheme())
                setSettings()
            }
        }
    }

}



