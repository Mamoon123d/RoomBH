package com.e.roomexample.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.e.roomexample.data.model

@Dao
interface MyDao {

    @Query("select * from entries ")
    fun getList(): LiveData<List<model>>


    // @Insert(onConflict = REPLACE)
    @Insert()
    suspend fun insertItem(data: model)

    @Delete
    suspend fun deleteItem(data: model)

    @Update
    suspend fun updateItem(data: model)

    @Query("select EXISTS(select * from entries where td = :td)")
     // @Query("select * from entries where td=:td")
    suspend fun isExists(td: Int): Boolean

}