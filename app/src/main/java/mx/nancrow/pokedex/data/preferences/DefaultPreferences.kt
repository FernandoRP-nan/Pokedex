package mx.nancrow.pokedex.data.preferences

import android.content.SharedPreferences
import mx.nancrow.pokedex.domain.preferences.Preferences

class DefaultPreferences(
    private val sharedPref: SharedPreferences
): Preferences {
    override fun isDarkTheme(darkTheme: Boolean) {
        sharedPref.edit()
            .putBoolean(Preferences.IS_DARK_THEME, darkTheme)
            .apply()
    }

    override fun loadDarkTheme(): Boolean {
        return sharedPref
            .getBoolean(Preferences.IS_DARK_THEME, false)
    }
}