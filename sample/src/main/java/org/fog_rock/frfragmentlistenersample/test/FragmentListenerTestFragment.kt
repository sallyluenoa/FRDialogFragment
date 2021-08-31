package org.fog_rock.frfragmentlistenersample.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import org.fog_rock.frfragmentlistener.fragment.FRFragmentListener
import org.fog_rock.frfragmentlistener.fragment.restoreFragmentEventListener
import org.fog_rock.frfragmentlistenersample.databinding.FragmentFragmentListenerTestBinding

class FragmentListenerTestFragment : Fragment() {

    interface Listener: FRFragmentListener {

        fun onClickedButton()

        fun onSelectedSpinnerItem(item: String)
    }

    companion object {
        fun newInstance(fragmentListenerKey: String): FragmentListenerTestFragment =
            FragmentListenerTestFragment().apply {
                arguments = bundleOf(
                    FRFragmentListener.ARGS_LISTENER_KEY to fragmentListenerKey
                )
            }
    }

    private var _binding: FragmentFragmentListenerTestBinding? = null
    private val binding get() = _binding!!

    private val listener: Listener? by lazy { restoreFragmentEventListener() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFragmentListenerTestBinding.inflate(inflater, container, false)

        binding.button.setOnClickListener { listener?.onClickedButton() }

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                listener?.onSelectedSpinnerItem(parent?.getItemAtPosition(position).toString())
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        return binding.root
    }
}