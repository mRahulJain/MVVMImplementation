package com.android.mvvmimplementation.model

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("2.5/weather")
    fun getWeather(
        @Query("q") place : String,
        @Query("appid") key : String
    ) : Call<weatherP>
    @GET("2.5/weather")
    fun getWeatherLatLon(
        @Query("lat") myLat : Double,
        @Query("lon") myLong : Double,
        @Query("appid") key : String
    ) : Call<weatherP>

}