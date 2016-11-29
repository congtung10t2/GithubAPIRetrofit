package com.example.hoangcongtung.simpleandroidapplication.models.local;
/**
 * Created by hoangcongtung on 11/26/16.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.hoangcongtung.simpleandroidapplication.models.model.UserItem;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "demo.db";
    public static final String USERS_TABLE_NAME = "users";
    public static final String USERS_COLUMN_LOGIN = "login";
    public static final String USERS_COLUMN_AVATAR_URL = "avatar_url";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
            "create table " + USERS_TABLE_NAME + " " +
                "(id integer primary key, " + USERS_COLUMN_LOGIN + " text,"
                + USERS_COLUMN_AVATAR_URL + " text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertUser(String login, String avatar_url) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERS_COLUMN_LOGIN, login);
        contentValues.put(USERS_COLUMN_AVATAR_URL, avatar_url);
        db.insert(USERS_TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean insertUser(UserItem userItem) {
        return insertUser(userItem.getLogin(), userItem.getAvatar_url());
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =
            db.rawQuery("select * from " + USERS_TABLE_NAME + " where id=" + id + "", null);
        return res;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, USERS_TABLE_NAME);
        return numRows;
    }

    public boolean updateUser(Integer id, String login, String avatar_url) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERS_COLUMN_LOGIN, login);
        contentValues.put(USERS_COLUMN_AVATAR_URL, avatar_url);
        db.update(USERS_TABLE_NAME, contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public boolean updateUser(Integer id, UserItem userItem) {
        return updateUser(id, userItem.getLogin(), userItem.getAvatar_url());
    }

    public Integer deleteUser(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(USERS_TABLE_NAME,
            "id = ? ",
            new String[]{Integer.toString(id)});
    }

    public ArrayList<UserItem> getAllUsers() {
        ArrayList<UserItem> users = new ArrayList<UserItem>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + USERS_TABLE_NAME, null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            UserItem userItem = new UserItem(res.getString(res.getColumnIndex(USERS_COLUMN_LOGIN)
            ), res.getString(res.getColumnIndex(USERS_COLUMN_AVATAR_URL)));
            users.add(userItem);
            res.moveToNext();
        }
        return users;
    }
}