package com.kings.tdd.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface ShoppingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingItem(list: ShoppingItem)

    @Delete
    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    @Query("SELECT * FROM shopping_item")
    fun observeAllShoppingItem(): LiveData<List<ShoppingItem>>

    @Query("SELECT SUM(price * amount) FROM shopping_item")
    fun observeTotalPrice(): LiveData<Float>
}