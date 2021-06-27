package com.itechart.covid_tracker.screens.favorites.view

import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.itechart.covid_tracker.R
import com.itechart.covid_tracker.screens.favorites.FavoritesViewModel

class RecyclerAdapter(private val viewModel: FavoritesViewModel) : RecyclerView.Adapter<RecyclerAdapter.LineViewHolder>(), Swipable {

    //on create empty line
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LineViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_main, parent, false)
        val ib_favorites = itemView.findViewById<ImageButton>(R.id.ib_star)
        ib_favorites.visibility = INVISIBLE
        return LineViewHolder(itemView)
    }

    //filling line views
    override fun onBindViewHolder(holder: LineViewHolder, position: Int) {
        val day = viewModel.countries[position]

        val tv_label = holder.line.findViewById<TextView>(R.id.tv_label)
        tv_label.text = day.name
    }

    override fun getItemCount() = viewModel.listLength

    override fun onItemDismissed(nom: Int) {
        viewModel.countries[nom].favorite = false
        viewModel.countries.removeAt(nom)
        notifyItemRemoved(nom)
    }

    /**
     * container of a line with itemView in it
     * itemView - LinearLayout generated from item_main.xml
     */
    class LineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val line = itemView
    }
}