package com.nazeer.gallery.Api;

/**
 * Created by nazeer on 16/06/16.
 */
public class ApiCallback<T> {
    public void onSuccess(T response){};
    public void onException(Exception e){};
}