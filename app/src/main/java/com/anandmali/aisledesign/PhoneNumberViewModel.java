package com.anandmali.aisledesign;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PhoneNumberViewModel extends ViewModel {

    @Inject
    public PhoneNumberViewModel() {
    }

    public String getSommeValue() {
        return "some value";
    }

}
