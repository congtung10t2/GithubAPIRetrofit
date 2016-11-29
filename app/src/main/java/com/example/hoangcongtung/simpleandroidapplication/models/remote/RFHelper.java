package com.example.hoangcongtung.simpleandroidapplication.models.remote;

import com.example.hoangcongtung.simpleandroidapplication.Constants;
import com.example.hoangcongtung.simpleandroidapplication.R;
import com.example.hoangcongtung.simpleandroidapplication.models.MainData;
import com.example.hoangcongtung.simpleandroidapplication.models.model.UserItem;
import com.example.hoangcongtung.simpleandroidapplication.services.GitHubAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hoangcongtung on 11/26/16.
 */
public class RFHelper implements Callback<List<UserItem>> {
    private Call<List<UserItem>> callUser;

    public void init() {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.END_POINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        GitHubAPI service = retrofit.create(GitHubAPI.class);
        callUser = service.getUsers();
        callUser.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<UserItem>> call, Response<List<UserItem>> response) {
        if (response.isSuccessful()) {
            MainData.getInstance().getLoadUsersCallback().onDataLoaded(response.body());
            return;
        }
        MainData.getInstance().getLoadUsersCallback().onDataFailed(R.string.data_null);
    }

    @Override
    public void onFailure(Call<List<UserItem>> call, Throwable throwable) {
        MainData.getInstance().getLoadUsersCallback().onDataFailed(R.string.data_load_failed);
    }
}
