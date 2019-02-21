package com.dynasoft.in.samsungtest.view.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

/**
 * Created by sundeep on 2/12/2019.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder > {

    List<ImageModel> mImageModelList = new ArrayList<>();
    Context mContext;
    public String title = "";
    public String thrubmNailUrl = "";
    private ImageModel mImageModel;
    private OnClickListener mOnClickListner;


    public interface OnClickListener{
        void onClickListener(int position);
    }
    public ImageAdapter(Context context, List<ImageModel> imageModels) {
        this.mContext = context;
        this.mImageModelList = imageModels;

    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_list_cardview,
                viewGroup, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder imageViewHolder, int i) {
        mImageModel = mImageModelList.get(i);
        imageViewHolder.imageLink.setText("Tile "+mImageModel.getId());
        Picasso.with(mContext)
                .load(mImageModel.getUrl())
                .into(imageViewHolder.imageView);
        title = mImageModel.getTitle();
        thrubmNailUrl = mImageModel.getThumbnailUrl();

    }

    public void setmOnClickListner(OnClickListener onClickListner) {
        mOnClickListner = onClickListner;
    }

    @Override
    public int getItemCount() {
        return mImageModelList.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {

        private TextView imageLink;
        private ImageView imageView;
        public ImageViewHolder(View viewItem) {
            super(viewItem);

            imageLink = (TextView) viewItem.findViewById(R.id.image_link);
            imageView = (ImageView) viewItem.findViewById(R.id.image_icon);

            viewItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mOnClickListner != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            mOnClickListner.onClickListener(position);
                        }
                    }
                }
            });

            /*imageLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity activity = (MainActivity) view.getContext();
                    ImageDetailFragment imageDetailFragment = new ImageDetailFragment();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("IMAGEMODEL", mImageModel);
                    bundle.putString("TITLE", title);
                    bundle.putString("THUMBIMAGEURL", thrubmNailUrl);
                    imageDetailFragment.setArguments(bundle);

                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, imageDetailFragment).addToBackStack(null).commit();


                }
            });*/

        }

    }

    public void setImagesList(List<ImageModel> imanewImageModelges) {
        this.mImageModelList = imanewImageModelges;
        notifyDataSetChanged();
    }


}
