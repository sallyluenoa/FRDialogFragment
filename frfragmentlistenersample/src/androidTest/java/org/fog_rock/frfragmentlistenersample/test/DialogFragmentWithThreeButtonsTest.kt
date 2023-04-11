/*
 * Copyright (c) 2021 SallyLueNoa
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.fog_rock.frfragmentlistenersample.test

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import org.fog_rock.frfragmentlistener.dialog.FRDialogFragment
import org.fog_rock.frfragmentlistenersample.R
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Test codes for `FRDialogFragment` with `DialogFragmentTestActivity`.
 * It shows three buttons - a positive, a negative, and a neutral button.
 * @see org.fog_rock.frfragmentlistenersample.test.DialogFragmentTestActivity
 * @see org.fog_rock.frfragmentlistener.dialog.FRDialogFragment
 */
@RunWith(AndroidJUnit4::class)
class DialogFragmentWithThreeButtonsTest {

    @get:Rule val activityScenarioRule =
        ActivityScenarioRule(DialogFragmentTestActivity::class.java)

    @Before
    fun before() {
        Intents.init()

        // Perform view actions.
        Espresso.onView(Matchers.allOf(
            ViewMatchers.withId(R.id.switchCompat),
            ViewMatchers.isNotChecked(),
            ViewMatchers.withParent(ViewMatchers.withId(R.id.layout_item_negative))
        )).perform(ViewActions.click())
        Espresso.onView(Matchers.allOf(
            ViewMatchers.withId(R.id.switchCompat),
            ViewMatchers.isNotChecked(),
            ViewMatchers.withParent(ViewMatchers.withId(R.id.layout_item_neutral))
        )).perform(ViewActions.click())
        Espresso.onView(Matchers.allOf(
            ViewMatchers.withId(R.id.switchCompat),
            ViewMatchers.isNotChecked(),
            ViewMatchers.withParent(ViewMatchers.withId(R.id.layout_item_cancelable))
        )).perform(ViewActions.click())
        Espresso.onView(Matchers.allOf(
            ViewMatchers.withId(R.id.switchCompat),
            ViewMatchers.isNotChecked(),
            ViewMatchers.withParent(ViewMatchers.withId(R.id.layout_item_callback))
        )).perform(ViewActions.click())
        Espresso.onView(Matchers.allOf(
            ViewMatchers.withId(R.id.editText),
            ViewMatchers.withParent(ViewMatchers.withId(R.id.layout_item_title))
        )).perform(ViewActions.typeText("Submit forms."), ViewActions.closeSoftKeyboard())
        Espresso.onView(Matchers.allOf(
            ViewMatchers.withId(R.id.editText),
            ViewMatchers.withParent(ViewMatchers.withId(R.id.layout_item_message))
        )).perform(ViewActions.typeText("Are you sure to submit this forms?"), ViewActions.closeSoftKeyboard())
        Espresso.onView(Matchers.allOf(
            ViewMatchers.withId(R.id.editText),
            ViewMatchers.withParent(ViewMatchers.withId(R.id.layout_item_positive))
        )).perform(ViewActions.typeText("Submit"), ViewActions.closeSoftKeyboard())
        Espresso.onView(Matchers.allOf(
            ViewMatchers.withId(R.id.editText),
            ViewMatchers.withParent(ViewMatchers.withId(R.id.layout_item_negative))
        )).perform(ViewActions.typeText("Cancel"), ViewActions.closeSoftKeyboard())
        Espresso.onView(Matchers.allOf(
            ViewMatchers.withId(R.id.editText),
            ViewMatchers.withParent(ViewMatchers.withId(R.id.layout_item_neutral))
        )).perform(ViewActions.typeText("Pending"), ViewActions.closeSoftKeyboard())
        Espresso.onView(Matchers.allOf(
            ViewMatchers.withId(R.id.spinner),
            ViewMatchers.withParent(ViewMatchers.withId(R.id.layout_item_cancelable))
        )).perform(ViewActions.click())
        Espresso.onData(Matchers.allOf(
            Matchers.`is`(Matchers.instanceOf(String::class.java)),
            Matchers.`is`("Non-cancelable")
        )).perform(ViewActions.click())
        Espresso.onView(Matchers.allOf(
            ViewMatchers.withId(R.id.spinner),
            ViewMatchers.withParent(ViewMatchers.withId(R.id.layout_item_callback))
        )).perform(ViewActions.click())
        Espresso.onData(Matchers.allOf(
            Matchers.`is`(Matchers.instanceOf(String::class.java)),
            Matchers.`is`("Callback 2")
        )).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.button_dialog))
            .perform(ViewActions.click())
    }

    @After
    fun after() {
        Intents.release()
    }

    /**
     * Check views onto the dialog fragment.
     */
    @Test
    fun threeButtons_checkViews() {
        // Check dialog fragment.
        activityScenarioRule.scenario.onActivity {
            val dialogFragment = it.supportFragmentManager.findFragmentByTag(
                FRDialogFragment.TAG_NAME) as? DialogFragment
            Truth.assertThat(dialogFragment).isInstanceOf(FRDialogFragment::class.java)
            Truth.assertThat(dialogFragment?.showsDialog).isTrue()
            Truth.assertThat(dialogFragment?.isCancelable).isFalse()
            Truth.assertThat(dialogFragment?.dialog).isInstanceOf(AlertDialog::class.java)
        }
        // Check views.
        Espresso.onView(ViewMatchers.withId(androidx.appcompat.R.id.alertTitle))
            .check(ViewAssertions.matches(ViewMatchers.withText("Submit forms.")))
        Espresso.onView(ViewMatchers.withId(android.R.id.message))
            .check(ViewAssertions.matches(ViewMatchers.withText("Are you sure to submit this forms?")))
        Espresso.onView(ViewMatchers.withId(android.R.id.button1))
            .check(ViewAssertions.matches(ViewMatchers.withText("Submit")))
        Espresso.onView(ViewMatchers.withId(android.R.id.button2))
            .check(ViewAssertions.matches(ViewMatchers.withText("Cancel")))
        Espresso.onView(ViewMatchers.withId(android.R.id.button3))
            .check(ViewAssertions.matches(ViewMatchers.withText("Pending")))
    }

    /**
     * Select the positive button after recreated activity.
     */
    @Test
    fun threeButtons_selectPositive_recreate() {
        // Recreate activity.
        activityScenarioRule.scenario.recreate()
        // Perform view actions.
        Espresso.onView(ViewMatchers.withId(android.R.id.button1))
            .perform(ViewActions.click())
        // Check dialog fragment.
        activityScenarioRule.scenario.onActivity {
            val dialogFragment = it.supportFragmentManager.findFragmentByTag(
                FRDialogFragment.TAG_NAME) as? DialogFragment
            Truth.assertThat(dialogFragment).isNull()
        }
        // Check views.
        Espresso.onView(ViewMatchers.withId(R.id.textView_result))
            .check(ViewAssertions.matches(ViewMatchers.withText("Result: Callback 2, which=-1")))
    }

    /**
     * Select the negative button.
     */
    @Test
    fun threeButtons_selectNegative() {
        // Perform view actions.
        Espresso.onView(ViewMatchers.withId(android.R.id.button2))
            .perform(ViewActions.click())
        // Check dialog fragment.
        activityScenarioRule.scenario.onActivity {
            val dialogFragment = it.supportFragmentManager.findFragmentByTag(
                FRDialogFragment.TAG_NAME) as? DialogFragment
            Truth.assertThat(dialogFragment).isNull()
        }
        // Check views.
        Espresso.onView(ViewMatchers.withId(R.id.textView_result))
            .check(ViewAssertions.matches(ViewMatchers.withText("Result: Callback 2, which=-2")))
    }

    /**
     * Select the neutral button after recreated activity.
     */
    @Test
    fun threeButtons_selectNeutral_recreate() {
        // Recreate activity.
        activityScenarioRule.scenario.recreate()
        // Perform view actions.
        Espresso.onView(ViewMatchers.withId(android.R.id.button3))
            .perform(ViewActions.click())
        // Check dialog fragment.
        activityScenarioRule.scenario.onActivity {
            val dialogFragment = it.supportFragmentManager.findFragmentByTag(
                FRDialogFragment.TAG_NAME) as? DialogFragment
            Truth.assertThat(dialogFragment).isNull()
        }
        // Check views.
        Espresso.onView(ViewMatchers.withId(R.id.textView_result))
            .check(ViewAssertions.matches(ViewMatchers.withText("Result: Callback 2, which=-3")))
    }

    /**
     * Press back.
     */
    @Test
    fun threeButtons_pressBack() {
        // Press back.
        Espresso.pressBack()
        // Check dialog fragment.
        activityScenarioRule.scenario.onActivity {
            val dialogFragment = it.supportFragmentManager.findFragmentByTag(
                FRDialogFragment.TAG_NAME) as? DialogFragment
            Truth.assertThat(dialogFragment).isNotNull()
        }
    }
}