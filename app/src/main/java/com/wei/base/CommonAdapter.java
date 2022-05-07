package com.wei.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Administrator
 * @Date 2018/11/26 14:42
 * @Version 1.0
 * @Description 通用适配器.
 * 注意:
 * 简单的适配器,就不用新写一个类继承CommonAdapter了,直接:
 * CommonAdapter adapter = new CommonAdapter<T>(this, lists, R.layout.item) { 实现convert() };
 */
public abstract class CommonAdapter<T> extends BaseAdapter {


    protected Context mContext;// 上下文
    protected List<T> mDataList;// 适配器数据容器
    protected LayoutInflater mInflater;// 布局注入器
    private int mLayoutId;// 不同的适配器不同的布局:就是单个item的布局

    public CommonAdapter(Context context, List<T> dataList, int layoutId) {
        this.mContext = context;
        this.mDataList = dataList == null ? new ArrayList<T>() : dataList;
        this.mLayoutId = layoutId;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    @Override
    public T getItem(int position) {
        return mDataList == null ? null : mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.get(mContext, convertView, parent, mLayoutId, position);// 初始化ViewHolder
        convert(holder, getItem(position));
        return holder.getConvertView(); // 这一行的代码要注意了
    }

    /**
     * 将convert方法公布出去
     *
     * @param holder
     * @param bean
     */
    public abstract void convert(ViewHolder holder, T bean);

    /**
     * 设置数据.(原数据集合对象没变,仅清空并设置新数据)
     *
     * @param list
     */
    public void setData(List<T> list) {
        mDataList.clear();
        if (list != null && list.size() > 0) {
            mDataList.addAll(list);
        }
        notifyDataSetChanged();
    }

    /**
     * 刷新数据.(用新数据集合对象替换原数据集合对象)
     *
     * @param list
     */
    public void refreshData(List<T> list) {
        if (list != null) {
            mDataList = list;
        } else {
            mDataList = new ArrayList<>();
        }
        notifyDataSetChanged();
    }

    /**
     * 添加数据并刷新.(如果没添加,不刷新)
     *
     * @param list
     */
    public void addData(List<T> list) {
        if (list != null && list.size() > 0) {
            mDataList.addAll(list);
            notifyDataSetChanged();
        }
    }

    /**
     * 获取数据.
     *
     * @return
     */
    public List<T> getData() {
        return mDataList;
    }

    /**
     * 仅清空数据,没有更新界面.
     */
    public void clear() {
        mDataList.clear();
    }

    /**
     * 清空数据,并更新界面.
     */
    public void clearAndRefresh() {
        mDataList.clear();
        notifyDataSetChanged();
    }
}