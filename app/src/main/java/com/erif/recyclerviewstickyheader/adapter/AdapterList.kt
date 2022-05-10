package com.erif.recyclerviewstickyheader.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.erif.recyclerviewstickyheader.R
import com.erif.recyclerviewstickyheader.adapter.holder.HolderHeader
import com.erif.recyclerviewstickyheader.adapter.holder.HolderList
import com.erif.recyclerviewstickyheader.model.People

class AdapterList: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: MutableList<People> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == 1) {
            HolderHeader(
                inflater.inflate(R.layout.item_list_header, parent, false)
            )
        } else {
            HolderList(
                inflater.inflate(R.layout.item_list, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]
        when (holder) {
            is HolderHeader -> {
                holder.bind(item)
            }
            is HolderList -> {
                holder.bind(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].type
    }

    fun setList(list: MutableList<People>) {
        this.list = list
        notifyDataSetChanged()
    }

}