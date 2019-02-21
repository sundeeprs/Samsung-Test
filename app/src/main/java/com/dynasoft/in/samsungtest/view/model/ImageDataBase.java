package com.dynasoft.in.samsungtest.view.model;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sundeep on 2/12/2019.
 */

@Database(entities = {ImageModel.class, ImagesFavoriteModel.class}, version = 3)
public abstract class ImageDataBase extends RoomDatabase {

    private static ImageDataBase instance;
    public abstract ImageDao imageDao();
    public abstract ImageFavoriteDao imageFavoriteDao();

    public static synchronized ImageDataBase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ImageDataBase.class, "image_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };




    /*private static class PopulateImageListInitially extends AsyncTask<Void, Void, Void> {
        private ImageDao imageDao;
        private List<ImageModel> mImageList = new ArrayList<>();

        public PopulateImageListInitially(ImageDataBase imageDataBase){
            imageDao = imageDataBase.imageDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mImageList.add(new ImageModel(1,1,"accusamus beatae ad facilis cum similique qui sunt",
                    "https://via.placeholder.com/600/92c952","https://via.placeholder.com/150/92c952" ));
            mImageList.add(new ImageModel(1,2,"reprehenderit est deserunt velit ipsam",
                    "https://via.placeholder.com/600/771796","https://via.placeholder.com/150/771796" ));
            mImageList.add(new ImageModel(1,3,"officia porro iure quia iusto qui ipsa ut modi",
                    "https://via.placeholder.com/600/24f355","https://via.placeholder.com/150/24f355" ));
            mImageList.add(new ImageModel(1,4,"culpa odio esse rerum omnis laboriosam voluptate repudiandae",
                    "https://via.placeholder.com/600/d32776","https://via.placeholder.com/150/d32776" ));

            return null;
        }
    }*/


}
