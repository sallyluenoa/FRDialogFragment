package org.fog_rock.frfragmentlistenersample

import android.os.Bundle
import org.fog_rock.frfragmentlistener.activity.FRAppCompatActivity
import org.fog_rock.frfragmentlistenersample.databinding.ActivityDialogSampleBinding

class DialogSampleActivity : FRAppCompatActivity() {

    private lateinit var binding: ActivityDialogSampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDialogSampleBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}