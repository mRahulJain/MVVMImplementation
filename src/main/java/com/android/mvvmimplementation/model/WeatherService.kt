package com.android.mvvmimplementation.model

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherService {

    private val BASE_URL = "https://api.openweathermap.org/data/"
    private val api : WeatherAPI

    init {
        api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherAPI::class.java)
    }

    fun getWeather(cityName : String) : Call<weatherP> {
        return api.getWeather("$cityName","211ea7257466473e2656daaceff392ff")
    }

    fun getWeatherLatLon(lat : Double, lon : Double) : Call<weatherP> {
        return api.getWeatherLatLon(lat,lon,"211ea7257466473e2656daaceff392ff")
    }
}