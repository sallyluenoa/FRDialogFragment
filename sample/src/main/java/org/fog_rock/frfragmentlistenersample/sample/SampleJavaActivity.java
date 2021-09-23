package org.fog_rock.frfragmentlistenersample.sample;

import android.os.Bundle;
import android.util.Log;

import org.fog_rock.frfragmentlistener.activity.FRAppCompatActivity;
import org.fog_rock.frfragmentlistener.dialog.FRDialogFragment;
import org.fog_rock.frfragmentlistenersample.R;
import org.fog_rock.frfragmentlistenersample.databinding.ActivitySampleBinding;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

/**
 * This is a Java sample code for API documents.
 */
public class SampleJavaActivity extends FRAppCompatActivity {

    private ActivitySampleBinding binding = null;

    /**
     * Register a dialog callback and keep a return value
     * as a private field in the subclass of `FRAppCompatActivity`.
     */
    private final String dialogCallbackKey = registerForDialogResult(which -> {
        // Write your result code here!
    });

    /**
     * Register a fragment listener and keep a return value
     * as a private field in the subclass of `FRAppCompatActivity`.
     */
    private final String fragmentListenerKey = registerForFragmentListener(new SampleJavaFragment.Listener() {
        @Override
        public void onClickedYesButton() {
            // Write your result code here!
        }
        @Override
        public void onClickedNoButton() {
            // Write your result code here!
        }
    });

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySampleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonDialog.setOnClickListener(v -> showDialog());

        replaceFragment(SampleJavaFragment.newInstance(fragmentListenerKey), R.id.fragment_container);
    }

    private void showDialog() {
        /**
         * Create and show a dialog fragment.
         */
        FRDialogFragment.Builder builder = new FRDialogFragment.Builder(this)
                .setTitle(R.string.title)
                .setMessage(R.string.message)
                .setPositiveButton(R.string.ok)
                .setNegativeButton(R.string.cancel)
                .setCallbackKey(dialogCallbackKey);
        builder.show();
    }

    private void replaceFragment(@NonNull Fragment fragment, @IdRes int resId) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(resId, fragment);
        transaction.commit();
    }
}
