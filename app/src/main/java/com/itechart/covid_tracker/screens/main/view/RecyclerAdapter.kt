package com.itechart.covid_tracker.screens.main.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.itechart.covid_tracker.R
import com.itechart.covid_tracker.screens.main.MainViewModel

const val ITEM_ON_PAGE_COUNT = 10

class RecyclerAdapter(private val fragment: MainFragment, private val viewModel: MainViewModel) : RecyclerView.Adapter<RecyclerAdapter.LineViewHolder>(){
    var offset = 0
    private set

    /**
     * container of a line with itemView in it
     * itemView - LinearLayout generated from item_main.xml
     */
    class LineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val line = itemView
        val ib_favorite = itemView.findViewById<ImageButton>(R.id.ib_star)
    }

    //on create empty line
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LineViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_main, parent, false)
        return LineViewHolder(itemView)
    }

    //filling line views
    override fun onBindViewHolder(holder: LineViewHolder, position: Int) {
        val realPosition = position + offset* ITEM_ON_PAGE_COUNT
        val country = viewModel.countries[realPosition]

        holder.ib_favorite.setImageResource(if (country.favorite) R.drawable.star_filled else R.drawable.star_empty) //favorites
        holder.ib_favorite.setOnClickListener {
            viewModel.starred(realPosition)
            holder.ib_favorite.setImageResource(if (country.favorite) R.drawable.star_filled else R.drawable.star_empty)
        }

        val tv_label = holder.line.findViewById<TextView>(R.id.tv_label)
        tv_label.text = country.name

        holder.line.setOnClickListener { //to open chart
            fragment.lineItemPressed(country.id)
        }
    }

    override fun getItemCount() = (viewModel.countries.size - offset* ITEM_ON_PAGE_COUNT).coerceAtMost(
        ITEM_ON_PAGE_COUNT
    )

    /**
     * set viewing page.
     * measures: [0, MainPresenter.getListLength() / ITEM_ON_PAGE_COUNT]
     */
    fun setPage(nom: Int){
        val newOffset = nom.coerceAtMost(viewModel.countries.size / ITEM_ON_PAGE_COUNT).coerceAtLeast(0)
        if (newOffset==offset) return
        offset = newOffset
        notifyDataSetChanged()
    }

    /**
     * filters showing items by names
     */
    fun filter(filter: String){
        viewModel.filter(filter)
        offset = 0
        fragment.updatePaging()
        notifyDataSetChanged()
    }
}