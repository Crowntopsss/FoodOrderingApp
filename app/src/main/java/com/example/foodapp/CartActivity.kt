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
    lateinit var adapter : CartAdapter

    private val cartItems = mutableListOf<ItemsModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setHomeButtonEnabled(true);
        val recyclerview = findViewById<RecyclerView>(R.id.restaurantRecyclerView)
        val layoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = layoutManager
        val data: MutableList<ItemsModel>  =
            intent.getSerializableExtra("cart") as MutableList<ItemsModel>
        cartItems.addAll(data)
        adapter = CartAdapter(cartItems,this@CartActivity)
        recyclerview.adapter = adapter
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun productClicked(data: ItemsModel) {
        cartItems.remove(data)
        adapter.submitItems(cartItems)
    }
}