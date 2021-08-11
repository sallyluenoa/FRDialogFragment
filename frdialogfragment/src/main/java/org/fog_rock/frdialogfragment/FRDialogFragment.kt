package org.fog_rock.frdialogfragment

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import org.fog_rock.frextensions.androidx.log.logE
import org.fog_rock.frextensions.androidx.log.logW

class FRDialogFragment : DialogFragment() {

    companion object {
        private const val ARGS_REQUEST_CODE = "request_code"
        private const val ARGS_TITLE = "title"
        private const val ARGS_MESSAGE = "message"
        private const val ARGS_POSITIVE_LABEL = "positive_label"
        private const val ARGS_NEGATIVE_LABEL = "negative_label"
        private const val ARGS_NEUTRAL_LABEL = "neutral_label"
        private const val ARGS_IS_PARENT_ACTIVITY = "is_parent_activity"
    }

    /**
     * ビルダークラス
     */
    class Builder(private val context: Context) {
        private var _title: String? = null
        private var _message: String? = null
        private var _positiveLabel: String? = null
        private var _negativeLabel: String? = null
        private var _neutralLabel: String? = null
        private var _cancelable = true

        fun setTitle(title: String): Builder {
            this._title = title
            return this
        }
        fun setTitle(@StringRes title: Int): Builder =
            setTitle(context.getString(title))

        fun setMessage(message: String): Builder {
            this._message = message
            return this
        }
        fun setMessage(@StringRes message: Int): Builder =
            setMessage(context.getString(message))

        fun setPositiveLabel(positiveLabel: String): Builder {
            this._positiveLabel = positiveLabel
            return this
        }
        fun setPositiveLabel(@StringRes positiveLabel: Int): Builder =
            setPositiveLabel(context.getString(positiveLabel))

        fun setNegativeLabel(negativeLabel: String): Builder {
            this._negativeLabel = negativeLabel
            return this
        }
        fun setNegativeLabel(@StringRes negativeLabel: Int): Builder =
            setNegativeLabel(context.getString(negativeLabel))

        fun setNeutralLabel(neutralLabel: String): Builder {
            this._neutralLabel = neutralLabel
            return this
        }
        fun setNeutralLabel(@StringRes neutralLabel: Int): Builder =
            setNeutralLabel(context.getString(neutralLabel))

        fun setCancelable(cancelable: Boolean): Builder {
            this._cancelable = cancelable
            return this
        }

        /**
         * Activity 上に AppDialogFragment を表示.
         */
        fun show(activity: FragmentActivity, requestCode: Int) {
            show(activity.supportFragmentManager, requestCode, true)
        }

        /**
         * Fragment 上に AppDialogFragment を表示.
         */
        fun show(fragment: Fragment, requestCode: Int) {
            show(fragment.childFragmentManager, requestCode, false)
        }

        private fun show(fragmentManager: FragmentManager, requestCode: Int, isParentActivity: Boolean) {
            FRDialogFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARGS_REQUEST_CODE, requestCode)
                    putString(ARGS_TITLE, _title)
                    putString(ARGS_MESSAGE, _message)
                    putString(ARGS_POSITIVE_LABEL, _positiveLabel)
                    putString(ARGS_NEGATIVE_LABEL, _negativeLabel)
                    putString(ARGS_NEUTRAL_LABEL, _neutralLabel)
                    putBoolean(ARGS_IS_PARENT_ACTIVITY, isParentActivity)
                }
                isCancelable = _cancelable
            }.show(fragmentManager, null)
        }
    }

    private val args: Bundle by lazy {
        arguments ?: run {
            logE("Not found arguments.")
            Bundle()
        }
    }
    private val requestCode: Int by lazy { args.getInt(ARGS_REQUEST_CODE) }
    private val title: String? by lazy { args.getString(ARGS_TITLE) }
    private val message: String? by lazy { args.getString(ARGS_MESSAGE) }
    private val positiveLabel: String? by lazy { args.getString(ARGS_POSITIVE_LABEL) }
    private val negativeLabel: String? by lazy { args.getString(ARGS_NEGATIVE_LABEL) }
    private val neutralLabel: String? by lazy { args.getString(ARGS_NEUTRAL_LABEL) }
    private val isParentActivity: Boolean by lazy { args.getBoolean(ARGS_IS_PARENT_ACTIVITY, true) }

    private val callback: FRDialogFragmentCallback? by lazy {
        (if (isParentActivity) requireActivity() as? FRDialogFragmentCallback
        else parentFragment as? FRDialogFragmentCallback) ?: run {
            logW("No implemented callback.")
            null
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireActivity()).apply {
            if (!title.isNullOrEmpty()) setTitle(title)
            if (!message.isNullOrEmpty()) setMessage(message)
            if (!positiveLabel.isNullOrEmpty()) {
                setPositiveButton(positiveLabel) { _, which ->
                    dismiss()
                    callDialogResult(which)
                }
            }
            if (!negativeLabel.isNullOrEmpty()) {
                setNegativeButton(negativeLabel) { _, which ->
                    dismiss()
                    callDialogResult(which)
                }
            }
            if (!neutralLabel.isNullOrEmpty()) {
                setNeutralButton(neutralLabel) { _, which ->
                    dismiss()
                    callDialogResult(which)
                }
            }
        }.create()

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        callDialogResult(FRDialogFragmentResult.BUTTON_CANCEL)
    }

    private fun callDialogResult(code: Int) {
        callback?.onDialogResult(
            requestCode,
            FRDialogFragmentResult.convertFromCode(code),
            Intent().apply { putExtras(args) }
        )
    }
}