package com.mvvmzomato.mvvmimplementation.model

import androidx.lifecycle.MutableLiveData
import com.google.gson.ExclusionStrategy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mvvmzomato.mvvmimplementation.R
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ZomatoService {

    private val BASE_URL = "https://api.openweathermap.org/data/"
    private val api : ZomatoAPI

    init {
        api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ZomatoAPI::class.java)
    }

    fun getCategories() : Call<MutableLiveData<weatherP>> {
        return api.getCategories("New York","211ea7257466473e2656daaceff392ff")
    }
}