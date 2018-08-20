package com.sjy.permission;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpecialAct extends AppCompatActivity {
    @BindView(R.id.tv_show1)
    TextView tv_show1;

    @BindView(R.id.tv_show2)
    TextView tv_show2;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        ButterKnife.bind(this);
    }
}
