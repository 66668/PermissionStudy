package com.sjy.permission;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sjy.permission.type1.BaseAct;
import com.sjy.permission.type1.MyPermissionListener;
import com.sjy.permission.utils.DataBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 权限封装：使用原生方式，需要每一次申请都要注意避免特殊情况，比较麻烦，本类将使用自定义封装的样式演示，一劳永逸
 * <p>
 * 方式1：将方法封装的act基类，利用监听处理
 */
public class MyPermissionAct extends BaseAct {
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
    private List<DataBean> lists = new ArrayList<>();
    private List<DataBean> selectList = new ArrayList<>();
    private String[] permissionArray;
    private String[] deniedArray;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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

                //封装权限使用，拒绝的情况已封装在base中，不需要自己再处理
                requestPermission(permissionArray, layout, new MyPermissionListener() {
                    @Override
                    public void onGranted() {
                        //权限通过，使用功能
                        Snackbar.make(layout, "MyPermissionAct--权限通过", Snackbar.LENGTH_SHORT)
                                .show();
                    }
                });
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
                break;
        }
    }


    // =========================权限相关代码==============================

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
