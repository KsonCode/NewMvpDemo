package com.laoxu.mvpdemo.model.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.laoxu.mvpdemo.R;
import com.laoxu.mvpdemo.model.entity.HomeEntity;

import java.util.List;

public class RxxpAdapter extends RecyclerView.Adapter<RxxpAdapter.MyViewHodler> {

    private Context context;

    private List<HomeEntity.ResultBean.RxxpBean.CommodityListBean> list;

    public RxxpAdapter(Context context, List<HomeEntity.ResultBean.RxxpBean.CommodityListBean> list) {
        this.context = context;
        this.list = list;
    }





    /**
     * 初始化viewhodler
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public MyViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //创建呢view
        View view = View.inflate(context,R.layout.rxxp_item_layout,null);
        //创建viewholder
        MyViewHodler myViewHodler = new MyViewHodler(view);
        //返回
        return myViewHodler;
    }

    /**
     * 使用viewhodler
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHodler holder, final int position) {

//        //设置图片圆角角度
//        RoundedCorners roundedCorners= new RoundedCorners(30);
////通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
//        RequestOptions options= RequestOptions.bitmapTransform(roundedCorners);


        Glide.with(context)
                .load(list.get(position).getMasterPic())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .priority(Priority.HIGH)
                .circleCrop()
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))//应用圆角配置
                .into(holder.iv);


        holder.nameTv.setText(list.get(position).getCommodityName());
        holder.priceTv.setText("¥："+list.get(position).getPrice());

        //点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, list.get(position).getCommodityName(), Toast.LENGTH_SHORT).show();
            }
        });





    }

    /**
     * 集合长度，多少条item数量
     * @return
     */
    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * 自定义viewhodler
     */
    class MyViewHodler extends RecyclerView.ViewHolder{

        private ImageView iv;
        private TextView nameTv,priceTv;

        public MyViewHodler(@NonNull View itemView) {
            super(itemView);

            iv = itemView.findViewById(R.id.iv);
            nameTv = itemView.findViewById(R.id.name);
            priceTv = itemView.findViewById(R.id.price);
        }
    }

}
