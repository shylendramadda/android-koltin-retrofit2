package com.example.retrofitkotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitkotlin.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*
import com.example.retrofitkotlin.models.Photo

class PhotoAdapter(val photoList: List<Photo>) :
    RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(viewHolder)
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = photoList[position]
        holder.idTV.text = "${position + 1}"
        holder.titleTV.text = photo.title
        holder.thumbnailTV.text = photo.thumbnailUrl

        Picasso.get().load(photo.url).into(holder.imageIV)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTV = itemView.titleTV
        val imageIV = itemView.imageIV
        val idTV = itemView.idTV
        val thumbnailTV = itemView.thumbnailTV

    }
}
