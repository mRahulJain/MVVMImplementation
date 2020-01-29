package com.android.mvvmimplementation.view


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.mvvmimplementation.R
import com.android.mvvmimplementation.model.weatherP
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_vertical.view.*

class VerticalFragment(dataFetched : weatherP) : Fragment() {

    val weather = dataFetched

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_vertical, container, false)

        val url = "https://openweathermap.org/img/wn/${weather.weather[0].icon}@2x.png"
        Log.d("myCHECK", "${weather.weather[0].icon}")
        view!!.placeName.text = weather.name
        Picasso.with(view!!.context)
            .load(url)
            .into(view!!.weatherType)
        view!!.tempCelsius.text = ((weather.main.temp-273.15).toInt()).toString()+"\u2103"
        view!!.main.text = weather.weather[0].main
        view!!.tempPressure.text = ((weather.main.pressure*100).toInt()).toString()+" P"
        view!!.tempHumidity.text = (weather.main.humidity).toString()+"%"

        view!!.swipeDown.setOnClickListener {
            (activity as InitialAct).detailContent()
        }
        return view
    }


}
