package edu.vt.cs5254.multiquiz

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MultiQuizTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        scenario = launch(MainActivity::class.java)
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun appContextHasCorrectPackageName() { // #1 passed
        val appContext = getInstrumentation().targetContext
        assertEquals("edu.vt.cs5254.multiquiz", appContext.packageName)
    }

    @Test
    fun clickingWrongAnswerEnablesSubmitButton() { // #2 passed
        onView(withId(R.id.answer_1_button))
            .perform(click())
        onView(withId(R.id.submit_button))
            .check(matches(isEnabled()))
    }

    @Test
    fun clickingHint3TimesDisablesAllButCorrect() { // #3 passed
        onView(withId(R.id.hint_button))
            .perform(click())
        onView(withId(R.id.hint_button))
            .perform(click())
        onView(withId(R.id.hint_button))
            .perform(click())
        onView(withId(R.id.answer_0_button))
            .check(matches(isEnabled()))
        onView(withId(R.id.answer_1_button))
            .check(matches(not(isEnabled())))
        onView(withId(R.id.answer_2_button))
            .check(matches(not(isEnabled())))
        onView(withId(R.id.answer_3_button))
            .check(matches(not(isEnabled())))
    }


    @Test
    fun clickingHint3TimesDisablesHint() { // #4 passed
        onView(withId(R.id.hint_button))
            .perform(click())
        onView(withId(R.id.hint_button))
            .perform(click())
        onView(withId(R.id.hint_button))
            .perform(click())
        onView(withId(R.id.hint_button))
            .check(matches(not(isEnabled())))
    }

    @Test
    fun clickingAnswer0ThenAnswer1DeselectsAnswer0AndSelectsAnswer1() { // #5 passed
        onView(withId(R.id.answer_0_button))
            .perform(click())
        onView(withId(R.id.answer_1_button))
            .perform(click())
        onView(withId(R.id.answer_0_button))
            .check(matches(not(isSelected())))
        onView(withId(R.id.answer_1_button))
            .check(matches(isSelected()))
    }

    @Test
    fun clickingAnswer1TwiceDeselectsAnswer1() { // #6 passed
        onView(withId(R.id.answer_1_button))
            .perform(click())
        onView(withId(R.id.answer_1_button))
            .perform(click())
        onView(withId(R.id.answer_1_button))
            .check(matches(not(isSelected())))
    }

    @Test
    fun clickingAnswer1ThenRotatingSelectsAnswer1() { // #7 passed
        onView(withId(R.id.answer_1_button))
            .perform(click())
        scenario.recreate()
        onView(withId(R.id.answer_1_button))
            .check(matches(isSelected()))
    }

    @Test
    fun clickingHint3TimesThenRotatingDisablesHint() { // #8 passed
        onView(withId(R.id.hint_button))
            .perform(click())
        onView(withId(R.id.hint_button))
            .perform(click())
        onView(withId(R.id.hint_button))
            .perform(click())
        scenario.recreate()
        onView(withId(R.id.hint_button))
            .check(matches(not(isEnabled())))
    }

    @Test
    fun clickingHint3TimesThenRotatingTwiceDisablesAllButCorrect() { // #9 passed
        onView(withId(R.id.hint_button))
            .perform(click())
        onView(withId(R.id.hint_button))
            .perform(click())
        onView(withId(R.id.hint_button))
            .perform(click())
        scenario.recreate()
        scenario.recreate()
        onView(withId(R.id.answer_0_button))
            .check(matches(isEnabled()))
        onView(withId(R.id.answer_1_button))
            .check(matches(not(isEnabled())))
        onView(withId(R.id.answer_2_button))
            .check(matches(not(isEnabled())))
        onView(withId(R.id.answer_3_button))
            .check(matches(not(isEnabled())))
    }

    @Test
    fun all4QuestionsCorrectOneHint() { // #10 passed
        onView(withId(R.id.answer_0_button))
            .perform(click())
        onView(withId(R.id.hint_button))
            .perform(click())
        onView(withId(R.id.submit_button))
            .perform(click())
        onView(withId(R.id.answer_1_button))
            .perform(click())
        onView(withId(R.id.submit_button))
            .perform(click())
        onView(withId(R.id.answer_2_button))
            .perform(click())
        onView(withId(R.id.submit_button))
            .perform(click())
        onView(withId(R.id.answer_3_button))
            .perform(click())
        onView(withId(R.id.submit_button))
            .perform(click())

        onView(withId(R.id.submitted_answers_value))
            .check(matches(withText("4"))) // this line
        onView(withId(R.id.correct_answers_value))
            .check(matches(withText("4")))
        onView(withId(R.id.hints_used_value))
            .check(matches(withText("1")))
    }

    @Test
    fun resultIntentContainsCorrectExtras() { // #11 passed
        onView(withId(R.id.answer_0_button))
            .perform(click())
        onView(withId(R.id.hint_button))
            .perform(click())
        onView(withId(R.id.submit_button))
            .perform(click())
        onView(withId(R.id.answer_1_button))
            .perform(click())
        onView(withId(R.id.submit_button))
            .perform(click())
        onView(withId(R.id.answer_1_button))
            .perform(click())
        onView(withId(R.id.submit_button))
            .perform(click())
        onView(withId(R.id.answer_3_button))
            .perform(click())

        Intents.init()

        onView(withId(R.id.submit_button))
            .perform(click())

        intended(hasExtra("edu.vt.cs5254.multiquiz.correct_answers", 3))
        intended(hasExtra("edu.vt.cs5254.multiquiz.submitted_answers", 4))
        intended(hasExtra("edu.vt.cs5254.multiquiz.hints_used", 1))
    }

    @Test
    fun resultHandlesRotationBeforeAndAfterReset() { // #12 passed
        val intent = ResultsActivity.newIntent(
            getInstrumentation().targetContext,
            7,
            8,
            9
        )
        val resultScenario: ActivityScenario<ResultsActivity> = launch(intent)
        onView(withId(R.id.correct_answers_value))
            .check(matches(withText("7")))
        onView(withId(R.id.submitted_answers_value))
            .check(matches(withText("8")))
        onView(withId(R.id.hints_used_value))
            .check(matches(withText("9")))

        resultScenario.recreate()

        onView(withId(R.id.correct_answers_value))
            .check(matches(withText("7")))
        onView(withId(R.id.submitted_answers_value))
            .check(matches(withText("8")))
        onView(withId(R.id.hints_used_value))
            .check(matches(withText("9")))

        onView(withId(R.id.reset_all_button))
            .perform(click())

        onView(withId(R.id.correct_answers_value))
            .check(matches(withText("0")))
        onView(withId(R.id.submitted_answers_value))
            .check(matches(withText("0")))
        onView(withId(R.id.hints_used_value))
            .check(matches(withText("0")))

        resultScenario.recreate()

        onView(withId(R.id.correct_answers_value))
            .check(matches(withText("0")))
        onView(withId(R.id.submitted_answers_value))
            .check(matches(withText("0")))
        onView(withId(R.id.hints_used_value))
            .check(matches(withText("0")))

        resultScenario.close()
    }

    @Test
    fun backFromResultShowsFirstQuestionInPreviousState() { // #13 passed
        all4QuestionsCorrectOneHint()
        pressBack()
        onView(withId(R.id.answer_0_button))
            .check(matches(isSelected()))
        onView(withId(R.id.answer_1_button))
            .check(matches(not(isSelected())))
        onView(withId(R.id.answer_2_button))
            .check(matches(not(isSelected())))
        onView(withId(R.id.answer_3_button))
            .check(matches(not(isSelected())))
        onView(withId(R.id.hint_button))
            .check(matches(isEnabled()))
            .perform(click())
            .check(matches(isEnabled()))
            .perform(click())
            .check(matches(not(isEnabled())))
        onView(withId(R.id.submit_button))
            .check(matches(isEnabled()))
    }

    @Test
    fun resultResetSetsScoresToZeroAndDisablesReset() { // #14 passed
        all4QuestionsCorrectOneHint()
        onView(withId(R.id.reset_all_button))
            .perform(click())
        onView(withId(R.id.reset_all_button))
            .check(matches(not(isEnabled())))
        onView(withId(R.id.submitted_answers_value))
            .check(matches(withText("0")))
        onView(withId(R.id.correct_answers_value))
            .check(matches(withText("0")))
        onView(withId(R.id.hints_used_value))
            .check(matches(withText("0")))
    }

    @Test
    fun backFromResetShowsFirstQuestionAsReset() { // #15 passed
        all4QuestionsCorrectOneHint()
        onView(withId(R.id.reset_all_button))
            .perform(click())
        pressBack()
        onView(withId(R.id.answer_0_button))
            .check(matches(not(isSelected())))
            .check(matches(isEnabled()))
        onView(withId(R.id.answer_1_button))
            .check(matches(not(isSelected())))
            .check(matches(isEnabled()))
        onView(withId(R.id.answer_2_button))
            .check(matches(not(isSelected())))
            .check(matches(isEnabled()))
        onView(withId(R.id.answer_3_button))
            .check(matches(not(isSelected())))
            .check(matches(isEnabled()))
        onView(withId(R.id.hint_button))
            .check(matches(isEnabled()))
        onView(withId(R.id.submit_button))
            .check(matches(not(isEnabled())))
    }

    @Test
    fun initialHintButtonIsEnabledSubmitButtonIsDisabled() { // #16 passed
        onView(withId(R.id.hint_button))
            .check(matches(isEnabled()))
        onView(withId(R.id.submit_button))
            .check(matches(not(isEnabled())))
    }

    @Test
    fun initialNoAnswersAreSelected() { // #17 passed
        onView(withId(R.id.answer_0_button))
            .check(matches(not(isSelected())))
        onView(withId(R.id.answer_1_button))
            .check(matches(not(isSelected())))
        onView(withId(R.id.answer_2_button))
            .check(matches(not(isSelected())))
        onView(withId(R.id.answer_3_button))
            .check(matches(not(isSelected())))
    }

    @Test
    fun clickingAnswer0SelectsAnswer0() { // #18 passed
        onView(withId(R.id.answer_0_button))
            .perform(click())
        onView(withId(R.id.answer_0_button))
            .check(matches(isSelected()))
    }

    @Test
    fun clickingAnswer1ThenHint3TimesDeselectsAnswer1() { // #19 passed
        onView(withId(R.id.answer_1_button))
            .perform(click())
        onView(withId(R.id.hint_button))
            .perform(click())
        onView(withId(R.id.hint_button))
            .perform(click())
        onView(withId(R.id.hint_button))
            .perform(click())
        onView(withId(R.id.answer_1_button))
            .check(matches(not(isSelected())))
    }

    @Test
    fun clickingAnswer0ThenSubmitPutsButtonsIntoInitStates() { // #20 passed
        onView(withId(R.id.answer_0_button))
            .perform(click())
        onView(withId(R.id.submit_button))
            .perform(click())
        onView(withId(R.id.answer_0_button))
            .check(matches(not(isSelected())))
        onView(withId(R.id.answer_1_button))
            .check(matches(not(isSelected())))
        onView(withId(R.id.answer_2_button))
            .check(matches(not(isSelected())))
        onView(withId(R.id.answer_3_button))
            .check(matches(not(isSelected())))
        onView(withId(R.id.hint_button))
            .check(matches(isEnabled()))
        onView(withId(R.id.submit_button))
            .check(matches(not(isEnabled())))
    }

    @Test
    fun exactly2QuestionsCorrect4Hints() { // #21 passed
        onView(withId(R.id.hint_button))
            .perform(click())
        onView(withId(R.id.answer_0_button))
            .perform(click())
        onView(withId(R.id.submit_button))
            .perform(click())

        onView(withId(R.id.hint_button))
            .perform(click())
        onView(withId(R.id.hint_button))
            .perform(click())
        onView(withId(R.id.hint_button))
            .perform(click())
        onView(withId(R.id.answer_1_button))
            .perform(click())
        onView(withId(R.id.submit_button))
            .perform(click())

        onView(withId(R.id.answer_0_button))
            .perform(click())
        onView(withId(R.id.submit_button))
            .perform(click())

        onView(withId(R.id.answer_1_button))
            .perform(click())
        onView(withId(R.id.submit_button))
            .perform(click())

        onView(withId(R.id.submitted_answers_value))
            .check(matches(withText("4")))

        onView(withId(R.id.correct_answers_value))
            .check(matches(withText("2")))

        onView(withId(R.id.hints_used_value))
            .check(matches(withText("4")))
    }

    @Test
    fun all4QuestionsCorrect6Hints() { // #22 passed
        onView(withId(R.id.answer_0_button))
            .perform(click())
        onView(withId(R.id.hint_button))
            .perform(click())
        onView(withId(R.id.submit_button))
            .perform(click())

        onView(withId(R.id.answer_1_button))
            .perform(click())
        onView(withId(R.id.hint_button))
            .perform(click())
        onView(withId(R.id.submit_button))
            .perform(click())

        onView(withId(R.id.answer_2_button))
            .perform(click())
        onView(withId(R.id.hint_button))
            .perform(click())
        onView(withId(R.id.hint_button))
            .perform(click())
        onView(withId(R.id.submit_button))
            .perform(click())

        onView(withId(R.id.answer_3_button))
            .perform(click())
        onView(withId(R.id.hint_button))
            .perform(click())
        onView(withId(R.id.hint_button))
            .perform(click())
        onView(withId(R.id.submit_button))
            .perform(click())

        onView(withId(R.id.submitted_answers_value))
            .check(matches(withText("4")))

        onView(withId(R.id.correct_answers_value))
            .check(matches(withText("4")))

        onView(withId(R.id.hints_used_value))
            .check(matches(withText("6")))
    }

    @Test
    fun exactly2QuestionCorrect2HintsRotationAfterSecondSubmit() { // #23 passed
        onView(withId(R.id.hint_button))
            .perform(click())
        onView(withId(R.id.answer_0_button))
            .perform(click())
        onView(withId(R.id.submit_button))
            .perform(click())

        onView(withId(R.id.hint_button))
            .perform(click())
        onView(withId(R.id.answer_1_button))
            .perform(click())
        onView(withId(R.id.submit_button))
            .perform(click())

        scenario.recreate()

        onView(withId(R.id.answer_0_button))
            .perform(click())
        onView(withId(R.id.submit_button))
            .perform(click())

        onView(withId(R.id.answer_0_button))
            .perform(click())
        onView(withId(R.id.submit_button))
            .perform(click())

        onView(withId(R.id.submitted_answers_value))
            .check(matches(withText("4")))

        onView(withId(R.id.correct_answers_value))
            .check(matches(withText("2")))

        onView(withId(R.id.hints_used_value))
            .check(matches(withText("2")))
    }

    @Test
    fun exactly1QuestionCorrectNoHintsRotationBeforeFinalSubmit() { // #24 passed! Yay!!!
        onView(withId(R.id.answer_0_button))
            .perform(click())
        onView(withId(R.id.submit_button))
            .perform(click())

        onView(withId(R.id.answer_0_button))
            .perform(click())
        onView(withId(R.id.submit_button))
            .perform(click())

        onView(withId(R.id.answer_0_button))
            .perform(click())
        onView(withId(R.id.submit_button))
            .perform(click())

        onView(withId(R.id.answer_0_button))
            .perform(click())

        scenario.recreate()

        onView(withId(R.id.submit_button))
            .perform(click())

        onView(withId(R.id.submitted_answers_value))
            .check(matches(withText("4")))

        onView(withId(R.id.correct_answers_value))
            .check(matches(withText("1")))

        onView(withId(R.id.hints_used_value))
            .check(matches(withText("0")))
    }

}
