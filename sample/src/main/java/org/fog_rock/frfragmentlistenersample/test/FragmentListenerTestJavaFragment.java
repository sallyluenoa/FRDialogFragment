package org.fog_rock.frfragmentlistenersample.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import org.fog_rock.frfragmentlistener.fragment.FRFragmentListener;
import org.fog_rock.frfragmentlistener.fragment.FRFragmentSupporter;
import org.fog_rock.frfragmentlistenersample.databinding.FragmentFragmentListenerTestBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * This is a Java sample code for android test.
 */
public class FragmentListenerTestJavaFragment extends Fragment {

    interface Listener extends FRFragmentListener {

        void onClickedButton();

        void onSelectedSpinnerItem(String item);
    }

    @NonNull
    public static FragmentListenerTestJavaFragment newInstance(@NonNull String fragmentListenerKey) {
        Bundle args = new Bundle();
        args.putString(FRFragmentListener.ARGS_LISTENER_KEY, fragmentListenerKey);
        FragmentListenerTestJavaFragment fragment = new FragmentListenerTestJavaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        final FragmentFragmentListenerTestBinding binding = FragmentFragmentListenerTestBinding.inflate(inflater, container, false);
        final Listener listener = restoreFragmentEventListener();

        binding.button.setOnClickListener(v -> {
            if (listener != null) listener.onClickedButton();
        });

        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (listener != null)
                    listener.onSelectedSpinnerItem(parent.getItemAtPosition(position).toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        return binding.getRoot();
    }

    @Nullable
    private Listener restoreFragmentEventListener() {
        final FRFragmentSupporter<Listener> supporter = new FRFragmentSupporter<>(Listener.class);
        return supporter.restoreFragmentEventListener(this);
    }

}