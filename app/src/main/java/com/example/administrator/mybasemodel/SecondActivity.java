package com.example.administrator.mybasemodel;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.administrator.mybasemodel.base.view.BaseActivity;
import com.example.administrator.mybasemodel.base.view.BaseRetureCarPager;
import com.example.administrator.mybasemodel.model.view.ReturnCarStepFour;
import com.example.administrator.mybasemodel.model.view.ReturnCarStepOne;
import com.example.administrator.mybasemodel.model.view.ReturnCarStepThree;
import com.example.administrator.mybasemodel.model.view.ReturnCarStepTwo;
import com.example.administrator.mybasemodel.mvp.presenter.impl.MvpBasePresenter;
import com.example.administrator.mybasemodel.utils.ToastUtils;
import com.example.administrator.mybasemodel.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作    者： Created by Administrator
 * 时    间： 2018/4/12 10:47
 * 描    述：
 * 版    权： 云杉智慧新能源技术有限公司版权所有
 */


public class SecondActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.vp_return_car)
    NoScrollViewPager vpReturnCar;

    private Signal process;

    private List<BaseRetureCarPager> carPagerList;
    private ReturnCarStepOne carStepOne;
    private ReturnCarStepTwo carStepTwo;
    private ReturnCarStepThree carStepThree;
    private ReturnCarStepFour carStepFour;
    private MyReturnCarAdapter adapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_second;
    }

    @Override
    public MvpBasePresenter bindPresenter() {
        return null;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        carPagerList = new ArrayList<>();
        carStepOne = new ReturnCarStepOne("还车1", 0, this);
        carStepTwo = new ReturnCarStepTwo("还车2", 1, this);
        carStepThree = new ReturnCarStepThree("还车3", 2, this);
        carStepFour = new ReturnCarStepFour("还车4", 3, this);
        carPagerList.add(carStepOne);
        carPagerList.add(carStepTwo);
        carPagerList.add(carStepThree);
        carPagerList.add(carStepFour);
        adapter = new MyReturnCarAdapter(carPagerList, this);
        vpReturnCar.setAdapter(adapter);
        vpReturnCar.setOffscreenPageLimit(0);
        process = Signal.ONE;
        btnNext.setOnClickListener(this);
        setDefaultIndex(process);
    }

    private void setDefaultIndex(Signal processIndex) {
        switch (processIndex) {
            case ONE:
                vpReturnCar.setCurrentItem(0, false);
                carStepOne.beginAnimation(new BaseRetureCarPager.OnReturnCarAnimationListener() {
                    @Override
                    public void onReturnCarAnimationStart() {
                        btnNext.setClickable(false);
                        btnNext.setText("不可用");
                    }

                    @Override
                    public void onReturnCarAnimationEnd() {
                        btnNext.setClickable(true);
                        btnNext.setText("可用");
                    }
                });
                break;
            case TWO:
                vpReturnCar.setCurrentItem(1, false);
                carStepTwo.beginAnimation(new BaseRetureCarPager.OnReturnCarAnimationListener() {
                    @Override
                    public void onReturnCarAnimationStart() {
                        btnNext.setClickable(false);
                        btnNext.setText("不可用");
                    }

                    @Override
                    public void onReturnCarAnimationEnd() {
                        btnNext.setClickable(true);
                        btnNext.setText("可用");
                    }
                });
                break;
            case THREE:
                vpReturnCar.setCurrentItem(2, false);
                carStepThree.beginAnimation(new BaseRetureCarPager.OnReturnCarAnimationListener() {
                    @Override
                    public void onReturnCarAnimationStart() {
                        btnNext.setClickable(false);
                        btnNext.setText("不可用");
                    }

                    @Override
                    public void onReturnCarAnimationEnd() {
                        btnNext.setClickable(true);
                        btnNext.setText("可用");
                    }
                });
                break;
            case FOUR:
                vpReturnCar.setCurrentItem(3, false);
                carStepFour.beginAnimation(new BaseRetureCarPager.OnReturnCarAnimationListener() {
                    @Override
                    public void onReturnCarAnimationStart() {
                        btnNext.setClickable(false);
                        btnNext.setText("不可用");
                    }

                    @Override
                    public void onReturnCarAnimationEnd() {
                        btnNext.setClickable(true);
                        btnNext.setText("可用");
                    }
                });
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                switch (process) {
                    case ONE:
                        /**
                         * 下一步
                         */
                        process = Signal.TWO;
                        carStepOne.exitAnimation(new BaseRetureCarPager.OnReturnCarAnimationListener() {
                            @Override
                            public void onReturnCarAnimationStart() {

                            }

                            @Override
                            public void onReturnCarAnimationEnd() {
                                carStepOne.killAnimation();
                                setDefaultIndex(process);
                            }
                        });
                        break;
                    case TWO:
                        /**
                         * 下一步
                         */
                        process = Signal.THREE;
                        carStepTwo.exitAnimation(new BaseRetureCarPager.OnReturnCarAnimationListener() {
                            @Override
                            public void onReturnCarAnimationStart() {

                            }

                            @Override
                            public void onReturnCarAnimationEnd() {
                                carStepTwo.killAnimation();
                                setDefaultIndex(process);
                            }
                        });
                        break;
                    case THREE:
                        /**
                         * 立即还车
                         */
                        process = Signal.FOUR;
                        carStepThree.exitAnimation(new BaseRetureCarPager.OnReturnCarAnimationListener() {
                            @Override
                            public void onReturnCarAnimationStart() {

                            }

                            @Override
                            public void onReturnCarAnimationEnd() {
                                carStepThree.killAnimation();
                                setDefaultIndex(process);
                            }
                        });
                        break;
                    case FOUR:
                        /**
                         *
                         */
                        ToastUtils.shortMessage("支付");
//                        carStepThree.exitAnimation(new BaseRetureCarPager.OnReturnCarAnimationListener() {
//                            @Override
//                            public void onReturnCarAnimationStart() {
//
//                            }
//
//                            @Override
//                            public void onReturnCarAnimationEnd() {
//                                carStepThree.killAnimation();
//                                setDefaultIndex(process);
//                            }
//                        });
                        break;

                }
                break;
        }
    }

    enum Signal {
        ONE, TWO, THREE, FOUR
    }

    class MyReturnCarAdapter extends PagerAdapter {

        private List<BaseRetureCarPager> carPagerList;
        private Context context;

        public MyReturnCarAdapter(List<BaseRetureCarPager> carPagerList, Context context) {
            this.carPagerList = carPagerList;
            this.context = context;
        }

        @Override
        public int getCount() {
            return carPagerList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BaseRetureCarPager carPager = carPagerList.get(position);
            container.addView(carPager.rootView);
            return carPager.rootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView(carPagerList.get(position).rootView);
        }
    }



}
