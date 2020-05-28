package com.example.mvvmwithflowexample.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmwithflowexample.R

class CityAdapter(var itemList : List<String>) : RecyclerView.Adapter<CityAdapter.SearchCityViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchCityViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_city, parent, false) as TextView

        return SearchCityViewHolder(textView)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: SearchCityViewHolder, position: Int) {
        holder.textView.text = itemList[position]
    }

    fun setItems(itemList: List<String>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }

    inner class SearchCityViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
}