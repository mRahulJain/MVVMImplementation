package com.android.mvvmimplementation.viewModel

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.mvvmimplementation.model.WeatherService
import com.android.mvvmimplementation.model.retrofitCallback
import com.android.mvvmimplementation.model.weatherP
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsStatusCodes

class categoriesViewModel : ViewModel() {

    private val weatherService = WeatherService()
    lateinit var dataFetched : weatherP
    var dataLoadError = MutableLiveData<Boolean>()
    var loading = MutableLiveData<Boolean>()

    fun refresh(myLat : Double, myLong: Double) {
        fetchData(myLat, myLong)
    }

    private fun fetchData(myLat: Double, myLong: Double) {
        loading.value = true
        weatherService.getWeatherLatLon(myLat, myLong)
            .enqueue(retrofitCallback{ throwable, response ->
                response?.let {
                    if(it.isSuccessful) {
                        dataFetched = it.body()!!
                        loading.value = false
                        dataLoadError.value = false
                    } else {
                        dataLoadError.value = true
                        loading.value = true
                        Log.d("myCHECK", it.message())
                    }
                }
                throwable?.let {
                    Log.d("myCHECK", "INSIDE THROWABLE")
                    Log.d("myCHECK", it.localizedMessage)
                }
            })
    }
}