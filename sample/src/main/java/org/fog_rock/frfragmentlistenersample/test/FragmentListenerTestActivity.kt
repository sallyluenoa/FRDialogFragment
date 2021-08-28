package org.fog_rock.frfragmentlistenersample.test

import android.os.Bundle
import org.fog_rock.frfragmentlistener.activity.FRAppCompatActivity
import org.fog_rock.frfragmentlistenersample.databinding.ActivityFragmentListenerTestBinding

/**
 * This is a sample code for android test.
 */
class FragmentListenerTestActivity : FRAppCompatActivity() {

    private lateinit var binding: ActivityFragmentListenerTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFragmentListenerTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}