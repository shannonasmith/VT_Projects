package edu.vt.cs5254.fancygallery.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * CS 5254 Project 3 - FancyGallery
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.04.25
 */

// BNRG 20.23  Adding PhotoResponse
@JsonClass(generateAdapter = true)
data class PhotoResponse(
    @Json(name = "photo") val galleryItems: List<GalleryItem>
)