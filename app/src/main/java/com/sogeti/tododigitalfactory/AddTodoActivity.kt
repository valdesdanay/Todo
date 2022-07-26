package com.sogeti.tododigitalfactory

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sogeti.tododigitalfactory.data.Todo
import java.util.*


class AddTodoActivity : AppCompatActivity() {
    var todo: Todo? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_todo)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnCancel = findViewById<Button>(R.id.btnCancel)
        val edtTitle = findViewById<EditText>(R.id.edtTitle)
        val edtDesc = findViewById<EditText>(R.id.edtDesc)
        todo = intent.getParcelableExtra("todo")

        todo?.let {
            edtTitle.setText(it.title)
            edtDesc.setText(it.description)
        } ?: kotlin.run {

        }

        btnSave.setOnClickListener {

            if (edtTitle.text.isNotEmpty()) {

                val now = Date().time
                var newTodo: Todo? = null
                val replyIntent = Intent()
                todo?.let {

                    newTodo = Todo(
                        it.id,
                        edtTitle.text.toString(),
                        edtDesc.text.toString(),
                        it.done,
                        it.doneDate,
                        now
                    )
                    replyIntent.putExtra(REQUEST_KEY, UPDATE_REQUEST_CODE)
                } ?: kotlin.run {
                    newTodo = Todo(
                        null,
                        edtTitle.text.toString(),
                        edtDesc.text.toString(),
                        false,
                        0,
                        now
                    )
                    replyIntent.putExtra(REQUEST_KEY, INSERT_REQUEST_CODE)
                }

                newTodo?.let {
                    replyIntent.putExtra(EXTRA_REPLY, newTodo)
                }
                setResult(RESULT_OK, replyIntent)
                finish()
            } else {

                Toast.makeText(this, getString(R.string.invalid_title), Toast.LENGTH_SHORT).show()
                edtTitle?.error = getString(R.string.invalid_title)

            }

        }
        btnCancel.setOnClickListener {
            val returnIntent = Intent()
            setResult(RESULT_CANCELED, returnIntent)
            finish()
        }
    }

    companion object {
        const val EXTRA_REQUEST = "todo"
        const val EXTRA_REPLY = "todo"
        const val REQUEST_KEY = "requestCode"
        const val INSERT_REQUEST_CODE = 1
        const val UPDATE_REQUEST_CODE = 2
    }
}