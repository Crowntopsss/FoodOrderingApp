package com.example.foodapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.data.ItemsModel

class CartAdapter(private var mList: MutableList<ItemsModel>, val callback: Callback) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_item, parent, false)

        return ViewHolder(view,callback)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = mList[position]
        holder.name.text = item.name
        holder.price.text = "Price: ${item.pricing}"
        holder.remove.setOnClickListener {
            holder.callback.productClicked(item)
        }

    }

    fun submitItems(items:List<ItemsModel>){
        mList = items.toMutableList()
        notifyDataSetChanged()
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(view: View, val callback: Callback) : RecyclerView.ViewHolder(view) {
        val remove: ImageView = itemView.findViewById(R.id.restaurantImageView)
        val name: TextView = itemView.findViewById(R.id.restaurantNameTextView)
        val price: TextView = itemView.findViewById(R.id.ratingTextView)
    }

    interface Callback{
        fun productClicked(data: ItemsModel)
    }
}
