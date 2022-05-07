package com.wei.jifenqi;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wei.base.AlertDialogUtils;
import com.wei.base.BaseDialog;
import com.wei.base.SharedPreferencesUtils;
import com.wei.base.ToastUtils;
import com.wei.constant.ConstantForSharedPreferences;

/**
 * @Author WCL
 * @Date 2019/11/7 15:39
 * @Version 1.0
 * @Description
 */
public class MainActivity extends Activity implements View.OnClickListener {

    /**
     * Filed Comment:"开始新的计分"按钮.
     */
    private TextView mTvSetPlayerNum;
    /**
     * Filed Comment:"进入上次计分"按钮.
     */
    private TextView mTvBack;
    private Dialog mSetPlayerNumDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();//找控件
        initData();
        addListener();
    }


    private void initView() {
        TextView tvUseExplain = findViewById(R.id.tv_use_explain);
        tvUseExplain.setText(
                "简介:\n多人游戏时,用来记录各人的分数.\n\n" +
                        "用法:\n[1]点击\"开始新的计分\";\n[2]选择游戏人数;\n[3]为玩家输入代号,即可开始计分.\n退出后,也可点击\"进入上次计分\",回到之前的记录.\n\n" +
                        "技术支持: Mr.wei"
        );
        mTvSetPlayerNum = findViewById(R.id.tv_set_player_num);
        mTvBack = findViewById(R.id.tv_back);
    }

    private void initData() {
        String playerGradeInfo = SharedPreferencesUtils.getParam(ConstantForSharedPreferences.PLAYER_GRADE_INFO, "");//本地记录的"上次玩家及分数信息".
        if (!TextUtils.isEmpty(playerGradeInfo)) {//有上次记录.
            startActivity(new Intent(this, AllPlayerInfoActivity.class));
        }
    }

    private void addListener() {
        mTvSetPlayerNum.setOnClickListener(this);
        mTvBack.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String playerGradeInfo = SharedPreferencesUtils.getParam(ConstantForSharedPreferences.PLAYER_GRADE_INFO, "");//本地记录的"上次玩家及分数信息".
        if (TextUtils.isEmpty(playerGradeInfo)) {//没有上次记录.
            mTvBack.setVisibility(View.GONE);//隐藏
        } else {//有上次记录.
            mTvBack.setVisibility(View.VISIBLE);//显示
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_set_player_num://"开始新的计分"按钮.
                setPlayerNumDialog();
                break;
            case R.id.tv_back://"进入上次计分"按钮.
                startActivity(new Intent(this, AllPlayerInfoActivity.class));
                break;
        }
    }

    /**
     * 设置玩家数量的Dialog.
     */
    private void setPlayerNumDialog() {
        if (mSetPlayerNumDialog == null) {
            mSetPlayerNumDialog = BaseDialog.createDialog(this, R.layout.dialog_set_player_num, Gravity.CENTER);
            mSetPlayerNumDialog.setCanceledOnTouchOutside(false);
            //找Dialog中的控件.
            Window win = mSetPlayerNumDialog.getWindow();
            ImageView ivNumCut = win.findViewById(R.id.iv_num_cut);//玩家数"减少"按钮
            ImageView ivNumAdd = win.findViewById(R.id.iv_num_add);//玩家数"增加"按钮
            final TextView tvNum = win.findViewById(R.id.tv_num);//玩家数
            Button btCancel = win.findViewById(R.id.btn_cancel);// 取消
            Button btSure = win.findViewById(R.id.btn_ok);// 确定
            //对控件做处理及设置事件.
            ivNumCut.setOnClickListener(new View.OnClickListener() {//减少
                @Override
                public void onClick(View view) {
                    try {
                        String num = tvNum.getText().toString();
                        int numInt = Integer.parseInt(num);
                        if (numInt > 2) {
                            numInt--;
                            tvNum.setText(numInt + "");
                        } else {
                            ToastUtils.showToast("至少应有2位玩家", 500);
                        }
                    } catch (Exception e) {
                        ToastUtils.showToast("非整数错误", 500);
                    }
                }
            });
            ivNumAdd.setOnClickListener(new View.OnClickListener() {//增加
                @Override
                public void onClick(View view) {
                    try {
                        String num = tvNum.getText().toString();
                        int numInt = Integer.parseInt(num);
                        numInt++;
                        tvNum.setText(numInt + "");
                    } catch (Exception e) {
                        ToastUtils.showToast("非整数错误", 500);
                    }
                }
            });
            btCancel.setOnClickListener(new View.OnClickListener() {//取消
                @Override
                public void onClick(View arg0) {
                    mSetPlayerNumDialog.dismiss();
                    tvNum.setText("2");
                }
            });
            btSure.setOnClickListener(new View.OnClickListener() {//确定
                @Override
                public void onClick(View arg0) {
                    try {
                        String num = tvNum.getText().toString();
                        int numInt = Integer.parseInt(num);
                        Intent intent = new Intent(MainActivity.this, InputPlayerActivity.class);
                        intent.putExtra(InputPlayerActivity.PLAYER_NUM, numInt);
                        startActivity(intent);
                        mSetPlayerNumDialog.dismiss();
                    } catch (Exception e) {
                        ToastUtils.showToast("非整数错误", 500);
                    }
                }
            });
        } else {
            mSetPlayerNumDialog.show();
        }

    }


    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        AlertDialogUtils.showDialog("退出应用", "确认退出应用吗?", this, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);//结束进程.
            }
        });
    }

}
