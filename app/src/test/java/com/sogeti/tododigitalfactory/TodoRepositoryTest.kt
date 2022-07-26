package com.sogeti.tododigitalfactory

import com.sogeti.tododigitalfactory.data.Todo
import com.sogeti.tododigitalfactory.data.TodoDao
import com.sogeti.tododigitalfactory.data.TodoRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class TodoRepositoryTest {

    private lateinit var todoRepository: TodoRepository
    private val todoDao = mock(TodoDao::class.java)
    private val todo = Todo(
        TodoTest.ID,
        TodoTest.TITLE,
        TodoTest.DESCRIPTION,
        TodoTest.DONE,
        TodoTest.DONE_DATE,
        TodoTest.LAST_UPDATE
    )

    @Before
    fun initUseCase() {
        todoRepository = TodoRepository(todoDao)
    }

    @Test
    fun insertTodoCallDaoInsert(){
        runBlocking {
            todoRepository.insertTodo(todo)
            verify(todoDao).insertOne(todo)
        }
    }

    @Test
    fun updateTodoCallDaoInsert(){
        runBlocking {
            todoRepository.updateTodo(todo)
            verify(todoDao).updateTodo(todo)
        }
    }
}