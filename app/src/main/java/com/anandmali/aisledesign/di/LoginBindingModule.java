package com.anandmali.aisledesign.di;

import com.anandmali.aisledesign.ui.viewmodel.LoginBinding;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityRetainedComponent;
import dagger.hilt.android.scopes.ActivityRetainedScoped;

@Module
@InstallIn(ActivityRetainedComponent.class)
public class LoginBindingModule {

    @ActivityRetainedScoped
    @Provides
    public LoginBinding provideLoginBinding() {
        return new LoginBinding();
    }

}
