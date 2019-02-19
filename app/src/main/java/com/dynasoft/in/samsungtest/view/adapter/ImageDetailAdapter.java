package com.dynasoft.in.samsungtest.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dynasoft.in.samsungtest.R;
import com.dynasoft.in.samsungtest.view.model.ImageModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ImageDetailAdapter extends RecyclerView.Adapter<ImageDetailAdapter.ImgeDetailsViewHolder> {


    private Context mContext;
    private List<ImageModel> imageDetailsList = new ArrayList<>();


    public ImageDetailAdapter(Context context, List<ImageModel> imageModel){
        mContext = context;
        imageDetailsList = imageModel;

    }
    @Override
    public ImgeDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.image_details_favorite_list,
                parent, false);


        return new ImgeDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImgeDetailsViewHolder holder, int position) {

        ImageModel imageModel = imageDetailsList.get(position);
        holder.title.setText(imageModel.getTitle());
        Picasso.with(mContext).load(imageModel.getThumbnailUrl()).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return imageDetailsList.size();
    }

    public class ImgeDetailsViewHolder extends RecyclerView.ViewHolder {

         String mdetailTitle = "";
         String mdetailThumbNailURL = "";
         TextView title;
         ImageView imageView;

         public ImgeDetailsViewHolder(View viewItem){
             super(viewItem);

             title = (TextView) viewItem.findViewById(R.id.image_view_favorite_title);
             imageView = (ImageView) viewItem.findViewById(R.id.image_view_favorite);


         }


    }
}
