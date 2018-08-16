package com.sjy.permission.utils;

import java.io.Serializable;

public class DataBean implements Serializable {
    String permission;
    boolean isUsed;

    public DataBean(String permission,  boolean isUsed) {
        this.permission = permission;
        this.isUsed = isUsed;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }


    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }
}
