package com.sjy.permission;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 两个特殊权限的授权，做法是使用startActivityForResult启动授权界面来完成
 * 关于这两个特殊权限，一般不建议应用申请
 */
public class SpecialAct extends AppCompatActivity {

    @BindView(R.id.btn_special1)
    Button btn_special1;

    @BindView(R.id.btn_special2)
    Button btn_special2;
    //
    private static final int REQUEST_WRITE_SETTINGS = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_special);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_special1, R.id.btn_special2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_special1:
                requestSYSTEM_ALERT_WINDOW();
                break;
            case R.id.btn_special2:
                requestWriteSettings();
                break;
        }
    }

    //=========================权限相关的代码==============================

    /**
     * 请求SYSTEM_ALERT_WINDOW
     */
    private void requestSYSTEM_ALERT_WINDOW() {
        //大于android 6.0才可以申请权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && this.getApplicationInfo().targetSdkVersion >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                //隐式Intent
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                intent.setData(Uri.parse("package:" + getPackageName()));//不加会显示所有可能的app
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                //TODO 非常不建议使用startActivityForResult，已测试。
            } else {
                Toast.makeText(SpecialAct.this, "双屏异显-授权通过，可以使用了", Toast.LENGTH_SHORT).show();
                //TODO do something you need
            }
        }
    }


    private void requestWriteSettings() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, REQUEST_WRITE_SETTINGS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_WRITE_SETTINGS://系统设置
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (Settings.System.canWrite(this)) {
                        Toast.makeText(SpecialAct.this, "系统设置-授权通过", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SpecialAct.this, "系统设置-授权拒绝", Toast.LENGTH_SHORT).show();
                    }
                }
                break;

        }

    }
}
