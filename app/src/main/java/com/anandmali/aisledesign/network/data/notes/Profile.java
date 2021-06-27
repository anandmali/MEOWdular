package com.anandmali.aisledesign.network.data.notes;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Profile{

    @SerializedName("general_information")
    private GeneralInformation generalInformation;

    private List<Photo> photos;

    public GeneralInformation getGeneralInformation() {
        return generalInformation;
    }

    public List<Photo> getPhotos() {
        return photos;
    }
}
