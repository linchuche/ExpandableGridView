package com.comslin.expandablegv;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        mRv.setLayoutManager(gridLayoutManager);
        mRv.setAdapter(new GridAdapter(this));
    }

    private void initView() {
        mRv = (RecyclerView) findViewById(R.id.rv);
    }
}
