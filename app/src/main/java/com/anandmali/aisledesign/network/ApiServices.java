package com.anandmali.aisledesign.network;

import com.anandmali.aisledesign.network.data.LoginData;
import com.anandmali.aisledesign.network.data.PhoneNumberData;
import com.anandmali.aisledesign.network.data.TokenData;
import com.anandmali.aisledesign.network.data.VerifyOtpData;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiServices {

    @POST("users/phone_number_login")
    Single<LoginData> phoneNumberLogin(@Body PhoneNumberData phoneNumberData);

    @POST("users/verify_otp")
    Single<TokenData> verifyOtp(@Body VerifyOtpData verifyOtpData);

}
