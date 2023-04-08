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
 * This is a sample code for API documents.
 */
class SampleFragment : Fragment() {

    /**
     * A sub-interface of `FRFragmentListener` that notifies the parent activity of fragment events.
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