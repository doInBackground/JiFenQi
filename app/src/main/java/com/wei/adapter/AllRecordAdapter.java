package com.wei.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wei.base.CommonAdapter;
import com.wei.base.ToastUtils;
import com.wei.base.ViewHolder;
import com.wei.bean.PlayerInfo;
import com.wei.bean.RecordInfo;
import com.wei.jifenqi.R;

import java.util.List;

/**
 * @Author WCL
 * @Date 2019/11/8 9:24
 * @Version 1.0
 * @Description 全部对局记录列表的适配器.
 */
public class AllRecordAdapter extends CommonAdapter<RecordInfo> {

    public AllRecordAdapter(Context context, List<RecordInfo> dataList) {
        super(context, dataList, R.layout.item_all_record);
    }

    @Override
    public void convert(ViewHolder holder, RecordInfo bean) {
        holder.setText(R.id.tv_time, "第" + (holder.getPosition() + 1) + "局 " + bean.getTime());
        holder.setText(R.id.tv_record, bean.getLog());

        //找控件
        TextView tvTime = holder.getView(R.id.tv_time);
        if (bean.isCountZero()) {
            tvTime.setTextColor(0xFF000000);
        } else {
            tvTime.setTextColor(0xFF990000);
        }
    }

}
