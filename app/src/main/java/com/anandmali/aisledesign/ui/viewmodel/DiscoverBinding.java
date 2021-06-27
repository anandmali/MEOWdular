package com.anandmali.aisledesign.ui.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.anandmali.aisledesign.BR;
import com.anandmali.aisledesign.network.data.notes.TestProfileListData;

import javax.inject.Inject;

import dagger.hilt.android.scopes.ActivityRetainedScoped;

@ActivityRetainedScoped
public class DiscoverBinding extends BaseObservable {

    private boolean isLoading;
    private String bannerPhotoUrl;
    private String bannerName;

    @Inject
    public DiscoverBinding() {
    }

    @Bindable
    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        this.isLoading = loading;
        notifyPropertyChanged(BR.loading);
    }

    @Bindable
    public String getBannerName() {
        return bannerName;
    }

    public void setBannerName(String bannerName) {
        this.bannerName = bannerName;
        notifyPropertyChanged(BR.bannerName);
    }

    @Bindable
    public String getBannerPhotoUrl() {
        return bannerPhotoUrl;
    }

    public void setBannerPhotoUrl(String bannerPhotoUrl) {
        this.bannerPhotoUrl = bannerPhotoUrl;
        notifyPropertyChanged(BR.bannerPhotoUrl);
    }

    public void setViewBindings(TestProfileListData profileListData) {
        String firstName = profileListData.getInvites().getProfiles().get(0).getGeneralInformation().getFirstName();
        int age = profileListData.getInvites().getProfiles().get(0).getGeneralInformation().getAge();
        String name = firstName + ", " + age;
        setBannerName(name);

        String url = profileListData.getInvites().getProfiles().get(0).getPhotos().get(0).getPhoto();
        setBannerPhotoUrl(url);
    }

}
