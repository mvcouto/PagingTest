package com.example.marcos.pagingtest

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_task.view.*

class ListAdapter : PagedListAdapter<Task, TaskViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(container: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(LayoutInflater.from(container.context).inflate(R.layout.item_task,
                container, false))
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = getItem(position) ?: return

        holder.itemView.taskId.text = task.id
        holder.itemView.taskTitle.text = task.title
        holder.itemView.taskDescription.text = task.description
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Task>() {
            override fun areItemsTheSame(oldTask: Task, newTask: Task): Boolean =
                    oldTask.id == newTask.id

            override fun areContentsTheSame(oldTask: Task, newTask: Task): Boolean =
                    oldTask == newTask
        }
    }
}

