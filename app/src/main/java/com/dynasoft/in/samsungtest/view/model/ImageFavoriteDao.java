package com.dynasoft.in.samsungtest.view.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ImageFavoriteDao {

    @Insert
    void insert(ImagesFavoriteModel imagesFavoriteModel);

    @Update( onConflict = OnConflictStrategy.REPLACE)
    void update(ImagesFavoriteModel imagesFavoriteModel);

    @Query("select * from favorite_image_table")
    List<ImagesFavoriteModel> getAllFavoriteImages();
}
