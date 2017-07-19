package com.nowenui.systemtweaker.fragments;

import android.os.Parcel;
import android.os.Parcelable;

public class Wifi implements Parcelable {
    public static final Parcelable.Creator<Wifi> CREATOR = new Parcelable.Creator<Wifi>() {

        @Override
        public Wifi createFromParcel(Parcel source) {
            Wifi wifimsg = new Wifi();
            wifimsg.wifiName = source.readString();
            wifimsg.wifiCode = source.readString();
            return wifimsg;
        }

        @Override
        public Wifi[] newArray(int size) {
            return new Wifi[size];
        }
    };
    public String wifiName;
    public String wifiCode;

    public Wifi() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(wifiName);
        dest.writeString(wifiCode);
    }
}