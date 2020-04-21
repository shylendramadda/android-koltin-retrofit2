package network

import models.Details
import retrofit2.Call
import retrofit2.http.GET

interface GetData {

    @GET("photos")
    fun getPhotos(): Call<List<Details>>

}