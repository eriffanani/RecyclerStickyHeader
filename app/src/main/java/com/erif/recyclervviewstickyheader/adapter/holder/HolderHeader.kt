package com.erif.recyclervviewstickyheader.adapter.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.erif.recyclervviewstickyheader.R
import com.erif.recyclervviewstickyheader.model.People

class HolderHeader(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val txtTitle: TextView = itemView.findViewById(R.id.item_header_title)

    fun bind(item: People) {
        txtTitle.text = item.headerTitle
    }

}