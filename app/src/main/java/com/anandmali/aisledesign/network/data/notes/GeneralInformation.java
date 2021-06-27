package com.anandmali.aisledesign.network.data.notes;

import com.google.gson.annotations.SerializedName;

public class GeneralInformation {

    @SerializedName("first_name")
    private String firstName;

    private int age;

    public String getFirstName() {
        return firstName;
    }

    public int getAge() {
        return age;
    }
}
