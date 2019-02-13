package com.dynasoft.in.samsungtest.view.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.dynasoft.in.samsungtest.view.model.ImageModel;
import com.dynasoft.in.samsungtest.view.model.ImageRepository;
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

    private ImageRepository imageRepository;
    private LiveData<List<ImageModel>> allImages;
    private Retrofit mRetrofit;

    public ImageViewModel(@NonNull Application application) {
        super(application);

        //CallRetrofit
        callRetrofit();


        imageRepository = new ImageRepository(application);
         allImages = imageRepository.getAllImages();


    }

   public LiveData<List<ImageModel>> getAllImagesFromRepo() {
        return allImages;
    }

    public void insertFavrite(ImageModel imageModel) {
       // imageRepository.insertImages(imageModel);

    }



    private void callRetrofit() {

        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ImageAPIInterface apiInterface = mRetrofit.create(ImageAPIInterface.class);
        Call<List<ImageModel>> call = apiInterface.getAllImages();

        call.enqueue(new Callback<List<ImageModel>>() {
            @Override
            public void onResponse(Call<List<ImageModel>> call, Response<List<ImageModel>> response) {

                if(!response.isSuccessful()) {
                    return;
                }

                imageRepository.deleteAllImage();
                List<ImageModel> imageModelList = response.body();
                for(ImageModel imageModel : imageModelList){
                    imageRepository.insertImages(imageModel);
                }
            }

            @Override
            public void onFailure(Call<List<ImageModel>> call, Throwable t) {
            }
        });
    }
}
