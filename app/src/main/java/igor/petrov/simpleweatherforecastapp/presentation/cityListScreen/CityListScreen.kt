package igor.petrov.simpleweatherforecastapp.presentation.cityListScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import igor.petrov.simpleweatherforecastapp.BottomNavigation
import igor.petrov.simpleweatherforecastapp.R
import igor.petrov.simpleweatherforecastapp.data.LoadingState
import igor.petrov.simpleweatherforecastapp.navigation.SimpleWeatherAppScreens
import igor.petrov.simpleweatherforecastapp.presentation.ErrorItem
import igor.petrov.simpleweatherforecastapp.presentation.LoadingView

@Composable
fun CityListScreen(navController: NavHostController) {
    val context = LocalContext.current
    val cityListViewModel = hiltViewModel<CityListViewModel>()
    val locations by cityListViewModel.locationsList.collectAsState()
    val loadingState by cityListViewModel.loadingState.collectAsState()

    Column {
        LazyColumn(Modifier
            .padding(integerResource(id = R.integer.default_padding).dp)
            .weight(1f)) {
            items(locations.size) {
                HorizontalDivider()
                Text(text = locations[it].englishName ?: "---", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.clickable {
                    navController.navigate(SimpleWeatherAppScreens.City.name + "/${locations[it].key}/${locations[it].englishName}")
                })
            }
            when (loadingState) {
                is LoadingState.Loading -> item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                is LoadingState.Error -> {
                    item { ErrorItem((loadingState as LoadingState.Error).exception.localizedMessage ?: "Unknown error", onClickRetry = { cityListViewModel.getCachedCities() }) }
                }
                else -> {}
            }
        }
        BottomNavigation(navController = navController)
    }
}