package com.sjy.permission;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sjy.permission.type1.BaseAct;
import com.sjy.permission.type1.MyPermissionListener;
import com.sjy.permission.utils.DataBean;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * 第三方框架--RxPermissions：https://github.com/tbruyelle/RxPermissions
 */
public class RxPermissionAct extends BaseAct {
    //=======================================================
    @BindView(R.id.layout)
    RelativeLayout layout;

    @BindView(R.id.tv_show2)
    TextView tv_show1;

    @BindView(R.id.btn_multi_select)
    Button btn_multi_select;

    @BindView(R.id.btn_rx_1)
    Button btn_rx_1;
    @BindView(R.id.btn_rx_2)
    Button btn_rx_2;
    @BindView(R.id.btn_rx_3)
    Button btn_rx_3;

    //=======================================================
    private static final int REQUEST_MULTI = 11;
    private static final int REQUEST_MULTI_EACH = 12;
    private static final int REQUEST_SINGLE = 13;
    //=======================================================
    private List<DataBean> lists = new ArrayList<>();
    private List<DataBean> selectList = new ArrayList<>();
    private String[] permissionArray;
    RxPermissions rxPermissions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_rxpermission);
        ButterKnife.bind(this);
        getIntentData();
        rxPermissions = new RxPermissions(this);
    }

    @OnClick({R.id.btn_multi_select, R.id.btn_rx_1, R.id.btn_rx_2, R.id.btn_rx_3})
    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.btn_multi_select://选择权限
                if (lists == null || lists.size() <= 0) {
                    Toast.makeText(this, "没有可用的权限了，请删除软件重新安装测试", Toast.LENGTH_LONG).show();
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable("bean", (Serializable) lists);
                startActForResult(MultiSelectAct.class, bundle);
                break;

            case R.id.btn_rx_1://申请
                //
                if (selectList == null || selectList.size() <= 0) {
                    Toast.makeText(this, "请选择权限", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (selectList.size() != 1) {
                    Toast.makeText(this, "只可以选择一个权限", Toast.LENGTH_SHORT).show();
                    return;
                }
                requestSingle();
                break;
            case R.id.btn_rx_2://申请
                if (selectList == null || selectList.size() <= 0) {
                    Toast.makeText(this, "请选择权限", Toast.LENGTH_SHORT).show();
                    return;
                }

                requestMulti();

                break;
            case R.id.btn_rx_3://申请
                if (selectList == null || selectList.size() <= 0) {
                    Toast.makeText(this, "请选择权限", Toast.LENGTH_SHORT).show();
                    return;
                }
                requestEach();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            backAct();
        }
        return super.onKeyDown(keyCode, event);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 102://MultiSelectAct选择权限返回值
                if (data != null) {
                    getSelectData(data);
                }
            case REQUEST_MULTI://永久拒绝后，手动打开权限的返回处理
                //3.再次申请
                requestMulti();
                break;
                case REQUEST_MULTI_EACH://永久拒绝后，手动打开权限的返回处理
                //3.再次申请
                requestEach();
                break;
        }
    }

    // =========================权限相关代码==============================


    /**
     * RxPermissions申请一个权限
     */
    private void requestSingle() {

        rxPermissions.request(permissionArray)
                .subscribe(granted -> {
                    if (granted) { // Always true pre-M
                        Snackbar.make(layout, "申请权限通过",
                                Snackbar.LENGTH_SHORT)
                                .show();

                    } else {
                        Snackbar.make(layout, "申请权限拒绝",
                                Snackbar.LENGTH_SHORT)
                                .show();

                    }

                });

        //等价：
//        rxPermissions.request(permissionArray)
//                .subscribe(new Consumer<Boolean>() {
//                    @Override
//                    public void accept(Boolean aBoolean) throws Exception {
//
//                        if (aBoolean) {
//                            Snackbar.make(layout, "申请权限通过",
//                                    Snackbar.LENGTH_SHORT)
//                                    .show();
//                        } else {
//                            Snackbar.make(layout, "申请权限拒绝",
//                                    Snackbar.LENGTH_SHORT)
//                                    .show();
//
//                        }
//                    }
//                });

        //等价：
//        rxPermissions.requestEach(permissionArray)
//                .subscribe(new Consumer<Permission>() {
//                    @Override
//                    public void accept(Permission permission) throws Exception {
//                        if (permission.granted) {
//                            Snackbar.make(layout, "申请权限通过",
//                                    Snackbar.LENGTH_SHORT)
//                                    .show();
//                        } else {
//                            Snackbar.make(layout, "申请权限拒绝",
//                                    Snackbar.LENGTH_SHORT)
//                                    .show();
//
//                        }
//                    }
//                });

    }

    /**
     * RxPermissions同时申请多个权限
     */
    private void requestMulti() {

        //
        rxPermissions.request(permissionArray)//这里填写所需要的权限
                .subscribe(granted -> {
                    if (granted) { // Always true pre-M
                        //当所有权限都允许之后，返回true
                        Snackbar.make(layout, "所有权限通过",
                                Snackbar.LENGTH_SHORT)
                                .show();
                    } else {
                        //只要有一个权限禁止，返回false，
                        // 下一次申请只申请没通过申请的权限
                        Snackbar.make(layout, "有权限拒绝", Snackbar.LENGTH_SHORT)
                                .setAction("设置", new View.OnClickListener() {//
                                    @Override
                                    public void onClick(View view) {
                                        //3.申请权限

                                        //如果简单拒绝，可再次申请
//                                        requestEach();

                                        //如果 不询问+拒绝，走该处代码（推荐）
                                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                        Uri uri = Uri.fromParts("package", RxPermissionAct.this.getPackageName(), null);
                                        intent.setData(uri);
                                        startActivityForResult(intent, REQUEST_MULTI);
                                    }
                                })
                                .show();
                    }

                });
    }

    /**
     * 该处处理有问题，拒绝后，一直申请，覆盖重新申请的界面，不太友好
     */
    private void requestEach() {

        rxPermissions
                .requestEach(permissionArray)
                .subscribe(permission -> { // will emit 2 Permission objects
                    if (permission.granted) {
                        //当所有权限都允许之后，返回true
                        Snackbar.make(layout, "所有权限通过",
                                Snackbar.LENGTH_SHORT)
                                .show();
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        // Denied permission without ask never again
                        Snackbar.make(layout, "拒绝不可以使用", Snackbar.LENGTH_SHORT)
                                .setAction("允许", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        //3.申请权限
                                        //如果简单拒绝，可再次申请
//                                        requestEach();

                                        //如果 不询问+拒绝，走该处代码（推荐）
                                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                        Uri uri = Uri.fromParts("package", RxPermissionAct.this.getPackageName(), null);
                                        intent.setData(uri);
                                        startActivityForResult(intent, REQUEST_MULTI_EACH);
                                    }
                                })
                                .show();
                        requestEach();
                    } else {
                        //只要有一个权限禁止，返回false，
                        // 下一次申请只申请没通过申请的权限
                        Snackbar.make(layout, "有权限拒绝", Snackbar.LENGTH_SHORT)
                                .setAction("设置", new View.OnClickListener() {//
                                    @Override
                                    public void onClick(View view) {
                                        //3.申请权限

                                        //如果简单拒绝，可再次申请
//                                        requestEach();

                                        //如果 不询问+拒绝，走该处代码（推荐）
                                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                        Uri uri = Uri.fromParts("package", RxPermissionAct.this.getPackageName(), null);
                                        intent.setData(uri);
                                        startActivityForResult(intent, REQUEST_MULTI_EACH);
                                    }
                                })
                                .show();
                    }
                });

        //等价
