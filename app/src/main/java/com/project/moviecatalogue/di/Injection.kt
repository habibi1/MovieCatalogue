package com.project.moviecatalogue.di

import android.content.Context
import com.project.moviecatalogue.data.source.CatalogRepository
import com.project.moviecatalogue.data.source.remote.repository.RemoteDataSource

object Injection {
    fun provideRepository(): CatalogRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return CatalogRepository.getInstance(remoteDataSource)
    }
}