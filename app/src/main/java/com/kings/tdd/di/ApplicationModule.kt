package com.kings.tdd.di

import android.app.Application
import androidx.room.Room
import com.kings.tdd.data.local.ShoppingItemDatabase
import com.kings.tdd.data.remote.ShoppingApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideRoom(application: Application): ShoppingItemDatabase{
        return Room.databaseBuilder(
            application,
            ShoppingItemDatabase::class.java,
            "shopping_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideRetrofitApi(): ShoppingApi {
        return Retrofit
            .Builder()
            .baseUrl("https://pixabay.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create()
    }

}