package edu.vt.cs5254.dreamcatcher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.vt.cs5254.dreamcatcher.databinding.ActivityMainBinding

/**
 * CS 5254 Project 2A - Dreamcatcher
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.02.27
 */

/*
 ********* THERE IS NOTHING ELSE TO BE DONE IN MAIN ACTIVITY EXCEPT... *********
 --> Will need to go into activity_main.xml and change the following fields:
        android:name="edu.vt.cs5254.dreamcatcher.DreamDetailFragment" <-- change to DreamListFragment
        tools:layout="@layout/fragment_dream_detail" <-- change to fragment_dream_list
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}

