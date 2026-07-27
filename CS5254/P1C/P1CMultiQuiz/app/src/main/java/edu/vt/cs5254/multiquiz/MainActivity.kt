package edu.vt.cs5254.multiquiz

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.vt.cs5254.multiquiz.databinding.ActivityMainBinding

/**
 * CS 5254 Project 1C - MultiQuiz
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.02.18
 *
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val quizVM: QuizViewModel by viewModels()
    private lateinit var buttonList: List<Button>

    private val resultsLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // data from ResultsActivity will come out below

        quizVM.moveToNext()
        if (result.resultCode == Activity.RESULT_OK) {
            if (result.data?.getBooleanExtra(RESET_ALL, false) == true) {
                quizVM.resetAll()
            }
        }
        updateQuestion()
        updateView()
    }

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

        binding.hintButton.setOnClickListener {
            quizVM.answerList.filter { !it.isCorrect && it.isEnabled }
                .random()
                .let {
                    it.isEnabled = false
                    it.isSelected = false
                }
            updateView()
        }

        binding.submitButton.setOnClickListener {
            if (quizVM.hasMoreQuestions) {
                quizVM.moveToNext()
                updateQuestion()
                updateView()
            } else {
                /*Snackbar.make(
                    binding.submitButton,
                    "LAUNCH RESULTS ACTIVITY!",
                    Snackbar.LENGTH_SHORT
                ).show()*/
                // start the results activity
                val intent = ResultsActivity.newIntent(
                    this@MainActivity,
                    correctAnswers = quizVM.correctAnswers,
                    submittedAnswers = quizVM.submittedAnswers,
                    hintsUsed = quizVM.hintsUsed
                )
                // startActivity(intent)
                resultsLauncher.launch(intent)
            }
        }

        quizVM.answerList.zip(buttonList)
            .forEach { (answer, button) ->
                button.setOnClickListener {
                    quizVM.answerList.filter {
                        it != answer
                    }
                        .forEach {
                            it.isSelected = false
                        }
                    answer.isSelected =
                        !answer.isSelected // set selected state to what it was NOT before
                    updateView()
                }
            }
        updateQuestion()
        updateView()
    }

    private fun updateQuestion() {
        binding.questionTextView.setText(quizVM.questionResId)
        quizVM.answerList.zip(buttonList)
            .forEach { (answer, button) ->
                button.setText(answer.textResId)
                button.setOnClickListener {
                    quizVM.answerList.filter { it != answer }
                        .forEach { nonClickedAnswer ->
                            nonClickedAnswer.isSelected = false
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
        binding.hintButton.isEnabled = quizVM.answerList.any { it.isEnabled && !it.isCorrect }
        binding.submitButton.isEnabled = quizVM.answerList.any { it.isSelected }
    }
}

