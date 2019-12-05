package com.laoxu.mvpdemo.model;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.laoxu.mvpdemo.api.Api;
import com.laoxu.mvpdemo.app.App;
import com.laoxu.mvpdemo.contract.IHomeContract;
import com.laoxu.mvpdemo.model.entity.HomeEntity;

public class HomeModel implements IHomeContract.IModel {
    @Override
    public void getHome(final ModelCallback modelCallback) {


        //队列

        RequestQueue requestQueue = Volley.newRequestQueue(App.getContext());

        //请求对象
        StringRequest stringRequest = new StringRequest(Api.HOME_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                HomeEntity homeEntity = new Gson().fromJson(response,HomeEntity.class);

                modelCallback.success(homeEntity);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        //加入队列
        requestQueue.add(stringRequest);
    }
}
