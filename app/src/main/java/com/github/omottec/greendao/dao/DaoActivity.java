package com.github.omottec.greendao.dao;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.github.omottec.greendao.R;


/**
 * Created by qinbingbing on 07/04/2017.
 */

public class DaoActivity extends AppCompatActivity implements View.OnClickListener {
    private View mRootView;
    private TextView mUserTv;
    private TextView mNoteTv;
    private TextView mGoodTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_dao);
        mUserTv = (TextView) findViewById(R.id.tv_user);
        mNoteTv = (TextView) findViewById(R.id.tv_note);
        mGoodTv = (TextView) findViewById(R.id.tv_good);
        mUserTv.setOnClickListener(this);
        mNoteTv.setOnClickListener(this);
        mGoodTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.tv_user:
                intent = new Intent(this, UserActivity.class);
                break;
            case R.id.tv_note:
                intent = new Intent(this, NoteActivity.class);
                break;
            case R.id.tv_good:
                intent = new Intent(this, GoodActivity.class);
                break;
        }
        startActivity(intent);
    }
}
