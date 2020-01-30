package com.android.mvvmimplementation.view


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.mvvmimplementation.R
import com.android.mvvmimplementation.model.weatherP
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_vertical_fragment_details.view.*
import java.util.*

class VerticalFragmentDetails(dataFetched : weatherP) : Fragment() {

    val weather = dataFetched
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_vertical_fragment_details, container, false)

        view!!.placeNameDetail.text = weather.name
        view!!.placeCountryDetail.text = weather.sys.country

        val url = "https://openweathermap.org/img/wn/${weather.weather[0].icon}@2x.png"
        Picasso.with(view!!.context)
            .load(url)
            .into(view!!.weatherTypeDetail)

        view!!.tempCelsiusDetail.text = ((weather.main.temp-273.15).toInt()).toString()+"\u2103"
        view!!.minTempCelsiusDetail.text = ((weather.main.temp_min-273.15).toInt()).toString()+"\u2103"
        view!!.feelsTempCelsiusDetail.text = ((weather.main.feels_like-273.15).toInt()).toString()+"\u2103"
        view!!.maxTempCelsiusDetail.text = ((weather.main.temp_max-273.15).toInt()).toString()+"\u2103"

        val calendarSunrise = Calendar.getInstance()
        val timeSunrise = Date(weather.sys.sunrise*1000)
        calendarSunrise.time = timeSunrise
        view!!.tempSunrise.text = "${calendarSunrise.get(Calendar.HOUR_OF_DAY)}:${calendarSunrise.get(Calendar.MINUTE)}AM"

        val calendarSunset = Calendar.getInstance()
        val timeSunset = Date(weather.sys.sunset*1000)
        calendarSunset.time = timeSunset
        view!!.tempSunset.text = "${calendarSunset.get(Calendar.HOUR_OF_DAY)}:${calendarSunset.get(Calendar.MINUTE)}PM"

        view!!.tempPressureDetail.text = ((weather.main.pressure*100).toInt()).toString()+" P"

        view!!.tempHumidityDetail.text = weather.main.humidity.toInt().toString()+"%"

        view!!.tempWind.text = weather.wind.speed.toString()+" km/hr"

        view!!.tempVisibility.text = weather.visibility+" m"

        view!!.ivClose.setOnClickListener {
            (activity as InitialAct).closeContent()
        }

        view!!.searchButton.setOnClickListener {
            val intent = Intent(view!!.context, SearchActivity::class.java)
            startActivity(intent)
        }
        return view
    }


}
