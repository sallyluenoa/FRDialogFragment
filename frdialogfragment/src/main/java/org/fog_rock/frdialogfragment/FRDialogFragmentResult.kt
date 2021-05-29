package org.fog_rock.frdialogfragment

import android.content.DialogInterface

enum class FRDialogFragmentResult(private val code: Int) {
    /**
     * キャンセル
     */
    CANCEL(FRDialogFragmentResult.BUTTON_CANCEL),

    /**
     * 肯定ボタン
     */
    POSITIVE(DialogInterface.BUTTON_POSITIVE),

    /**
     * 否定ボタン
     */
    NEGATIVE(DialogInterface.BUTTON_NEGATIVE),

    /**
     * 中間ボタン
     */
    NEUTRAL(DialogInterface.BUTTON_NEUTRAL),
    ;

    internal companion object {
        const val BUTTON_CANCEL = 0

        fun convertFromCode(code: Int): FRDialogFragmentResult =
                values().find { it.code == code } ?: CANCEL
    }
}