package com.android.mvvmimplementation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.mvvmimplementation.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGetStarted.setOnClickListener {
            val intent = Intent(this, InitialAct::class.java)
            intent.putExtra("type", "start")
            intent.putExtra("placeName", "")
            startActivity(intent)
            finish()
        }
    }
}
