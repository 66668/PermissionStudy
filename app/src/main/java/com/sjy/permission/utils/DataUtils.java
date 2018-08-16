package com.sjy.permission.utils;

import android.Manifest;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.WRITE_CALENDAR;
import static android.Manifest.permission_group.CALENDAR;
import static android.Manifest.permission_group.CAMERA;


public class DataUtils {

    public static List<DataBean> createData() {
        List<DataBean> lists = new ArrayList<>();
        lists.add(new DataBean(android.Manifest.permission.WRITE_CALENDAR, false));
        lists.add(new DataBean(android.Manifest.permission.CAMERA, false));
        lists.add(new DataBean(android.Manifest.permission.READ_CONTACTS, false));

        lists.add(new DataBean(android.Manifest.permission.ACCESS_COARSE_LOCATION, false));
        lists.add(new DataBean(android.Manifest.permission.RECORD_AUDIO, false));
        lists.add(new DataBean(android.Manifest.permission.CALL_PHONE, false));

        lists.add(new DataBean(android.Manifest.permission.BODY_SENSORS, false));
        lists.add(new DataBean(android.Manifest.permission.SEND_SMS, false));
        lists.add(new DataBean(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, false));


        return lists;
    }

    // 权限组数据
    public static List<DataBean> createGroupData() {
        List<DataBean> lists = new ArrayList<>();
        lists.add(new DataBean(android.Manifest.permission_group.CALENDAR, false));
        lists.add(new DataBean(android.Manifest.permission_group.CAMERA, false));
        lists.add(new DataBean(android.Manifest.permission_group.CONTACTS, false));
        lists.add(new DataBean(android.Manifest.permission_group.LOCATION, false));
        lists.add(new DataBean(android.Manifest.permission_group.MICROPHONE, false));
        lists.add(new DataBean(android.Manifest.permission_group.PHONE, false));
        lists.add(new DataBean(android.Manifest.permission_group.SENSORS, false));
        lists.add(new DataBean(android.Manifest.permission_group.SMS, false));
        lists.add(new DataBean(android.Manifest.permission_group.STORAGE, false));

        return lists;
    }

    public static List<DataBean> getUnUseData(List<DataBean> lists) {
        List<DataBean> newList = new ArrayList<>();
        for (DataBean bean : lists) {
            if (!bean.isUsed) {
                newList.add(bean);
            }
        }
        Log.d("SJY", "处理 返回=" + newList.size());
        return newList;
    }


    public static String[] getUnUseData2(List<DataBean> lists) {
        List<DataBean> newList = new ArrayList<>();

        for (DataBean bean : lists) {
            if (!bean.isUsed) {
                newList.add(bean);
            }
        }
        String[] arrays = new String[newList.size()];
        for (int i = 0; i < newList.size(); i++) {
            arrays[i] = newList.get(i).getPermission();
        }
        return arrays;
    }

    public static List<DataBean> getUseData(List<DataBean> lists) {
        List<DataBean> newList = new ArrayList<>();
        for (DataBean bean : lists) {
            if (bean.isUsed) {
                newList.add(bean);
            }
        }
        return newList;
    }

    public static String[] getUseData2(List<DataBean> lists) {
        List<DataBean> newList = new ArrayList<>();
        for (DataBean bean : lists) {
            if (bean.isUsed) {
                newList.add(bean);
            }
        }
        String[] arrays = new String[newList.size()];
        for (int i = 0; i < newList.size(); i++) {
            arrays[i] = newList.get(i).getPermission();
        }
        return arrays;
    }


}
