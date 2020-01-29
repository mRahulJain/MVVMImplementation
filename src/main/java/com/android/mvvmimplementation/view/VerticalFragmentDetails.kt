package com.android.mvvmimplementation.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.mvvmimplementation.R
import com.android.mvvmimplementation.model.weatherP
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_vertical_fragment_details.view.*

class VerticalFragmentDetails(dataFetched : weatherP) : Fragment() {

    val weather = dataFetched
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_vertical_fragment_details, container, false)

        val url = "https://openweathermap.org/img/wn/${weather.weather[0].icon}@2x.png"
        view!!.placeNameDetail.text = weather.name
        view!!.placeCountryDetail.text = weather.sys.country
        Picasso.with(view!!.context)
            .load(url)
            .into(view!!.weatherTypeDetail)
        view!!.tempCelsiusDetail.text = ((weather.main.temp-273.15).toInt()).toString()+"\u2103"
        view!!.minTempCelsiusDetail.text = ((weather.main.temp_min-273.15).toInt()).toString()+"\u2103"
        view!!.feelsTempCelsiusDetail.text = ((weather.main.feels_like-273.15).toInt()).toString()+"\u2103"
        view!!.maxTempCelsiusDetail.text = ((weather.main.temp_max-273.15).toInt()).toString()+"\u2103"

        view!!.ivClose.setOnClickListener {
            (activity as InitialAct).closeContent()
        }
        return view
    }


}
