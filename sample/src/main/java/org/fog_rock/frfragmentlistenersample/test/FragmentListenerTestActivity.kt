package org.fog_rock.frfragmentlistenersample.test

import android.os.Bundle
import org.fog_rock.frextensions.androidx.fragment.replaceFragment
import org.fog_rock.frfragmentlistener.activity.FRAppCompatActivity
import org.fog_rock.frfragmentlistenersample.R
import org.fog_rock.frfragmentlistenersample.databinding.ActivityFragmentListenerTestBinding

/**
 * This is a sample code for android test.
 */
class FragmentListenerTestActivity : FRAppCompatActivity() {

    private lateinit var binding: ActivityFragmentListenerTestBinding

    private val fragmentListenerKey = registerForFragmentListener(
        object : FragmentListenerTestFragment.Listener {
            override fun onClickedButton() {
                binding.textViewResult.text = "Button clicked."
            }
            override fun onSelectedSpinnerItem(item: String) {
                binding.textViewResult.text = "Item selected: $item"
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFragmentListenerTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(
            FragmentListenerTestFragment.newInstance(fragmentListenerKey),
            R.id.fragment_container
        )
    }

    /**
     * This method uses tests for `registerForFragmentListener`.
     * It would be called from androidTest.
     */
    fun testOfRegisterForFragmentListener() {
        val listenerKey = registerForFragmentListener(
            object : FragmentListenerTestFragment.Listener {
                override fun onClickedButton() {}
                override fun onSelectedSpinnerItem(item: String) {}
            }
        )
    }
}