//        rxPermissions
//                .requestEach(permissionArray)
//                .subscribe(new Consumer<Permission>() {
//                    @Override
//                    public void accept(Permission permission) throws Exception {
//                        if (permission.granted) {
//                            // `permission.name` is granted !
//        Snackbar.make(layout, "所有权限通过",
//                Snackbar.LENGTH_SHORT)
//                .show();
//                        } else if (permission.shouldShowRequestPermissionRationale) {
//                            // Denied permission without ask never again
//        requestEach();
//                        } else {
//                            // Denied permission with ask never again
//                            // Need to go to the settings
//        Snackbar.make(layout, "有权限拒绝",
//                Snackbar.LENGTH_SHORT)
//                .show();
//                        }
//                    }
//                });
    }


    // =========================private==============================
    private void getIntentData() {
        lists = (List<DataBean>) getIntent().getExtras().getSerializable("bean");
        if (lists == null || lists.size() <= 0) {
            Toast.makeText(this, "没有可用的权限了，请删除软件重新安装测试", Toast.LENGTH_LONG).show();
        }
    }

    private void getSelectData(Intent data) {
        selectList = (List<DataBean>) data.getExtras().getSerializable("bean");
        if (selectList == null || selectList.size() <= 0) {
            Toast.makeText(this, "选择数据返回为空", Toast.LENGTH_SHORT).show();
            tv_show1.setText("");
            return;
        }
        permissionArray = new String[selectList.size()];
        for (int i = 0; i < selectList.size(); i++) {
            DataBean dataBean = selectList.get(i);
            permissionArray[i] = dataBean.getPermission();
        }
        showData();
    }

    private void showData() {
        StringBuilder builder = new StringBuilder();
        for (DataBean bean : selectList) {
            builder.append(bean.getPermission());
            builder.append("\n");
        }
        tv_show1.setText(builder.toString());
    }

    private void backAct() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("bean", (Serializable) lists);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        setResult(102, intent);
        this.finish();
    }

    //=========================跳转==============================
    private void startAct(Class clz) {
        Intent intent = new Intent(this, clz);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    private void startAct(Class clz, Bundle bundle) {
        Intent intent = new Intent(this, clz);
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    private void startActForResult(Class clz, Bundle bundle) {
        Intent intent = new Intent(this, clz);
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivityForResult(intent, 101);
    }
}
