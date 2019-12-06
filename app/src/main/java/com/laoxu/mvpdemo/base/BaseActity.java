package com.laoxu.mvpdemo.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.laoxu.mvpdemo.base.mvp.BasePresenter;
import com.laoxu.mvpdemo.base.mvp.IBaseView;
import com.laoxu.mvpdemo.presenter.HomePresenter;

public abstract class BaseActity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {
    public P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayoutId());
        presenter = initPresenter();
        if (presenter!=null){
            //绑定view
            presenter.attachView(this);
        }
        initView();
        initData();


    }

    protected abstract void initData();


    protected abstract void initView();

    /**
     * 初始化p，让子类实现
     * @return
     */
    protected abstract P initPresenter();


    /**
     * 绑定根布局
     * @return
     */
    protected abstract int bindLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            //解绑
            presenter.detach();
        }
    }
}
