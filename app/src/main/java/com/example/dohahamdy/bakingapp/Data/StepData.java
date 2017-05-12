package com.example.dohahamdy.bakingapp.Data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by DOHA HAMDY on 5/7/2017.
 */

public class StepData implements Parcelable {

    String id;
    String shortDescription;
    String description;
    String videoURL;
    String thumbnailURL;

    public StepData(){}
    protected StepData(Parcel parcel){
        id=parcel.readString();
        shortDescription=parcel.readString();
        description=parcel.readString();
        videoURL=parcel.readString();
        thumbnailURL=parcel.readString();
    }
    public static final Creator<StepData>CREATOR=new Creator<StepData>() {
        @Override
        public StepData createFromParcel(Parcel source) {
            return new StepData(source);
        }

        @Override
        public StepData[] newArray(int size) {
            return new StepData[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(shortDescription);
        dest.writeString(description);
        dest.writeString(videoURL);
        dest.writeString(thumbnailURL);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

}

