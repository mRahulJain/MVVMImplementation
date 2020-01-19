package com.mvvmzomato.mvvmimplementation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.mvvmzomato.mvvmimplementation.model.ZomatoService
import com.mvvmzomato.mvvmimplementation.model.retrofitCallback
import com.mvvmzomato.mvvmimplementation.model.weatherP

class categoriesViewModel : ViewModel() {

    private val zomatoService = ZomatoService()

    var dataFetched = MutableLiveData<weatherP>()
    var dataLoadError = MutableLiveData<Boolean>()
    var loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchData()
    }

    private fun fetchData() {
        loading.value = true
        zomatoService.getCategories()
            .enqueue(retrofitCallback{ throwable, response ->
                response?.let {
                    if(it.isSuccessful) {
                        Log.d("myCHECK", "IF PART")
                        dataFetched = it.body()!!
                        loading.value = false
                        dataLoadError.value = false
                    } else {
                        dataLoadError.value = true
                        loading.value = false
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