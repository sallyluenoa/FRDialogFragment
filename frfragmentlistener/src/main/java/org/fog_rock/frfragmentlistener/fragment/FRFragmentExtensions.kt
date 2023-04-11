/*
 * Copyright (c) 2021 SallyLueNoa
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.fog_rock.frfragmentlistener.fragment

import androidx.fragment.app.Fragment
import org.fog_rock.frfragmentlistener.activity.FRAppCompatActivity
import org.fog_rock.frlogs.frLogW

/**
 * Restore a fragment listener from the arguments and the activity holder.
 * @return A fragment listener with the specified type
 * @sample org.fog_rock.frfragmentlistenersample.sample.SampleFragment.listener
 */
inline fun <reified T: FRFragmentListener> Fragment.restoreFragmentEventListener(): T? {
    val listener = restoreFRFragmentEventListener() ?: return null
    return listener as? T ?: run {
        frLogW("Invalid subclass of FragmentEventListener.")
        return null
    }
}

/**
 * @suppress It would be called by `Fragment#restoreFragmentEventListener`.
 */
fun Fragment.restoreFRFragmentEventListener(): FRFragmentListener? {
    val callbackKey = arguments?.getString(FRFragmentListener.ARGS_LISTENER_KEY) ?: run {
        frLogW("Not found fragment listener key.")
        return null
    }
    val activity = requireActivity() as? FRAppCompatActivity ?: run {
        frLogW("Activity does not extends FRAppCompatActivity.")
        return null
    }
    return activity.fragmentListenerHolder[callbackKey] ?: run {
        frLogW("Not found fragment listener from holders.")
        return null
    }
}