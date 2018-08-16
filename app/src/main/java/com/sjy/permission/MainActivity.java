package com.sjy.permission;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.sjy.permission.utils.DataBean;
import com.sjy.permission.utils.DataUtils;
import com.sjy.permission.utils.PermissionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    //=======================================================

    @BindView(R.id.btn_single)
    Button btn_single;

    @BindView(R.id.btn_multi)
    Button btn_multi;

    @BindView(R.id.btn_single_1)
    Button btn_single_1;

    @BindView(R.id.btn_setting1)
    Button btn_setting1;

    @BindView(R.id.btn_setting2)
    Button btn_setting2;

    @BindView(R.id.btn_setting3)
    Button btn_setting3;

    @BindView(R.id.btn_other)
    Button btn_other;

    //=======================================================
    private List<DataBean> lists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        createData();
    }

    @OnClick({R.id.btn_single, R.id.btn_multi, R.id.btn_other, R.id.btn_single_1, R.id.btn_setting1, R.id.btn_setting2, R.id.btn_setting3})
    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.btn_setting1://权限设置1
                new PermissionUtils(this).goPermissionSettings();//方式1
                // new PermissionUtils(this).goIntentSetting();//方式2

                break;
            case R.id.btn_setting2://权限设置2
                new PermissionUtils(this).getAppDetailSettingIntent();
                break;
            case R.id.btn_setting3://系统应用管理
                new PermissionUtils(this).getSystemManageIntent();
                break;

            case R.id.btn_single://单个权限

                Bundle bundle = new Bundle();
                bundle.putSerializable("bean", (Serializable) lists);
                startActForResult(SingleAct.class, bundle);
                break;

            case R.id.btn_multi://多个权限


                Bundle bundle2 = new Bundle();
                bundle2.putSerializable("bean", (Serializable) lists);
                startActForResult(MultiAct.class, bundle2);
                break;

            case R.id.btn_single_1://自定义权限

                startAct(SpecialAct.class);
                break;


            case R.id.btn_other://特殊权限

                startAct(SpecialAct.class);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }

        refreshData(data);
    }

    //=======================================================

    private void createData() {
        lists = DataUtils.createData();
    }


    private void refreshData(Intent data) {
        List<DataBean> datalists = (List<DataBean>) data.getExtras().getSerializable("bean");
        lists = DataUtils.getUnUseData(datalists);
        Log.d("SJY", "main 返回=" + lists.size());
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
