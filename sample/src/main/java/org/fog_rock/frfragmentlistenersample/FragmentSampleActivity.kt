package org.fog_rock.frfragmentlistenersample

import android.os.Bundle
import org.fog_rock.frfragmentlistener.activity.FRAppCompatActivity
import org.fog_rock.frfragmentlistenersample.databinding.ActivityFragmentSampleBinding

class FragmentSampleActivity : FRAppCompatActivity() {

    private lateinit var binding: ActivityFragmentSampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFragmentSampleBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}