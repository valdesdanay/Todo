package com.sogeti.tododigitalfactory

import android.os.Parcel
import com.sogeti.tododigitalfactory.data.Todo
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify


class TodoTest {

    private lateinit var todo: Todo

    @Before
    fun initUseCase() {
        todo = Todo(ID,TITLE,DESCRIPTION,DONE,DONE_DATE,LAST_UPDATE)
    }

    @Test
    fun createParcelFromAllValues(){
        val parcel = mock(Parcel::class.java)
        todo.writeToParcel(parcel, 0);

        val orderVerifier = Mockito.inOrder(parcel)
        orderVerifier.verify(parcel).writeValue(ID)
        orderVerifier.verify(parcel).writeString(TITLE)
        orderVerifier.verify(parcel).writeString(DESCRIPTION)
        orderVerifier.verify(parcel).writeBoolean(DONE)
        orderVerifier.verify(parcel).writeLong(DONE_DATE)
        orderVerifier.verify(parcel).writeLong(LAST_UPDATE)
    }



    companion object{
        const val ID = 1;
        const val TITLE = "TITLE";
        const val DESCRIPTION = "DESCRIPTION";
        const val DONE = true;
        const val DONE_DATE = 2L;
        const val LAST_UPDATE = 3L;
    }
}