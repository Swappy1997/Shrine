package com.example.mvvmhilt.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shrine.ItemRepository
import com.example.shrine.models.ShrineList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(private val repository: ItemRepository) : ViewModel() {

    val shrineItemsLiveData: LiveData<ShrineList> = repository.shrineItemsLiveData

    fun fetchShrineItems() {
        viewModelScope.launch {
            try {
                repository.fetchShrineItems()
            } catch (e: Exception) {
                // Handle the exception here
            }
        }
    }}