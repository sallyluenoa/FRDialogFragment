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

package org.fog_rock.frfragmentlistenersample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.fog_rock.frextensions.androidx.content.startActivity
import org.fog_rock.frfragmentlistenersample.databinding.ActivityMainBinding
import org.fog_rock.frfragmentlistenersample.sample.SampleActivity
import org.fog_rock.frfragmentlistenersample.test.DialogFragmentTestActivity
import org.fog_rock.frfragmentlistenersample.test.FragmentListenerTestActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSample.setOnClickListener {
            startActivity<SampleActivity>()
        }
        binding.buttonDialogFragmentTest.setOnClickListener {
            startActivity<DialogFragmentTestActivity>()
        }
        binding.buttonFragmentListenerTest.setOnClickListener {
            startActivity<FragmentListenerTestActivity>()
        }
    }
}