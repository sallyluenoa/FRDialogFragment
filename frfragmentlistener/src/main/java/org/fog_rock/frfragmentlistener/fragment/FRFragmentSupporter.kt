package org.fog_rock.frfragmentlistener.fragment

import androidx.fragment.app.Fragment
import org.fog_rock.frextensions.androidx.log.logW
import org.fog_rock.frfragmentlistener.activity.FRAppCompatActivity

class FRFragmentSupporter<T : FRFragmentListener>(private val tClass: Class<T>) {

    fun restoreFragmentEventListener(fragment: Fragment): T? {
        val callbackKey = fragment.arguments?.getString(FRFragmentListener.ARGS_LISTENER_KEY) ?: run {
            logW("Not found fragment listener key.")
            return null
        }
        val activity = fragment.requireActivity() as? FRAppCompatActivity ?: run {
            logW("Activity does not extends FRAppCompatActivity.")
            return null
        }
        val listener = activity.fragmentListenerHolder[callbackKey] ?: run {
            logW("Not found fragment listener from holders.")
            return null
        }
        if (!tClass.isAssignableFrom(listener.javaClass)) {
            logW("Invalid subclass of FragmentEventListener.")
            return null
        }
        @Suppress("UNCHECKED_CAST")
        return listener as T
    }
}