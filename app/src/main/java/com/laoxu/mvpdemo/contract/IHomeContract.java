package com.laoxu.mvpdemo.contract;

import android.content.Context;

import com.laoxu.mvpdemo.model.entity.HomeEntity;

public interface IHomeContract {
    interface IModel{

        void getHome(ModelCallback modelCallback);//获取首页数据
        interface ModelCallback{
            void success(HomeEntity homeEntity);
            void error(Throwable throwable);
        }
    }

    interface IView{
        void success(HomeEntity homeEntity);
        void error(Throwable throwable);
    }

    interface IPresenter{

        void getHome();//获取首页数据
    }
}
