package com.mvvmzomato.mvvmimplementation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvvmzomato.mvvmimplementation.R
import com.mvvmzomato.mvvmimplementation.model.ZomatoService
import com.mvvmzomato.mvvmimplementation.model.retrofitCallback
import com.mvvmzomato.mvvmimplementation.viewModel.categoriesViewModel
import kotlinx.android.synthetic.main.activity_initial.*

class InitialAct : AppCompatActivity() {

    lateinit var categoryViewModel : categoriesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial)

        categoryViewModel = ViewModelProviders.of(this).get(categoriesViewModel::class.java)
        categoryViewModel.refresh()


        observeViewModel()
    }

    private fun observeViewModel() {
        categoryViewModel.dataFetched.observe(this, Observer {
            rvCategories.apply {
                rvCategories.apply {
                    layoutManager = LinearLayoutManager(this@InitialAct)
                    adapter = CategoriesAdapter(this@InitialAct, it)
                }
            }
        })
    }
}
