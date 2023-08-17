package com.example.foodapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.adapter.CartAdapter
import com.example.foodapp.data.ItemsModel


class CartActivity : AppCompatActivity(), CartAdapter.Callback {

    // Adapter for the RecyclerView
    lateinit var adapter: CartAdapter

    // List to hold cart items
    private val cartItems = mutableListOf<ItemsModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        // Enable back button in the action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        // Initialize RecyclerView
        val recyclerview = findViewById<RecyclerView>(R.id.restaurantRecyclerView)
        val layoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = layoutManager

        // Get cart items from intent
        val data: MutableList<ItemsModel> =
            intent.getSerializableExtra("cart") as MutableList<ItemsModel>
        cartItems.addAll(data)

        // Create and set the adapter for RecyclerView
        adapter = CartAdapter(cartItems, this@CartActivity)
        recyclerview.adapter = adapter
    }

    // Handle back navigation
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    // Callback function when a product is clicked
    override fun productClicked(data: ItemsModel) {
        cartItems.remove(data)
        adapter.submitItems(cartItems)
    }
}
