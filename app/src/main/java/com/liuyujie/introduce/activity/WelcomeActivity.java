package com.liuyujie.introduce.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.liuyujie.introduce.R;
import com.liuyujie.introduce.views.CircularImage;

public class WelcomeActivity extends AppCompatActivity {
    private boolean isFirstIn = false;
    private static final int TIME=3000;
    private static final int GO_HOME = 1000;
    private static final int GO_GUIDE = 1001;
    private TextView versionTxt;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case GO_HOME:
                    goHome();
                    break;
                case GO_GUIDE:
                    goGuide();
                    break;
            }
        }
    };

    private void goGuide() {
        Intent intent = new Intent(this,GuideActivity.class);
        startActivity(intent);
        finish();
    }

    private void goHome() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void init(){
        SharedPreferences preferences = getSharedPreferences("firstIn", MODE_PRIVATE);
        isFirstIn = preferences.getBoolean("firstIn",true);
        mHandler.sendEmptyMessageDelayed(GO_GUIDE,TIME);
//        if(!isFirstIn){
//            mHandler.sendEmptyMessageDelayed(GO_HOME,TIME);
//        }else {
//            SharedPreferences.Editor editor = preferences.edit();
//            editor.putBoolean("firstIn",false);
//            editor.commit();
//        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        init();
        getVersion();
        setPersonImg();
    }

    private void setPersonImg() {
        CircularImage headImg = (CircularImage) findViewById(R.id.circleImg);
        headImg.setImageResource(R.drawable.me);
    }


    public void getVersion() {
        PackageManager packageManager = getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            int versionCode = packageInfo.versionCode;
            versionTxt = (TextView) findViewById(R.id.versiontxt);
            versionTxt.setText("2016/5/1  v"+(double)versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
