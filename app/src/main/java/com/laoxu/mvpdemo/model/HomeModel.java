package com.laoxu.mvpdemo.model;

import com.google.gson.Gson;
import com.laoxu.mvpdemo.api.Api;
import com.laoxu.mvpdemo.contract.IHomeContract;
import com.laoxu.mvpdemo.model.entity.BannerEntity;
import com.laoxu.mvpdemo.model.entity.HomeEntity;
import com.laoxu.mvpdemo.utils.VolleyUtils;

import java.util.HashMap;

public class HomeModel implements IHomeContract.IModel {
    /**
     * 获取首页
     * @param modelCallback
     */
    @Override
    public void getHome(final ModelCallback modelCallback) {

        VolleyUtils.getInstance().doGet( Api.HOME_URL, new VolleyUtils.VolleyCallback() {
            @Override
            public void success(String response) {

                HomeEntity homeEntity = new Gson().fromJson(response,HomeEntity.class);
                modelCallback.success(homeEntity);

            }

            @Override
            public void failure(Throwable error) {

                modelCallback.error(error);

            }
        });

    }

    /**
     * 获取轮播图，ad
     * @param modelCallback
     */
    @Override
    public void getBanner(final ModelCallback modelCallback) {

        VolleyUtils.getInstance().doGet(Api.BANNER_URL, new VolleyUtils.VolleyCallback() {
            @Override
            public void success(String response) {

                BannerEntity bannerEntity = new Gson().fromJson(response, BannerEntity.class);
                modelCallback.success(bannerEntity);


            }

            @Override
            public void failure(Throwable error) {

                modelCallback.error(error);
            }
        });

    }
}
