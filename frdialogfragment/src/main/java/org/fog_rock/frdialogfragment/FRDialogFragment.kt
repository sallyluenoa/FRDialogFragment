package org.fog_rock.frdialogfragment

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import org.fog_rock.frextensions.androidx.log.logW

class FRDialogFragment : DialogFragment() {

    fun interface Callback {
        fun onDialogResult(which: Int)
    }

    /**
     * ビルダークラス
     */
    class Builder(private val context: Context) {

        private val args = Bundle()
        private var cancelable: Boolean = false

        fun setCallbackKey(key: String): Builder = also { it.args.putString(ARGS_CALLBACK_KEY, key) }

        fun setTitle(title: String): Builder = also { it.args.putString(ARGS_TITLE, title) }
        fun setTitle(@StringRes titleId: Int): Builder = setTitle(context.getString(titleId))

        fun setMessage(message: String): Builder = also { it.args.putString(ARGS_MESSAGE, message) }
        fun setMessage(@StringRes messageId: Int): Builder = setMessage(context.getString(messageId))

        fun setPositiveButton(text: String): Builder = also { it.args.putString(ARGS_POS_TEXT, text) }
        fun setPositiveButton(@StringRes textId: Int): Builder = setPositiveButton(context.getString(textId))

        fun setNegativeButton(text: String): Builder = also { it.args.putString(ARGS_NEG_TEXT, text) }
        fun setNegativeButton(@StringRes textId: Int): Builder = setNegativeButton(context.getString(textId))

        fun setNeutralButton(text: String): Builder = also { it.args.putString(ARGS_NEU_TEXT, text) }
        fun setNeutralButton(@StringRes textId: Int): Builder = setNeutralButton(context.getString(textId))

        fun setCancelable(cancelable: Boolean): Builder = also { it.cancelable = cancelable }

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
    private val title: String? by lazy { args.getString(ARGS_TITLE) }
    private val message: String? by lazy { args.getString(ARGS_MESSAGE) }
    private val posText: String? by lazy { args.getString(ARGS_POS_TEXT) }
    private val negText: String? by lazy { args.getString(ARGS_NEG_TEXT) }
    private val neuText: String? by lazy { args.getString(ARGS_NEU_TEXT) }

    private val callback: Callback? by lazy {
        val key = args.getString(ARGS_CALLBACK_KEY) ?: run {
            logW("Not found callback key.")
            return@lazy null
        }
        val activity = requireActivity() as? FRAppCompatActivity ?: run {
            logW("Activity does not extends FRAppCompatActivity.")
            return@lazy null
        }
        val callback = activity.callbackHolders[key] ?: run {
            logW("Not found callback from holders.")
            return@lazy null
        }
        callback
    }

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