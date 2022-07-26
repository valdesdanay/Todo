package com.sogeti.tododigitalfactory

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sogeti.tododigitalfactory.adapter.TodoAdapter
import com.sogeti.tododigitalfactory.data.Todo
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: TodoAdapter
    //Search filter string
    private var searchFilter: String? = null

    private val todoViewModel: TodoViewModel by viewModels {
        TodoViewModelFactory((application as TodoApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            openActivityForResult(AddTodoActivity.INSERT_REQUEST_CODE)
        }

        val edtFilter = findViewById<EditText>(R.id.edtFilter)

        val btnSearch = findViewById<Button>(R.id.btnSearch)
        btnSearch.setOnClickListener {
            searchFilter = edtFilter.text.toString()
            todoViewModel.searchFilter.value = searchFilter as String
        }
        adapter = TodoAdapter(object : TodoAdapter.OnClickListener<Todo> {
            override fun onClick(todo: Todo) {
                onItemSelected(todo)
            }

            override fun onChangeDoneState(todo: Todo) {
                onDoneChanged(todo)
            }

        })
        initRecyclerView()

        todoViewModel.allFilteredTodos.observe(this) { todos ->
            // Update the cached copy of the todos in the adapter.
            todos.let { adapter.setTodos(it) }
        }
    }

    private fun initRecyclerView() {
        val manager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this, manager.orientation)
        val recyclerView = findViewById<RecyclerView>(R.id.rcvTodo)
        recyclerView.layoutManager = manager
        recyclerView.adapter = adapter

        recyclerView.addItemDecoration(decoration)
    }

    private fun onItemSelected(todo: Todo) {
        Toast.makeText(this, todo.title, Toast.LENGTH_SHORT).show()

        openActivityForResult(AddTodoActivity.UPDATE_REQUEST_CODE, todo)
    }

    private fun onDoneChanged(todo: Todo) {
        val doneDate = if (!todo.done) {
            Date().time
        } else {
            0
        }

        val difTodo = Todo(
            todo.id,
            todo.title,
            todo.description,
            !todo.done,
            doneDate,
            Date().time
        )
        todoViewModel.update(difTodo)
    }

    private fun openActivityForResult(requestCode: Int, todo: Todo? = null) {
        val intent = Intent(this@MainActivity, AddTodoActivity::class.java)
        todo?.let {
            intent.putExtra(AddTodoActivity.EXTRA_REQUEST, todo)
        }
        intent.putExtra(AddTodoActivity.REQUEST_KEY, requestCode)
        startForResult.launch(intent)
    }


    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                intent?.let {
                    val todo: Todo? = it.getParcelableExtra(AddTodoActivity.EXTRA_REPLY)
                    when (it.getIntExtra(AddTodoActivity.REQUEST_KEY, 0)) {
                        AddTodoActivity.INSERT_REQUEST_CODE -> {
                            todo?.let { t ->
                                todoViewModel.insert(t)
                            }
                        }
                        AddTodoActivity.UPDATE_REQUEST_CODE -> {
                            todo?.let { t->
                                todoViewModel.update(t)
                            }
                        }
                        else -> {//Do nothing}
                        }

                    }

                }
            }

        }
}