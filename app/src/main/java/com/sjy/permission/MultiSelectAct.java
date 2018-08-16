package com.sjy.permission;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.sjy.permission.adpapter.MultiAdapter;
import com.sjy.permission.utils.DataBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MultiSelectAct extends AppCompatActivity {
    @BindView(R.id.tv_show)
    RecyclerView tv_show;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    // =======================================================
    private List<DataBean> lists = new ArrayList<>();
    MultiAdapter adapter;
    LinearLayoutManager manager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_list);
        ButterKnife.bind(this);

        lists = (List<DataBean>) getIntent().getExtras().getSerializable("bean");
        if (lists == null || lists.size() <= 0) {
            Toast.makeText(this, "没有可用的权限了，请删除软件重新安装测试", Toast.LENGTH_LONG).show();
            return;
        }
        createAdapter();
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
            public void onItemClickListener(DataBean bean) {
                backAct(bean);
            }
        });
    }

    private void backAct(DataBean bean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("bean", bean);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        setResult(102, intent);
        this.finish();
    }

}
