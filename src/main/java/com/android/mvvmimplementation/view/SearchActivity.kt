package com.android.mvvmimplementation.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.mvvmimplementation.R
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.item_search.view.*

class SearchActivity : AppCompatActivity() {

    var recommededList : ArrayList<String> = arrayListOf(
        "New Delhi", "Kokrajhar", "Jaipur", "Mumbai", "Kolkata", "Bengaluru", "Chennai", "Hyderabad",
        "Jodhpur", "Agra", "Lucknow", "Guwahati", "Dibrugarh", "Patna", "Amritsar", "Thiruvananthapuram",
        "Pune", "Chandigarh"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        rvRecommended.apply {
            layoutManager = GridLayoutManager(this@SearchActivity,
                2,
                GridLayoutManager.HORIZONTAL,
                false)
            adapter = adapter(this@SearchActivity, recommededList)
        }

        search.setOnClickListener {
            val intent = Intent(this, InitialAct::class.java)
            intent.putExtra("type", "search")
            intent.putExtra("placeName", "${searchText.text}")
            startActivity(intent)
            finish()
        }

    }

    class adapter(val context: Context, val nameList : ArrayList<String>) :
        RecyclerView.Adapter<adapter.NameViewHolder>() {

        val fallDown = AnimationUtils.loadAnimation(context, R.anim.anim_fall_down)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
            val li = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val itemView = li.inflate(R.layout.item_search, parent, false)
            return NameViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return nameList.size
        }

        override fun onBindViewHolder(holder: adapter.NameViewHolder, position: Int) {
            holder.itemView.parentRecommend.startAnimation(fallDown)
            holder.itemView.name.text = nameList[position]
            holder.itemView.parentRecommend.setOnClickListener {
                val intent = Intent(context, InitialAct::class.java)
                intent.putExtra("type", "search")
                intent.putExtra("placeName", nameList[position])
                context.startActivity(intent)
                (context as Activity).finish()
            }
        }


        class NameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    }
}
