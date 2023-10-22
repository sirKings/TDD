package com.kings.tdd.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "shopping_item")
data class ShoppingItem(
    var name: String,
    var price: Int,
    var amount: Int,

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)
