package com.example.marcos.pagingtest

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val tasks: LiveData<PagedList<Task>> = LivePagedListBuilder(
            TaskDatasource.Factory(),
            PagedList.Config.Builder()
                    .setPageSize(20)
                    .setEnablePlaceholders(false)
                    .build()
    ).build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar()
        setupRecyclerView()
        setupDataListener()
    }

    private fun setupToolbar() {

    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = ListAdapter()
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    private fun setupDataListener() {
        tasks.observe(this, Observer {
            (recyclerView.adapter as? ListAdapter)?.submitList(it)
        })
    }
}
