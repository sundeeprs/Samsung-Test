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
    private LiveData<List<ImagesFavoriteModel>> allFavoriteImages;
    private List<ImagesFavoriteModel> favoriteImages;

    public ImageDetailViewModel(@NonNull Application application) {
        super(application);

        imageRepository = new ImageRepository(application);
        allFavoriteImages = imageRepository.getAllFavoriteImages();
        favoriteImages = imageRepository.getFavoriteImages();

    }

    public LiveData<List<ImagesFavoriteModel>> getAllFavoriteImagesFromRepo() {
        return allFavoriteImages;
    }

    public List<ImagesFavoriteModel> getFavoriteImagesFromRepo() {
        return favoriteImages;
    }

    public void insertFavorite(ImagesFavoriteModel imagesFavoriteModel) {
        imageRepository.insertFavoriteImages(imagesFavoriteModel);

    }

    public void updateFavorite(ImagesFavoriteModel imagesFavoriteModel) {
        imageRepository.insertFavoriteImages(imagesFavoriteModel);

    }

    public void deleteAllFavorite() {
        imageRepository.deleteAllFavoriteImage();

    }

    public void delete(ImagesFavoriteModel imagesFavoriteModel) {
        imageRepository.deleteFavoriteImage(imagesFavoriteModel);

    }

}
