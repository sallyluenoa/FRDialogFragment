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
import org.fog_rock.frfragmentlistener.activity.FRAppCompatActivity
import org.fog_rock.frfragmentlistener.dialog.FRDialogFragment
import org.fog_rock.frfragmentlistenersample.R
import org.fog_rock.frfragmentlistenersample.databinding.ActivityDialogFragmentTestBinding

/**
 * This is a sample code for android test.
 */
class DialogFragmentTestActivity : FRAppCompatActivity() {

    private lateinit var binding: ActivityDialogFragmentTestBinding

    private val callback1Key = registerForDialogResult {
        binding.textViewResult.text =
            getString(R.string.dialog_test_textview_result, getString(R.string.callback_1), it)
    }

    private val callback2Key = registerForDialogResult {
        binding.textViewResult.text =
            getString(R.string.dialog_test_textview_result, getString(R.string.callback_2), it)
    }

    private val title: String? get() {
        val binding = this.binding.contentViewSetting.layoutItemTitle
        return if (binding.switchCompat.isChecked) binding.editText.text.toString() else null
    }

    private val message: String? get() {
        val binding = this.binding.contentViewSetting.layoutItemMessage
        return if (binding.switchCompat.isChecked) binding.editText.text.toString() else null
    }

    private val positive: String? get() {
        val binding = this.binding.contentViewSetting.layoutItemPositive
        return if (binding.switchCompat.isChecked) binding.editText.text.toString() else null
    }

    private val negative: String? get() {
        val binding = this.binding.contentViewSetting.layoutItemNegative
        return if (binding.switchCompat.isChecked) binding.editText.text.toString() else null
    }

    private val neutral: String? get() {
        val binding = this.binding.contentViewSetting.layoutItemNeutral
        return if (binding.switchCompat.isChecked) binding.editText.text.toString() else null
    }

    private val cancelable: Boolean? get() {
        val binding = this.binding.contentViewSetting.layoutItemCancelable
        return when (if (binding.switchCompat.isChecked) binding.spinner.selectedItem else null) {
            getString(R.string.cancelable) -> true
            getString(R.string.non_cancelable) -> false
            else -> null
        }
    }

    private val callbackKey: String? get() {
        val binding = this.binding.contentViewSetting.layoutItemCallback
        return when (if (binding.switchCompat.isChecked) binding.spinner.selectedItem else null) {
            getString(R.string.callback_1) -> callback1Key
            getString(R.string.callback_2) -> callback2Key
            else -> null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDialogFragmentTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonDialog.setOnClickListener {
            binding.textViewResult.setText(R.string.result)
            showDialog()
        }
    }

    private fun showDialog() {
        FRDialogFragment.Builder(this).apply {
            title?.let { if (it.isNotEmpty()) setTitle(it) else setTitle(R.string.title) }
            message?.let { if (it.isNotEmpty()) setMessage(it) else setMessage(R.string.message) }
            positive?.let { if (it.isNotEmpty()) setPositiveButton(it) else setPositiveButton(R.string.positive) }
            negative?.let { if (it.isNotEmpty()) setNegativeButton(it) else setNegativeButton(R.string.negative) }
            neutral?.let { if (it.isNotEmpty()) setNeutralButton(it) else setNeutralButton(R.string.neutral) }
            cancelable?.let { setCancelable(it) }
            callbackKey?.let { setCallbackKey(it) }
        }.show()
    }

}