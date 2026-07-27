package edu.vt.cs5254.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG
import com.google.android.material.snackbar.Snackbar

/**
 * CS 5254 Project 0 - GeoQuiz
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.01.20
 *
 */

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)

        trueButton.setOnClickListener { view: View ->
            Snackbar.make(view, R.string.correct_toast, LENGTH_LONG)
                .show()
        }

        falseButton.setOnClickListener { view: View ->
            Snackbar.make(view, R.string.incorrect_toast, LENGTH_LONG)
                .show()
        }
    }

}