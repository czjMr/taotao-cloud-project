package com.taotao.cloud.sensitive.sensitive.sensitive.model.sensitive.entry;


import com.taotao.cloud.sensitive.sensitive.sensitive.annotation.custom.SensitiveEntryCustom;

import java.util.Arrays;
import java.util.List;

/**
 * 对象中有列表，列表中放置的为对象
 */
public class CustomUserEntryObject {

    @SensitiveEntryCustom
    private User user;

    @SensitiveEntryCustom
    private List<User> userList;

    @SensitiveEntryCustom
    private User[] userArray;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public User[] getUserArray() {
        return userArray;
    }

    public void setUserArray(User[] userArray) {
        this.userArray = userArray;
    }

    @Override
    public String toString() {
        return "CustomUserEntryObject{" +
                "user=" + user +
                ", userList=" + userList +
                ", userArray=" + Arrays.toString(userArray) +
                '}';
    }
}
