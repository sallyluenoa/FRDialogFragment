package org.fog_rock.frfragmentlistenersample

import android.os.Bundle
import org.fog_rock.frfragmentlistener.activity.FRAppCompatActivity
import org.fog_rock.frfragmentlistener.dialog.FRDialogFragment
import org.fog_rock.frfragmentlistenersample.databinding.ActivityDialogSampleBinding

class DialogSampleActivity : FRAppCompatActivity() {

    private lateinit var binding: ActivityDialogSampleBinding

    private val callback1Key = registerForDialogResult{
        binding.textViewResult.text = "Callback1 Result: $it"
    }

    private val callback2Key = registerForDialogResult{
        binding.textViewResult.text = "Callback2 Result: $it"
    }

    private val title: String? get() {
        val binding = this.binding.contentViewSetting.layoutItemTitle
        return if (binding.switchCompat.isChecked) binding.editText.text.toString() else null
    }

    private val message: String? get() {
        val binding = this.binding.contentViewSetting.layoutItemMessage
        return if (binding.switchCompat.isChecked) binding.editText.text.toString() else null
    }

    private val positive: String? get() {
        val binding = this.binding.contentViewSetting.layoutItemPositive
        return if (binding.switchCompat.isChecked) binding.editText.text.toString() else null
    }

    private val negative: String? get() {
        val binding = this.binding.contentViewSetting.layoutItemNegative
        return if (binding.switchCompat.isChecked) binding.editText.text.toString() else null
    }

    private val neutral: String? get() {
        val binding = this.binding.contentViewSetting.layoutItemNeutral
        return if (binding.switchCompat.isChecked) binding.editText.text.toString() else null
    }

    private val cancelable: Boolean? get() {
        val binding = this.binding.contentViewSetting.layoutItemCancelable
        return when (if (binding.switchCompat.isChecked) binding.spinner.selectedItem else null) {
            getString(R.string.cancelable) -> true
            getString(R.string.non_cancelable) -> false
            else -> null
        }
    }

    private val callbackKey: String? get() {
        val binding = this.binding.contentViewSetting.layoutItemCallback
        return when (if (binding.switchCompat.isChecked) binding.spinner.selectedItem else null) {
            getString(R.string.callback_1) -> callback1Key
            getString(R.string.callback_2) -> callback2Key
            else -> null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDialogSampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonDialog.setOnClickListener {
            binding.textViewResult.setText(R.string.result)
            showDialog()
        }
    }

    private fun showDialog() {
        FRDialogFragment.Builder(this).apply {
            title?.let { if (it.isNotEmpty()) setTitle(it) else setTitle(R.string.title) }
            message?.let { if (it.isNotEmpty()) setMessage(it) else setMessage(R.string.message) }
            positive?.let { if (it.isNotEmpty()) setPositiveButton(it) else setPositiveButton(R.string.positive) }
            negative?.let { if (it.isNotEmpty()) setNegativeButton(it) else setNegativeButton(R.string.negative) }
            neutral?.let { if (it.isNotEmpty()) setNeutralButton(it) else setNeutralButton(R.string.neutral) }
            cancelable?.let { setCancelable(it) }
            callbackKey?.let { setCallbackKey(it) }
        }.create()
            .show(supportFragmentManager, null)
    }

}