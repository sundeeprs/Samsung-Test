package com.dynasoft.in.samsungtest.view.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.dynasoft.in.samsungtest.view.viewmodel.ImageViewModel;

import java.util.List;

/**
 * Created by sundeep on 2/12/2019.
 */

@Dao
public interface ImageDao {

    @Insert
    void insert(ImageModel imageModel);

    @Query("DELETE FROM image_table")
    void deleteAll();

    @Query("select * from image_table")
    LiveData<List<ImageModel>> getAllImages();

}
