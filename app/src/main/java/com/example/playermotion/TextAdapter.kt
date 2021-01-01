package com.example.playermotion

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TextAdapter(
    private val data: List<String>
) : RecyclerView.Adapter<TextAdapter.TextViewHolder>() {

    class TextViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TextViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false) as TextView
        return TextViewHolder(textView)
    }

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        holder.textView.text = data[position]
    }

    override fun getItemCount() = data.size
}