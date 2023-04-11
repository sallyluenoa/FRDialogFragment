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
 * It shows two buttons - a positive and a negative button.
 * @see org.fog_rock.frfragmentlistenersample.test.DialogFragmentTestActivity
 * @see org.fog_rock.frfragmentlistener.dialog.FRDialogFragment
 */
@RunWith(AndroidJUnit4::class)
class DialogFragmentWithTwoButtonsTest {

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
            ViewMatchers.withParent(ViewMatchers.withId(R.id.layout_item_cancelable))
        )).perform(ViewActions.click())
        Espresso.onView(Matchers.allOf(
            ViewMatchers.withId(R.id.switchCompat),
            ViewMatchers.isNotChecked(),
            ViewMatchers.withParent(ViewMatchers.withId(R.id.layout_item_callback))
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
    fun twoButtons_checkViews() {
        // Check dialog fragment.
        activityScenarioRule.scenario.onActivity {
            val dialogFragment = it.supportFragmentManager.findFragmentByTag(
                FRDialogFragment.TAG_NAME) as? DialogFragment
            Truth.assertThat(dialogFragment).isInstanceOf(FRDialogFragment::class.java)
            Truth.assertThat(dialogFragment?.showsDialog).isTrue()
            Truth.assertThat(dialogFragment?.isCancelable).isTrue()
            Truth.assertThat(dialogFragment?.dialog).isInstanceOf(AlertDialog::class.java)
        }
        // Check views.
        Espresso.onView(ViewMatchers.withId(androidx.appcompat.R.id.alertTitle))
            .check(ViewAssertions.matches(ViewMatchers.withText("Title")))
        Espresso.onView(ViewMatchers.withId(android.R.id.message))
            .check(ViewAssertions.matches(ViewMatchers.withText("Message")))
        Espresso.onView(ViewMatchers.withId(android.R.id.button1))
            .check(ViewAssertions.matches(ViewMatchers.withText("Positive")))
        Espresso.onView(ViewMatchers.withId(android.R.id.button2))
            .check(ViewAssertions.matches(ViewMatchers.withText("Negative")))
        Espresso.onView(ViewMatchers.withId(android.R.id.button3))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
    }

    /**
     * Select the positive button.
     */
    @Test
    fun twoButtons_selectPositive() {
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
            .check(ViewAssertions.matches(ViewMatchers.withText("Result: Callback 1, which=-1")))
    }

    /**
     * Select the negative button after recreated activity.
     */
    @Test
    fun twoButtons_selectNegative_recreate() {
        // Recreate activity.
        activityScenarioRule.scenario.recreate()
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
            .check(ViewAssertions.matches(ViewMatchers.withText("Result: Callback 1, which=-2")))
    }

    /**
     * Press back.
     */
    @Test
    fun twoButtons_pressBack() {
        // Press back.
        Espresso.pressBack()
        // Check dialog fragment.
        activityScenarioRule.scenario.onActivity {
            val dialogFragment = it.supportFragmentManager.findFragmentByTag(
                FRDialogFragment.TAG_NAME) as? DialogFragment
            Truth.assertThat(dialogFragment).isNull()
        }
        // Check views.
        Espresso.onView(ViewMatchers.withId(R.id.textView_result))
            .check(ViewAssertions.matches(ViewMatchers.withText("Result: Callback 1, which=0")))
    }
}