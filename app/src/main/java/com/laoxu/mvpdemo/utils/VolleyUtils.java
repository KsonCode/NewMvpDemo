package com.laoxu.mvpdemo.utils;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.laoxu.mvpdemo.app.App;

import java.util.Map;

/**
 * volley
 */
public class VolleyUtils {

    private RequestQueue requestQueue;//volley请求队列
    //双重检验锁的单例模式---企业中，让你手写单例模式
    //属性私有
    private static VolleyUtils instance;

    //构造方法私有,防止外界（调用者）new 出新的对象
    private VolleyUtils() {

        requestQueue = Volley.newRequestQueue(App.getContext());

    }

    /**
     * 暴露公共方法，创建私有对象，供外部调用，双重：两次判断，检验锁：同步锁
     *
     * @return
     */
    public static VolleyUtils getInstance() {
        //第一重
        if (instance == null) {
            //加锁：为了解决多线程并发安全
            synchronized (VolleyUtils.class) {
                //二重
                if (instance == null) {
                    instance = new VolleyUtils();
                }
            }
        }
        return instance;
    }

    /**
     * volley的get请求
     */
    public void doGet(final Map<String, String> params, String url, final VolleyCallback volleyCallback) {

        String endUrl = url+"?";

        if (params!=null&&params.size()>0){
            for (Map.Entry<String, String> stringStringEntry : params.entrySet()) {

                endUrl+=stringStringEntry.getKey()+"="+stringStringEntry.getValue()+"&";

            }
        }
        System.out.println("endurl==="+endUrl);
        //第二步
        StringRequest stringRequest = new StringRequest(StringRequest.Method.GET, endUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (volleyCallback != null) {
                    volleyCallback.success(response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (volleyCallback != null) {
                    volleyCallback.failure(error);
                }

            }
        });
        //第三步
        requestQueue.add(stringRequest);

    }

    /**
     * volley的post请求
     */
    public void doPost(final Map<String, String> params, String url, final VolleyCallback volleyCallback) {

        //第二步
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (volleyCallback != null) {
                    volleyCallback.success(response);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (volleyCallback != null) {
                    volleyCallback.failure(error);
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        //第三步
        requestQueue.add(stringRequest);
    }


    /**
     * 回调volley响应数据的
     */
    public interface VolleyCallback {
        void success(String response);

        void failure(Throwable error);
    }


}
