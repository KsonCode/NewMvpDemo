package com.laoxu.mvpdemo.model.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.laoxu.mvpdemo.R;
import com.laoxu.mvpdemo.model.entity.HomeEntity;

import java.util.List;

public class PzshAdapter extends RecyclerView.Adapter<PzshAdapter.MyViewHodler> {

    private Context context;

    private List<HomeEntity.ResultBean.PzshBean.CommodityListBeanX> list;

    public PzshAdapter(Context context, List<HomeEntity.ResultBean.PzshBean.CommodityListBeanX> list) {
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
    public void onBindViewHolder(@NonNull MyViewHodler holder, int position) {


        Glide.with(context)
                .load(list.get(position).getMasterPic())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .priority(Priority.HIGH)
                .circleCrop()
                .centerCrop()
                .into(holder.iv);


        holder.nameTv.setText(list.get(position).getCommodityName());
        holder.priceTv.setText("¥："+list.get(position).getPrice());





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
