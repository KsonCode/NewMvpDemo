package com.laoxu.mvpdemo.presenter;

import android.content.Context;

import com.laoxu.mvpdemo.contract.IHomeContract;
import com.laoxu.mvpdemo.model.HomeModel;
import com.laoxu.mvpdemo.model.entity.HomeEntity;

public class HomePresenter implements IHomeContract.IPresenter {

    private HomeModel homeModel;
    private IHomeContract.IView iView;
    public HomePresenter(IHomeContract.IView iView){
        this.iView = iView;
        homeModel = new HomeModel();

    }
    @Override
    public void getHome() {

        homeModel.getHome(new IHomeContract.IModel.ModelCallback() {
            @Override
            public void success(HomeEntity homeEntity) {
                iView.success(homeEntity);
            }

            @Override
            public void error(Throwable throwable) {

                iView.error(throwable);
            }
        });


    }
}
