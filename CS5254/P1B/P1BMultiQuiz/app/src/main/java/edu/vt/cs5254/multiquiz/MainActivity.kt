package edu.vt.cs5254.multiquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import edu.vt.cs5254.multiquiz.databinding.ActivityMainBinding

/**
 * CS 5254 Project 1B - MultiQuiz
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.02.5
 *
 */

class MainActivity : AppCompatActivity() {

    private val quizVM: QuizViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
    private lateinit var buttonList: List<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonList = listOf(
            binding.answer0Button,
            binding.answer1Button,
            binding.answer2Button,
            binding.answer3Button,
        )

        quizVM.answerList.zip(buttonList)
            .forEach { (answer, button) -> // clicked answer and clicked button
                button.setOnClickListener {
                    quizVM.answerList.filter {
                        it != answer
                    }
                        .forEach {
                            it.isSelected = false
                        }
                    answer.isSelected = !answer.isSelected // set selected state to what it was NOT before
                    updateView()
                }
            }

        binding.hintButton.setOnClickListener {
            quizVM.answerList.filterNot {
                it.isCorrect
            }
                .random()
                .let {
                    it.isSelected = false
                    it.isEnabled = false
                }
            updateView()
        }

        binding.submitButton.setOnClickListener {
            quizVM.moveToNext()
            updateQuestion()
            updateView()
        }

        updateQuestion()
        updateView()
    }

    private fun updateQuestion() {
        val questionTextResId = quizVM.questionResId
        binding.questionTextView.setText(questionTextResId)

        buttonList.zip(quizVM.answerList).forEach { (button, answer) ->
            button.setText(answer.textResId)
            button.requestLayout() // adapts size of answer buttons to "fit" answers
            button.setOnClickListener {
                quizVM.answerList.filter {
                    it.isSelected
                }
                    .forEach {
                        it.isSelected = false
                    }
                answer.isSelected = !answer.isSelected
                updateView()
            }
        }
    }

    private fun updateView() {
        quizVM.answerList.zip(buttonList)
            .forEach { (answer, button) ->
                button.isSelected = answer.isSelected
                button.isEnabled = answer.isEnabled
                button.updateColor()
            }
        binding.hintButton.isEnabled = quizVM.answerList.any {
            it.isEnabled
        }
        binding.submitButton.isEnabled = quizVM.answerList.any {
            it.isSelected
        }
    }
}

