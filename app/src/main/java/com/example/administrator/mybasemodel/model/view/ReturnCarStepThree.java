package com.example.administrator.mybasemodel.model.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.mybasemodel.R;
import com.example.administrator.mybasemodel.base.view.BaseRetureCarPager;
import com.example.administrator.mybasemodel.utils.Params;

/**
 * 作    者： Created by Administrator
 * 时    间： 2018/4/12 14:22
 * 描    述：
 * 版    权： 云杉智慧新能源技术有限公司版权所有
 */


public class ReturnCarStepThree extends BaseRetureCarPager {
    LinearLayout llThreeCar;
    LinearLayout llThreeTips;
    private AnimatorSet beginAnimator;
    private AnimatorSet exitAnimator;
    private int tipWidth;
    private int tipHeight;
    private int carWidth;
    private int carHeight;

    public ReturnCarStepThree(String title, int index, Context context) {
        super(title, index, context);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.pager_return_car_three;
    }

    @Override
    public void initView(View root) {
        llThreeCar = root.findViewById(R.id.ll_three_car);
        llThreeTips = root.findViewById(R.id.ll_three_tips);
        llThreeCar.setVisibility(View.INVISIBLE);
        llThreeTips.setVisibility(View.INVISIBLE);
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
            llThreeTips.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    llThreeTips.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    llThreeCar.post(new Runnable() {
                        @Override
                        public void run() {
                            beginAnimator = new AnimatorSet();
                            tipWidth = llThreeTips.getMeasuredWidth();
                            tipHeight = llThreeTips.getMeasuredHeight();
                            carWidth = llThreeCar.getMeasuredWidth();
                            carHeight = llThreeCar.getMeasuredHeight();
                            ObjectAnimator carTransY = ObjectAnimator.ofFloat(llThreeCar, "translationY", -carHeight, 0f);
                            ObjectAnimator tipTransY = ObjectAnimator.ofFloat(llThreeTips, "translationY", tipWidth, 0f);
                            beginAnimator.play(carTransY).with(tipTransY);
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
                                    llThreeTips.setVisibility(View.VISIBLE);
                                    llThreeCar.setVisibility(View.VISIBLE);
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
        ObjectAnimator carTransY = ObjectAnimator.ofFloat(llThreeCar, "translationY", 0f, -carHeight);
        ObjectAnimator tipTransY = ObjectAnimator.ofFloat(llThreeTips, "translationY", 0, tipWidth);
        exitAnimator.play(carTransY).with(tipTransY);
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
