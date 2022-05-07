package com.wei.adapter;

import android.content.Context;

import com.wei.base.CommonAdapter;
import com.wei.base.ViewHolder;
import com.wei.bean.PlayerInfo;
import com.wei.jifenqi.R;

import java.util.List;

/**
 * @Author WCL
 * @Date 2019/11/7 17:46
 * @Version 1.0
 * @Description
 */
public class AllPlayerAdapter extends CommonAdapter<PlayerInfo> {

    public AllPlayerAdapter(Context context, List<PlayerInfo> dataList) {
        super(context, dataList, R.layout.item_all_player);
    }

    @Override
    public void convert(ViewHolder holder, PlayerInfo bean) {
        holder.setText(R.id.tv_num_name, bean.getNum() + "号玩家 : " + bean.getName());
        holder.setText(R.id.tv_grade, "分数 : " + bean.getGrade());
    }
}
