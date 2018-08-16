package com.sjy.permission;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sjy.permission.utils.DataBean;
import com.sjy.permission.utils.DataUtils;

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

    @OnClick({R.id.btn_single, R.id.btn_multi, R.id.btn_other})
    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.btn_single://单个权限

                Bundle bundle = new Bundle();
                bundle.putSerializable("bean", (Serializable) lists);
                startAct(SingleAct.class, bundle);
                btn_single.setClickable(false);
                break;

            case R.id.btn_multi://多个权限

                Bundle bundle2 = new Bundle();
                bundle2.putSerializable("bean", (Serializable) lists);
                startAct(MultiAct.class, bundle2);
                btn_multi.setClickable(false);
                break;

            case R.id.btn_other://特殊权限

                startAct(SpecialAct.class);
                btn_other.setClickable(false);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        btn_single.setClickable(true);
        btn_multi.setClickable(true);
        btn_other.setClickable(true);
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
