package igor.petrov.simpleweatherforecastapp.navigation

import androidx.annotation.StringRes
import igor.petrov.simpleweatherforecastapp.R

enum class SimpleWeatherAppScreens (@StringRes val title:Int) {
    Search(title = R.string.text_search),
    City(title = R.string.text_city),
    CityList(title = R.string.text_city_list)
}