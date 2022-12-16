package com.e.roomexample.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.e.roomexample.repo.Repository
import com.e.roomexample.vm.MainViewModel

class MainMvFactory(private val repo: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repo) as T

    }
}