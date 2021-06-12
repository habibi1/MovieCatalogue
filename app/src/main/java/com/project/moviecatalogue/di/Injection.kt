package com.project.moviecatalogue.di

import android.content.Context
import com.project.moviecatalogue.data.CatalogueRepository
import com.project.moviecatalogue.data.source.local.LocalDataSource
import com.project.moviecatalogue.data.source.local.room.CatalogueRoomDatabase
import com.project.moviecatalogue.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(context: Context): CatalogueRepository {
        val database = CatalogueRoomDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.catalogDao())
        return CatalogueRepository.getInstance(remoteDataSource, localDataSource)
    }
}