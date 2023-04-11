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

/**
 * A listener interface for fragment events.
 * @see org.fog_rock.frfragmentlistener.activity.FRAppCompatActivity.registerForFragmentListener
 * @see org.fog_rock.frfragmentlistener.fragment.restoreFragmentEventListener
 * @sample org.fog_rock.frfragmentlistenersample.sample.SampleFragment.Listener
 * @sample org.fog_rock.frfragmentlistenersample.sample.SampleActivity.fragmentListenerKey
 * @sample org.fog_rock.frfragmentlistenersample.sample.SampleFragment.newInstance
 * @sample org.fog_rock.frfragmentlistenersample.sample.SampleFragment.listener
 */
interface FRFragmentListener {

    companion object {
        /**
         * A key of the pair for registering a fragment listener key to arguments of fragment.
         * @sample org.fog_rock.frfragmentlistenersample.sample.SampleFragment.newInstance
         */
        const val ARGS_LISTENER_KEY = "listener_key"
    }
}