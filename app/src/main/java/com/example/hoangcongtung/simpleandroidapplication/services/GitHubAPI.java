package com.example.hoangcongtung.simpleandroidapplication.services;

import com.example.hoangcongtung.simpleandroidapplication.models.model.UserItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by hoangcongtung on 11/26/16.
 */
public interface GitHubAPI {
    @GET ("/users")
    Call<List<UserItem>> getUsers();
}