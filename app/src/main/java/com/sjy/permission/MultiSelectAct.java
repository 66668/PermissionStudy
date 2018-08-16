package com.sjy.permission;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sjy.permission.adpapter.MultiAdapter;
import com.sjy.permission.utils.DataBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MultiSelectAct extends AppCompatActivity {
    @BindView(R.id.tv_show)
    TextView tv_show;

    @BindView(R.id.btn_back)
    Button btn_back;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    // =======================================================
    private List<DataBean> lists = new ArrayList<>();
    private List<DataBean> selectList = new ArrayList<>();
    int count = 0;
    MultiAdapter adapter;
    LinearLayoutManager manager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_list);
        ButterKnife.bind(this);

        tv_show.setVisibility(View.VISIBLE);
        btn_back.setVisibility(View.VISIBLE);

        lists = (List<DataBean>) getIntent().getExtras().getSerializable("bean");
        if (lists == null || lists.size() <= 0) {
            Toast.makeText(this, "没有可用的权限了，请删除软件重新安装测试", Toast.LENGTH_LONG).show();
            return;
        }
        createAdapter();
    }

    @OnClick(R.id.btn_back)
    public void onClicks(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                backAct();
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

    // =========================private==============================


    private void createAdapter() {
        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter = new MultiAdapter(this);
        adapter.setList(lists);
        recyclerView.setAdapter(adapter);

        //监听
        adapter.setOnItemClickListener(new MultiAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                showData(lists.get(position));
            }
        });
    }

    private void showData(DataBean bean) {
        if (selectList.size() <= 0) {
            selectList.add(bean);
            count++;
        } else {
            boolean isExist = false;
            for (DataBean item : selectList) {
                if (item.getPermission().equals(bean.getPermission())) {
                    isExist = true;
                }
            }
            if (!isExist) {
                selectList.add(bean);
                count++;
            }
        }

        tv_show.setText("已选中：" + count + "个");
    }

    private void backAct() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("bean", (Serializable) selectList);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        setResult(102, intent);
        this.finish();
    }

}
