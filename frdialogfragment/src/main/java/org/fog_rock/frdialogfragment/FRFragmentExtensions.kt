package org.fog_rock.frdialogfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import org.fog_rock.frextensions.androidx.log.logW

/**
 * Restore a fragment listener associated with a key from the activity holder.
 * @param args Arguments of a fragment
 * @param key A key associated with the listener
 * @return A fragment event listener with the specified type
 * @see org.fog_rock.frdialogfragment.FRAppCompatActivity.registerForFragmentListener
 */
inline fun <reified T: FRFragmentEventListener> Fragment.restoreFragmentEventListener(args: Bundle, key: String): T? {
    val listener = restoreFRFragmentEventListener(args, key) ?: return null
    return listener as? T ?: run {
        logW("Invalid subclass of FragmentEventListener.")
        return null
    }
}

/**
 * Restore a fragment listener associated with a key from the activity holder.
 * It would be called by Fragment#restoreFragmentEventListener.
 * @param args Arguments of a fragment
 * @param key A key associated with the listener
 * @return A fragment event listener
 * @see org.fog_rock.frdialogfragment.restoreFragmentEventListener
 */
fun Fragment.restoreFRFragmentEventListener(args: Bundle, key: String): FRFragmentEventListener? {
    val callbackKey = args.getString(key) ?: run {
        logW("Not found fragment listener key.")
        return null
    }
    val activity = requireActivity() as? FRAppCompatActivity ?: run {
        logW("Activity does not extends FRAppCompatActivity.")
        return null
    }
    return activity.fragmentListenerHolder[callbackKey] ?: run {
        logW("Not found fragment listener from holders.")
        return null
    }
}