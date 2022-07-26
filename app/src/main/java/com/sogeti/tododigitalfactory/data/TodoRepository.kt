package com.sogeti.tododigitalfactory.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow


class TodoRepository(private val todoDao: TodoDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    fun allFilterTodos(filter: String): Flow<List<Todo>> = todoDao.getAllFiltered(filter)

    // Insert new todo
    @WorkerThread
    suspend fun insertTodo(todo: Todo) {
        todoDao.insertOne(todo)
    }

    // update todo
    @WorkerThread
    suspend fun updateTodo(todo: Todo) {
        todoDao.updateTodo(todo)
    }

}