package org.fog_rock.frfragmentlistenersample.test;

import android.os.Bundle;

import org.fog_rock.frfragmentlistener.activity.FRAppCompatActivity;
import org.fog_rock.frfragmentlistener.dialog.FRDialogFragment;
import org.fog_rock.frfragmentlistenersample.R;
import org.fog_rock.frfragmentlistenersample.databinding.ActivityDialogFragmentTestBinding;
import org.fog_rock.frfragmentlistenersample.databinding.LayoutItemSwitchEdittextBinding;
import org.fog_rock.frfragmentlistenersample.databinding.LayoutItemSwitchSpinnerBinding;

import java.util.Objects;

import androidx.annotation.Nullable;

/**
 * This is a Java sample code for android test.
 */
public class DialogFragmentTestJavaActivity extends FRAppCompatActivity {

    private ActivityDialogFragmentTestBinding binding;

    private final String callback1Key = registerForDialogResult(witch -> {
        binding.textViewResult.setText(
                getString(R.string.dialog_test_textview_result, getString(R.string.callback_1), witch));
    });

    private final String callback2Key = registerForDialogResult(witch -> {
        binding.textViewResult.setText(
                getString(R.string.dialog_test_textview_result, getString(R.string.callback_2), witch));
    });

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDialogFragmentTestBinding.inflate(getLayoutInflater());

        binding.buttonDialog.setOnClickListener(v -> {
            binding.textViewResult.setText(R.string.result);
            showDialog();
        });
    }

    private void showDialog() {
        FRDialogFragment.Builder builder = new FRDialogFragment.Builder(this);
        String title = title();
        if (title != null) {
            if (!title.isEmpty()) builder.setTitle(title);
            else builder.setTitle(R.string.title);
        }
        String message = message();
        if (message != null) {
            if (!message.isEmpty()) builder.setMessage(message);
            else builder.setMessage(R.string.message);
        }
        String positive = positive();
        if (positive != null) {
            if (!positive.isEmpty()) builder.setPositiveButton(positive);
            else builder.setPositiveButton(R.string.positive);
        }
        String negative = negative();
        if (negative != null) {
            if (!negative.isEmpty()) builder.setNegativeButton(negative);
            else builder.setNegativeButton(R.string.negative);
        }
        String neutral = neutral();
        if (neutral != null) {
            if (!neutral.isEmpty()) builder.setNeutralButton(neutral);
            else builder.setNeutralButton(R.string.neutral);
        }
        Boolean cancelable = cancelable();
        if (cancelable != null) {
            builder.setCancelable(cancelable);
        }
        String callbackKey = callbackKey();
        if (callbackKey != null) {
            builder.setCallbackKey(callbackKey);
        }
        builder.show();
    }

    @Nullable
    private String title() {
        LayoutItemSwitchEdittextBinding binding = this.binding.contentViewSetting.layoutItemTitle;
        return (binding.switchCompat.isChecked()
                ? Objects.requireNonNull(binding.editText.getText()).toString()
                : null);
    }

    @Nullable
    private String message() {
        LayoutItemSwitchEdittextBinding binding = this.binding.contentViewSetting.layoutItemMessage;
        return (binding.switchCompat.isChecked()
                ? Objects.requireNonNull(binding.editText.getText()).toString()
                : null);
    }

    @Nullable
    private String positive() {
        LayoutItemSwitchEdittextBinding binding = this.binding.contentViewSetting.layoutItemPositive;
        return (binding.switchCompat.isChecked()
                ? Objects.requireNonNull(binding.editText.getText()).toString()
                : null);
    }

    @Nullable
    private String negative() {
        LayoutItemSwitchEdittextBinding binding = this.binding.contentViewSetting.layoutItemNegative;
        return (binding.switchCompat.isChecked()
                ? Objects.requireNonNull(binding.editText.getText()).toString()
                : null);
    }

    @Nullable
    private String neutral() {
        LayoutItemSwitchEdittextBinding binding = this.binding.contentViewSetting.layoutItemNeutral;
        return (binding.switchCompat.isChecked()
                ? Objects.requireNonNull(binding.editText.getText()).toString()
                : null);
    }

    @Nullable
    private Boolean cancelable() {
        LayoutItemSwitchSpinnerBinding binding = this.binding.contentViewSetting.layoutItemCancelable;
        String item = (binding.switchCompat.isChecked()
                ? binding.spinner.getSelectedItem().toString()
                : null);
        if (getString(R.string.cancelable).equals(item)) return true;
        if (getString(R.string.non_cancelable).equals(item)) return false;
        return null;
    }

    @Nullable
    private String callbackKey() {
        LayoutItemSwitchSpinnerBinding binding = this.binding.contentViewSetting.layoutItemCallback;
        String item = (binding.switchCompat.isChecked()
                ? binding.spinner.getSelectedItem().toString()
                : null);
        if (getString(R.string.callback_1).equals(item)) return callback1Key;
        if (getString(R.string.callback_2).equals(item)) return callback2Key;
        return null;
    }
}