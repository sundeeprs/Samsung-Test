package com.dynasoft.in.samsungtest.view.model;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

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

public class ImageRepository {

    public static final String TAG = ImageRepository.class.getSimpleName();

    private ImageFavoriteDao mImageFavoriteDao;
    private ImageDao imageDao;
    private LiveData<List<ImageModel>> allImages;
    private LiveData<List<ImagesFavoriteModel>> allFavoriteImages;
    private List<ImagesFavoriteModel> favoriteImages;
    private Retrofit mRetrofit;

    public ImageRepository(Application application) {

        //Call Retrofit
        callRetrofit();

        ImageDataBase imageDataBase = ImageDataBase.getInstance(application);
        imageDao = imageDataBase.imageDao();
        mImageFavoriteDao = imageDataBase.imageFavoriteDao();
        allImages = imageDao.getAllImages();
        allFavoriteImages = mImageFavoriteDao.getAllFavoriteImages();
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

                deleteAllImage();
                List<ImageModel> imageModelList = response.body();
                for(ImageModel imageModel : imageModelList){
                    insertImages(imageModel);
                }
            }

            @Override
            public void onFailure(Call<List<ImageModel>> call, Throwable t) {
                Log.i(TAG, "Error while getting data form backed");
            }
        });
    }

    public void insertImages(ImageModel imageModel) {
        new InsertImageToRepo(imageDao).execute(imageModel);

    }

    public void insertFavoriteImages(ImagesFavoriteModel imagesFavoriteModel) {
        new InsertFavoriteImageToRepo(mImageFavoriteDao).execute(imagesFavoriteModel);

    }

    public void updateFavoriteImages(ImagesFavoriteModel imagesFavoriteModel) {
        new UpdateFavoriteImageToRepo(mImageFavoriteDao).execute(imagesFavoriteModel);

    }

    public void deleteAllImage() {
        new DeleteAllImageFromRepo(imageDao).execute();
    }

    public void deleteAllFavoriteImage() {
        new DeleteAllFavoriteImageFromRepo(mImageFavoriteDao).execute();
    }


    public void deleteFavoriteImage(ImagesFavoriteModel imagesFavoriteModel) {
        new DeleteAFavoriteImageFromRepo(mImageFavoriteDao).execute(imagesFavoriteModel);
    }

    public LiveData<List<ImageModel>> getAllImages() {
        return allImages;
    }

    public LiveData<List<ImagesFavoriteModel>> getAllFavoriteImages() {
        return allFavoriteImages;
    }

    public List<ImagesFavoriteModel> getFavoriteImages() {
        return favoriteImages;
    }


    private static class InsertImageToRepo extends AsyncTask<ImageModel, Void , Void> {
        private ImageDao imageDao;

        private InsertImageToRepo(ImageDao imageDao) {
            this.imageDao = imageDao;

        }

        @Override
        protected Void doInBackground(ImageModel... imageModels) {
            imageDao.insert(imageModels[0]);
            return null;
        }
    }


    private static class InsertFavoriteImageToRepo extends AsyncTask<ImagesFavoriteModel, Void , Void> {
        private final ImageFavoriteDao mImageFavoriteDao;

        private InsertFavoriteImageToRepo(ImageFavoriteDao imageFavoriteDao) {
            this.mImageFavoriteDao = imageFavoriteDao;

        }

        @Override
        protected Void doInBackground(ImagesFavoriteModel... imagesFavoriteModels) {

            //mImageFavoriteDao.deleteAllFavoriteImages();
            for (ImagesFavoriteModel imageFavModel : imagesFavoriteModels){
                mImageFavoriteDao.insert(imageFavModel);
            }
            Log.i(TAG, "Image inserted to Favorite table.....");
            return null;
        }
    }

    private static class UpdateFavoriteImageToRepo extends AsyncTask<ImagesFavoriteModel, Void , Void> {
        private final ImageFavoriteDao mImageFavoriteDao;

        private UpdateFavoriteImageToRepo(ImageFavoriteDao imageFavoriteDao) {
            this.mImageFavoriteDao = imageFavoriteDao;

        }

        @Override
        protected Void doInBackground(ImagesFavoriteModel... imagesFavoriteModels) {

            mImageFavoriteDao.update(imagesFavoriteModels[0]);
            Log.i(TAG, "Image updated to Favorite table.....");
            return null;
        }
    }

    private static class DeleteAllImageFromRepo extends AsyncTask<Void, Void, Void> {
        private ImageDao imageDao;

        private DeleteAllImageFromRepo(ImageDao imageDao) {
            this.imageDao = imageDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            imageDao.deleteAll();
            return null;
        }
    }

    private static class DeleteAllFavoriteImageFromRepo extends AsyncTask<Void, Void, Void> {
        private ImageFavoriteDao imageFavoriteDao;

        private DeleteAllFavoriteImageFromRepo(ImageFavoriteDao imageFavoriteDao) {
            this.imageFavoriteDao = imageFavoriteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            imageFavoriteDao.deleteAllFavoriteImages();
            return null;
        }
    }

    private static class DeleteAFavoriteImageFromRepo extends AsyncTask<ImagesFavoriteModel, Void , Void> {
        private ImageFavoriteDao imageFavoriteDao;

        private DeleteAFavoriteImageFromRepo(ImageFavoriteDao imageFavoriteDao) {
            this.imageFavoriteDao = imageFavoriteDao;
        }

        @Override
        protected Void doInBackground(ImagesFavoriteModel... imagesFavoriteModels) {
            imageFavoriteDao.delete(imagesFavoriteModels[0]);
            return null;
        }
    }
}
