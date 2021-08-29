package org.fog_rock.frfragmentlistenersample.test

import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.fog_rock.frfragmentlistenersample.R
import org.hamcrest.Matchers
import org.junit.*
import org.junit.runner.RunWith

/**
 * Test codes for `FRFragmentListener` with `FragmentListenerTestActivity`.
 * @see FragmentListenerTestActivity
 * @see org.fog_rock.frfragmentlistener.fragment.FRFragmentListener
 * @see org.fog_rock.frfragmentlistener.activity.FRAppCompatActivity.registerForFragmentListener
 */
@RunWith(AndroidJUnit4::class)
class FragmentListenerTest {

    @get:Rule val activityScenarioRule =
        ActivityScenarioRule(FragmentListenerTestActivity::class.java)

    @Before
    fun before() {
        Intents.init()
    }

    @After
    fun after() {
        Intents.release()
    }

    /**
     * A test for clicked button.
     * @see FragmentListenerTestFragment.Listener.onClickedButton
     */
    @Test
    fun clickedButton() {
        // Perform view actions.
        Espresso.onView(ViewMatchers.withId(R.id.button))
            .perform(ViewActions.click())
        // Check views.
        Espresso.onView(ViewMatchers.withId(R.id.textView_result))
            .check(ViewAssertions.matches(ViewMatchers.withText("Button clicked.")))
    }

    /**
     * A test for selected spinner "Positive".
     * @see FragmentListenerTestFragment.Listener.onSelectedSpinnerItem
     */
    @Test
    fun selectedSpinnerPositive() {
        // Perform view actions.
        Espresso.onView(ViewMatchers.withId(R.id.spinner))
            .perform(ViewActions.click())
        Espresso.onData(Matchers.allOf(
            Matchers.`is`(Matchers.instanceOf(String::class.java)),
            Matchers.`is`("Positive")
        )).perform(ViewActions.click())
        // Check views.
        Espresso.onView(ViewMatchers.withId(R.id.spinner))
            .check(ViewAssertions.matches(ViewMatchers.withSpinnerText("Positive")))
        Espresso.onView(ViewMatchers.withId(R.id.textView_result))
            .check(ViewAssertions.matches(ViewMatchers.withText("Item selected: Positive")))
    }

    /**
     * A test for selected spinner "Negative".
     * @see FragmentListenerTestFragment.Listener.onSelectedSpinnerItem
     */
    @Test
    fun selectedSpinnerNegative() {
        // Perform view actions.
        Espresso.onView(ViewMatchers.withId(R.id.spinner))
            .perform(ViewActions.click())
        Espresso.onData(Matchers.allOf(
            Matchers.`is`(Matchers.instanceOf(String::class.java)),
            Matchers.`is`("Negative")
        )).perform(ViewActions.click())
        // Check views.
        Espresso.onView(ViewMatchers.withId(R.id.spinner))
            .check(ViewAssertions.matches(ViewMatchers.withSpinnerText("Negative")))
        Espresso.onView(ViewMatchers.withId(R.id.textView_result))
            .check(ViewAssertions.matches(ViewMatchers.withText("Item selected: Negative")))
    }

    /**
     * A test for selected spinner "Neutral".
     * @see FragmentListenerTestFragment.Listener.onSelectedSpinnerItem
     */
    @Test
    fun selectedSpinnerNeutral() {
        // Perform view actions.
        Espresso.onView(ViewMatchers.withId(R.id.spinner))
            .perform(ViewActions.click())
        Espresso.onData(Matchers.allOf(
            Matchers.`is`(Matchers.instanceOf(String::class.java)),
            Matchers.`is`("Neutral")
        )).perform(ViewActions.click())
        // Check views.
        Espresso.onView(ViewMatchers.withId(R.id.spinner))
            .check(ViewAssertions.matches(ViewMatchers.withSpinnerText("Neutral")))
        Espresso.onView(ViewMatchers.withId(R.id.textView_result))
            .check(ViewAssertions.matches(ViewMatchers.withText("Item selected: Neutral")))
    }

    /**
     * Multi tests with recreate activity.
     * Click button -> Recreate activity -> Select spinner "Negative"
     */
    @Test
    fun multiEvent_withRecreate() {
        // Perform view actions.
        Espresso.onView(ViewMatchers.withId(R.id.button))
            .perform(ViewActions.click())
        // Check views.
        Espresso.onView(ViewMatchers.withId(R.id.textView_result))
            .check(ViewAssertions.matches(ViewMatchers.withText("Button clicked.")))

        // Recreate activity.
        activityScenarioRule.scenario.recreate()
        // Check views.
        Espresso.onView(ViewMatchers.withId(R.id.textView_result))
            .check(ViewAssertions.matches(ViewMatchers.withText("Item selected: Positive")))

        // Perform view actions.
        Espresso.onView(ViewMatchers.withId(R.id.spinner))
            .perform(ViewActions.click())
        Espresso.onData(Matchers.allOf(
            Matchers.`is`(Matchers.instanceOf(String::class.java)),
            Matchers.`is`("Negative")
        )).perform(ViewActions.click())
        // Check views.
        Espresso.onView(ViewMatchers.withId(R.id.spinner))
            .check(ViewAssertions.matches(ViewMatchers.withSpinnerText("Negative")))
        Espresso.onView(ViewMatchers.withId(R.id.textView_result))
            .check(ViewAssertions.matches(ViewMatchers.withText("Item selected: Negative")))
    }

    /**
     * A failed test due to call method `registerForFragmentListener()` with created state.
     * @see FragmentListenerTestActivity.testOfRegisterForFragmentListener
     */
    @Test
    fun registerForFragmentListener_created() {
        activityScenarioRule.scenario.apply {
            // Move to created state.
            moveToState(Lifecycle.State.CREATED)
            // Call registerForFragmentListener. Exception would be occurred.
            try {
                onActivity { it.testOfRegisterForFragmentListener() }
                Assert.fail("Should have thrown IllegalStateException.")
            } catch (e: IllegalStateException) {
                close()
            }
        }
    }

    /**
     * A failed test due to call method `registerForFragmentListener()` with resumed state.
     * @see FragmentListenerTestActivity.testOfRegisterForFragmentListener
     */
    @Test
    fun registerForFragmentListener_resumed() {
        activityScenarioRule.scenario.apply {
            // Move to resumed state.
            moveToState(Lifecycle.State.RESUMED)
            // Call registerForFragmentListener. Exception would be occurred.
            try {
                onActivity { it.testOfRegisterForFragmentListener() }
                Assert.fail("Should have thrown IllegalStateException.")
            } catch (e: IllegalStateException) {
                close()
            }
        }
    }
}