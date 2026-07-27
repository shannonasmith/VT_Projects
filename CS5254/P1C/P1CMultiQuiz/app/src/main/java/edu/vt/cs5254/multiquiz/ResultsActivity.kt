package edu.vt.cs5254.multiquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.vt.cs5254.multiquiz.databinding.ActivityResultsBinding

/**
 * CS 5254 Project 1C - MultiQuiz
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.02.18
 *
 */
private const val CORRECT_ANSWERS = "edu.vt.cs5254.multiquiz.correct_answers"
private const val SUBMITTED_ANSWERS = "edu.vt.cs5254.multiquiz.submitted_answers"
private const val HINTS_USED = "edu.vt.cs5254.multiquiz.hints_used"

const val RESET_ALL = "edu.vt.cs5254.multiquiz.reset_all"

class ResultsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultsBinding
    private val resultsVM: ResultsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

    /*
    The Reset All button behavior is as follows:
        Before the Reset All button is clicked:
            Nothing changes in the results activity
            Upon returning to the main activity via Back:
                The first question/answers will be displayed
                The submitted answers stat should be reset to zero
                Every button will be as it was before reaching the results screen (for all questions)

    After the Reset All button is clicked (if it is clicked at all):
        All three stats should display as zero
        The Reset All button becomes disabled
        Upon returning to the main activity via Back:
            The first question/answers will be displayed
            All stats should be reset to zero
            Every answer button will be enabled and deselected (for all questions)
            The hint button will be enabled, and the submit button will be disabled (for all questions)
     */

        binding.resetAllButton.setOnClickListener {
            resultsVM.isReset = true
            updateResults()
            updateView()
        }
        updateResults()
        updateView()
    }

    /*
    When fetching the values, you need to use something like this:
        val correctAnswers = intent.getIntExtra(EXTRA_CORRECT_ANSWERS, 0)
    To display them, you can use either:
        binding.correctAnswersValue.text = correctAnswers.toString()
    …or…
        binding.correctAnswersValue.text = "$correctAnswers"
     */
    private fun updateView() {
        if (resultsVM.isReset) {
            binding.correctAnswersValue.text = "0"
            binding.submittedAnswersValue.text = "0"
            binding.hintsUsedValue.text = "0"
        } else {
            binding.correctAnswersValue.text =
                intent.getIntExtra(CORRECT_ANSWERS, 0).toString()
            binding.submittedAnswersValue.text =
                intent.getIntExtra(SUBMITTED_ANSWERS, 0).toString()
            binding.hintsUsedValue.text =
                intent.getIntExtra(HINTS_USED, 0).toString()
        }
        binding.resetAllButton.isEnabled = !resultsVM.isReset
    }

    private fun updateResults() {
        setResult(
            Activity.RESULT_OK,
            Intent().apply { putExtra(RESET_ALL, resultsVM.isReset) })
    }

    companion object { // using the techniques shown in BNRG Chapter 7
        fun newIntent(
            packageContext: Context,
            correctAnswers: Int,
            submittedAnswers: Int,
            hintsUsed: Int,
        ): Intent {
            return Intent(packageContext, ResultsActivity::class.java)
                .apply {
                    putExtra(CORRECT_ANSWERS, correctAnswers)
                    putExtra(SUBMITTED_ANSWERS, submittedAnswers)
                    putExtra(HINTS_USED, hintsUsed)
                }
        }
    }

}