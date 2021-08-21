package org.fog_rock.frfragmentlistener.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import org.fog_rock.frfragmentlistener.fragment.FRFragmentListener
import org.fog_rock.frfragmentlistener.fragment.restoreFragmentEventListener

/**
 * A subclass of DialogFragment to display a alert dialog conveniently.
 * The class can be displayed positive, negative, and neutral buttons.
 * @see org.fog_rock.frfragmentlistener.dialog.FRDialogFragment.Builder
 * @see org.fog_rock.frfragmentlistener.dialog.FRDialogFragment.Callback
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
    class Builder(private val context: Context) {

        private val args = Bundle()
        private var cancelable: Boolean = false

        /**
         * Set a key associated with the callback.
         * @param key A callback key
         * @return This builder object itself
         * @see org.fog_rock.frfragmentlistener.activity.FRAppCompatActivity.registerForDialogResult
         */
        fun setCallbackKey(key: String): Builder = also { it.args.putString(ARGS_CALLBACK_KEY, key) }

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
        fun setTitle(@StringRes titleId: Int): Builder = setTitle(context.getString(titleId))

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
        fun setMessage(@StringRes messageId: Int): Builder = setMessage(context.getString(messageId))

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
        fun setPositiveButton(@StringRes textId: Int): Builder = setPositiveButton(context.getString(textId))

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
        fun setNegativeButton(@StringRes textId: Int): Builder = setNegativeButton(context.getString(textId))

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
        fun setNeutralButton(@StringRes textId: Int): Builder = setNeutralButton(context.getString(textId))

        /**
         * Set whether a dialog is cancelable or not.
         * @param cancelable True if a dialog is cancelable, or false otherwise.
         * @return This builder object itself
         */
        fun setCancelable(cancelable: Boolean): Builder = also { it.cancelable = cancelable }

        /**
         * Creates a FRDialogFragment with arguments supplied to this builder.
         * @return A FRDialogFragment created from this builder
         */
        fun create(): FRDialogFragment = FRDialogFragment().apply {
            arguments = this@Builder.args
            isCancelable = this@Builder.cancelable
        }
    }

    private companion object {
        const val ARGS_CALLBACK_KEY = "callback_key"
        const val ARGS_TITLE = "title"
        const val ARGS_MESSAGE = "message"
        const val ARGS_POS_TEXT = "pos_text"
        const val ARGS_NEG_TEXT = "neg_text"
        const val ARGS_NEU_TEXT = "neu_Text"
    }

    private val args: Bundle by lazy {
        arguments ?: throw IllegalArgumentException("Not found arguments.")
    }
    private val callback: Callback? by lazy { restoreFragmentEventListener(args, ARGS_CALLBACK_KEY) }
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