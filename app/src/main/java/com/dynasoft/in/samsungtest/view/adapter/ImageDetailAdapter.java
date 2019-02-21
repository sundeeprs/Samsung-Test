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
import com.dynasoft.in.samsungtest.view.model.ImagesFavoriteModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ImageDetailAdapter extends RecyclerView.Adapter<ImageDetailAdapter.ImgeDetailsViewHolder> {


    private Context mContext;
    private List<ImagesFavoriteModel> imageDetailsList = new ArrayList<>();


    public ImageDetailAdapter(Context context, List<ImagesFavoriteModel> imageModel){
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

        ImagesFavoriteModel imageModel = imageDetailsList.get(position);
        holder.title.setText(imageModel.getFavoriteTitle());
        Picasso.with(mContext).load(imageModel.getFavoriteThumbNail()).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        if(imageDetailsList.size() > 0)
            return imageDetailsList.size();
        else
            return 0;
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

    public void setFavoriteImages(List<ImagesFavoriteModel> imanewImageModelges) {
        this.imageDetailsList = imanewImageModelges;
        notifyDataSetChanged();
    }

    public ImagesFavoriteModel getImageFavAt(int position) {
        return imageDetailsList.get(position);
    }

}
