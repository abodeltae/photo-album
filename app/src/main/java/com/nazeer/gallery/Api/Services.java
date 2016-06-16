package com.nazeer.gallery.Api;

import com.nazeer.gallery.Api.models.Flower;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by nazeer on 16/06/16.
 */
public interface Services {
    @GET("feeds/flowers.json")
    Call<List<Flower>> getFlowers();
}
