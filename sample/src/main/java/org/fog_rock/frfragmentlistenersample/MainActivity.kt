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