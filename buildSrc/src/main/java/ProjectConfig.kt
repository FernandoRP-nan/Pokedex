object ProjectConfig {
    const val appId = "mx.nancrow.pokedex"
    const val compileSdk = 34
    const val minSdk = 26
    const val targetSdk = 34

    const val versionCode = 1
    private const val versionMajor = 1
    private const val versionMinor = 2
    private const val versionPatch = 0

    fun getVersionName(): String {
        return "$versionMajor.$versionMinor.$versionPatch"
    }

    //BuildConfig create base url
    const val baseUrlDev = "https://pokeapi.co/api/v2/"
    const val baseUrlProd = ""

}