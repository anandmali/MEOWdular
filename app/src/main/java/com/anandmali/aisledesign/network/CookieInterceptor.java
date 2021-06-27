package com.anandmali.aisledesign.network;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CookieInterceptor implements Interceptor {

    private final String COOKIE = "__cfduid=df9b865983bd04a5de2cf5017994bbbc71618565720";

    private final SessionManager sessionManager;

    public CookieInterceptor(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        String token = sessionManager.getToken();
        if (request.header("authentication_required") != null) {
            builder.addHeader("Authorization", token);
            builder.addHeader("Cookie", COOKIE);
        }

        request = builder.build();

        return chain.proceed(request);
    }

}
