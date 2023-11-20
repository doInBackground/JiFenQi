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
import com.wei.jifenqi.R;

import java.util.List;

/**
 * @Author WCL
 * @Date 2019/11/8 9:24
 * @Version 1.0
 * @Description 单局结算界面, ListView的适配器.
 */
public class ChangeGradeAdapter extends CommonAdapter<PlayerInfo> implements View.OnClickListener {


    public ChangeGradeAdapter(Context context, List<PlayerInfo> dataList) {
        super(context, dataList, R.layout.item_change_grade);
    }

    @Override
    public void convert(ViewHolder holder, PlayerInfo bean) {
        holder.setText(R.id.tv_num_name, bean.getNum() + "号玩家(" + bean.getName() + ")");
        holder.setText(R.id.tv_grade, bean.getGrade() + "");

        //找控件
        ImageView ivGradeCut = holder.getView(R.id.iv_grade_cut);
        ImageView ivGradeAdd = holder.getView(R.id.iv_grade_add);
        TextView tvGrade = holder.getView(R.id.tv_grade);
        //记标记
        ivGradeCut.setTag(holder.getPosition());
        ivGradeAdd.setTag(holder.getPosition());
        //点击事件
        ivGradeCut.setOnClickListener(this);
        ivGradeAdd.setOnClickListener(this);
        //数据处理
//        if (bean.isChange()) {
//            tvGrade.setTextColor(0xFFFF0000);
//        } else {
//            tvGrade.setTextColor(0xFF000000);
//        }
        refreshTvColor(tvGrade);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.iv_grade_cut:
                try {
                    //界面改变
                    TextView tvGrade = ((LinearLayout) view.getParent()).findViewById(R.id.tv_grade);//找到对应的分数控件
                    int grade = Integer.parseInt(tvGrade.getText().toString());//当前变化分数
                    grade--;
                    tvGrade.setText(grade + "");
                    refreshTvColor(tvGrade);
                    //实际数据改变
                    int position = (int) view.getTag();
                    setCurrentGrade(position, grade);//更新当前玩家数据.
                } catch (Exception e) {
                    ToastUtils.showToast("减少出错", 500);
                }
                break;
            case R.id.iv_grade_add:
                try {
                    //界面改变
                    TextView tvGrade = ((LinearLayout) view.getParent()).findViewById(R.id.tv_grade);//找到对应的分数控件
                    int grade = Integer.parseInt(tvGrade.getText().toString());//当前变化分数
                    grade++;
                    tvGrade.setText(grade + "");
                    refreshTvColor(tvGrade);
                    //实际数据改变
                    int position = (int) view.getTag();
                    setCurrentGrade(position, grade);
                } catch (Exception e) {
                    ToastUtils.showToast("增加出错", 500);
                }
                break;
        }
    }

    //根据TextView上数字大小,改变数字颜色.
    private void refreshTvColor(TextView tvGrade) {
        try {
            int grade = Integer.parseInt(tvGrade.getText().toString());//当前变化分数
            if (grade > 0) {
                tvGrade.setTextColor(0xFFFF0000);//红.
            } else if (grade < 0) {
                tvGrade.setTextColor(0xFF00FF00);//绿.
            } else {
                tvGrade.setTextColor(0xFF000000);//黑.
            }
        } catch (Exception e) {
        }
    }

    //改变当前玩家的分数.(当前玩家索引,当前玩家分数)
    private void setCurrentGrade(int position, int grade) {
        //设置当前玩家分数.
        List<PlayerInfo> playerList = this.getData();
        PlayerInfo player = playerList.get(position);
        player.setGrade(grade);
        player.setChange(true);
    }

    //改变当前玩家的分数,如果是倒数第二位玩家,则自动计算并设置最后一位玩家的分数.(当前玩家索引,当前玩家分数).有BUG!
    private void setLastGrade(int position, int grade) {
        //设置当前玩家分数.
        List<PlayerInfo> playerList = this.getData();
        PlayerInfo player = playerList.get(position);
        player.setGrade(grade);
        //如果是倒数第二位玩家,自动计算并显示倒数第一位玩家分数.
        if ((playerList.size() - 2) == position) {//是倒数第二位玩家
            int count = 0;//除开倒数第一位玩家,其他玩家的分数总和.
            for (int i = 0; i < playerList.size(); i++) {
                if (i <= (playerList.size() - 2)) {
                    count += playerList.get(i).getGrade();
                }
            }
            playerList.get(playerList.size() - 1).setGrade((-count));//自动设置最后一位的分数.
            notifyDataSetChanged();
        }
    }

    //改变当前玩家的分数,且如果仅剩最后一位玩家没有计算分数,则自动计算最后一位玩家的分数.(当前玩家索引,当前玩家分数).有BUG!
    private void setLastOneGrade(int position, int grade) {
        //设置当前玩家分数.
        List<PlayerInfo> playerList = this.getData();
        PlayerInfo player = playerList.get(position);
        player.setGrade(grade);
        player.setChange(true);
        //判断还有几个玩家没设置过分数.
        int noChangeCount = 0;//没有计算过分数的玩家数量.
        int noChangeIndex = -1;//最后一个没有计算过分数的玩家的索引.
        int changeCount = 0;//已经计算过分数的玩家的分数总和.
        for (int i = 0; i < playerList.size(); i++) {
            PlayerInfo playerInfo = playerList.get(i);
            if (!playerInfo.isChange()) {
                noChangeCount += 1;
                noChangeIndex = i;
            } else {
                changeCount += playerInfo.getGrade();
            }
        }
        if (noChangeCount == 1 && noChangeIndex >= 0) {//只剩一个玩家没设置过分数.
            playerList.get(noChangeIndex).setGrade(-changeCount);//自动设置最后一位没计算分数的玩家的分数.
            notifyDataSetChanged();
        }
    }

}
