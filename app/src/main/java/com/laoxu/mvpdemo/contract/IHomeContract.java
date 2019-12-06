package com.laoxu.mvpdemo.contract;

import android.content.Context;

import com.laoxu.mvpdemo.base.mvp.IBaseModel;
import com.laoxu.mvpdemo.base.mvp.IBaseView;
import com.laoxu.mvpdemo.model.entity.HomeEntity;

public interface IHomeContract {
    interface IModel extends IBaseModel {

        void getHome(ModelCallback modelCallback);//获取首页数据
        void getBanner(ModelCallback modelCallback);//获取广告（AD）banner数据
        interface ModelCallback{
            void success(Object data);
            void error(Throwable throwable);
        }


    }

    interface IView extends IBaseView {
        void success(Object data);
        void error(Throwable throwable);
    }

    interface IPresenter{

        void getHome();//获取首页数据
        void getBanner();//获取广告（AD）banner数据
    }
}
