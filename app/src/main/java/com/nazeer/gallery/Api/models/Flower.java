package com.nazeer.gallery.Api.models;

import com.google.gson.annotations.SerializedName;
import com.nazeer.gallery.Api.ApiClient;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getPhoto() {
        return ApiClient.IMAGE_BASE_URL+photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
