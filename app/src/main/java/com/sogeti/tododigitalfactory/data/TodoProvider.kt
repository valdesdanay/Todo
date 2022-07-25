package com.sogeti.tododigitalfactory.data

import com.sogeti.tododigitalfactory.data.Todo
import java.util.*

class TodoProvider {
    companion object {

        val todoList = listOf(
            Todo(
                null,
                "WAKE UP!!",
                "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore .",
                false,
                0,
                Date().time
            ),
            Todo(
                null,
                "Get the dog out",
                "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore .",
                false,
                0,
                Date().time
            ),
            Todo(
                null,
                "Take the kids to the school",
                "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore .",
                false,
                0,
                Date().time
            )
        )
    }
}