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

package org.fog_rock.frfragmentlistenersample.sample

import android.os.Bundle
import org.fog_rock.frextensions.androidx.fragment.replaceFragment
import org.fog_rock.frfragmentlistener.activity.FRAppCompatActivity
import org.fog_rock.frfragmentlistener.dialog.FRDialogFragment
import org.fog_rock.frfragmentlistenersample.R
import org.fog_rock.frfragmentlistenersample.databinding.ActivitySampleBinding

/**
 * This is a sample code for API documents.
 */
class SampleActivity : FRAppCompatActivity() {

    private lateinit var binding: ActivitySampleBinding

    /**
     * Register a dialog callback and keep a return value
     * as a private field in the subclass of `FRAppCompatActivity`.
     */
    private val dialogCallbackKey = registerForDialogResult {
        // Write your result code here!
    }

    /**
     * Register a fragment listener and keep a return value
     * as a private field in the subclass of `FRAppCompatActivity`.
     */
    private val fragmentListenerKey = registerForFragmentListener(object : SampleFragment.Listener {
        override fun onClickedYesButton() {
            // Write your result code here!
        }
        override fun onClickedNoButton() {
            // Write your result code here!
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonDialog.setOnClickListener { showDialog() }

        replaceFragment(SampleFragment.newInstance(fragmentListenerKey), R.id.fragment_container)
    }

    private fun showDialog() {
        /**
         * Create and show a dialog fragment.
         */
        FRDialogFragment.Builder(this).apply {
            setTitle(R.string.title)
            setMessage(R.string.message)
            setPositiveButton(R.string.ok)
            setNegativeButton(R.string.cancel)
            setCallbackKey(dialogCallbackKey)
        }.show()
    }
}