package com.dynasoft.in.samsungtest.view.model;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by sundeep on 2/12/2019.
 */

public class ImageRepository {

    private ImageDao imageDao;
    private LiveData<List<ImageModel>> allImages;

    public ImageRepository(Application application) {
        ImageDataBase imageDataBase = ImageDataBase.getInstance(application);
        imageDao = imageDataBase.imageDao();
        allImages = imageDao.getAllImages();
    }

    public void insertImages(ImageModel imageModel) {
        new InsertImageToRepo(imageDao).execute(imageModel);

    }

    public void deleteAllImage() {
        new DeleteAllImageFromRepo(imageDao).execute();
    }

    public LiveData<List<ImageModel>> getAllImages() {
        return allImages;
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
}
