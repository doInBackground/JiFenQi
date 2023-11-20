package com.wei.adapter;

import android.content.Context;
import android.widget.TextView;

import com.wei.base.CommonAdapter;
import com.wei.base.ViewHolder;
import com.wei.bean.PlayerInfo;
import com.wei.jifenqi.R;

import java.util.List;

/**
 * @Author WCL
 * @Date 2019/11/7 17:46
 * @Version 1.0
 * @Description 玩家计分信息界面, ListView的适配器.
 */
public class AllPlayerAdapter extends CommonAdapter<PlayerInfo> {

    public AllPlayerAdapter(Context context, List<PlayerInfo> dataList) {
        super(context, dataList, R.layout.item_all_player);
    }

    @Override
    public void convert(ViewHolder holder, PlayerInfo bean) {
        holder.setText(R.id.tv_num_name, bean.getNum() + "号玩家(" + bean.getName() + ")");
        holder.setText(R.id.tv_grade, "分数: " + bean.getGrade());
        refreshTvColor(holder.getView(R.id.tv_grade), bean.getGrade());
    }

    //根据TextView上数字大小,改变数字颜色.
    private void refreshTvColor(TextView tvGrade, int grade) {
        if (grade > 0) {
            tvGrade.setTextColor(0xFFFF0000);//红.
        } else if (grade < 0) {
            tvGrade.setTextColor(0xFF00FF00);//绿.
        } else {
            tvGrade.setTextColor(0xFF000000);//黑.
        }
    }

}
