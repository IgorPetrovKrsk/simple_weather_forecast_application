package igor.petrov.simpleweatherforecastapp.entity

interface Location {
    val key:String
    val type:String
    val localizedName:String?
    val englishName:String?
    val geoPosition:GeoPosition?
}