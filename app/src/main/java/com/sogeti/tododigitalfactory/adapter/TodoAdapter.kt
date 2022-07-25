package com.sogeti.tododigitalfactory.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sogeti.tododigitalfactory.data.Todo
import com.sogeti.tododigitalfactory.R

class TodoAdapter(
    private val onClickListener: OnClickListener<Todo>
) : RecyclerView.Adapter<TodoViewHolder>() {

    var todoList = mutableListOf<Todo>()

    fun setTodos(todos: List<Todo>) {
        this.todoList = todos.toMutableList()
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TodoViewHolder(layoutInflater.inflate(R.layout.item_todo, parent, false))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val item = todoList[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int = todoList.size

    interface OnClickListener<Todo> {
        fun onClick(todo:  Todo)
        fun onChangeDoneState(todo: Todo)
    }


}