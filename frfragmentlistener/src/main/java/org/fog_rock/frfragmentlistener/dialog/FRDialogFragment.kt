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

package org.fog_rock.frfragmentlistener.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import org.fog_rock.frfragmentlistener.fragment.FRFragmentListener
import org.fog_rock.frfragmentlistener.fragment.restoreFragmentEventListener

/**
 * A subclass of DialogFragment to display a alert dialog conveniently.
 * The class can be displayed positive, negative, and neutral buttons.
 * @see org.fog_rock.frfragmentlistener.dialog.FRDialogFragment.Builder
 * @see org.fog_rock.frfragmentlistener.dialog.FRDialogFragment.Callback
 * @sample org.fog_rock.frfragmentlistenersample.sample.SampleActivity.dialogCallbackKey
 * @sample org.fog_rock.frfragmentlistenersample.sample.SampleActivity.showDialog
 */
class FRDialogFragment : DialogFragment() {

    /**
     * A callback interface for FRDialogFragment
     * @see org.fog_rock.frfragmentlistener.dialog.FRDialogFragment
     */
    fun interface Callback : FRFragmentListener {
        /**
         * This method will be invoked when a button in the dialog is tapped.
         * @param which A position of the button that was tapped
         */
        fun onDialogResult(which: Int)
    }

    /**
     * A builder class for FRDialogFragment
     * @see org.fog_rock.frfragmentlistener.dialog.FRDialogFragment
     */
    class Builder(private val activity: FragmentActivity) {

        private val args = Bundle()
        private var cancelable: Boolean = true

        /**
         * Set a key associated with the callback.
         * @param key A callback key
         * @return This builder object itself
         * @see org.fog_rock.frfragmentlistener.activity.FRAppCompatActivity.registerForDialogResult
         */
        fun setCallbackKey(key: String): Builder =
            also { it.args.putString(FRFragmentListener.ARGS_LISTENER_KEY, key) }

        /**
         * Set a title text.
         * @param title A title text
         * @return This builder object itself
         */
        fun setTitle(title: String): Builder = also { it.args.putString(ARGS_TITLE, title) }

        /**
         * Set a title text.
         * @param titleId A string recourse ID of a title text
         * @return This builder object itself
         */
        fun setTitle(@StringRes titleId: Int): Builder = setTitle(activity.getString(titleId))

        /**
         * Set a message.
         * @param message A message
         * @return This builder object itself
         */
        fun setMessage(message: String): Builder = also { it.args.putString(ARGS_MESSAGE, message) }

        /**
         * Set a message.
         * @param messageId A string recourse ID for a message
         * @return This builder object itself
         */
        fun setMessage(@StringRes messageId: Int): Builder = setMessage(activity.getString(messageId))

        /**
         * Set a text of a positive button.
         * @param text A text of a positive button
         * @return This builder object itself
         */
        fun setPositiveButton(text: String): Builder = also { it.args.putString(ARGS_POS_TEXT, text) }

        /**
         * Set a text of a positive button.
         * @param textId A string recourse ID for a text of a positive button
         * @return This builder object itself
         */
        fun setPositiveButton(@StringRes textId: Int): Builder = setPositiveButton(activity.getString(textId))

        /**
         * Set a text of a negative button.
         * @param text A text of a negative button
         * @return This builder object itself
         */
        fun setNegativeButton(text: String): Builder = also { it.args.putString(ARGS_NEG_TEXT, text) }

        /**
         * Set a text of a negative button.
         * @param textId A string recourse ID for a text of a negative button
         * @return This builder object itself
         */
        fun setNegativeButton(@StringRes textId: Int): Builder = setNegativeButton(activity.getString(textId))

        /**
         * Set a text of a neutral button.
         * @param text A text of a neutral button
         * @return This builder object itself
         */
        fun setNeutralButton(text: String): Builder = also { it.args.putString(ARGS_NEU_TEXT, text) }

        /**
         * Set a text of a neutral button.
         * @param textId A string recourse ID for a text of a neutral button
         * @return This builder object itself
         */
        fun setNeutralButton(@StringRes textId: Int): Builder = setNeutralButton(activity.getString(textId))

        /**
         * Set whether a dialog is cancelable or not.
         * The default value is true.
         * @param cancelable True if a dialog is cancelable, or false otherwise.
         * @return This builder object itself
         */
        fun setCancelable(cancelable: Boolean): Builder = also { it.cancelable = cancelable }

        /**
         * Create a FRDialogFragment with the arguments supplied to this builder and display it on the parent activity.
         */
        fun show() {
            FRDialogFragment().apply {
                arguments = this@Builder.args
                isCancelable = this@Builder.cancelable
            }.show(activity.supportFragmentManager, TAG_NAME)
        }
    }

    companion object {
        /**
         * A tag name added to the fragment manager of the parent activity.
         */
        const val TAG_NAME = "fr_dialog_fragment"

        private const val ARGS_TITLE = "title"
        private const val ARGS_MESSAGE = "message"
        private const val ARGS_POS_TEXT = "pos_text"
        private const val ARGS_NEG_TEXT = "neg_text"
        private const val ARGS_NEU_TEXT = "neu_Text"
    }

    private val args: Bundle by lazy {
        arguments ?: throw IllegalArgumentException("Not found arguments.")
    }
    private val callback: Callback? by lazy { restoreFragmentEventListener() }
    private val title: String? by lazy { args.getString(ARGS_TITLE) }
    private val message: String? by lazy { args.getString(ARGS_MESSAGE) }
    private val posText: String? by lazy { args.getString(ARGS_POS_TEXT) }
    private val negText: String? by lazy { args.getString(ARGS_NEG_TEXT) }
    private val neuText: String? by lazy { args.getString(ARGS_NEU_TEXT) }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireActivity()).apply {
            title?.let { setTitle(it) }
            message?.let { setMessage(message) }
            posText?.let {
                setPositiveButton(it) { _, which ->
                    dismiss()
                    callback?.onDialogResult(which)
                }
            }
            negText?.let {
                setNegativeButton(it) { _, which ->
                    dismiss()
                    callback?.onDialogResult(which)
                }
            }
            neuText?.let {
                setNeutralButton(it) { _, which ->
                    dismiss()
                    callback?.onDialogResult(which)
                }
            }
        }.create()

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        callback?.onDialogResult(0)
    }
}