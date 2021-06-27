package com.anandmali.aisledesign.ui.view.notes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.anandmali.aisledesign.databinding.FragmentDiscoverBinding;
import com.anandmali.aisledesign.network.NetworkState;
import com.anandmali.aisledesign.network.data.notes.LikesProfile;
import com.anandmali.aisledesign.network.data.notes.TestProfileListData;
import com.anandmali.aisledesign.ui.viewmodel.DiscoverBinding;
import com.anandmali.aisledesign.ui.viewmodel.DiscoverViewModel;
import com.anandmali.aisledesign.utils.GridSpacingItemDecoration;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class DiscoverFragment extends Fragment {

    private FragmentDiscoverBinding binding;

    @Inject
    DiscoverBinding discoverBinding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDiscoverBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DiscoverViewModel discoverViewModel = new ViewModelProvider(this).get(DiscoverViewModel.class);

        discoverViewModel.getStatus().observe(getViewLifecycleOwner(), this::observeStatus);

        binding.setModel(discoverBinding);

    }

    private void observeStatus(NetworkState<TestProfileListData> networkState) {
        discoverBinding.setLoading(false);
        switch (networkState.getStatus()) {
            case SUCCESS:
                setUpLikesProfile(networkState.getResponse());
                break;
            case ERROR:
                Snackbar.make(binding.getRoot(),
                        networkState.getErrorMessage(),
                        Snackbar.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }

    private void setUpLikesProfile(TestProfileListData response) {
        discoverBinding.setViewBindings(response);

        binding.rvLikesProfile.setHasFixedSize(true);
        binding.rvLikesProfile.addItemDecoration(new GridSpacingItemDecoration(2, 16, false));
        List<LikesProfile> list = response.getLikes().getProfiles();
        LikesProfileAdapter adapter = new LikesProfileAdapter(list);
        binding.rvLikesProfile.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
