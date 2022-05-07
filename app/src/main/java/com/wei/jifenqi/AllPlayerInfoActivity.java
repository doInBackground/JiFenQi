package com.wei.jifenqi;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wei.adapter.AllPlayerAdapter;
import com.wei.adapter.ChangeGradeAdapter;
import com.wei.base.AlertDialogUtils;
import com.wei.base.BaseDialog;
import com.wei.base.BaseTitleBar;
import com.wei.base.JsonUtil;
import com.wei.base.SharedPreferencesUtils;
import com.wei.base.ToastUtils;
import com.wei.bean.PlayerInfo;
import com.wei.constant.ConstantForSharedPreferences;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author WCL
 * @Date 2019/11/7 17:32
 * @Version 1.0
 * @Description
 */
public class AllPlayerInfoActivity extends Activity implements View.OnClickListener {

    /**
     * Filed Comment:TitleBar.
     */
    private BaseTitleBar mTitleBar;
    /**
     * Filed Comment:ListView.
     */
    private ListView mLv;
    /**
     * Filed Comment:当前所有玩家信息展示列表的适配器.
     */
    private AllPlayerAdapter mAdapter;
    /**
     * Filed Comment:变更分数弹窗.
     */
    private AlertDialog mChangeGradeDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_player_info);
        initView();//找控件
        initData();
        addListener();
    }

    private void initView() {
        mTitleBar = findViewById(R.id.title_bar);
        mLv = findViewById(R.id.lv);
    }

    private void initData() {
        String playerGradeInfo = SharedPreferencesUtils.getParam(ConstantForSharedPreferences.PLAYER_GRADE_INFO, "");
        if (TextUtils.isEmpty(playerGradeInfo)) {
            ToastUtils.showToast("暂无玩家信息", 500);
        } else {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<PlayerInfo>>() {
            }.getType();// 指定T的类型
            ArrayList<PlayerInfo> playerList = gson.fromJson(playerGradeInfo, type);// 解析
            mAdapter = new AllPlayerAdapter(this, playerList);
            mLv.setAdapter(mAdapter);
        }
    }

    private void addListener() {
        mTitleBar.setLeftLayoutClickListener(this);//左上角返回键.
        mTitleBar.setRightLayoutClickListener2(this);//右上角文字
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_title_left://返回键
                finish();
                break;
            case R.id.btn_title_right2://"变更"
                changeGradeDialog();
                break;
        }
    }

    /**
     * 变更分数弹窗.
     */
    private void changeGradeDialog() {
        if (mChangeGradeDialog == null) {
            mChangeGradeDialog = BaseDialog.createDialog(this, R.layout.dialog_change_grade, Gravity.CENTER);
            mChangeGradeDialog.setCanceledOnTouchOutside(false);
            //找Dialog中的控件.
            Window win = mChangeGradeDialog.getWindow();
            ListView lv = win.findViewById(R.id.lv);//计分列表
            Button btCancel = win.findViewById(R.id.btn_cancel);// 取消
            Button btSure = win.findViewById(R.id.btn_ok);// 确定
            //数据展示.
            final ChangeGradeAdapter adapter = new ChangeGradeAdapter(AllPlayerInfoActivity.this, null);
            for (PlayerInfo player1 : mAdapter.getData()) {
                PlayerInfo player2 = new PlayerInfo(player1.getNum(), player1.getName(), 0);//新建Bean对象.
                adapter.getData().add(player2);
            }
            lv.setAdapter(adapter);
            //对控件做处理及设置事件.
            btCancel.setOnClickListener(new View.OnClickListener() {//取消
                @Override
                public void onClick(View arg0) {
                    mChangeGradeDialog.dismiss();
                    //要变化的积分清除
                    for (PlayerInfo player : adapter.getData()) {
                        player.setGrade(0);
                    }
                    adapter.notifyDataSetChanged();
                }
            });
            btSure.setOnClickListener(new View.OnClickListener() {//确定
                @Override
                public void onClick(View arg0) {
                    int count = 0;
                    final List<PlayerInfo> players1 = mAdapter.getData();
                    final List<PlayerInfo> players2 = adapter.getData();
                    //判断变化分数之和,是否为零.
                    for (PlayerInfo player2 : players2) {
                        count += player2.getGrade();
                    }
                    if (count != 0) {//总数之和不为零.
                        AlertDialogUtils.showDialog("分数之和非零提醒", "所有玩家的收入支出之和非零,确定结算吗?", AllPlayerInfoActivity.this, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                count(players1, players2, adapter);
                            }
                        });
                    } else {//总数之和为零.
                        count(players1, players2, adapter);
                    }
                }
            });
        } else {
            mChangeGradeDialog.show();
        }

    }

    /**
     * 整合计算两个集合的分数.
     *
     * @param players1
     * @param players2
     * @param adapter
     */
    private void count(List<PlayerInfo> players1, List<PlayerInfo> players2, ChangeGradeAdapter adapter) {
        //遍历变化数据集合.
        for (PlayerInfo player2 : players2) {
            int num = player2.getNum();
            PlayerInfo player1 = null;//实际展示的对象.
            //从所有数据中,找到唯一标识num对应的对象.
            for (PlayerInfo player : players1) {
                if (player.getNum() == num) {
                    player1 = player;
                }
            }
            //分数计算
            if (player1 != null) {
                int newGrade = player1.getGrade() + player2.getGrade();
                player1.setGrade(newGrade);
            } else {
                ToastUtils.showToast("分数改变出错", 500);
            }
        }
        //刷新界面.
        mAdapter.notifyDataSetChanged();
        //记录在本地.
        String playerInfoJson = JsonUtil.beanListToJson(players1);
        SharedPreferencesUtils.setParam(ConstantForSharedPreferences.PLAYER_GRADE_INFO, playerInfoJson);
        mChangeGradeDialog.dismiss();
        //要变化的积分清除
        for (PlayerInfo player : players2) {
            player.setGrade(0);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        AlertDialogUtils.showDialog("退出应用", "本次计分信息会被保存,下次默认重新进入本次计分信息.", this, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);//结束进程.
            }
        });
    }
}
