package edu.vt.cs5254.fancygallery.api

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * CS 5254 Project 3 - FancyGallery
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.04.25
 */

// BNRG 20.16
private const val API_KEY = "10acf81baba355b47ebb57844a8e1998"

// BNRG 20.6 -> 20.6  Adding a Retrofit API interface
interface FlickrApi {
    // BNRG 20.16  Defining the “fetch recent interesting photos” request
    @GET(
        "services/rest/?method=flickr.interestingness.getList" +
                "&api_key=$API_KEY" +
                "&format=json" +
                "&nojsoncallback=1" +
                "&extras=url_s,geo"

    )


    // BNRG 20.25  Updating fetchPhoto()’s return type
    suspend fun fetchPhotos(@Query("per_page") fetchCount: Int): FlickrResponse
}