package com.anandmali.aisledesign;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PhoneNumberFragment extends Fragment {

    PhoneNumberViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_phone_number, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(PhoneNumberViewModel.class);

        Log.e("Some value printed", viewModel.getSommeValue());

        Button button = view.findViewById(R.id.btn_continue);
        button.setOnClickListener(v -> NavHostFragment.findNavController(this).navigate(R.id.action_phoneNumberFragment_to_otpFragment));

    }
}
