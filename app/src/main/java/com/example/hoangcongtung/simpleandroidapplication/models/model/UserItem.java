package com.example.hoangcongtung.simpleandroidapplication.models.model;

/**
 * Created by hoangcongtung on 11/26/16.
 */
public class UserItem {
    private String login;
    private String avatar_url;

    public UserItem(String login, String avatar_url) {
        this.login = login;
        this.avatar_url = avatar_url;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
}
