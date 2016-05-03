package com.liuyujie.introduce.activity;

import android.content.Intent;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.liuyujie.introduce.R;
import com.liuyujie.introduce.adapter.GuideViewPagerAdapter;
import com.liuyujie.introduce.animations.RokeAnimation;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

public class GuideActivity extends AppCompatActivity {
    private ViewPager vp;
    private GuideViewPagerAdapter viewPagerAdapter;
    private ArrayList<View> viewArrayList;
    private ImageView[] dots;
    private int[] ids = {R.id.iv1,R.id.iv2,R.id.iv3};
    private Timer timer;
    private Button goMainBtn;
    private boolean isOpen = true;
    private int index = 0;
    /**
     * 引导页1的浮动小球
     */
    public android.os.Handler mHandler = new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
                switch (msg.what){
                    case 1:
                        goMainBtn.clearAnimation();
                        goMainBtn.setAnimation(AnimationUtils.loadAnimation(GuideActivity.this,R.anim.push_in));
                        break;
                    case 2:
                        goMainBtn.clearAnimation();
                        goMainBtn.setAnimation(AnimationUtils.loadAnimation(GuideActivity.this,R.anim.push_out));
                        break;
                }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        initDots();
        initAdapter();
        initTimer();
        initOne();
        initThree();
    }

    private void initOne() {
        RokeAnimation ra = new RokeAnimation();
        ra.setDuration(1000);
        ra.setRepeatCount(-1);
        viewArrayList.get(0).findViewById(R.id.personPop).setAnimation(ra);
        viewArrayList.get(0).findViewById(R.id.sexPop).setAnimation(ra);
        viewArrayList.get(0).findViewById(R.id.agePop).setAnimation(ra);
    }

    private void initTimer() {
        timer = new Timer(true);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if(isOpen){
                    if(index==2){
                        index=0;
                    }
                    index++;
                    Message message = new Message();
                    message.what = index;
                    mHandler.sendMessage(message);
                }
            }
        };
        timer.schedule(task,0,4000);
    }

    /**
     * 初始化引导页3
     */
    private void initThree() {
        goMainBtn = (Button) viewArrayList.get(2).findViewById(R.id.goMainBtn);
        goMainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GuideActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    /**
     * 初始化底部导航点
     */
    private void initDots() {
        dots = new ImageView[viewArrayList.size()];
        for (int i=0;i<viewArrayList.size();i++){
            dots[i] = (ImageView) findViewById(ids[i]);
        }
    }

    /**
     * 设置ViewPager适配器及滑屏事件监听
     */
    private void initAdapter() {
        viewPagerAdapter = new GuideViewPagerAdapter(viewArrayList,this);
        vp.setAdapter(viewPagerAdapter);
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i=0;i<ids.length;i++){
                    if(position==i){
                        dots[i].setBackgroundResource(R.drawable.point_select);
                    }else {
                        dots[i].setBackgroundResource(R.drawable.point_normal);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 初始化视图
     */
    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(this);
        viewArrayList = new ArrayList<>();
        viewArrayList.add(inflater.inflate(R.layout.guide_img_one,null));
        viewArrayList.add(inflater.inflate(R.layout.guide_img_two,null));
        viewArrayList.add(inflater.inflate(R.layout.guide_img_three,null));
        vp = (ViewPager) findViewById(R.id.guideViewPager);
    }

    @Override
    protected void onDestroy() {
        isOpen = false;
        if (timer != null) {
            timer.cancel();// 退出计时器
        }
        timer = null;
        super.onDestroy();
    }
}
