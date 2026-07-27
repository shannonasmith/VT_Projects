package edu.vt.cs5254.fancygallery

import androidx.lifecycle.ViewModel
import org.osmdroid.api.IGeoPoint
import org.osmdroid.util.GeoPoint

/**
 * CS 5254 Project 3 - FancyGallery
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.04.25
 */

class MapViewModel : ViewModel() {
    var zoomLevel: Double = 1.5
        private set

    var mapCenter: IGeoPoint = GeoPoint(0.0, 0.0)
        private set

    fun saveMapState(zoom: Double, center: IGeoPoint) {
        zoomLevel = zoom
        mapCenter = center
    }
}