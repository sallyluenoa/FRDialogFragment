package org.fog_rock.frdialogfragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.fog_rock.frextensions.androidx.log.logI

/**
 * A subclass of AppCompatActivity to keep dialog callbacks in the holder.
 */
open class FRAppCompatActivity: AppCompatActivity() {

    internal val callbackHolders: MutableMap<String, FRDialogFragment.Callback> = mutableMapOf()

    private var enabledRegisterToCallbackHolders = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enabledRegisterToCallbackHolders = false
    }

    /**
     * Register a callback in the holder to receive the result from the dialog.
     * @param callback A dialog callback
     * @return A key associated with the callback
     */
    fun registerForDialogResult(callback: FRDialogFragment.Callback): String {
        if (!enabledRegisterToCallbackHolders) {
            throw IllegalStateException(
                "Cannot register callback. This method must be called before onCreate().")
        }
        val key = "callback#${callbackHolders.size}"
        callbackHolders[key] = callback
        logI("Registered callback. key: $key")
        return key
    }
}