package com.anandmali.aisledesign.network;

import com.anandmali.aisledesign.network.data.LoginData;
import com.anandmali.aisledesign.network.data.PhoneNumberData;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;


public class LoginRepository {

    private final ApiServices apiServices;

    @Inject
    public LoginRepository(ApiServices apiServices) {
        this.apiServices = apiServices;
    }

    public Single<LoginData> doLogin(PhoneNumberData phoneNumberData) {
        return apiServices.phoneNumberLogin(phoneNumberData);
    }

}
