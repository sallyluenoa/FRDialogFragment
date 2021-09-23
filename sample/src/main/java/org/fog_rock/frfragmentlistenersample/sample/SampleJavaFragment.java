package org.fog_rock.frfragmentlistenersample.sample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.fog_rock.frfragmentlistener.fragment.FRFragmentListener;
import org.fog_rock.frfragmentlistener.fragment.FRFragmentSupporter;
import org.fog_rock.frfragmentlistenersample.databinding.FragmentSampleBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * This is a Kotlin sample code for API documents.
 */
public class SampleJavaFragment extends Fragment {

    /**
     * A sub-interface of FRFragmentListener that notifies the parent activity of fragment events.
     */
    interface Listener extends FRFragmentListener {

        void onClickedYesButton();

        void onClickedNoButton();
    }

    /**
     * Register the listener key to arguments
     * when generate a new instance of the fragment.
     * The key of the pair would be `FRFragmentListener.ARGS_LISTENER_KEY`
     * and its value would be the listener key.
     */
    @NonNull
    public static SampleJavaFragment newInstance(@NonNull String fragmentListenerKey) {
        Bundle args = new Bundle();
        args.putString(FRFragmentListener.ARGS_LISTENER_KEY, fragmentListenerKey);
        SampleJavaFragment fragment = new SampleJavaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        final FragmentSampleBinding binding = FragmentSampleBinding.inflate(inflater, container, false);
        final Listener listener = restoreFragmentEventListener();

        binding.buttonYes.setOnClickListener(v -> {
            if (listener != null) listener.onClickedYesButton();
        });
        binding.buttonNo.setOnClickListener(v -> {
            if (listener != null) listener.onClickedNoButton();
        });

        return binding.getRoot();
    }

    /**
     * Restore a fragment listener in the fragment.
     */
    @Nullable
    private Listener restoreFragmentEventListener() {
        final FRFragmentSupporter<Listener> supporter = new FRFragmentSupporter<>(Listener.class);
        return supporter.restoreFragmentEventListener(this);
    }
}