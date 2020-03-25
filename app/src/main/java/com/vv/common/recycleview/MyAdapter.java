package com.vv.common.recycleview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc:
 * <p>
 * Date: 2020/3/25
 * Copyright: Copyright (c) 2010 - 2019
 * Company: @微微科技有限公司
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @param
 * @author zhengxiaobin
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.BigViewHolder> {
    private List<CategoryEn> dataList = new ArrayList<>();

    MyAdapter(List<CategoryEn> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public BigViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int resId = R.layout.item_category_big;
        if (viewType == CategoryEn.TYPE_SMALL) {
            resId = R.layout.item_category_small;
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);
        return new BigViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BigViewHolder holder, int position) {
        CategoryEn categoryEn = dataList.get(position);
        holder.tv_title.setText(categoryEn.title);
        holder.tv_subTitle.setText(categoryEn.subTitle);
//        holder.icon.setImageResource(categoryEn.icon);
        if (categoryEn.isFocus) {
            startFlick(holder.tv_subTitle);
        }
    }

    /**
     * 开启View闪烁效果
     * 控件闪烁，其实就是控制控件的透明度，从可见到逐渐不可见，再逐渐到可见，一直反复。因此，要想实现控件闪烁，只需要使用android中的alpha动画即可
     */
    private void startFlick(View view) {
        if (null == view) {
            return;
        }
        Animation alphaAnimation = new AlphaAnimation(1, 0.4f);
        alphaAnimation.setDuration(300);
        alphaAnimation.setInterpolator(new LinearInterpolator());
        alphaAnimation.setRepeatCount(2);
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        view.startAnimation(alphaAnimation);
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return dataList.get(position).type;
    }

    public class BigViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_title;
        public TextView tv_subTitle;
        public ImageView icon;

        public BigViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_subTitle = itemView.findViewById(R.id.tv_subTitle);
            icon = itemView.findViewById(R.id.icon);
        }
    }
}
