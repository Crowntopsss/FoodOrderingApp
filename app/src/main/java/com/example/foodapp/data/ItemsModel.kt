package com.example.foodapp.data

import android.os.Parcelable
import java.io.Serializable


data class ItemsModel(val image: Int, val name: String, val pricing: String): Serializable{
   constructor(): this(0,"","")

   override fun equals(other: Any?): Boolean {
      return  (other is ItemsModel  && name.equals(other.name))
   }
}