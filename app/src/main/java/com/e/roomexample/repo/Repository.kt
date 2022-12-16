package com.e.roomexample.repo

import androidx.lifecycle.LiveData
import com.e.roomexample.data.model
import com.e.roomexample.database.MyDao

class Repository(private val dao: MyDao) {
    fun getList(): LiveData<List<model>> {
        return dao.getList()
    }

    suspend fun insertItem(data: model) {
        return dao.insertItem(data)
    }

    suspend fun deleteItem(data: model) {
        return dao.deleteItem(data)
    }

    suspend fun updateItem(data: model) {
        return dao.updateItem(data)
    }

    suspend fun isExistsTd(td: Int): Boolean {
        return dao.isExists(td)
    }

}