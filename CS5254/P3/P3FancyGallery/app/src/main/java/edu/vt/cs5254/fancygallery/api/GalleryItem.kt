package edu.vt.cs5254.fancygallery.api

import android.net.Uri
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * CS 5254 Project 3 - FancyGallery
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.04.25
 */

// BNRG 20.22  Integrating Moshi
@JsonClass(generateAdapter = true)

// BNRG 20.19  Creating a model object class
data class GalleryItem(
    val title: String,
    val id: String,
    @Json(name = "url_s") val url: String, // 20.22
    val latitude: Double,
    val longitude: Double,

    // 4.18 Q&A & BNRG 23.1  Adding code for the photo page
    val owner: String
) {
    val photoPageUri: Uri
        get() = Uri.parse("https://www.flickr.com/photos/")
            .buildUpon()
            .appendPath(owner)
            .appendPath(id)
            .build()
}
