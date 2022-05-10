package com.erif.recyclerviewstickyheader.adapter.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.erif.recyclerviewstickyheader.R
import com.erif.recyclerviewstickyheader.model.People

class HolderList(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val txtTitle: TextView = itemView.findViewById(R.id.item_title)
    private val txtDesc: TextView = itemView.findViewById(R.id.item_desc)

    fun bind(item: People) {
        txtTitle.text = item.title
        txtDesc.text = item.desc
    }

}