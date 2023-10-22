package com.kings.tdd.repository

import androidx.lifecycle.LiveData
import com.kings.tdd.data.local.ShoppingItem
import com.kings.tdd.data.local.ShoppingItemDatabase
import com.kings.tdd.data.remote.ShoppingApi
import com.kings.tdd.data.remote.response.ImageResponse
import com.kings.util.Resource
import java.lang.Exception
import javax.inject.Inject

class ShoppingRepositoryImpl @Inject constructor(
    private val database: ShoppingItemDatabase,
    private val api: ShoppingApi
): ShoppingRepository {
    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        database.getShoppingItemDao().insertShoppingItem(shoppingItem)
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        database.getShoppingItemDao().deleteShoppingItem(shoppingItem)
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return database.getShoppingItemDao().observeAllShoppingItem()
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return  database.getShoppingItemDao().observeTotalPrice()
    }

    override suspend fun searchShoppingItem(searchQuery: String): Resource<ImageResponse> {
        return try {
            val result = api.searchImages(searchQuery = searchQuery)
            if(result.isSuccessful){
                result.body()?.let {
                    Resource.Success(data = it)
                } ?: kotlin.run {
                    Resource.Error(error = "An Error occurred trying")
                }
            }else{
                Resource.Error(error = "An Error occurred trying")
            }
        }catch (e: Exception){
            e.printStackTrace()
            Resource.Error(error = "Error: ${e.localizedMessage ?: "Could not connect to the internet"}")
        }
    }
}