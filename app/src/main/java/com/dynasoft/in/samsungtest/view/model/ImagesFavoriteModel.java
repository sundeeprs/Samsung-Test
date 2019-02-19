package com.dynasoft.in.samsungtest.view.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity(tableName = "favorite_image_table")
public class ImagesFavoriteModel implements Parcelable {

    @PrimaryKey
    @NonNull
    private Integer imageId;
    private String mFavoriteTitle;
    private String mFavoriteThumbNail;

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getFavoriteTitle() {
        return mFavoriteTitle;
    }

    public void setFavoriteTitle(String favoriteTitle) {
        this.mFavoriteTitle = favoriteTitle;
    }


    public String getFavoriteThumbNail() {
        return mFavoriteThumbNail;
    }

    public void setmFavoriteThumbNail(String favoriteThumbNail) {
        this.mFavoriteThumbNail = favoriteThumbNail;
    }


    public ImagesFavoriteModel(Integer imageId, String favoriteTitle, String favoriteThumbNail) {
        this.imageId = imageId;
        this.mFavoriteTitle = favoriteTitle;
        this.mFavoriteThumbNail = favoriteThumbNail;
    }


    protected ImagesFavoriteModel(Parcel in) {
        imageId = in.readInt();
        mFavoriteTitle = in.readString();
        mFavoriteThumbNail = in.readString();
    }

    public static final Creator<ImagesFavoriteModel> CREATOR = new Creator<ImagesFavoriteModel>() {
        @Override
        public ImagesFavoriteModel createFromParcel(Parcel in) {
            return new ImagesFavoriteModel(in);
        }

        @Override
        public ImagesFavoriteModel[] newArray(int size) {
            return new ImagesFavoriteModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(imageId);
        parcel.writeString(mFavoriteTitle);
        parcel.writeString(mFavoriteThumbNail);
    }
}
