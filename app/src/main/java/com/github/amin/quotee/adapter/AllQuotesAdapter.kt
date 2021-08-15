package com.github.amin.quotee.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.amin.quotee.R
import com.github.amin.quotee.databinding.ListItemQuotesBinding

class AllQuotesAdapter(var context: Context): RecyclerView.Adapter<AllQuotesAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
        Holder(LayoutInflater.from(context).inflate(R.layout.list_item_quotes, parent, false))

    override fun onBindViewHolder(holder: Holder, position: Int) {}

    override fun getItemCount(): Int = 16

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = ListItemQuotesBinding.bind(itemView)
    }
}