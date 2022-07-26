package com.sogeti.tododigitalfactory.adapter

import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sogeti.tododigitalfactory.data.Todo
import com.sogeti.tododigitalfactory.R

class TodoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val title = view.findViewById<TextView>(R.id.txvTitle)
    val done = view.findViewById<CheckBox>(R.id.ckbDone)
    fun render(todo: Todo, onClickListener: TodoAdapter.OnClickListener<Todo>) {
        title.text = todo.title

        done.setOnCheckedChangeListener(null)

        done.isChecked = todo.done

        if (todo.done) {
            title.paintFlags = title.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            title.paintFlags = title.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

        done.setOnCheckedChangeListener { _, _ ->
            onClickListener.onChangeDoneState(todo)
        }

        itemView.setOnClickListener {
            onClickListener.onClick(todo)
        }
    }
}