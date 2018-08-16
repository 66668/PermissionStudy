package com.sjy.permission;

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

public class MultiAct extends AppCompatActivity {
    //=======================================================
    @BindView(R.id.layout)
    RelativeLayout layout;

    @BindView(R.id.tv_show2)
    TextView tv_show1;

    @BindView(R.id.btn_multi_select)
    Button btn_multi_select;

    @BindView(R.id.btn_multi_apply)
    Button btn_multi_apply;
    //=======================================================

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
        setContentView(R.layout.act_multi);
        ButterKnife.bind(this);
        getIntentData();
    }

    @OnClick({R.id.btn_multi_select, R.id.btn_multi_apply})
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

            case R.id.btn_multi_apply://申请
                //
                if (selectList == null || selectList.size() <= 0) {
                    Toast.makeText(this, "请选择权限", Toast.LENGTH_SHORT).show();
                    return;
                }
                applyPermissions();
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
            case REQUEST_SECOND_OPEN://永久拒绝后，手动打开权限的返回处理
                //3.再次申请
                ActivityCompat.requestPermissions(MultiAct.this, permissionArray, REQUEST_CODE_MULTI);

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


    private void applyPermissions() {

        //1.判断sdk 和targetsdk是否满足
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && this.getApplicationInfo().targetSdkVersion >= Build.VERSION_CODES.M) {
            //1.判断是否需要申请权限(一般需要全部判断，本处测试写一个)
            if (ContextCompat.checkSelfPermission(this, permissionArray[0]) != PackageManager.PERMISSION_GRANTED) {
                //2.是否需要向用户解释为何申请权限(一般需要全部判断，本处测试写一个)
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissionArray[0])) {
                    //解释为何申请权限的代码
                    Snackbar.make(layout, "该功能需要使用权限（自定义）",
                            Snackbar.LENGTH_INDEFINITE)
                            .setAction("允许", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    //3.申请权限
                                    ActivityCompat.requestPermissions(MultiAct.this, permissionArray, REQUEST_CODE_MULTI);
                                }
                            })
                            .show();
                } else {
                    //3.申请权限
                    ActivityCompat.requestPermissions(MultiAct.this, permissionArray, REQUEST_CODE_MULTI);
                }
            } else {
                //已申请
                Toast.makeText(this, "权限已经申请，可以使用功能", Toast.LENGTH_LONG).show();
                for (DataBean bean : selectList) {
                    for (DataBean item : lists) {
                        if (item.getPermission().equals(bean.getPermission())) {
                            item.setUsed(true);
                        }
                    }
                }
            }

        } else {
            Toast.makeText(this, "sdk小于android5.0,不需要申请权限", Toast.LENGTH_LONG).show();
        }


    }

    /**
     * 04
     *
     * @param requestCode
     * @param permissions  多个权限的数组
     * @param grantResults 多个权限结果数组，通过的是0，不通过的是-1
     */

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull final String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_MULTI:
                boolean isGranted = true;
                StringBuilder builder = new StringBuilder();
                deniedArray = new String[grantResults.length];
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] < 0) {
                        isGranted = false;
                        builder.append(permissions[i]);
                        builder.append(" ");
                        deniedArray[i] = permissions[i];
                    }
                }

                if (isGranted) {
                    //多个权限申请 通过的处理
                    Snackbar.make(layout, "申请权限通过",
                            Snackbar.LENGTH_SHORT)
                            .show();
                    //可以执行功能了
                    for (DataBean item : selectList) {
                        for (DataBean bean : lists) {
                            if (item.getPermission().equals(bean.getPermission())) {
                                bean.setUsed(true);
                            }
                        }

                    }

                } else {
                    //权限申请拒绝的处理
                    Snackbar.make(layout, "权限" + builder.toString() + "被拒绝！", Snackbar.LENGTH_SHORT)
                            .setAction("设置", new View.OnClickListener() {//TODO
                                @Override
                                public void onClick(View view) {
                                    //不询问，直接打开设置界面
                                    if (!ActivityCompat.shouldShowRequestPermissionRationale(MultiAct.this, deniedArray[0])) {
                                        //3.手动打开权限
                                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                        Uri uri = Uri.fromParts("package", MultiAct.this.getPackageName(), null);
                                        intent.setData(uri);
                                        startActivityForResult(intent, REQUEST_SECOND_OPEN);
                                    }
                                }
                            })
                            .show();
                }

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
