package com.anandmali.aisledesign.network;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CookieInterceptor implements Interceptor {

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        Request request = chain.request();
        Request.Builder builder = request.newBuilder();

        //TODO remove hard coded values for token, use shared pref
        if (request.header("authentication_required") != null) {
            builder.addHeader("Authorization", "32c7794d2e6a1f7316ef35aa1eb34541");
            builder.addHeader("Cookie", "__cfduid=df9b865983bd04a5de2cf5017994bbbc71618565720");
        }

        request = builder.build();

        return chain.proceed(request);
    }
}
