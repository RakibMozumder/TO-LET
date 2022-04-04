package com.example.to_let.Model;

import android.widget.TextView;

public class SearchModel {
    private String locationHint;
    private String addressHint;

    public SearchModel(String locationHint, String addressHint) {
        this.locationHint = locationHint;
        this.addressHint = addressHint;
    }

    public String getLocationHint() {
        return locationHint;
    }

    public void setLocationHint(String locationHint) {
        this.locationHint = locationHint;
    }

    public String getAddressHint() {
        return addressHint;
    }

    public void setAddressHint(String addressHint) {
        this.addressHint = addressHint;
    }
}