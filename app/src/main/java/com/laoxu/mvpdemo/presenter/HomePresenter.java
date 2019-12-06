package com.laoxu.mvpdemo.presenter;

import android.content.Context;

import com.laoxu.mvpdemo.base.mvp.BasePresenter;
import com.laoxu.mvpdemo.contract.IHomeContract;
import com.laoxu.mvpdemo.model.HomeModel;
import com.laoxu.mvpdemo.model.entity.HomeEntity;

public class HomePresenter extends BasePresenter<HomeModel, IHomeContract.IView> implements IHomeContract.IPresenter {
    @Override
    public void getHome() {

        model.getHome(new IHomeContract.IModel.ModelCallback() {
            @Override
            public void success(Object data) {
                getView().success(data);
            }

            @Override
            public void error(Throwable throwable) {

                getView().error(throwable);
            }
        });

    }

    @Override
    public void getBanner() {

        model.getBanner(new IHomeContract.IModel.ModelCallback() {
            @Override
            public void success(Object data) {
                getView().success(data);
            }

            @Override
            public void error(Throwable throwable) {

                getView().error(throwable);

            }
        });

    }

    @Override
    protected HomeModel initModel() {
        return new HomeModel();
    }
}
