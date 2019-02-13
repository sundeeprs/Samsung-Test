package com.dynasoft.in.samsungtest.view.service;

import com.dynasoft.in.samsungtest.view.model.ImageModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by sundeep on 2/12/2019.
 */

public interface ImageAPIInterface {

    @GET("photos")
    Call<List<ImageModel>> getAllImages();
}
