package com.example.administrator.mybasemodel.model.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.mybasemodel.R;
import com.example.administrator.mybasemodel.SecondActivity;
import com.example.administrator.mybasemodel.base.view.BaseRetureCarPager;
import com.example.administrator.mybasemodel.utils.LogUtils;
import com.example.administrator.mybasemodel.utils.Params;

/**
 * 作    者： Created by Administrator
 * 时    间： 2018/4/12 14:22
 * 描    述：
 * 版    权： 云杉智慧新能源技术有限公司版权所有
 */


public class ReturnCarStepTwo extends BaseRetureCarPager {

    private LinearLayout llSecondTips;
    private ImageView ivSecondCar;
    private AnimatorSet beginAnimator;
    private AnimatorSet exitAnimator;
    private int tipWidth;
    private int tipHeight;
    private int carWidth;
    private int carHeight;

    public ReturnCarStepTwo(String title, int index, Context context) {
        super(title, index, context);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.pager_return_car_two;
    }

    @Override
    public void initView(View root) {
        llSecondTips = root.findViewById(R.id.ll_second_tips);
        ivSecondCar = root.findViewById(R.id.iv_second_car);
        llSecondTips.setVisibility(View.INVISIBLE);
        ivSecondCar.setVisibility(View.INVISIBLE);
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
            llSecondTips.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    llSecondTips.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    ivSecondCar.post(new Runnable() {
                        @Override
                        public void run() {
                            beginAnimator = new AnimatorSet();
                            tipWidth = llSecondTips.getMeasuredWidth();
                            tipHeight = llSecondTips.getMeasuredHeight();
                            carWidth = ivSecondCar.getMeasuredWidth();
                            carHeight = ivSecondCar.getMeasuredHeight();
                            ObjectAnimator carTransX = ObjectAnimator.ofFloat(ivSecondCar, "translationX", carWidth, 0f);
                            ObjectAnimator tipTransX = ObjectAnimator.ofFloat(llSecondTips, "translationX", -tipWidth, 0f);
                            beginAnimator.play(carTransX).with(tipTransX);
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
                                    llSecondTips.setVisibility(View.VISIBLE);
                                    ivSecondCar.setVisibility(View.VISIBLE);
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
        ObjectAnimator carTransX = ObjectAnimator.ofFloat(ivSecondCar, "translationX", 0f, carWidth);
        ObjectAnimator tipTransX = ObjectAnimator.ofFloat(llSecondTips, "translationX", 0, -tipWidth);
        exitAnimator.play(carTransX).with(tipTransX);
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
