package igor.petrov.simpleweatherforecastapp.presentation.searchScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import igor.petrov.simpleweatherforecastapp.SharedPreferencesManager
import igor.petrov.simpleweatherforecastapp.data.LoadingState
import igor.petrov.simpleweatherforecastapp.domain.GetLocationsUseCase
import igor.petrov.simpleweatherforecastapp.entity.Location
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(private val getLocationsUseCase: GetLocationsUseCase) :
    ViewModel() {

    private val _latestSearchText = MutableStateFlow(SharedPreferencesManager.getString(LATEST_SEARCH_KEY,""))
    val latestSearchText = _latestSearchText.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _loadingState = MutableStateFlow<LoadingState>(LoadingState.Loading)
    val loadingState = _loadingState.asStateFlow()

    private var _locationsList = MutableStateFlow<List<Location>>(emptyList())
    private var allLocationList: List<Location> = emptyList()
    val locationsList = _locationsList.asStateFlow()

    init {
        getLocations()
    }

    fun getLocations() {
        _loadingState.value = LoadingState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                delay(2000L)
                allLocationList = getLocationsUseCase.getLocations().sortedBy { it.englishName }
                _locationsList.value = allLocationList
                _loadingState.value = LoadingState.Ready
            } catch (e: Exception) {
                _loadingState.value = LoadingState.Error(e)
            }
        }
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
        _latestSearchText.value = text
        if (text.isBlank())
            _locationsList.value = allLocationList
        else {
            _locationsList.value = allLocationList.filter {
                it.englishName!!.uppercase().contains(text.trim().uppercase())
            }
            viewModelScope.launch {
                SharedPreferencesManager.saveString(LATEST_SEARCH_KEY, text)
            }
        }

    }

    companion object
    {
        private const val LATEST_SEARCH_KEY = "latest_search"
    }
}

