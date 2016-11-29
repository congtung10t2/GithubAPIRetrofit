package com.example.hoangcongtung.simpleandroidapplication.models;

import android.content.Context;
import android.preference.PreferenceManager;

import com.example.hoangcongtung.simpleandroidapplication.Constants;
import com.example.hoangcongtung.simpleandroidapplication.R;
import com.example.hoangcongtung.simpleandroidapplication.models.local.DBHelper;
import com.example.hoangcongtung.simpleandroidapplication.models.model.UserItem;
import com.example.hoangcongtung.simpleandroidapplication.models.remote.RFHelper;

import java.util.List;

/**
 * Created by hoangcongtung on 11/26/16.
 */
public class MainData {
    private static MainData mainData;
    private DBHelper localDatabase;
    private ILoadUsersCallback iLoadUsersCallback;

    public static MainData getInstance() {
        if (mainData == null)
            mainData = new MainData();
        return mainData;
    }

    public ILoadUsersCallback getLoadUsersCallback() {
        return iLoadUsersCallback;
    }

    public void initWithUserCallback(ILoadUsersCallback iLoadUsersCallback) {
        this.iLoadUsersCallback = iLoadUsersCallback;
    }

    public DBHelper getLocalDatabase(Context context) {
        if (localDatabase == null)
            localDatabase = new DBHelper(context);
        return localDatabase;
    }

    public void load(Context context) {
        if (isDatabaseSaved(context)) {
            List<UserItem> data = getLocalDatabase(context).getAllUsers();
            if (data != null) {
                iLoadUsersCallback.onDataLoaded(data);
                return;
            }
            iLoadUsersCallback.onDataFailed(R.string.data_null);
        }
        new RFHelper().init();
    }

    public boolean isDatabaseSaved(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
            .getBoolean(Constants.DATA_VAR, false);
    }

    public void databaseSaved(Context context, List<UserItem> users) {
        if (isDatabaseSaved(context))
            return;
        for (UserItem user : users) {
            getLocalDatabase(context).insertUser(user);
        }
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(Constants.DATA_VAR, true)
            .apply();
    }
}
