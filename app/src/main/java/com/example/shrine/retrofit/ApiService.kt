package com.example.shrine.retrofit


import com.example.shrine.models.ShrineList
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/kappa/image/task")
    suspend fun getItems(): Response<ShrineList>

}