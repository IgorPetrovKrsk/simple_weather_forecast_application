package igor.petrov.simpleweatherforecastapp

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.hilt.android.HiltAndroidApp
import igor.petrov.simpleweatherforecastapp.data.AppDatabase

@HiltAndroidApp
class App : Application() {

    lateinit var db: AppDatabase

    init {
        instance = this
    }

    companion object {
        private var instance: App? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }
    override fun onCreate() {
        super.onCreate()
        // initialize for any

        // Use ApplicationContext.
        // example: SharedPreferences etc...
        val context: Context = App.applicationContext()
        SharedPreferencesManager.init(this)

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "simple_weather_app_database"
        ).fallbackToDestructiveMigration().build()
    }
}