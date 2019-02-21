package com.dynasoft.in.samsungtest.view.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ImageFavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ImagesFavoriteModel imagesFavoriteModel);

    @Update( onConflict = OnConflictStrategy.REPLACE)
    void update(ImagesFavoriteModel imagesFavoriteModel);

    @Delete
    void delete(ImagesFavoriteModel imagesFavoriteModel);

    @Query("select * from favorite_image_table ORDER BY imageId DESC")
    LiveData<List<ImagesFavoriteModel>> getAllFavoriteImages();

    @Query("delete from favorite_image_table")
    void deleteAllFavoriteImages();

}
