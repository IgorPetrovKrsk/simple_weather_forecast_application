package igor.petrov.simpleweatherforecastapp.data

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://dataservice.accuweather.com/"

object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    val accuweatherApi: AccuweatherApi = retrofit.create(AccuweatherApi::class.java)
}