package com.example.retrofitkotlin

import adapters.RetrofitAdapter
import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item.*
import models.Details
import network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    val details = ArrayList<Details>()
    lateinit var adapter: RetrofitAdapter
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = RetrofitAdapter(details, this)
        recyclerRV.adapter = adapter
        recyclerRV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        progressDialog = ProgressDialog.show(this, "Loading", "Fetching Data Please wait...", false)

        getData()
    }

    private fun getData() {
        val call: Call<List<Details>> = RetrofitInstance.getClient.getPhotos()
        call.enqueue(object : Callback<List<Details>> {

            override fun onResponse(
                call: Call<List<Details>>?,
                response: Response<List<Details>>?
            ) {
                progressDialog.dismiss()
                details.addAll(response!!.body()!!)
                adapter .notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<Details>>?, t: Throwable?) {
                progressDialog.dismiss()
            }

        })
    }


}
