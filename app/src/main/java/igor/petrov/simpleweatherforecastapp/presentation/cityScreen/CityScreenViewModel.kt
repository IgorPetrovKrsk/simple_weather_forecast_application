package igor.petrov.simpleweatherforecastapp.presentation.cityScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import igor.petrov.simpleweatherforecastapp.data.LoadingState
import igor.petrov.simpleweatherforecastapp.data.dto.DailyForecastDto
import igor.petrov.simpleweatherforecastapp.domain.GetForecastUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityScreenViewModel @Inject constructor(private val getForecastUseCase: GetForecastUseCase) :
    ViewModel() {

    var locationKey:String = ""
    private val _dailyForecast = MutableStateFlow<List<DailyForecastDto>>(emptyList())
    val dailyforecast = _dailyForecast.asStateFlow()

    private val _loadingState = MutableStateFlow<LoadingState>(LoadingState.Loading)
    val loadingState = _loadingState.asStateFlow()

    fun get5daysForecast() {
        _loadingState.value = LoadingState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                delay(2000L)
                _dailyForecast.value = getForecastUseCase.get5DaysForecast(locationKey)
                _loadingState.value = LoadingState.Ready
            } catch (e: Exception) {
                _loadingState.value = LoadingState.Error(e)
            }
        }
    }
}

