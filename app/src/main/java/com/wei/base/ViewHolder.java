package com.wei.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * @Author Administrator
 * @Date 2019/6/19 9:53
 * @Version 1.0
 * @Description 公用ViewHolder类.
 */
public class ViewHolder {

    private SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;

    public ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.mPosition = position;
        this.mViews = new SparseArray<>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
    }

    /**
     * 获取ViewHolder对象.
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new ViewHolder(context, parent, layoutId, position);
        } else {
            ViewHolder holder = (ViewHolder) convertView.getTag();
            //即时ViewHolder是复用的，但是position记得更新一下
            holder.mPosition = position;
            return holder;
        }
    }

    /**
     * 通过viewId获取控件.
     * 使用的是泛型T,返回的是View的子类.
     *
     * @param viewId 控件ID
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 通过id设置View不可见.
     *
     * @param viewId
     */
    public void setViewGone(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        view.setVisibility(View.GONE);
    }

    /**
     * 通过id设置View可见.
     *
     * @param viewId
     */
    public void setViewVisible(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        view.setVisibility(View.VISIBLE);
    }

    /**
     * 通过id设置TextView文字.
     *
     * @param viewId
     * @return
     */
    public void setText(int viewId, String textStr) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        ((TextView) view).setText(textStr);
    }

    /**
     * 获取位置.
     *
     * @return
     */
    public int getPosition() {
        return mPosition;
    }

    /**
     * 获取item对象.
     */
    public View getConvertView() {
        return mConvertView;
    }
}
