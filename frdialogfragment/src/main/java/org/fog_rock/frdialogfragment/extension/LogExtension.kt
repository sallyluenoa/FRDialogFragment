package org.fog_rock.frdialogfragment.extension

import android.util.Log

private val Any.TAG: String get() = this::class.java.simpleName

internal fun Any.logV(message: String) = Log.v(TAG, message)

internal fun Any.logD(message: String) = Log.d(TAG, message)

internal fun Any.logI(message: String) = Log.i(TAG, message)

internal fun Any.logW(message: String) = Log.w(TAG, message)

internal fun Any.logE(message: String) = Log.e(TAG, message)
