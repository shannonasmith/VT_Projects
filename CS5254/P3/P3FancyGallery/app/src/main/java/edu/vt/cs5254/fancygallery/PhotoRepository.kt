package edu.vt.cs5254.fancygallery

import edu.vt.cs5254.fancygallery.api.FlickrApi
import edu.vt.cs5254.fancygallery.api.GalleryItem
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

/**
 * CS 5254 Project 3 - FancyGallery
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.04.25
 */

// 20.12 Creating PhotoRepository
class PhotoRepository {
    private val flickrApi: FlickrApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.flickr.com/") // 20.17  Updating the base URL
            // 20.26  Updating PhotoRepository for Moshi
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        flickrApi = retrofit.create()
    }

    // 20.26  Updating PhotoRepository for Moshi
    suspend fun fetchPhotos(count: Int = 100): List<GalleryItem> =
        flickrApi.fetchPhotos(fetchCount = count).photos.galleryItems
}