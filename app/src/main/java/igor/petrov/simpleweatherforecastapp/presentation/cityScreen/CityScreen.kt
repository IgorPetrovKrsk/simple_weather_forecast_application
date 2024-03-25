package igor.petrov.simpleweatherforecastapp.presentation.cityScreen

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import igor.petrov.simpleweatherforecastapp.R
import igor.petrov.simpleweatherforecastapp.data.LoadingState
import igor.petrov.simpleweatherforecastapp.presentation.ErrorItem
import igor.petrov.simpleweatherforecastapp.presentation.LoadingView
import java.util.Locale

@Composable
fun CityScreen(locationKey: String) {
    val cityScreenViewModel = hiltViewModel<CityScreenViewModel>()
    cityScreenViewModel.locationKey = locationKey
    cityScreenViewModel.get5daysForecast()

    val dailyForecast by cityScreenViewModel.dailyforecast.collectAsState()
    val loadingState by cityScreenViewModel.loadingState.collectAsState()

    val inputDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault())
    val outputDateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

    Column(Modifier.padding(integerResource(id = R.integer.default_padding).dp)) {
        Text(text = stringResource(id = R.string.text_city_forcast), style = MaterialTheme.typography.headlineMedium)
        LazyColumn {
            items(dailyForecast.size ?: 0) {
                HorizontalDivider()
                val day = inputDateFormat.parse(dailyForecast[it].date)
                val dayText = outputDateFormat.format(day)
                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                    Text(text = dayText, style = MaterialTheme.typography.bodyLarge)
                    Text(text = stringResource(id = R.string.text_min_temp)
                            + dailyForecast[it].temperature.minimumTemperatureData.value
                            + " " + dailyForecast[it].temperature.minimumTemperatureData.unit
                            + " " + stringResource(id = R.string.text_max_temp)
                            + " " + dailyForecast[it].temperature.maximumTemperatureData.value
                            + " " + dailyForecast[it].temperature.maximumTemperatureData.unit, style = MaterialTheme.typography.bodyLarge)
                }

            }
            when (loadingState) {
                is LoadingState.Loading -> item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                is LoadingState.Error ->
                    item { ErrorItem((loadingState as LoadingState.Error).exception.localizedMessage ?: "Unknown error", onClickRetry = { cityScreenViewModel.get5daysForecast() }) }

                else -> {}
            }
        }
    }
}