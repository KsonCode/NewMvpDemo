package com.laoxu.mvpdemo.base.mvp;

import java.lang.ref.WeakReference;

/**
 * 抽象p层基类
 */
public abstract class BasePresenter<M extends IBaseModel,V extends IBaseView> {

    public M model;
    public WeakReference<V> weakReference;

    public BasePresenter(){

        model = initModel();

    }

    /**
     * view不通过构造传递，需要通过单独的绑定方法进行传递
     * @param v
     */
    public void attachView(V v){
        weakReference = new WeakReference<>(v);
    }


    protected abstract M initModel();

    /**
     * 销毁view，释放资源，解绑
     */
    public void detach(){

        if (weakReference!=null){
            weakReference.clear();
            weakReference = null;
        }
    }

    /**
     * 暴露v层给调用者（子类）使用
     * @return
     */
    public V getView(){
        return weakReference.get();//得到包装的对象
    }

}
