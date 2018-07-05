package com.example.marcos.pagingtest

import android.arch.paging.DataSource
import android.arch.paging.PositionalDataSource
import android.util.Log
import java.util.*

class TaskDatasource : PositionalDataSource<Task>() {

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Task>) {
        val pageSize = params.loadSize
        val offset = params.startPosition

        val tasks = mutableListOf<Task>()
        for (i in offset until offset + pageSize) {
            tasks.add(Task(UUID.randomUUID().toString(), "Title $i", "Description $i."))
        }

        Log.d("TASK_DATASOURCE", "pageSize=$pageSize / offset=$offset")

        try {
            Thread.sleep(5000)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        callback.onResult(tasks)
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Task>) {
        val pageSize = params.pageSize
        val offset = params.requestedStartPosition

        val tasks = mutableListOf<Task>()
        for (i in offset until offset + pageSize) {
            tasks.add(Task(UUID.randomUUID().toString(), "Title $i", "Description $i."))
        }

        Log.d("TASK_DATASOURCE", "pageSize=$pageSize / offset=$offset")

        try {
            Thread.sleep(5000)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        callback.onResult(tasks, offset)
    }

    class Factory : DataSource.Factory<Int, Task>() {
        override fun create(): DataSource<Int, Task> {
            return TaskDatasource()
        }
    }
}