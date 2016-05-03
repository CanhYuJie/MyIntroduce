package com.liuyujie.introduce.animations;

import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by Administrator on 2016/5/3.
 */
public class RokeAnimation extends Animation {

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {

        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        t.getMatrix().setTranslate((float)Math.sin(interpolatedTime*10)*10,0);
        super.applyTransformation(interpolatedTime, t);
    }
}
