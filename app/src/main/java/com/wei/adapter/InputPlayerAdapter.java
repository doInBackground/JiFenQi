package com.wei.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.wei.base.CommonAdapter;
import com.wei.base.ViewHolder;
import com.wei.bean.PlayerInfo;
import com.wei.jifenqi.R;

import java.util.List;

/**
 * @Author WCL
 * @Date 2019/11/7 16:28
 * @Version 1.0
 * @Description 设置玩家信息界面的Adapter.
 */
public class InputPlayerAdapter extends CommonAdapter<PlayerInfo> {


    public InputPlayerAdapter(Context context, List<PlayerInfo> dataList) {
        super(context, dataList, R.layout.item_input_player);
    }

    @Override
    public void convert(ViewHolder holder, final PlayerInfo bean) {
        holder.setText(R.id.tv_num, "请输入" + bean.getNum() + "号玩家代号:");//条目标题
        EditText etName = holder.getView(R.id.et_name);//玩家姓名控件
        etName.setText(bean.getName());
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                bean.setName(editable.toString());
            }
        });
    }


}
