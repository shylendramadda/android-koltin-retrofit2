package com.example.retrofitkotlin

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitkotlin.adapters.PhotoAdapter
import com.example.retrofitkotlin.models.Photo
import com.example.retrofitkotlin.network.RetrofitInstance
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    val photosList = ArrayList<Photo>()
    lateinit var photosAdapter: PhotoAdapter
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        photosAdapter = PhotoAdapter(photosList)
        recyclerPhotos.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerPhotos.adapter = photosAdapter
        progressDialog = ProgressDialog.show(this, "Loading", "Fetching Data Please wait...", false)

        getPhotosFromServer()

    }

    private fun getPhotosFromServer() {
        val call: Call<List<Photo>> = RetrofitInstance.getClient.getPhotos()
       call.enqueue(object : Callback<List<Photo>?> {
           override fun onFailure(call: Call<List<Photo>?>, t: Throwable) {
               Toast.makeText(this@MainActivity, "Something went wrong", Toast.LENGTH_SHORT)
                   .show()
           progressDialog.dismiss()
           }

           override fun onResponse(call: Call<List<Photo>?>, response: Response<List<Photo>?>) {
               if (response != null && response.isSuccessful && response.body() != null) {
                   val listOfPhotos = response.body()!!
                   photosList.addAll(listOfPhotos)
                   photosAdapter.notifyDataSetChanged()
               } else {
                   Toast.makeText(this@MainActivity, "Something went wrong", Toast.LENGTH_SHORT)
                       .show()
               }
               progressDialog.dismiss()
           }
       })
    }


}
