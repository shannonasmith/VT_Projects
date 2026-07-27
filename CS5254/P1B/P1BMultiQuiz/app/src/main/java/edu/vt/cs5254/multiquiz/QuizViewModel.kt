package edu.vt.cs5254.multiquiz

import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {

    private val questionList = listOf(
        Question(
            R.string.question_1, listOf(
                Answer(R.string.anthony, true),
                Answer(R.string.carrie, false),
                Answer(R.string.harrison, false),
                Answer(R.string.mark, false)
            )
        ),
        Question(
            R.string.question_2, listOf(
                Answer(R.string.naboo, false),
                Answer(R.string.geonosis, true),
                Answer(R.string.tatooine, false),
                Answer(R.string.coruscant, false)
            )
        ),
        Question(
            R.string.question_3, listOf(
                Answer(R.string.iceland, false),
                Answer(R.string.denmark, false),
                Answer(R.string.norway, true),
                Answer(R.string.greenland, false)
            )
        ),
        Question(
            R.string.question_4, listOf(
                Answer(R.string.fifty_five, false),
                Answer(R.string.seventy_eight, false),
                Answer(R.string.two_hundred, false),
                Answer(R.string.over_two_twenty, true)
            ))
    )

    private var currentIndex = 0

    val questionResId: Int
        get() = questionList[currentIndex].questionResId

    val answerList
        get() = questionList[currentIndex].answerList

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionList.size
    }
}


