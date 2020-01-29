package com.android.mvvmimplementation.model

data class weatherP(
    val coord : coordinate,
    val weather : ArrayList<weatherC>,
    val base : String,
    val main : mainC,
    val visibility : String,
    val wind : windC,
    val sys : Sys,
    val name : String,
    val id : String
)

data class Sys(
    val sunrise : String,
    val sunset : String,
    val country : String
)

data class coordinate(
    val lon : Float,
    val lat : Float
)

data class weatherC(
    val id : Int,
    val main : String,
    val description : String,
    val icon : String
)

data class mainC(
    val temp : Float,
    val feels_like : Float,
    val temp_min : Float,
    val temp_max : Float,
    val pressure : Float,
    val humidity : Float
)

data class windC(
    val speed : Float,
    val deg : Float
)