package com.anandmali.aisledesign.ui.view.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.anandmali.aisledesign.R;
import com.anandmali.aisledesign.utils.Utils;
import com.anandmali.aisledesign.databinding.FragmentPhoneNumberBinding;
import com.anandmali.aisledesign.network.NetworkState;
import com.anandmali.aisledesign.ui.viewmodel.LoginBinding;
import com.anandmali.aisledesign.ui.viewmodel.PhoneNumberViewModel;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PhoneNumberFragment extends Fragment {

    PhoneNumberViewModel viewModel;
    private FragmentPhoneNumberBinding binding;

    @Inject
    public LoginBinding loginBinding;

    @Inject
    public Utils utils;

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
        binding.setModel(viewModel.getLoginBinding());

        viewModel.getStatus().observe(getViewLifecycleOwner(), this::observeStatus);
    }

    private void observeStatus(NetworkState<Boolean> networkState) {
        //noinspection ConstantConditions
        utils.hideKeyboardFrom(getActivity(), getActivity().getWindow().getDecorView());
        loginBinding.setLoading(false);
        loginBinding.setSubmitted(false);
        switch (networkState.getStatus()) {
            case SUCCESS:
                navigateToOtpScreen();
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

    private void navigateToOtpScreen() {
        String number = loginBinding.getPhoneNumber();
        Bundle args = new Bundle();
        args.putString("phoneNumber", number);
        NavHostFragment.findNavController(this).navigate(R.id.startOtpFragment, args);
    }

}
