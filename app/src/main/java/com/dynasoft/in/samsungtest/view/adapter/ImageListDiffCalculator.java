package com.dynasoft.in.samsungtest.view.adapter;

import android.support.v7.util.DiffUtil;

import com.dynasoft.in.samsungtest.view.model.ImagesFavoriteModel;

import java.util.List;

public class ImageListDiffCalculator extends DiffUtil.Callback {

    private List<ImagesFavoriteModel> mOldImageFavModelList;
    private List<ImagesFavoriteModel> mNewImageFavModelList;

    public ImageListDiffCalculator(List<ImagesFavoriteModel> oldImageFavModel,
                                    List<ImagesFavoriteModel> newImageFavModel) {
       this.mOldImageFavModelList = oldImageFavModel;
       this.mNewImageFavModelList = newImageFavModel;
    }

    private ImageListDiffCalculator() {
        super();
    }

    @Override
    public int getOldListSize() {
        return mOldImageFavModelList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewImageFavModelList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldImageFavModelList.get(oldItemPosition).getImageId() ==
                mNewImageFavModelList.get(newItemPosition).getImageId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        ImagesFavoriteModel oldImageFavList = mOldImageFavModelList.get(oldItemPosition);
        ImagesFavoriteModel newImageFavList = mNewImageFavModelList.get(newItemPosition);

        if (oldImageFavList.getFavoriteTitle() == newImageFavList.getFavoriteTitle() &&
                oldImageFavList.getFavoriteThumbNail() == newImageFavList.getFavoriteThumbNail()) {
            return true;
        }
        return false;
    }
}
