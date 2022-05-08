package com.wei.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.wei.base.CommonAdapter;
import com.wei.base.ViewHolder;
import com.wei.bean.PlayerInfo;
import com.wei.jifenqi.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author WCL
 * @Date 2019/11/7 16:28
 * @Version 1.0
 * @Description 设置玩家信息界面的Adapter.
 */
public class InputPlayerAdapter extends CommonAdapter<PlayerInfo> {

    private final Map<Integer, TextWatcher> mTextWatchers = new HashMap<>();

    public InputPlayerAdapter(Context context, List<PlayerInfo> dataList) {
        super(context, dataList, R.layout.item_input_player);
    }

    @Override
    public void convert(ViewHolder holder, final PlayerInfo bean) {
//        Log.d("TAG", "holderPosition=" + holder.getPosition() + "  " + bean);

        holder.setText(R.id.tv_num, "请输入" + bean.getNum() + "号玩家代号:");//条目标题

        String name = bean.getName();//玩家姓名
        EditText etName = holder.getView(R.id.et_name);//玩家姓名控件
        etName.setHint(name);

        //第1步:根据控件标签,清除旧的监听者.
        Object oldTag = etName.getTag();
        if (oldTag != null) {//现在控件身上有旧的监听者.
            TextWatcher oldWatcher = mTextWatchers.get(((int) oldTag));
            if (oldWatcher != null) {
                etName.removeTextChangedListener(oldWatcher);
            }
        }

        //第2步:先判断有没有当前position的监听者.没有就创建并添加到监听者集合.
        int position = holder.getPosition();
        TextWatcher watcher = mTextWatchers.get(position);
        if (watcher == null) {
            watcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    String currentName = editable.toString();
                    Log.d("TAG", currentName);
                    if (TextUtils.isEmpty(currentName)) {
                        bean.setName(bean.getNum() + "号");
                    } else {
                        bean.setName(currentName);
                    }
                }
            };
            mTextWatchers.put(position, watcher);
        }

        //第3步:中间步骤,设置控件真实值.
        if (TextUtils.equals(name, (position + 1) + "号")) {
            etName.setText("");
        } else {
            etName.setText(name);
        }

        //第4步:控件添加监听者.
        etName.addTextChangedListener(watcher);

        //第5步:控件记录标签.
        etName.setTag(position);
    }

}
