package edu.vt.cs5254.fancygallery.api

import com.squareup.moshi.JsonClass

/**
 * CS 5254 Project 3 - FancyGallery
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.04.25
 */

// 20.24  Adding FlickrResponse
@JsonClass(generateAdapter = true)
data class FlickrResponse(
    val photos: PhotoResponse
)