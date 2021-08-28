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