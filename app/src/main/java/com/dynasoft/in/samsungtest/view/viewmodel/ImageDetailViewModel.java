package com.dynasoft.in.samsungtest.view.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.dynasoft.in.samsungtest.view.model.ImageRepository;
import com.dynasoft.in.samsungtest.view.model.ImagesFavoriteModel;

import java.util.List;

public class ImageDetailViewModel extends AndroidViewModel {

    public static final String TAG = ImageDetailViewModel.class.getSimpleName();

    private ImageRepository imageRepository;
    private List<ImagesFavoriteModel> allFavoriteImages;

    public ImageDetailViewModel(@NonNull Application application) {
        super(application);

        imageRepository = new ImageRepository(application);
        allFavoriteImages = imageRepository.getAllFavoriteImages();

    }

    public List<ImagesFavoriteModel> getAllFavoriteImagesFromRepo() {
        return allFavoriteImages;
    }

    public void insertFavorite(ImagesFavoriteModel imagesFavoriteModel) {
        imageRepository.insertFavoriteImages(imagesFavoriteModel);

    }

    public void updateFavorite(ImagesFavoriteModel imagesFavoriteModel) {
        imageRepository.insertFavoriteImages(imagesFavoriteModel);

    }

}
