package mx.nancrow.pokedex.presentation.screens.act3

import mx.pokedex.presentation.viewstate.ViewState

data class Act3ViewState(
    val itemsSettings: List<SettingsType>  = listOf(),
    val isDarkTheme: Boolean = false,
    ) : ViewState()