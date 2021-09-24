package org.fog_rock.frfragmentlistenersample.test;

import android.os.Bundle;

import org.fog_rock.frfragmentlistener.activity.FRAppCompatActivity;
import org.fog_rock.frfragmentlistenersample.R;
import org.fog_rock.frfragmentlistenersample.databinding.ActivityFragmentListenerTestBinding;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

/**
 * This is a Java sample code for android test.
 */
public class FragmentListenerTestJavaActivity extends FRAppCompatActivity {

    private ActivityFragmentListenerTestBinding binding;

    private final String fragmentListenerKey = registerForFragmentListener(
            new FragmentListenerTestFragment.Listener() {
                @Override
                public void onClickedButton() {
                    binding.textViewResult.setText(R.string.fragment_test_textview_clicked_button);
                }

                @Override
                public void onSelectedSpinnerItem(@NonNull String item) {
                    binding.textViewResult.setText(getString(R.string.fragment_test_textview_selected_item, item));
                }
            }
    );

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFragmentListenerTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(
                FragmentListenerTestJavaFragment.newInstance(fragmentListenerKey),
                R.id.fragment_container
        );
    }

    private void replaceFragment(@NonNull Fragment fragment, @IdRes int resId) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(resId, fragment);
        transaction.commit();
    }

    /**
     * This method uses tests for `registerForFragmentListener`.
     * It would be called from androidTest.
     */
    public void testOfRegisterForFragmentListener() {
        String listenerKey = registerForFragmentListener(
                new FragmentListenerTestFragment.Listener() {
                    @Override
                    public void onClickedButton() {}
                    @Override
                    public void onSelectedSpinnerItem(@NonNull String item) {}
                }
        );
    }
}