package com.dynasoft.in.samsungtest.view.views;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dynasoft.in.samsungtest.R;
import com.dynasoft.in.samsungtest.view.adapter.ImageDetailAdapter;
import com.dynasoft.in.samsungtest.view.model.ImageDataBase;
import com.dynasoft.in.samsungtest.view.model.ImageFavoriteDao;
import com.dynasoft.in.samsungtest.view.model.ImageModel;
import com.dynasoft.in.samsungtest.view.model.ImagesFavoriteModel;
import com.dynasoft.in.samsungtest.view.viewmodel.ImageDetailViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ImageDetailActivity extends AppCompatActivity {

    public static final String TAG = ImageDetailActivity.class.getSimpleName();

    private TextView mImageDetailTitle;
    private ImageView mImageDetailsThumbNail, favoriteIcon;
    private ImageDetailViewModel mImageDetailViewModel;
    private ImageModel mParcebleImageModel;
    private ImagesFavoriteModel mImagesFavoriteModel;
    private RecyclerView mImageDetailRecyclerView;
    private List<ImagesFavoriteModel> mImageFavoriteList = new ArrayList<>();
    private ImageDetailAdapter imageDetailAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.image_detail_layout);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Favorite Images");
        mImageDetailViewModel = ViewModelProviders.of(this).get(ImageDetailViewModel.class);
        mImageDetailRecyclerView = (RecyclerView) findViewById(R.id.imageDetailsRecyclerView);

        Intent intent = getIntent();
        Bundle bundel = intent.getExtras();
        mParcebleImageModel = (ImageModel) bundel.get("IMGAE_DETAILS_MODEL");


        mImageDetailTitle = (TextView) findViewById(R.id.title_textview);
        mImageDetailsThumbNail = (ImageView) findViewById(R.id.image_thumbnail);

        String thumbNailURL = mParcebleImageModel.getThumbnailUrl();
        mImageDetailTitle.setText(mParcebleImageModel.getTitle());
        Picasso.with(this).load(thumbNailURL).fit().centerInside().into(mImageDetailsThumbNail);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mImageDetailRecyclerView.setLayoutManager(linearLayoutManager);
        mImageDetailRecyclerView.setHasFixedSize(true);
        mImageDetailRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

        imageDetailAdapter = new ImageDetailAdapter(this, mImageFavoriteList);
        mImageDetailRecyclerView.setAdapter(imageDetailAdapter);

        mImageDetailViewModel.getAllFavoriteImagesFromRepo().observe(this, new Observer<List<ImagesFavoriteModel>>() {
            @Override
            public void onChanged(@Nullable List<ImagesFavoriteModel> imagesFavoriteModels) {
                mImageFavoriteList = imagesFavoriteModels;
                imageDetailAdapter.setFavoriteImages(imagesFavoriteModels);
                Toast.makeText(ImageDetailActivity.this, "No Favorite stored yet!", Toast.LENGTH_LONG).show();

            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                mImageDetailViewModel.delete(imageDetailAdapter.getImageFavAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(mImageDetailRecyclerView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_image_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.favorite_icon:
                saveImages();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void saveImages() {

        ImagesFavoriteModel imagesFavoriteModel = new ImagesFavoriteModel(mParcebleImageModel.getId(),
                mParcebleImageModel.getTitle(), mParcebleImageModel.getThumbnailUrl());

        mImageDetailViewModel.insertFavorite(imagesFavoriteModel);
    }

}
