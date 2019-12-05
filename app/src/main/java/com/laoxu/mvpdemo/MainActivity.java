package com.laoxu.mvpdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.laoxu.mvpdemo.contract.IHomeContract;
import com.laoxu.mvpdemo.model.entity.HomeEntity;
import com.laoxu.mvpdemo.presenter.HomePresenter;

public class MainActivity extends AppCompatActivity implements IHomeContract.IView {


    private HomePresenter homePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homePresenter = new HomePresenter(this);

        homePresenter.getHome();

    }

    /**
     * 成功获取数据
     * @param homeEntity
     */
    @Override
    public void success(HomeEntity homeEntity) {

        Toast.makeText(this, homeEntity.getMessage(), Toast.LENGTH_SHORT).show();
    }

    /**
     * 失败
     * @param throwable
     */
    @Override
    public void error(Throwable throwable) {

        Toast.makeText(this, "网络异常", Toast.LENGTH_SHORT).show();

    }
}
