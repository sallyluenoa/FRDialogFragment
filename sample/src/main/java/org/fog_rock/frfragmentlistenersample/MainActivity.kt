package org.fog_rock.frfragmentlistenersample

import android.os.Bundle
import org.fog_rock.frextensions.androidx.log.logI
import org.fog_rock.frfragmentlistener.activity.FRAppCompatActivity
import org.fog_rock.frfragmentlistener.dialog.FRDialogFragment
import org.fog_rock.frfragmentlistenersample.databinding.ActivityMainBinding

class MainActivity : FRAppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val callbackKey = registerForDialogResult {
        logI("callback result: $it")
        binding.textViewResult.text = "Callback Result: $it"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonDialog.setOnClickListener {
            FRDialogFragment.Builder(this).apply {
                setTitle(R.string.title)
                setMessage(R.string.message)
                setPositiveButton(R.string.ok)
                setNegativeButton(R.string.cancel)
                setCancelable(true)
                setCallbackKey(callbackKey)
            }.create().show(supportFragmentManager, null)
        }
    }
}