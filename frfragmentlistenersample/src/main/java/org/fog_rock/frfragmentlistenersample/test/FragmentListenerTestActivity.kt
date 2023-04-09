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

import android.os.Bundle
import org.fog_rock.frextensions.androidx.fragment.replaceFragment
import org.fog_rock.frfragmentlistener.activity.FRAppCompatActivity
import org.fog_rock.frfragmentlistenersample.R
import org.fog_rock.frfragmentlistenersample.databinding.ActivityFragmentListenerTestBinding

/**
 * This is a sample code for android test.
 */
class FragmentListenerTestActivity : FRAppCompatActivity() {

    private lateinit var binding: ActivityFragmentListenerTestBinding

    private val fragmentListenerKey = registerForFragmentListener(
        object : FragmentListenerTestFragment.Listener {
            override fun onClickedButton() {
                binding.textViewResult.setText(R.string.fragment_test_textview_clicked_button)
            }
            override fun onSelectedSpinnerItem(item: String) {
                binding.textViewResult.text =
                    getString(R.string.fragment_test_textview_selected_item, item)
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFragmentListenerTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(
            FragmentListenerTestFragment.newInstance(fragmentListenerKey),
            R.id.fragment_container
        )
    }

    /**
     * This method uses tests for `registerForFragmentListener`.
     * It would be called from androidTest.
     */
    fun testOfRegisterForFragmentListener() {
        val listenerKey = registerForFragmentListener(
            object : FragmentListenerTestFragment.Listener {
                override fun onClickedButton() {}
                override fun onSelectedSpinnerItem(item: String) {}
            }
        )
    }
}