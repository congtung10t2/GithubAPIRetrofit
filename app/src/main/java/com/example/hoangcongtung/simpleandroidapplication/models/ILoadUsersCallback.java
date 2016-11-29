package com.example.hoangcongtung.simpleandroidapplication.models;

import com.example.hoangcongtung.simpleandroidapplication.models.model.UserItem;

import java.util.List;

/**
 * Created by hoangcongtung on 11/26/16.
 */
public interface ILoadUsersCallback {
    void onDataLoaded(List<UserItem> userItems);
    void onDataFailed(int resources);
}
