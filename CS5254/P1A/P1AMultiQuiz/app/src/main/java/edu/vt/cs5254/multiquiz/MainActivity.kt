package edu.vt.cs5254.multiquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import edu.vt.cs5254.multiquiz.databinding.ActivityMainBinding

/**
 * CS 5254 Project 1A - MultiQuiz
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.01.30
 *
 */

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding // allows access to activity_main.xml objects
    private lateinit var buttonList: List<Button>

    // model
    private val answerList = listOf(
        Answer(R.string.brisbane, false),
        Answer(R.string.canberra, true),
        Answer(R.string.melbourne, false),
        Answer(R.string.sydney, false)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonList = listOf(
            binding.button0,
            binding.button1,
            binding.button2,
            binding.button3,
        )

        buttonList.zip(answerList)
            .forEach { (button, answer) ->
                button.setOnClickListener {
                    answer.isSelected
                    answerList.filter { !answer.isSelected }
                        .forEach { answer ->
                            answer.isSelected = true
                            answer.isSelected = !answer.isSelected
                        }
                    answer.isSelected = !answer.isSelected
                    updateView()
                }
            }

        binding.buttonLifeline.setOnClickListener {
            answerList.filter { answer -> !answer.isCorrect } /* keep ONLY the incorrect answers */
                .take(2) // <-- first two incorrect answers
                .forEach { answer -> /* disable and deselect each */
                    answer.isEnabled = false
                    answer.isSelected = false
                }
            updateView() // <-- update the buttons from here
        }

        binding.buttonReset.setOnClickListener { // This lambda must enable and deselect every answer object.
            answerList.forEach { answer ->
                answer.isEnabled = true
                answer.isSelected = false
            }
            updateView() // <-- update the buttons from here
        }
        updateView()
    }

    private fun updateView() {
        answerList.zip(buttonList)
            .forEach { (answer, button) -> // Note: don't mutate the *answer* here; only mutate the *button* instead
                button.isSelected = answer.isSelected
                button.isEnabled = answer.isEnabled
                button.updateColor()
            }
        // only one more line here, to handle enabling/disabling the lifeline
        // You need to set that isEnabled property to a value calculated from the answerList.
        binding.buttonLifeline.isEnabled = answerList.all {
            it.isEnabled
        }
    }
}

