package com.liuyujie.introduce.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.liuyujie.introduce.R;
import com.liuyujie.introduce.adapter.GuideViewPagerAdapter;

import java.util.ArrayList;

public class GuideActivity extends AppCompatActivity {
    private ViewPager vp;
    private GuideViewPagerAdapter viewPagerAdapter;
    private ArrayList<View> viewArrayList;
    private ImageView[] dots;
    private int[] ids = {R.id.iv1,R.id.iv2,R.id.iv3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        initDots();
        initAdapter();
    }

    private void initDots() {
        dots = new ImageView[viewArrayList.size()];
        for (int i=0;i<viewArrayList.size();i++){
            dots[i] = (ImageView) findViewById(ids[i]);
        }
    }

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

    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(this);
        viewArrayList = new ArrayList<>();
        viewArrayList.add(inflater.inflate(R.layout.guide_img_one,null));
        viewArrayList.add(inflater.inflate(R.layout.guide_img_two,null));
        viewArrayList.add(inflater.inflate(R.layout.guide_img_three,null));
        vp = (ViewPager) findViewById(R.id.guideViewPager);
    }
}
