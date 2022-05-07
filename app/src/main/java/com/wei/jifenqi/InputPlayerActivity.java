package com.wei.jifenqi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.wei.adapter.InputPlayerAdapter;
import com.wei.base.BaseTitleBar;
import com.wei.base.JsonUtil;
import com.wei.base.SharedPreferencesUtils;
import com.wei.base.ToastUtils;
import com.wei.bean.PlayerInfo;
import com.wei.constant.ConstantForSharedPreferences;

import java.util.List;

/**
 * @Author WCL
 * @Date 2019/11/7 15:39
 * @Version 1.0
 * @Description
 */
public class InputPlayerActivity extends Activity implements View.OnClickListener {

    /**
     * Filed Comment:通过Intent,从上个界面传来的玩家的数量.
     */
    public static final String PLAYER_NUM = "playerNum";
    /**
     * Filed Comment:TitleBar.
     */
    private BaseTitleBar mTitleBar;
    /**
     * Filed Comment:ListView.
     */
    private ListView mLv;
    /**
     * Filed Comment:ListView的适配器.
     */
    private InputPlayerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_player);
        initView();//找控件
        initData();
        addListener();
    }

    private void initView() {
        mTitleBar = findViewById(R.id.title_bar);
        mLv = findViewById(R.id.lv);
    }

    private void initData() {
        int playerNum = getIntent().getIntExtra(PLAYER_NUM, 0);
        if (playerNum <= 0) {//选择玩家数量时候出错.
            ToastUtils.showToast("玩家数量选择出错", 500);
        } else {
            //ListView适配器.
            mAdapter = new InputPlayerAdapter(this, null);
            List<PlayerInfo> playerList = mAdapter.getData();//获取已有数据集合.
            //添加数据.
            for (int i = 0; i < playerNum; i++) {
                int num = i + 1;
                PlayerInfo player = new PlayerInfo(num, num + "号", 0);//新建玩家信息.
                playerList.add(player);
            }
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
            case R.id.btn_title_right2://"开始计分"
                String playerInfoJson = JsonUtil.beanListToJson(mAdapter.getData());
                SharedPreferencesUtils.setParam(ConstantForSharedPreferences.PLAYER_GRADE_INFO, playerInfoJson);
                Log.d("WWW", playerInfoJson);
                startActivity(new Intent(this, AllPlayerInfoActivity.class));
                finish();
                break;
        }
    }


}
