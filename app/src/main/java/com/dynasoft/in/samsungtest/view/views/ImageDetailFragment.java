package com.dynasoft.in.samsungtest.view.views;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dynasoft.in.samsungtest.R;
import com.dynasoft.in.samsungtest.view.model.ImageModel;
import com.dynasoft.in.samsungtest.view.viewmodel.ImageViewModel;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageDetailFragment extends Fragment {

    private ImageView thumbImageView, favoriteIcon;
    private TextView titleTextView;
    private String imgURL = "";
    private String title = "";
    private ImageViewModel mImageViewModel;
    private ImageModel mParcebleImageModel;


    public ImageDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mParcebleImageModel = (ImageModel) getArguments().getParcelable("IMAGEMODEL");
        title = getArguments().getString("TITLE");
        imgURL = getArguments().getString("THUMBIMAGEURL");


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image_detail, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mImageViewModel = ViewModelProviders.of(getActivity()).get(ImageViewModel.class);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        titleTextView = (TextView) view.findViewById(R.id.title_textview);
        titleTextView.setText(title);
        favoriteIcon = (ImageView) view.findViewById(R.id.favorite_icon);
        thumbImageView = (ImageView) view.findViewById(R.id.image_thumbnail);
        Picasso.with(getActivity())
                .load(imgURL)
                .into(thumbImageView);

        favoriteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "Favorite saved to database.", Toast.LENGTH_LONG).show();
                //TODO
                //call View model to save the favorite to Room DB
               // mImageViewModel.insertFavrite(thumbImageView);
                mImageViewModel.insertFavrite(mParcebleImageModel);
            }
        });

    }
}
