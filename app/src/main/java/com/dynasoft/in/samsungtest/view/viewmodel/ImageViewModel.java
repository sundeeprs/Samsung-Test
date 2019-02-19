package com.dynasoft.in.samsungtest.view.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.dynasoft.in.samsungtest.view.model.ImageModel;
import com.dynasoft.in.samsungtest.view.model.ImageRepository;
import com.dynasoft.in.samsungtest.view.model.ImagesFavoriteModel;
import com.dynasoft.in.samsungtest.view.service.ImageAPIInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sundeep on 2/12/2019.
 */

public class ImageViewModel extends AndroidViewModel {

    public static final String TAG = ImageViewModel.class.getSimpleName();

    private ImageRepository imageRepository;
    private LiveData<List<ImageModel>> allImages;


    public ImageViewModel(@NonNull Application application) {
        super(application);

        imageRepository = new ImageRepository(application);
        allImages = imageRepository.getAllImages();

    }

   public LiveData<List<ImageModel>> getAllImagesFromRepo() {
        return allImages;
    }

}
