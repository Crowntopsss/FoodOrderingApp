package com.example.foodapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.adapter.CustomAdapter
import com.example.foodapp.data.ItemsModel
import java.io.Serializable


class OrderActivity : AppCompatActivity(), CustomAdapter.Callback {

    // List to hold selected cart items
    private val cartItems = mutableListOf<ItemsModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        // Initialize RecyclerView
        val recyclerview = findViewById<RecyclerView>(R.id.restaurantRecyclerView)
        val layoutManager = GridLayoutManager(this, 2)
        recyclerview.layoutManager = layoutManager

        // Prepare data for RecyclerView
        val data = ArrayList<ItemsModel>()
        data.add(ItemsModel(R.drawable.image, "Rice", "2,000"))
        data.add(ItemsModel(R.drawable.image_two, "Beans", "4,800"))
        data.add(ItemsModel(R.drawable.image_three, "Parropa", "2,500"))
        data.add(ItemsModel(R.drawable.image_four, "Barrydi", "1,360"))

        // Create and set the adapter for RecyclerView
        val adapter = CustomAdapter(data, this@OrderActivity)
        recyclerview.adapter = adapter
    }

    // Callback function when a product is clicked
    override fun productClicked(data: ItemsModel) {
        cartItems.add(data)
        Toast.makeText(this, "Item added to cart", Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate menu layout
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.cart -> openCart()
            else -> {}
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openCart() {
        // Open CartActivity and pass cart items as extras
        val intent = Intent(this, CartActivity::class.java)
        intent.putExtra("cart", cartItems as Serializable)
        startActivity(intent)
    }
}
