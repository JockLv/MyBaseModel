package com.example.administrator.mybasemodel.model.view;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.ImageView;

import com.example.administrator.mybasemodel.R;
import com.example.administrator.mybasemodel.base.view.BaseRetureCarPager;
import com.example.administrator.mybasemodel.utils.DensityUtil;
import com.example.administrator.mybasemodel.utils.LogUtils;
import com.example.administrator.mybasemodel.utils.Params;

/**
 * 作    者： Created by Administrator
 * 时    间： 2018/4/12 14:22
 * 描    述：
 * 版    权： 云杉智慧新能源技术有限公司版权所有
 */


public class ReturnCarStepOne extends BaseRetureCarPager {

    private ImageView ivFirstRect;
    private ImageView ivFirstCar;
    private AnimatorSet beginAnimator;
    private AnimatorSet exitAnimator;
    private int rectWidth;
    private int rectHeight;
    private int carWidth;
    private int carHeight;
    private int titleBarHeight;

    public ReturnCarStepOne(String title, int index, Context context) {
        super(title, index, context);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.pager_return_car_one;
    }

    @Override
    public void initView(View root) {
        ivFirstRect = root.findViewById(R.id.iv_first_rect);
        ivFirstCar = root.findViewById(R.id.iv_first_car);
        titleBarHeight = DensityUtil.dip2px(mContext, 100);
    }

    @Override
    public boolean beginAnimation(final OnReturnCarAnimationListener listener) {
        if(beginAnimator != null) {
            beginAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    if (listener != null) {
                        listener.onReturnCarAnimationEnd();
                    }
                }

                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    if (listener != null) {
                        listener.onReturnCarAnimationStart();
                    }
                }
            });
            beginAnimator.start();
        }else {
        ivFirstRect.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ivFirstRect.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                ivFirstCar.post(new Runnable() {
                    @Override
                    public void run() {
                        beginAnimator = new AnimatorSet();
                        rectWidth = ivFirstRect.getMeasuredWidth();
                        rectHeight = ivFirstRect.getMeasuredHeight();
                        carWidth = ivFirstCar.getMeasuredWidth();
                        carHeight = ivFirstCar.getMeasuredHeight();
                        ObjectAnimator carTransY = ObjectAnimator.ofFloat(ivFirstCar, "translationY", carHeight, 0f);
                        ObjectAnimator rectScaleX = ObjectAnimator.ofFloat(ivFirstRect, "scaleX", (float) windowWidth / rectWidth, 1f);
                        ObjectAnimator rectScaleY = ObjectAnimator.ofFloat(ivFirstRect, "scaleY", (float) windowHeight / rectHeight, 1f);
                        ObjectAnimator rectTransY = ObjectAnimator.ofFloat(ivFirstRect, "translationY", -rectHeight, 0f);
                        beginAnimator.play(rectScaleX).with(rectScaleY).with(rectTransY).with(carTransY);
                        beginAnimator.setDuration(Params.RETURN_CAR_TIME);
                        beginAnimator.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                if (listener != null) {
                                    listener.onReturnCarAnimationEnd();
                                }
                            }

                            @Override
                            public void onAnimationStart(Animator animation) {
                                super.onAnimationStart(animation);
                                if (listener != null) {
                                    listener.onReturnCarAnimationStart();
                                }
                            }
                        });
                        beginAnimator.start();
                    }
                });
            }
            });
        }
        return true;
    }

    @Override
    public boolean exitAnimation(final OnReturnCarAnimationListener listener) {
        exitAnimator = new AnimatorSet();
        ObjectAnimator carTransY = ObjectAnimator.ofFloat(ivFirstCar, "translationY", 0f, -carHeight);
        ObjectAnimator carAlpha = ObjectAnimator.ofFloat(ivFirstRect, "alpha", 1.0f, 0.0f);
        exitAnimator.play(carAlpha).with(carTransY);
        exitAnimator.setDuration(Params.RETURN_CAR_TIME);
        exitAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (listener != null) {
                    listener.onReturnCarAnimationEnd();
                }
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                if (listener != null) {
                    listener.onReturnCarAnimationStart();
                }
            }
        });
        exitAnimator.start();
        return true;
    }

    @Override
    public boolean killAnimation() {
        if(beginAnimator != null) {
            beginAnimator.cancel();
            beginAnimator = null;
        }
        if (exitAnimator != null) {
            exitAnimator.cancel();
            exitAnimator = null;
        }
        return true;
    }
}
