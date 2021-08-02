package com.erif.recyclervviewstickyheader.item_decoration

import android.graphics.Canvas
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.erif.recyclervviewstickyheader.R
import com.erif.recyclervviewstickyheader.adapter.holder.HolderHeader
import com.erif.recyclervviewstickyheader.adapter.holder.HolderList
import com.erif.recyclervviewstickyheader.model.People

class StickyDecoration constructor(private val list: MutableList<People>): RecyclerView.ItemDecoration() {

    private var headerView: View? = null

    override fun onDrawOver(c: Canvas, recyclerView: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, recyclerView, state)

        val topChild: View? = recyclerView.getChildAt(0)
        val secondChild: View? = recyclerView.getChildAt(1)

        topChild?.let {
            val adapterPos = recyclerView.getChildAdapterPosition(topChild)
            val holder = recyclerView.getChildViewHolder(topChild)
            var title: String? = list[adapterPos].headerTitle
            if (holder is HolderList) {
                for (i in adapterPos..0) {
                    if (list[i].type == 1) {
                        title = list[i].title
                        break
                    }
                }
            }
            headerView = inflateHeaderView(recyclerView, title)
            fixLayoutSize(headerView ?: return, recyclerView)
            secondChild?.let { sChild ->
                val holderSeconds = recyclerView.getChildViewHolder(sChild)
                if (holderSeconds is HolderHeader) {
                    val headerBottom = headerView?.bottom ?: 0
                    val childTop = sChild.top
                    if (headerBottom >= childTop) {
                        moveHeader(c, headerView ?: return, sChild)
                        return
                    }
                }
            }
            drawHeader(c, headerView)
        }
        //c.drawRect(rect, paintRect)
    }

    private fun drawHeader(c: Canvas, header: View?) {
        c.save()
        c.translate(0f, 0f)
        header?.visibility = View.VISIBLE
        header?.draw(c)
        c.restore()
    }

    private fun moveHeader(c: Canvas, currentHeader: View, nextHeader: View) {
        c.save()
        val yPos = nextHeader.top - currentHeader.height
        c.translate(0f, yPos.toFloat())
        currentHeader.draw(c)
        c.restore()
    }

    private fun inflateHeaderView(parent: RecyclerView, title: String?): View {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sticky_header, parent, false)
        val textView: TextView = view.findViewById(R.id.sticky_title)
        textView.text = title
        return view
    }

    private fun fixLayoutSize(view: View, parent: ViewGroup) {
        val widthSpec = View.MeasureSpec.makeMeasureSpec(
            parent.width,
            View.MeasureSpec.EXACTLY
        )
        val heightSpec = View.MeasureSpec.makeMeasureSpec(
            parent.height,
            View.MeasureSpec.UNSPECIFIED
        )
        val childWidth = ViewGroup.getChildMeasureSpec(
            widthSpec,
            parent.paddingStart + parent.paddingEnd,
            view.layoutParams.width
        )
        val childHeight = ViewGroup.getChildMeasureSpec(
            heightSpec,
            parent.paddingTop + parent.paddingBottom,
            view.layoutParams.height
        )
        view.measure(childWidth, childHeight)
        view.layout(0, 0, view.measuredWidth, view.measuredHeight)
    }

    private fun log(title: String) {
        Log.d("StickyApp", title)
    }

    fun idle() {
        headerView?.visibility = View.INVISIBLE
    }

}