package org.fog_rock.frfragmentlistenersample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.fog_rock.frextensions.androidx.content.startActivity
import org.fog_rock.frfragmentlistenersample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSample.setOnClickListener {
            startActivity<SampleActivity>()
        }
        binding.buttonDialogSample.setOnClickListener {
            startActivity<DialogSampleActivity>()
        }
        binding.buttonFragmentSample.setOnClickListener {
            startActivity<FragmentSampleActivity>()
        }
    }
}