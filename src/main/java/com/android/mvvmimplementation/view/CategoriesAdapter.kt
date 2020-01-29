package com.android.mvvmimplementation.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.mvvmimplementation.R
import com.android.mvvmimplementation.model.weatherP
import kotlinx.android.synthetic.main.layout_item_categories.view.*

class CategoriesAdapter(val context: Context, var nameList: weatherP) : RecyclerView.Adapter<CategoriesAdapter.NameViewHolder>() {

    fun update(nameL: weatherP) {
        nameList = nameL
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        val li = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = li.inflate(R.layout.layout_item_categories, parent, false)
        return NameViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: CategoriesAdapter.NameViewHolder, position: Int) {
        holder.itemView.tvCategoryName.text = nameList.main.temp.toString()
    }


    class NameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}