package com.sjy.permission;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sjy.permission.utils.DataBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SingleAct extends AppCompatActivity {
    //=======================================================
    @BindView(R.id.tv_show1)
    TextView tv_show1;

    @BindView(R.id.btn_select)
    Button btn_select;

    @BindView(R.id.btn_apply)
    Button btn_apply;
    //=======================================================
    private List<DataBean> lists = new ArrayList<>();
    String permissionStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_single);
        ButterKnife.bind(this);
        getIntentData();
    }

    @OnClick({R.id.btn_select, R.id.btn_apply})
    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.btn_select://选择权限
                if (lists == null || lists.size() <= 0) {
                    Toast.makeText(this, "没有可用的权限了，请删除软件重新安装测试", Toast.LENGTH_LONG).show();
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable("bean", (Serializable) lists);
                startActForResult(SingleSelectAct.class, bundle);
                break;

            case R.id.btn_apply://申请
                //
                permissionStr = tv_show1.getText().toString();
                if (permissionStr == null || permissionStr.isEmpty()) {
                    Toast.makeText(this, "请选择权限", Toast.LENGTH_LONG).show();
                    return;
                }
                applyPermission();
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        getSelectData(data);
    }

    //=========================和权限相关的代码==============================

    private void applyPermission() {

    }


    // =========================private==============================
    private void getIntentData() {
        lists = (List<DataBean>) getIntent().getExtras().getSerializable("bean");
        if (lists == null || lists.size() <= 0) {
            Toast.makeText(this, "没有可用的权限了，请删除软件重新安装测试", Toast.LENGTH_LONG).show();
        }
    }

    private void getSelectData(Intent data) {
        DataBean bean = (DataBean) data.getExtras().getSerializable("bean");
        if (bean == null) {
            Toast.makeText(this, "选择数据返回为空", Toast.LENGTH_SHORT).show();
            return;
        }
        permissionStr = bean.getPermission();

        showData(permissionStr);
    }

    private void showData(String permissionStr) {
        tv_show1.setText(permissionStr);
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
