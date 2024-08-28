package com.mandrykevich.testtask

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.mandrykevich.testtask.Adapter.Offer

data class Offer(val id: String, val title: String, val link: String, val button: String?)

data class ButtonData(val text: String)

class OffersAdapter(private val offers: List<Offer>) :
    RecyclerView.Adapter<OffersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_filter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val offer = offers[position]
        holder.titleTextView.text = offer.title

        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(offer.link))
            holder.itemView.context.startActivity(intent)
        }

        val resourceId = holder.itemView.context.resources.getIdentifier("ic_${offer.id}", "drawable", holder.itemView.context.packageName)
        if (resourceId != 0) {
            holder.imageView?.setImageResource(resourceId)
            holder.imageView?.visibility = View.VISIBLE
            holder.imageBG.setBackgroundResource(R.drawable.backgroun_icon)
        } else {
            holder.imageView?.setImageResource(0)
            holder.imageBG.setBackgroundResource(R.drawable.backgroud_icon_empty)
        }

        if (offer.button != null) {
            holder.button.text = offer.button
            holder.button.visibility = View.VISIBLE

            holder.button.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(offer.link))
                holder.itemView.context.startActivity(intent)
            }
        } else {
            holder.button.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = offers.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.filter_head)
        val button: TextView = view.findViewById(R.id.filter_toview)
        val imageView: ImageView? = view.findViewById(R.id.filer_iv)
        val imageBG: CardView = view.findViewById(R.id.cv_icon)
    }
}