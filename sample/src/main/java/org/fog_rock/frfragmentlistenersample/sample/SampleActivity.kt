package org.fog_rock.frfragmentlistenersample.sample

import android.os.Bundle
import org.fog_rock.frextensions.androidx.fragment.replaceFragment
import org.fog_rock.frfragmentlistener.activity.FRAppCompatActivity
import org.fog_rock.frfragmentlistener.dialog.FRDialogFragment
import org.fog_rock.frfragmentlistenersample.R
import org.fog_rock.frfragmentlistenersample.databinding.ActivitySampleBinding

/**
 * This is a Kotlin sample code for API documents.
 */
class SampleActivity : FRAppCompatActivity() {

    private lateinit var binding: ActivitySampleBinding

    /**
     * Register a dialog callback and keep a return value
     * as a private field in the subclass of `FRAppCompatActivity`.
     */
    private val dialogCallbackKey = registerForDialogResult {
        // Write your result code here!
    }

    /**
     * Register a fragment listener and keep a return value
     * as a private field in the subclass of `FRAppCompatActivity`.
     */
    private val fragmentListenerKey = registerForFragmentListener(object : SampleFragment.Listener {
        override fun onClickedYesButton() {
            // Write your result code here!
        }
        override fun onClickedNoButton() {
            // Write your result code here!
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonDialog.setOnClickListener { showDialog() }

        replaceFragment(SampleFragment.newInstance(fragmentListenerKey), R.id.fragment_container)
    }

    private fun showDialog() {
        /**
         * Create and show a dialog fragment.
         */
        FRDialogFragment.Builder(this).apply {
            setTitle(R.string.title)
            setMessage(R.string.message)
            setPositiveButton(R.string.ok)
            setNegativeButton(R.string.cancel)
            setCallbackKey(dialogCallbackKey)
        }.show()
    }
}