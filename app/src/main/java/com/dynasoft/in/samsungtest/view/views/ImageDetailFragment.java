package com.dynasoft.in.samsungtest.view.views;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dynasoft.in.samsungtest.R;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageDetailFragment extends Fragment {

    private ImageView thumbImageView, favoriteIcon;
    Context mContext;
    String imgURL = "";

    public ImageDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        imgURL = getArguments().getString("THUMBIMAGEURL");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image_detail, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        favoriteIcon = (ImageView) view.findViewById(R.id.favorite_icon);
        thumbImageView = (ImageView) view.findViewById(R.id.image_thumbnail);
        Picasso.with(getActivity())
                .load(imgURL)
                .into(thumbImageView);

        favoriteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                //call View model to save the favorite to Room DB
            }
        });

    }
}
