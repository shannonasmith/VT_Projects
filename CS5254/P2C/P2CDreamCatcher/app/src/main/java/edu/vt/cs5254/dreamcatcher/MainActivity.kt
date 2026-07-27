package edu.vt.cs5254.dreamcatcher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.vt.cs5254.dreamcatcher.databinding.ActivityMainBinding

/**
 * CS 5254 Project 2C - Dreamcatcher
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.04.09
 */

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}

