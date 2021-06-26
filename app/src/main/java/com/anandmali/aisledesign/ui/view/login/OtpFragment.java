package com.anandmali.aisledesign.ui.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.anandmali.aisledesign.Utils;
import com.anandmali.aisledesign.databinding.FragmentOtpBinding;
import com.anandmali.aisledesign.network.NetworkState;
import com.anandmali.aisledesign.network.SessionManager;
import com.anandmali.aisledesign.ui.view.notes.NotesActivity;
import com.anandmali.aisledesign.ui.viewmodel.LoginBinding;
import com.anandmali.aisledesign.ui.viewmodel.OtpViewModel;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OtpFragment extends Fragment {

    FragmentOtpBinding binding;
    OtpViewModel viewModel;

    @Inject
    public LoginBinding loginBinding;

    @Inject
    public Utils utils;

    @Inject
    SessionManager sessionManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentOtpBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(OtpViewModel.class);

        binding.setViewModel(viewModel);
        binding.setModel(loginBinding);

        Bundle args = getArguments();
        String phoneNumber = "+91 " + (args != null ? args.getString("phoneNumber") : "");
        loginBinding.setPhoneNumber(phoneNumber);

        viewModel.getStatus().observe(getViewLifecycleOwner(), this::observeStatus);

    }

    private void observeStatus(NetworkState<String> networkState) {
        //noinspection ConstantConditions
        utils.hideKeyboardFrom(getActivity(), getActivity().getWindow().getDecorView());
        loginBinding.setLoading(false);
        loginBinding.setSubmitted(false);
        switch (networkState.getStatus()) {
            case SUCCESS:
                navigateToOtpScreen(networkState.getResponse());
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

    private void navigateToOtpScreen(String token) {
        sessionManager.saveToken(token);
        sessionManager.getToken();

        Intent intent = new Intent(getActivity(), NotesActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

}
