package com.tomato.internetexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

class ListAdapter(private var items: List<MainItem>):
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false))


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val title: TextView = view.findViewById(R.id.title)
        private val image1: ImageView = view.findViewById(R.id.image1)
        private val image2: ImageView = view.findViewById(R.id.image2)

        fun bind(item: MainItem) {
            title.text = item.title

            Picasso.get()
                .load(item.image)
                .placeholder(androidx.appcompat.R.drawable.abc_star_black_48dp)
                .error(androidx.appcompat.R.drawable.abc_star_half_black_48dp)
                .resizeDimen(com.google.android.material.R.dimen.design_fab_size_normal,
                    com.google.android.material.R.dimen.design_fab_size_normal)
                //.resizeDimen(100, 100)
                .centerCrop()
                .into(image1)

            Glide.with(image2.context)
                .load(item.image)
                .placeholder(androidx.appcompat.R.drawable.abc_star_black_48dp)
                .override(image1.resources.getDimensionPixelSize(com.google.android.material.R.dimen.design_fab_size_normal))
                //.override(100)
                .error(androidx.appcompat.R.drawable.abc_star_half_black_48dp)
                .into(image2)
        }
    }
}