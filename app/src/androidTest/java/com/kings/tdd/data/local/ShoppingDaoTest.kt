package com.kings.tdd.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.kings.tdd.getOrAwaitValue
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class ShoppingDaoTest {

    private lateinit var database: ShoppingItemDatabase
    private lateinit var dao: ShoppingDao

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupRoom(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ShoppingItemDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.getShoppingItemDao()
    }

    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun givenShoppingItemIsInserted_shouldBeAvailableInDB() = runTest{
        val item = ShoppingItem("name",300,10f,"link.com",1)
        dao.insertShoppingItem(listOf(item))
        val allShoppingItems = dao.observeAllShoppingItem().getOrAwaitValue()
        assertThat(allShoppingItems).contains(item)
    }

    @Test
    fun givenShoppingItemIsDeleted_shouldNotBeAvailableInDB() = runTest {
        val item = ShoppingItem("name",300,10f,"link.com",1)
        val itemToDelete = ShoppingItem("name",300,10f,"link.com",2)
        dao.insertShoppingItem(listOf(item,itemToDelete))
        dao.deleteShoppingItem(itemToDelete)
        val allShoppingItems = dao.observeAllShoppingItem().getOrAwaitValue()
        assertThat(allShoppingItems).contains(item)
        assertThat(allShoppingItems).doesNotContain(itemToDelete)
    }

    @Test
    fun givenShoppingItemIsInserted_totalPriceShouldBeCorrect() = runTest {
        val item = ShoppingItem("name",300,10f,"link.com",1)
        val itemToDelete = ShoppingItem("name",300,10f,"link.com",2)
        dao.insertShoppingItem(listOf(item,itemToDelete))
        val totalPrice = dao.observeTotalPrice().getOrAwaitValue()
        assertThat(totalPrice).isEqualTo(6000)
    }
}