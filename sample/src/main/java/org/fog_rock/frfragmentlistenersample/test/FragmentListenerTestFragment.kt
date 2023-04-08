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