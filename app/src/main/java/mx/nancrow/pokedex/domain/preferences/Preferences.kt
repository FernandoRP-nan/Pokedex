package mx.nancrow.pokedex.domain.preferences


interface Preferences {
    fun isDarkTheme(darkTheme: Boolean)

    fun loadDarkTheme(): Boolean


    companion object {
        const val IS_DARK_THEME = "is_dark_theme"
    }


}