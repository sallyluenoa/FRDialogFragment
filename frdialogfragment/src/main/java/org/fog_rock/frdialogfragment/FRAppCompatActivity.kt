package org.fog_rock.frdialogfragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.fog_rock.frextensions.androidx.log.logI

/**
 * A subclass of AppCompatActivity to keep fragment listeners in the holder.
 */
open class FRAppCompatActivity: AppCompatActivity() {

    internal val fragmentListenerHolder: MutableMap<String, FRFragmentEventListener> = mutableMapOf()

    private var enabledFragmentListenerRegistration = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enabledFragmentListenerRegistration = false
    }

    /**
     * Register a dialog callback in the holder to receive the result from the dialog.
     * @param callback A dialog callback
     * @return A key associated with the callback
     * @throws IllegalStateException If it is called after Activity#onCreate().
     */
    fun registerForDialogResult(callback: FRDialogFragment.Callback): String =
        registerForFragmentListener(callback)

    /**
     * Register a fragment listener in the holder to receive events from the fragment.
     * The registered listener would be restored in the fragment.
     * @param listener A fragment event listener
     * @return A key associated with the listener
     * @throws IllegalStateException If it is called after Activity#onCreate().
     * @see org.fog_rock.frdialogfragment.restoreFragmentEventListener
     */
    fun registerForFragmentListener(listener: FRFragmentEventListener): String {
        if (!enabledFragmentListenerRegistration) {
            throw IllegalStateException(
                "Cannot register fragment listener. Must be called before Activity#onCreate().")
        }
        val key = "fragment_listener#${fragmentListenerHolder.size}"
        fragmentListenerHolder[key] = listener
        logI("Registered fragment listener. key: $key")
        return key
    }
}