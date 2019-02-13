package com.dynasoft.in.samsungtest.view.views;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.GridLayout;
import android.widget.Toast;

import com.dynasoft.in.samsungtest.R;
import com.dynasoft.in.samsungtest.view.adapter.ImageAdapter;
import com.dynasoft.in.samsungtest.view.model.ImageModel;
import com.dynasoft.in.samsungtest.view.service.ImageAPIInterface;
import com.dynasoft.in.samsungtest.view.viewmodel.ImageViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mImageRecyclerView;
    private ImageAdapter mImageAdapter;
    private List<ImageModel> mImageList = new ArrayList<>();
    private ImageViewModel mImageViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageRecyclerView = (RecyclerView) findViewById(R.id.imageRecyclerView);

        mImageViewModel = ViewModelProviders.of(this).get(ImageViewModel.class);

        mImageViewModel.getAllImagesFromRepo().observe(this, new Observer<List<ImageModel>>() {
            @Override
            public void onChanged(@Nullable List<ImageModel> imageModels) {
                mImageAdapter.setImages(imageModels);
            }
        });

         mImageAdapter = new ImageAdapter(this, mImageList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayout.VERTICAL, false);
        mImageRecyclerView.setLayoutManager(gridLayoutManager);
        mImageRecyclerView.setAdapter(mImageAdapter);

    }
}
