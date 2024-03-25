@file:OptIn(ExperimentalMaterial3Api::class)

package igor.petrov.simpleweatherforecastapp.presentation.searchScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
fun SearchScreen(navController: NavHostController) {
    val context = LocalContext.current
    val searchScreenViewModel = hiltViewModel<SearchScreenViewModel>()
    val searchText by searchScreenViewModel.searchText.collectAsState()
    val locations by searchScreenViewModel.locationsList.collectAsState()
    val loadingState by searchScreenViewModel.loadingState.collectAsState()
    val latestSearchText by searchScreenViewModel.latestSearchText.collectAsState()

    Column {
        OutlinedTextField(value = searchText, onValueChange = searchScreenViewModel::onSearchTextChange, enabled = loadingState !is LoadingState.Loading, placeholder = { Text(text = latestSearchText) }, modifier = Modifier
            .fillMaxWidth()
            .padding(integerResource(id = R.integer.default_padding).dp), leadingIcon = {
            Icon(Icons.Rounded.Search, contentDescription = null, modifier = Modifier.clickable {
                if (searchText.isBlank()) searchScreenViewModel.onSearchTextChange(latestSearchText)
            })
        })

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
                    item { ErrorItem((loadingState as LoadingState.Error).exception.localizedMessage ?: "Unknown error", onClickRetry = { searchScreenViewModel.getLocations() }) }
                }

                else -> {}
            }
        }
        BottomNavigation(navController = navController)
    }
}


