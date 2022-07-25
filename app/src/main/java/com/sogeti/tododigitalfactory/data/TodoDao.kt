package com.sogeti.tododigitalfactory.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM Todo ORDER BY done ASC,done_date ASC, last_update DESC")
    fun getAll(): Flow<List<Todo>>

    @Query("SELECT * FROM Todo WHERE title LIKE '%'||:filter||'%' ORDER BY done ASC,done_date ASC, last_update DESC")
    fun getAllFiltered(filter: String): Flow<List<Todo>>

    @Update
    suspend fun updateTodo(todo: Todo)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOne(todo: Todo)
}