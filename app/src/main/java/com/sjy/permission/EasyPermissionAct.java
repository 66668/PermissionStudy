package com.sjy.permission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sjy.permission.utils.DataBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 第三方框架  easyPermissions:https://github.com/googlesamples/easypermissions
 */
public class EasyPermissionAct extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    //=======================================================
    @BindView(R.id.layout)
    RelativeLayout layout;

    @BindView(R.id.tv_show2)
    TextView tv_show1;

    @BindView(R.id.btn_multi_select)
    Button btn_multi_select;

    @BindView(R.id.btn_1)
    Button btn_1;

    @BindView(R.id.btn_2)
    Button btn_2;

    //=======================================================

    private static final int REQUEST_CODE_SINGLE = 114;
    private static final int REQUEST_CODE_MULTI = 115;
    private static final int REQUEST_SECOND_OPEN = 125;//永久拒绝的手动打开设置

    //=======================================================
    private List<DataBean> lists = new ArrayList<>();
    private List<DataBean> selectList = new ArrayList<>();
    private String[] permissionArray;
    private String[] deniedArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_easypermission);
        ButterKnife.bind(this);
        getIntentData();
    }

    @OnClick({R.id.btn_multi_select, R.id.btn_1, R.id.btn_2})
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

            case R.id.btn_1://单个权限
                //
                if (selectList == null || selectList.size() <= 0) {
                    Toast.makeText(this, "请选择权限", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (selectList.size() == 1) {
                    applySinglePermission();
                } else {
                    Toast.makeText(this, "请选择一个权限", Toast.LENGTH_SHORT).show();
                }


                break;
            case R.id.btn_2://多个权限
                //
                if (selectList == null || selectList.size() <= 0) {
                    Toast.makeText(this, "请选择权限", Toast.LENGTH_SHORT).show();
                    return;
                }
                applyMultiPermission();

                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 102://MultiSelectAct选择权限返回值
                if (data != null) {
                    getSelectData(data);
                }
                break;
            default:
                if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
                    // Do something after user returned from app settings screen, like showing a Toast.
                    Snackbar.make(layout, "手动设置返回处理！",
                            Snackbar.LENGTH_SHORT)
                            .show();
                }
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

    //=========================权限相关的代码==============================


    /**
     * 单个权限申请
     */
    @AfterPermissionGranted(REQUEST_CODE_SINGLE)//可选项
    private void applySinglePermission() {
        if (hasCameraPermission()) {
            //权限申请 通过的处理
            Snackbar.make(layout, "权限通过，可使用功能",
                    Snackbar.LENGTH_SHORT)
                    .show();
        } else {
            // 申请权限
            EasyPermissions.requestPermissions(
                    this,
                    "app需要使用权限！（自定义）",
                    REQUEST_CODE_SINGLE,
                    permissionArray);
        }

    }

    /**
     * 多个权限申请
     */
    @AfterPermissionGranted(REQUEST_CODE_MULTI)
    private void applyMultiPermission() {

        if (hasCameraPermission()) {
            // Have permissions, do the thing!
            //权限申请 通过的处理
            Snackbar.make(layout, "所有权限都通过，可使用功能",
                    Snackbar.LENGTH_SHORT)
                    .show();
        } else {
            // Ask for both permissions
            EasyPermissions.requestPermissions(
                    this,
                    "app需要使用多个权限！（自定义）",
                    REQUEST_CODE_MULTI,
                    permissionArray);
        }
    }

    /**
     * 02
     *
     * @param requestCode
     * @param permissions  多个权限的数组
     * @param grantResults 多个权限结果数组，通过的是0，不通过的是-1
     */

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull final String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //onRequestPermissionsResult的回调 发送给库处理,this是 act implements EasyPermissions.PermissionCallbacks
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    private boolean hasCameraPermission() {
        return EasyPermissions.hasPermissions(this, permissionArray);
    }

    /**
     * EasyPermissions.PermissionCallbacks实现 通过
     *
     * @param requestCode
     * @param perms
     */
    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        switch (requestCode) {
            case REQUEST_CODE_SINGLE:
                Snackbar.make(layout, "单个权限通过",
                        Snackbar.LENGTH_SHORT)
                        .show();
                break;
            case REQUEST_CODE_MULTI:
                Snackbar.make(layout, "多个权限通过",
                        Snackbar.LENGTH_SHORT)
                        .show();
                break;

            default:
                Snackbar.make(layout, "default--requestCode=" + requestCode,
                        Snackbar.LENGTH_SHORT)
                        .show();
                break;
        }

    }

    /**
     * EasyPermissions.PermissionCallbacks实现 拒绝权限
     *
     * @param requestCode
     * @param perms
     */
    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        switch (requestCode) {
            case REQUEST_CODE_SINGLE:

                //跳转设置 手动打开权限
                if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
                    new AppSettingsDialog.Builder(this).build().show();
                }

                break;
            case REQUEST_CODE_MULTI:
                //跳转设置 手动打开权限
                if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
                    new AppSettingsDialog.Builder(this).build().show();
                }
                break;

            default:
                Snackbar.make(layout, "default--requestCode=" + requestCode,
                        Snackbar.LENGTH_SHORT)
                        .show();
                break;
        }
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
