package org.fog_rock.frfragmentlistenersample.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import org.fog_rock.frfragmentlistener.fragment.FRFragmentListener
import org.fog_rock.frfragmentlistener.fragment.restoreFragmentEventListener
import org.fog_rock.frfragmentlistenersample.databinding.FragmentSampleBinding

/**
 * This is a Kotlin sample code for API documents.
 */
class SampleFragment : Fragment() {

    /**
     * A sub-interface of FRFragmentListener that notifies the parent activity of fragment events.
     */
    interface Listener: FRFragmentListener {

        fun onClickedYesButton()

        fun onClickedNoButton()
    }

    companion object {

        fun newInstance(fragmentListenerKey: String): SampleFragment {
            /**
             * Register the listener key to arguments
             * when generate a new instance of the fragment.
             * The key of the pair would be `FRFragmentListener.ARGS_LISTENER_KEY`
             * and its value would be the listener key.
             */
            return SampleFragment().apply {
                arguments = bundleOf(
                    FRFragmentListener.ARGS_LISTENER_KEY to fragmentListenerKey
                )
            }
        }
    }

    private var _binding: FragmentSampleBinding? = null
    private val binding get() = _binding!!

    /**
     * Restore a fragment listener in the fragment.
     */
    private val listener: Listener? by lazy { restoreFragmentEventListener() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSampleBinding.inflate(inflater, container, false)

        binding.buttonYes.setOnClickListener { listener?.onClickedYesButton() }
        binding.buttonNo.setOnClickListener { listener?.onClickedNoButton() }

        return binding.root
    }
}