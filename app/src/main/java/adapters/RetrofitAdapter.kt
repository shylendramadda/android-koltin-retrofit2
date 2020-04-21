package adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitkotlin.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*
import models.Details

class RetrofitAdapter(val details: List<Details>, val context: Context) :
    RecyclerView.Adapter<RetrofitAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(viewHolder)
    }

    override fun getItemCount(): Int {
        return details.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val details = details[position]
        holder.idTV.text = details.id.toString()
        holder.titleTV.text = details.title
        holder.thumbnailTV.text = details.thumbnailUrl
        Picasso.get().load(details.url).into(holder.imageIV)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTV = itemView.titleTV
        val imageIV = itemView.imageIV
        val idTV = itemView.idTV
        val thumbnailTV = itemView.thumbnailTV

    }
}
