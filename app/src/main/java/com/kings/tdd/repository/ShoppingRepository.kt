package com.kings.tdd.repository

import androidx.lifecycle.LiveData
import com.kings.tdd.data.local.ShoppingItem
import com.kings.tdd.data.remote.response.ImageResponse
import com.kings.util.Resource

interface ShoppingRepository {
    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)
    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)
    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>
    fun observeTotalPrice(): LiveData<Float>
    suspend fun searchShoppingItem(searchQuery: String): Resource<ImageResponse>
}