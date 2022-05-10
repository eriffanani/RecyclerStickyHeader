package com.erif.recyclerviewstickyheader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erif.recyclerviewstickyheader.adapter.AdapterList
import com.erif.recyclerviewstickyheader.item_decoration.StickyDecoration
import com.erif.recyclerviewstickyheader.model.People

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: AdapterList
    private val list: MutableList<People> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.act_recyclerView)
        adapter = AdapterList()
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        val itemDecor = StickyDecoration(list)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    itemDecor.idle()
                }
            }
        })

        recyclerView.addItemDecoration(itemDecor)
        setupList()
    }

    private fun setupList() {
        var headerTitle = "Header 0"
        for (i in 0..30) {
            val headerPosition = getSectionPosition(i)
            if (headerPosition)
                headerTitle = "Header $i"
            list.add(
                People(
                    if (headerPosition) 1 else 0,
                    if (headerPosition) null else "Ini title $i",
                    if (headerPosition) null else "Deskripnya dong nomer $i",
                    headerTitle
                )
            )
        }
        adapter.setList(list)
    }

    private fun getSectionPosition(index: Int): Boolean {
        val pos = intArrayOf(
            5, 10, 15, 20, 25, 29
        )
        return getIndex(index, pos)
    }

    private fun getIndex(index: Int, position: IntArray): Boolean {
        return position.contains(index)
    }

}