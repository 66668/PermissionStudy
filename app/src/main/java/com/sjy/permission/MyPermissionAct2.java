package com.sjy.permission;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sjy.permission.type1.BaseAct;
import com.sjy.permission.type1.MyPermissionListener;
import com.sjy.permission.type2.PermissionHelper;
import com.sjy.permission.type2.PermissionInterface;
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
 * 方式2：将方法封装在工具类中，使act界面整洁
 */
public class MyPermissionAct2 extends BaseAct {
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
    PermissionHelper permissionHelper;

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
                toStartPermission();

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

    private void toStartPermission() {
        permissionHelper = new PermissionHelper(this, new PermissionInterface() {
            @Override
            public int getPermissionsRequestCode() {
                //设置权限请求requestCode，只有不跟onRequestPermissionsResult方法中的其他请求码冲突即可。
                return 10002;
            }

            @Override
            public String[] getPermissions() {
                //设置该界面所需的全部权限
                return permissionArray;
            }

            @Override
            public void requestPermissionsSuccess() {
                //权限请求用户已经全部允许
                Snackbar.make(layout, "MyPermissionAct2--权限通过", Snackbar.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void requestPermissionsFail() {
                Snackbar.make(layout, "有权限拒绝", Snackbar.LENGTH_SHORT)
                        .show();
            }

        });
        //发起调用：
        permissionHelper.requestPermissions();
    }

    //权限回调处理
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (permissionHelper.requestPermissionsResult(requestCode, permissions, grantResults)) {
            //权限请求已处理，不用再处理
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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
