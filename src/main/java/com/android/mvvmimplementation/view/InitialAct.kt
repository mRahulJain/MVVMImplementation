package com.android.mvvmimplementation.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProviders
import com.android.mvvmimplementation.R
import com.android.mvvmimplementation.viewModel.categoriesViewModel
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsStatusCodes
import kotlinx.android.synthetic.main.activity_initial.*

class InitialAct : AppCompatActivity(), LocationListener {

    lateinit var mVerticalFragment : VerticalFragment
    lateinit var mVerticalFragment1 : VerticalFragmentDetails
    lateinit var mVerticalFragment2 : VerticalFragment
    private var locationManager:LocationManager? = null
    private var myLat = 0.0
    private var myLong = 0.0
    private val LOCATION_REQ = 123
    private val CHECK_REQ = 121
    lateinit var categoryViewModel : categoriesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial)
        checkUserSettingsAndGetLocation()
    }

    inner class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        //three fragments
        override fun getCount(): Int {
            return 2
        }

        override fun getItem(position: Int): Fragment {
            when(position){
                0 -> {
                    mVerticalFragment = VerticalFragment(categoryViewModel.dataFetched)
                    return mVerticalFragment
                }
                1 -> {
                    mVerticalFragment1 = VerticalFragmentDetails(categoryViewModel.dataFetched)
                    return mVerticalFragment1
                }
            }
            mVerticalFragment2 = VerticalFragment(categoryViewModel.dataFetched)
            return mVerticalFragment2
        }
    }

    fun closeContent() {
        currentLocationWeather.currentItem=0
    }
    fun detailContent() {
        currentLocationWeather.currentItem=1
    }

    override fun onLocationChanged(it: Location?) {
        myLat = it!!.latitude
        myLong = it!!.longitude
        Log.d("myCHECK", "$myLat&$myLong")
        categoryViewModel = ViewModelProviders.of(this).get(categoriesViewModel::class.java)
        categoryViewModel.refresh(myLat, myLong)
        observeViewModel()
    }

    private fun observeViewModel() {
        categoryViewModel.loading.observe(this, Observer {
            if(it == false) {
                progressBar.isVisible = false
                currentLocationWeather.isVisible = true
                var adapter = ViewPagerAdapter(supportFragmentManager)
                currentLocationWeather.adapter = adapter
            }
        })
    }

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderEnabled(p0: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderDisabled(p0: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun checkAndStartLocationUpdates() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ),
                LOCATION_REQ
            )
        }else{
            startLocationUpdates()
        }
    }

    private fun checkUserSettingsAndGetLocation(){

        val locationRequest = LocationRequest().apply {
            interval = 1000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        val request = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
            .build()
        val client = LocationServices.getSettingsClient(this)

        client.checkLocationSettings(request).apply {
            addOnSuccessListener {
                Log.i("PUI","success")
                checkAndStartLocationUpdates()
            }
            addOnFailureListener{
                val e = it as ApiException
                if (e.statusCode == LocationSettingsStatusCodes.RESOLUTION_REQUIRED){
                    val resolvable = it as ResolvableApiException
                    resolvable.startResolutionForResult(this@InitialAct,CHECK_REQ)
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == LOCATION_REQ){
            for(i in grantResults.indices){
                if (grantResults[i]!= PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"Please Give ${permissions[i]}", Toast.LENGTH_LONG).show()
                    return
                }
            }
            startLocationUpdates()
        }
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val enabledProvider =
            when {
                locationManager!!.isProviderEnabled(LocationManager.NETWORK_PROVIDER) -> LocationManager.NETWORK_PROVIDER
                locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER) -> LocationManager.GPS_PROVIDER
                else -> "nill"
            }
        locationManager?.requestLocationUpdates(
            enabledProvider,
            1000,
            0f,
            this
        )
    }

    override fun onDestroy() {
        locationManager?.removeUpdates(this)
        super.onDestroy()
    }
}
