package mx.nancrow.pokedex.presentation.screens.home

sealed interface HomeViewEvent{
    data class OnEmailChange(val newText: String): HomeViewEvent
    object OnHiddenDialogError: HomeViewEvent
    object OnShowDialogError: HomeViewEvent
    object LoadMoreData: HomeViewEvent
    object LoadSearchData: HomeViewEvent
    data class UpdateFilter(val searchQuery: String): HomeViewEvent

}