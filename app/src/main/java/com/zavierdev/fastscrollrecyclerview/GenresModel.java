package com.zavierdev.fastscrollrecyclerview;

import android.os.Parcel;
import android.os.Parcelable;

public class GenresModel implements Parcelable {
    public String title;
    public String endpoint;

    public GenresModel() {}

    protected GenresModel(Parcel in) {
        title = in.readString();
        endpoint = in.readString();
    }

    public static final Creator<GenresModel> CREATOR = new Creator<GenresModel>() {
        @Override
        public GenresModel createFromParcel(Parcel in) {
            return new GenresModel(in);
        }

        @Override
        public GenresModel[] newArray(int size) {
            return new GenresModel[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(endpoint);
    }
}
