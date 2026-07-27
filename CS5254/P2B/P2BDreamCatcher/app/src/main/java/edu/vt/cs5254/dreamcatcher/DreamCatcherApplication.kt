package edu.vt.cs5254.dreamcatcher

import android.app.Application
import kotlinx.coroutines.DelicateCoroutinesApi

/**
 * CS 5254 Project 2B - Dreamcatcher
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.03.11
 */

@DelicateCoroutinesApi
class DreamCatcherApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DreamRepository.initialize(applicationContext)
        // Can also be "DreamRepository.initialize(this)" --> 12.18
    }
}