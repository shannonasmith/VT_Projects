package edu.vt.cs5254.fancygallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavHost
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import edu.vt.cs5254.fancygallery.databinding.ActivityMainBinding

/**
 * CS 5254 Project 3 - FancyGallery
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.04.25
 */

// 4.11 Q&A
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        // standard bottom navigation:
        val navHost = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHost
        binding.bottomNav.setupWithNavController(navHost.navController)

        // to avoid the bottom navigation from taking control of ALL navigations:
        binding.bottomNav.setOnItemSelectedListener { item ->
            NavigationUI.onNavDestinationSelected(item, navHost.navController)
            true
        }

        setContentView(binding.root)
    }
}