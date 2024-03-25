package igor.petrov.simpleweatherforecastapp.presentation.cityListScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import igor.petrov.simpleweatherforecastapp.data.LoadingState
import igor.petrov.simpleweatherforecastapp.domain.GetCachedCitiesUseCase
import igor.petrov.simpleweatherforecastapp.entity.Location
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityListViewModel @Inject constructor(private val getCachedCitiesUseCase: GetCachedCitiesUseCase) :
    ViewModel() {

    private val _loadingState = MutableStateFlow<LoadingState>(LoadingState.Loading)
    val loadingState = _loadingState.asStateFlow()

    private var _locationsList = MutableStateFlow<List<Location>>(emptyList())
    val locationsList = _locationsList.asStateFlow()

    init {
        getCachedCities()
    }

    fun getCachedCities() {
        _loadingState.value = LoadingState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                delay(2000L)
                _locationsList.value = getCachedCitiesUseCase.gatCachedCities().sortedBy { it.englishName }
                _loadingState.value = LoadingState.Ready
            } catch (e: Exception) {
                _loadingState.value = LoadingState.Error(e)
            }
        }
    }

}

