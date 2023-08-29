package com.example.shrine.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmhilt.viewmodel.ItemViewModel
import com.example.shrine.ItemRepository
import java.lang.reflect.Constructor
import javax.inject.Inject

class MainViewModelFactory  @Inject constructor(private val repository: ItemRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ItemViewModel(repository)as T
    }
}
