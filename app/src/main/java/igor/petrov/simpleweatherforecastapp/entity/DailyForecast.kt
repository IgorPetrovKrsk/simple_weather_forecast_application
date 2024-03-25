package igor.petrov.simpleweatherforecastapp.entity

interface DailyForecast {
    var key:String
    val date:String
    val temperature: Temperature
}