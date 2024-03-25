package igor.petrov.simpleweatherforecastapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import igor.petrov.simpleweatherforecastapp.navigation.SimpleWeatherAppScreens
import igor.petrov.simpleweatherforecastapp.presentation.SimpleWeatherAppBar
import igor.petrov.simpleweatherforecastapp.presentation.cityListScreen.CityListScreen
import igor.petrov.simpleweatherforecastapp.presentation.cityScreen.CityScreen
import igor.petrov.simpleweatherforecastapp.presentation.searchScreen.SearchScreen
import igor.petrov.simpleweatherforecastapp.ui.theme.SimpleWeatherForecastAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleWeatherForecastAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    SimpleWeatherApp()
                }
            }
        }
    }
}

@Composable
fun SimpleWeatherApp(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val locationName = backStackEntry?.arguments?.getString("locationName", null)
    val currentScreenName = locationName ?: stringResource(SimpleWeatherAppScreens.valueOf(backStackEntry?.destination?.route ?: SimpleWeatherAppScreens.Search.name).title)
    Scaffold(
        topBar = {
            SimpleWeatherAppBar(
                currentScreenName,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        innerPadding.calculateTopPadding()
        NavHost(navController = navController, startDestination = SimpleWeatherAppScreens.Search.name) {
            composable(route = SimpleWeatherAppScreens.Search.name) {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                ) {
                    SearchScreen(navController)
                }

            }
            composable(route = SimpleWeatherAppScreens.CityList.name) {

                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                ) {
                    CityListScreen(navController)
                }
            }
            composable(route = SimpleWeatherAppScreens.City.name + "/{locationKey}/{locationName}") { navBackStackEntry ->
                val locationKey = navBackStackEntry.arguments?.getString("locationKey")
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                ) {
                    locationKey?.let {
                        CityScreen(locationKey = locationKey)
                    }
                }
                //no bottom navigation if city view
            }
        }

    }
}

@Composable
fun BottomNavigation(navController: NavController) {
    HorizontalDivider(Modifier.fillMaxWidth(), color = Color.Black)
    Row(modifier = Modifier.fillMaxWidth())
    {
        Column(
            modifier = Modifier
                .weight(1f)
                .height(integerResource(id = R.integer.bottom_navigation_height).dp)
                .background(colorResource(id = R.color.teal_700))
                .wrapContentSize(Alignment.Center)
                .clickable { if (navController.currentDestination?.route != SimpleWeatherAppScreens.Search.name) navController.navigateUp() },
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Icon(Icons.Rounded.Search, contentDescription = null)
            Text(text = stringResource(id = R.string.text_search), fontWeight = FontWeight.Bold, color = if (navController.currentDestination?.route == SimpleWeatherAppScreens.Search.name) colorResource(id = R.color.purple_700) else Color.Unspecified)
        }
        VerticalDivider(Modifier.height(integerResource(id = R.integer.bottom_navigation_height).dp), color = Color.Black)
        Column(
            modifier = Modifier
                .weight(1f)
                .height(integerResource(id = R.integer.bottom_navigation_height).dp)
                .background(colorResource(id = R.color.teal_700))
                .wrapContentSize(Alignment.Center)
                .clickable { navController.navigate(SimpleWeatherAppScreens.CityList.name) },
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Icon(Icons.Rounded.Home, contentDescription = null)
            Text(text = stringResource(id = R.string.text_cities), fontWeight = FontWeight.Bold, color = if (navController.currentDestination?.route == SimpleWeatherAppScreens.CityList.name) colorResource(id = R.color.purple_700) else Color.Unspecified)
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    SimpleWeatherForecastAppTheme {
        SimpleWeatherApp()
    }
}