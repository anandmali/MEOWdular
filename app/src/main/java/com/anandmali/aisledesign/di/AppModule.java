package com.anandmali.aisledesign.di;

import android.content.Context;
import android.content.SharedPreferences;

import com.anandmali.aisledesign.BuildConfig;
import com.anandmali.aisledesign.network.ApiServiceMock;
import com.anandmali.aisledesign.network.ApiServices;
import com.anandmali.aisledesign.network.CookieInterceptor;
import com.anandmali.aisledesign.network.SessionManager;
import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient(SessionManager sessionManager) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (BuildConfig.DEBUG) {
            return new OkHttpClient.Builder()
                    .addInterceptor(new CookieInterceptor(sessionManager))
                    .addInterceptor(httpLoggingInterceptor)
                    .addInterceptor(new OkHttpProfilerInterceptor())
                    .build();
        } else {
            return new OkHttpClient.Builder()
                    .addInterceptor(new CookieInterceptor(sessionManager))
                    .build();
        }
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        String baseUrl = "https://cats.mock.co/V1/"; //Just a dummy URL to mock network setup.
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build();
    }

    @Provides
    public ApiServices provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiServices.class);
    }

    @Provides
    public ApiServiceMock provideApiServiceMock() {
        return new ApiServiceMock();
    }

    @Singleton
    @Provides
    public SharedPreferences providePref(@ApplicationContext Context context) {
        String prefName = BuildConfig.APPLICATION_ID;
        return context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
    }

    @Singleton
    @Provides
    public SessionManager provideSessionManager(SharedPreferences sharedPreferences) {
        return new SessionManager(sharedPreferences);
    }

}
