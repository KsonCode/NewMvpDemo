package com.laoxu.mvpdemo.model.entity;

import com.stx.xhb.androidx.entity.SimpleBannerInfo;

import java.util.List;

public class BannerEntity {
    public String message;
    public String status;
    public List<Banner> result;

    public static class Banner extends SimpleBannerInfo {
        public String imageUrl;//图片地址
        public String jumpUrl;//跳转到不同业务
        public String rank;//优先级
        public String title;//标题

        @Override
        public Object getXBannerUrl() {
            return null;
        }
    }
}
