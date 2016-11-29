package com.example.hoangcongtung.simpleandroidapplication.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.hoangcongtung.simpleandroidapplication.R;
import com.example.hoangcongtung.simpleandroidapplication.models.ILoadUsersCallback;
import com.example.hoangcongtung.simpleandroidapplication.models.MainData;
import com.example.hoangcongtung.simpleandroidapplication.models.model.UserItem;
import com.example.hoangcongtung.simpleandroidapplication.ui.adapter.UserAdapter;
import com.example.hoangcongtung.simpleandroidapplication.utils.AlertDialogUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ILoadUsersCallback {
    private ArrayList<UserItem> contents = new ArrayList();
    private RecyclerView viewUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        MainData.getInstance().initWithUserCallback(this);
        MainData.getInstance().load(this);
    }

    private void initViews() {
        viewUsers = (RecyclerView) findViewById(R.id.content_users);
        viewUsers.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setContents(List<UserItem> userItems) {
        UserAdapter userAdapter = new UserAdapter(userItems);
        userAdapter.setLayoutId(R.layout.item_user_row);
        viewUsers.setAdapter(userAdapter);
    }

    @Override
    public void onDataLoaded(List<UserItem> userItems) {
        setContents(userItems);
        MainData.getInstance().databaseSaved(this, userItems);
    }

    @Override
    public void onDataFailed(int resources) {
        AlertDialogUtils.show(this, R.string.warning, resources);
    }
}
