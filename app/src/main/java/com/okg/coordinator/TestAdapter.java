package com.okg.coordinator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.MyViewHolder> {
    private Context mContext;
    private List<String> mDataList = new ArrayList<>();

    public TestAdapter(Context context, List<String> dataList) {
        mContext = context;
        this.mDataList = dataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //找到item，也就是列表里面要显示的东西。
        MyViewHolder hoder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_text, parent, false));
        return hoder;
    }

    //这里面是写Holder里面的控件实现方法的
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tvText.setText(mDataList.get(position));
    }

    //一般就写数据源加上长度就好
    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void updateList(List<String> dataList) {
        mDataList = dataList;
        notifyDataSetChanged();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvText;

        public MyViewHolder(View itemView) {
            super(itemView);
            //实例化item里面的控件
            tvText = itemView.findViewById(R.id.tv_text);
        }
    }
}
