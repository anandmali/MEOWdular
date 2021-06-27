package com.anandmali.aisledesign.network;

import com.anandmali.aisledesign.network.data.LoginData;
import com.anandmali.aisledesign.network.data.PhoneNumberData;
import com.anandmali.aisledesign.network.data.TokenData;
import com.anandmali.aisledesign.network.data.VerifyOtpData;
import com.anandmali.aisledesign.network.data.notes.TestProfileListData;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiServices {

    @POST("users/phone_number_login")
    Single<LoginData> phoneNumberLogin(@Body PhoneNumberData phoneNumberData);

    @POST("users/verify_otp")
    Single<TokenData> verifyOtp(@Body VerifyOtpData verifyOtpData);

    @Headers("authentication_required: true")
    @GET("users/test_profile_list")
    Single<TestProfileListData> testProfileList();

}
