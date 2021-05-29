package org.fog_rock.frdialogfragment

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

data class FRDialogFragmentData(
    val code: Int,
    @StringRes private val titleResId: Int?,
    @StringRes private val messageResId: Int?,
    @StringRes private val positiveResId: Int?,
    @StringRes private val negativeResId: Int?,
    @StringRes private val neutralResId: Int?,
    private val cancelable: Boolean
) {

    fun show(activity: FragmentActivity) {
        dialogBuilder(activity).show(activity, code)
    }

    fun show(fragment: Fragment) {
        dialogBuilder(fragment.requireContext()).show(fragment, code)
    }

    private fun dialogBuilder(context: Context) =
        FRDialogFragment.Builder(context).apply {
            if (titleResId != null) setTitle(titleResId)
            if (messageResId != null) setMessage(messageResId)
            if (positiveResId != null) setPositiveLabel(positiveResId)
            if (negativeResId != null) setNegativeLabel(negativeResId)
            if (neutralResId != null) setNeutralLabel(neutralResId)
            setCancelable(cancelable)
        }
}