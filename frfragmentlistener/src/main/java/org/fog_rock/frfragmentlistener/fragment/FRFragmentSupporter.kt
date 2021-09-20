package org.fog_rock.frfragmentlistener.fragment

import androidx.fragment.app.Fragment
import org.fog_rock.frextensions.androidx.log.logW
import org.fog_rock.frfragmentlistener.activity.FRAppCompatActivity

/**
 * A support class to call from Java code.
 */
class FRFragmentSupporter<T : FRFragmentListener>(private val tClass: Class<T>) {

    /**
     * Restore a fragment listener from the arguments and the activity holder.
     * The method is assumed to be called from Java code.
     * For Kotlin code, use the Kotlin extension function instead.
     * @return A fragment listener with the specified type
     * @see org.fog_rock.frfragmentlistener.fragment.restoreFragmentEventListener
     * @sample org.fog_rock.frfragmentlistenersample.sample.SampleFragment.listener
     */
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