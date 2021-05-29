package org.fog_rock.frdialogfragment

import android.content.Intent

interface FRDialogFragmentCallback {

    /**
     * ユーザーの選択結果.
     * @param requestCode リクエストコード
     * @param result 結果
     * @param data 詳細情報
     */
    fun onDialogResult(requestCode: Int, result: FRDialogFragmentResult, data: Intent)
}