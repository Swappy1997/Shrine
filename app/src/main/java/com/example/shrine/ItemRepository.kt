package com.example.shrine

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shrine.models.ShrineList
import com.example.shrine.retrofit.ApiService
import javax.inject.Inject

class ItemRepository @Inject constructor(private val apiService: ApiService) {

    private val _shrineItemsLiveData = MutableLiveData<ShrineList>()
    val shrineItemsLiveData: LiveData<ShrineList>
        get() = _shrineItemsLiveData

    suspend fun fetchShrineItems() {
        try {
            val response = apiService.getItems()
            Log.d("ShrineRepository", "Response status: ${response.code()}")
            if (response.isSuccessful) {
                val shrineList = response.body()
                _shrineItemsLiveData.postValue(shrineList)
            } else {
                Log.e("ShrineRepository", "API call not successful")
            }
        } catch (e: Exception) {
            Log.e("ShrineRepository", "Exception: ${e.message}")
        }
    }
}