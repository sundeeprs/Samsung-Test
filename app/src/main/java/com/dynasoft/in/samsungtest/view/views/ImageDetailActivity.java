package com.dynasoft.in.samsungtest.view.views;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dynasoft.in.samsungtest.R;
import com.dynasoft.in.samsungtest.view.model.ImageModel;
import com.dynasoft.in.samsungtest.view.model.ImagesFavoriteModel;
import com.dynasoft.in.samsungtest.view.viewmodel.ImageDetailViewModel;
import com.dynasoft.in.samsungtest.view.viewmodel.ImageViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageDetailActivity extends AppCompatActivity {

    public static final String TAG = ImageDetailActivity.class.getSimpleName();

    private TextView mImageDetailTitle;
    private ImageView mImageDetailsThumbNail, favoriteIcon;
     private ImageDetailViewModel mImageDetailViewModel;
    private ImageModel mParcebleImageModel;
    private RecyclerView mImageDetailRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.image_detail_layout);

        mImageDetailViewModel = ViewModelProviders.of(this).get(ImageDetailViewModel.class);
        mImageDetailRecyclerView = (RecyclerView) findViewById(R.id.imageDetailsRecyclerView);

        Intent intent = getIntent();
        Bundle bundel = intent.getExtras();
        mParcebleImageModel = (ImageModel) bundel.get("IMGAE_DETAILS_MODEL");


        mImageDetailTitle = (TextView) findViewById(R.id.title_textview);
        mImageDetailsThumbNail = (ImageView) findViewById(R.id.image_thumbnail);
        favoriteIcon = (ImageView) findViewById(R.id.favorite_icon);

        String thumbNailURL = mParcebleImageModel.getThumbnailUrl();
        mImageDetailTitle.setText(mParcebleImageModel.getTitle());
        Picasso.with(this).load(thumbNailURL).fit().centerInside().into(mImageDetailsThumbNail);


        favoriteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(ImageDetailActivity.this, "Favorite saved to database.", Toast.LENGTH_LONG).show();
                //TODO
                //call View model to save the favorite to Room DB
                ImagesFavoriteModel imagesFavoriteModel = new ImagesFavoriteModel(mParcebleImageModel.getId(),
                        mParcebleImageModel.getTitle(), mParcebleImageModel.getThumbnailUrl());


                /*List<ImagesFavoriteModel> imageFavModels = mImageDetailViewModel.getAllFavoriteImagesFromRepo();
                if(imageFavModels.size() > 0) {
                    for (ImagesFavoriteModel imgFavModel : imageFavModels) {
                        if (imgFavModel.getImageId() == mParcebleImageModel.getId()) {
                            mImageDetailViewModel.updateFavorite(imagesFavoriteModel);
                        } else {
                            mImageDetailViewModel.insertFavorite(imagesFavoriteModel);
                        }

                    }
                } else {
                    Toast.makeText(ImageDetailActivity.this, "No Favorite stored yet!", Toast.LENGTH_SHORT).show();
                }*/

            }
        });

    }
}
