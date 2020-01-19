package com.mvvmzomato.mvvmimplementation.model

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ZomatoAPI {

    @GET("2.5/weather")
    fun getCategories(
        @Query("q") place : String,
        @Query("appid") key : String
    ) : Call<MutableLiveData<weatherP>>

}