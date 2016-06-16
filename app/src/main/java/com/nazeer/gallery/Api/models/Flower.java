package com.nazeer.gallery.Api.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nazeer on 16/06/16.
 */
public class Flower {
    @SerializedName("category")
    private String category;
    @SerializedName("price")
    private double price;
    @SerializedName("instructions")
    private String instructions;
    @SerializedName("photo")
    private String photo;
    @SerializedName("name")
    private String name;
    @SerializedName("productId")
    private String productId;

}
