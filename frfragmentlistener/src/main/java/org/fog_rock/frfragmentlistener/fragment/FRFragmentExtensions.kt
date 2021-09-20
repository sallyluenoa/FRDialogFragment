package org.fog_rock.frfragmentlistener.fragment

import androidx.fragment.app.Fragment
import org.fog_rock.frextensions.androidx.log.logW
import org.fog_rock.frfragmentlistener.activity.FRAppCompatActivity

/**
 * Restore a fragment listener from the arguments and the activity holder.
 * @return A fragment listener with the specified type
 * @sample org.fog_rock.frfragmentlistenersample.sample.SampleFragment.listener
 */
inline fun <reified T: FRFragmentListener> Fragment.restoreFragmentEventListener(): T? {
    val callbackKey = arguments?.getString(FRFragmentListener.ARGS_LISTENER_KEY) ?: run {
        logW("Not found fragment listener key.")
        return null
    }
    val activity = requireActivity() as? FRAppCompatActivity ?: run {
        logW("Activity does not extends FRAppCompatActivity.")
        return null
    }
    val listener = activity.fragmentListenerHolder[callbackKey] ?: run {
        logW("Not found fragment listener from holders.")
        return null
    }
    return listener as? T ?: run {
        logW("Invalid subclass of FragmentEventListener.")
        return null
    }
}