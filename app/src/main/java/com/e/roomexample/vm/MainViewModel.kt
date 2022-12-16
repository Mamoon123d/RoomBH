package com.e.roomexample.vm

import android.content.ContentUris
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.roomexample.data.model
import com.e.roomexample.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.log

class MainViewModel(private val repo: Repository) : ViewModel() {
    fun getList(): LiveData<List<model>> {
        return repo.getList()
    }

    fun insertItem(data: model) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.insertItem(data)
        }
    }

    fun deleteItem(data: model) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteItem(data)
        }
    }

    fun updateItem(data: model) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateItem(data)
        }
    }

    fun isExistsTD(td: Int): Boolean {
        var isExtsts: Boolean? = false
        viewModelScope.launch(Dispatchers.IO) {
            isExtsts = repo.isExistsTd(td)
            Log.d("isExistsTD","is exists : ${repo.isExistsTd(td)}")
        }
        return isExtsts!!
    }

}