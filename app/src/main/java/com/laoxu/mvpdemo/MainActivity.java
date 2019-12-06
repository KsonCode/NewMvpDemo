package com.laoxu.mvpdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.laoxu.mvpdemo.base.BaseActity;
import com.laoxu.mvpdemo.contract.IHomeContract;
import com.laoxu.mvpdemo.model.adapter.MlssAdapter;
import com.laoxu.mvpdemo.model.adapter.PzshAdapter;
import com.laoxu.mvpdemo.model.adapter.RxxpAdapter;
import com.laoxu.mvpdemo.model.entity.BannerEntity;
import com.laoxu.mvpdemo.model.entity.HomeEntity;
import com.laoxu.mvpdemo.presenter.HomePresenter;
import com.stx.xhb.androidx.XBanner;
import com.stx.xhb.androidx.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActity<HomePresenter> implements IHomeContract.IView {

    private XBanner xBanner;

    private RecyclerView rxxpRv;//热销新品
    private RecyclerView mlssRv;//魔力时尚
    private RecyclerView pzshRv;//品质生活

    @Override
    protected void initData() {

        presenter.getHome();//获取首页数据
        presenter.getBanner();//




    }

    @Override
    protected void initView() {

        xBanner = findViewById(R.id.xbanner);
        rxxpRv = findViewById(R.id.rv_rxxp);
        mlssRv = findViewById(R.id.rv_mlss);
        pzshRv = findViewById(R.id.rv_pzsh);
        //布局管理器，横向滑动的列表
        rxxpRv.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));

        //垂直列表
//        mlssRv.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        mlssRv.setLayoutManager(new LinearLayoutManager(this));
        //网格布局,2列
        pzshRv.setLayoutManager(new GridLayoutManager(this,2));


    }

    @Override
    protected HomePresenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public void success(final Object data) {

        if (data != null) {
            if (data instanceof HomeEntity) {//首页列表
                Toast.makeText(this, ((HomeEntity) data).getResult().getMlss().getName(), Toast.LENGTH_SHORT).show();
                System.out.println("xxxx1" + ((HomeEntity) data).getResult().getMlss().getName());

                //热销新品

                RxxpAdapter rxxpAdapter = new RxxpAdapter(this, ((HomeEntity) data).getResult().getRxxp().getCommodityList());

                rxxpRv.setAdapter(rxxpAdapter);
                //魔力时尚

                MlssAdapter mlssAdapter = new MlssAdapter(this,((HomeEntity) data).getResult().getMlss().getCommodityList());
                mlssRv.setAdapter(mlssAdapter);

                //品质生活

                PzshAdapter pzshAdapter = new PzshAdapter(this,((HomeEntity) data).getResult().getPzsh().getCommodityList());
                pzshRv.setAdapter(pzshAdapter);
            } else if (data instanceof BannerEntity) {//banner列表

                System.out.println("xxxx2" + ((BannerEntity) data).message);


                //轮播图加载


                final List<BannerEntity.Banner> bannerList = ((BannerEntity) data).result;

                xBanner.setBannerData(bannerList);

                xBanner.loadImage(new XBanner.XBannerAdapter() {
                    @Override
                    public void loadBanner(XBanner banner, Object model, View view, int position) {
                        Glide.with(MainActivity.this).load(bannerList.get(position).imageUrl).into((ImageView) view);
                    }
                });


            }
        }

    }

    /**
     * 失败
     *
     * @param throwable
     */
    @Override
    public void error(Throwable throwable) {

        Toast.makeText(this, "网络异常", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
