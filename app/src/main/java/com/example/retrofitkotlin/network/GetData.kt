package com.example.retrofitkotlin.network

import com.example.retrofitkotlin.models.Photo
import com.example.retrofitkotlin.models.Salary
import retrofit2.Call
import retrofit2.http.GET

interface GetData {

    @GET("photos")
    fun getPhotos(): Call<List<Photo>>

    @GET("user/salaries")
    fun getUserSalaries(): Call<List<Salary>>

}