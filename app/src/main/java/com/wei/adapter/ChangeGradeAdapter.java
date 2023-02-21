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
 * @Description
 */
public class ChangeGradeAdapter extends CommonAdapter<PlayerInfo> implements View.OnClickListener {


    public ChangeGradeAdapter(Context context, List<PlayerInfo> dataList) {
        super(context, dataList, R.layout.item_change_grade);
    }

    @Override
    public void convert(ViewHolder holder, PlayerInfo bean) {
        holder.setText(R.id.tv_num_name, bean.getNum() + "号玩家 : " + bean.getName());
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
        if (bean.isChange()) {
            tvGrade.setTextColor(0xFFFF0000);
        } else {
            tvGrade.setTextColor(0xFF000000);
        }
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
                    tvGrade.setTextColor(0xFFFF0000);
                    //实际数据改变
                    int position = (int) view.getTag();
                    setLastOneGrade(grade, position);
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
                    tvGrade.setTextColor(0xFFFF0000);
                    //实际数据改变
                    int position = (int) view.getTag();
                    setLastOneGrade(grade, position);
                } catch (Exception e) {
                    ToastUtils.showToast("增加出错", 500);
                }
                break;
        }
    }

    //改变当前玩家的分数,且自动计算最后一个没有设置分数的玩家的分数.(当前玩家分数,当前玩家索引)
    private void setLastOneGrade(int grade, int position) {
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

    //自动计算并设置最后一位玩家的分数.
    private void setLastGrade(int position) {
        //如果是倒数第二位玩家,自动计算并显示倒数第一位玩家分数.
        if ((this.getData().size() - 2) == position) {//是倒数第二位玩家
            int count = 0;//除开倒数第一位玩家,其他玩家的分数总和.
            for (int i = 0; i < this.getData().size(); i++) {
                if (i <= (this.getData().size() - 2)) {
                    count += this.getData().get(i).getGrade();
                }
            }
            this.getData().get(this.getData().size() - 1).setGrade((-count));//自动设置最后一位的分数.
            notifyDataSetChanged();
        }
    }
}
