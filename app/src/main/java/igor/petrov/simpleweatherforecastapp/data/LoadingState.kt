package igor.petrov.simpleweatherforecastapp.data

sealed class LoadingState {
    data object Ready : LoadingState()
    data object Loading : LoadingState()
    data class Error(val exception: Exception) : LoadingState()
}