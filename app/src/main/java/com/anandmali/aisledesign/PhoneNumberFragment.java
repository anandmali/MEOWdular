package com.anandmali.aisledesign;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.anandmali.aisledesign.databinding.FragmentPhoneNumberBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PhoneNumberFragment extends Fragment {

    PhoneNumberViewModel viewModel;

    private FragmentPhoneNumberBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPhoneNumberBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setLifecycleOwner(this);
        viewModel = new ViewModelProvider(this).get(PhoneNumberViewModel.class);
        binding.setViewModel(viewModel);

        viewModel.getStatus().observe(getViewLifecycleOwner(), this::observeStatus);
    }

    private void observeStatus(NetworkState<Boolean> networkState) {
        switch (networkState.getStatus()) {
            case SUCCESS:
                Log.e("Success", networkState.getResponse().toString());
                break;
            case ERROR:
                Log.e("Error", networkState.getErrorMessage());
                break;
            default:
                break;
        }
    }
}
