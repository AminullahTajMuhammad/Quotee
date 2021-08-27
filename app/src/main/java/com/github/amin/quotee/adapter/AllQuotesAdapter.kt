package com.github.amin.quotee.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.amin.quotee.R
import com.github.amin.quotee.data.remote.responses.QuotesResponse
import com.github.amin.quotee.databinding.ListItemQuotesBinding

class AllQuotesAdapter(var context: Context): RecyclerView.Adapter<AllQuotesAdapter.Holder>() {

    private val quotes = arrayListOf<QuotesResponse>()

    fun updateData(list: ArrayList<QuotesResponse>) {
        quotes.clear()
        quotes.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
        Holder(LayoutInflater.from(context).inflate(R.layout.list_item_quotes, parent, false))

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val quote = quotes[position]
        with(holder) {
            binding.tvQuote.text = quote.content
            binding.tvQuoteBy.text = quote.author

            itemView.setOnClickListener {
                onItemClick?.invoke(quote)
            }
        }
    }

    private var onItemClick: ((QuotesResponse) -> Unit)? = null
    fun onItemClick(listener: (QuotesResponse) -> Unit) {
        onItemClick = listener
    }

    override fun getItemCount(): Int = quotes.size

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = ListItemQuotesBinding.bind(itemView)
    }
}