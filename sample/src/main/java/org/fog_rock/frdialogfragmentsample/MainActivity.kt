package org.fog_rock.frdialogfragmentsample

import android.os.Bundle
import org.fog_rock.frdialogfragment.FRAppCompatActivity
import org.fog_rock.frdialogfragment.FRDialogFragment
import org.fog_rock.frdialogfragmentsample.databinding.ActivityMainBinding
import org.fog_rock.frextensions.androidx.log.logI

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