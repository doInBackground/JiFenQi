package com.wei.base;

import android.app.AlertDialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.wei.jifenqi.R;


/**
 *
 */
public class AlertDialogUtils {

//    /**
//     * 选择拍照 图库
//     */
//    static TextView onchoosetv;
//
//    public static void choosePicDia(Context mContext, final OnChooseListener onChooseListener) {
//        final AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
//        alertDialog.show();
//        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
//        alertDialog.setCanceledOnTouchOutside(true);
//        Window win = alertDialog.getWindow();
//        win.setContentView(R.layout.dialog_gongzuozhuangtai);
//        win.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的位置
//        win.setWindowAnimations(R.style.AnimBottom); // 添加动画
//        WindowManager.LayoutParams lp = win.getAttributes();
//        lp.width = lp.MATCH_PARENT; // 宽度
//        lp.height = lp.WRAP_CONTENT; // 高度
//        win.setAttributes(lp);
//        TextView close = (TextView) win.findViewById(R.id.dialog_choose_type_close);
//        TextView sure = (TextView) win.findViewById(R.id.dialog_choose_type_sure);
//        final TextView one = (TextView) win.findViewById(R.id.dialog_choose_type_one);
//        final TextView two = (TextView) win.findViewById(R.id.dialog_choose_type_two);
//        one.setText("拍照");
//        two.setText("图库");
//        close.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//                alertDialog.dismiss();
//            }
//        });
//        sure.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//                if (null != onchoosetv) {
//                    onChooseListener.onChooseOk(onchoosetv.getText().toString());
//                }
//                alertDialog.dismiss();
//            }
//        });
//        one.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (null != onchoosetv) {
//                    onchoosetv.setTextColor(Color.parseColor("#292929"));// 恢复
//                    onchoosetv = one;
//                    onchoosetv.setTextColor(Color.parseColor("#40a3fe"));// 蓝色
//                } else {
//                    onchoosetv = one;
//                    onchoosetv.setTextColor(Color.parseColor("#40a3fe"));// 蓝色
//                }
//            }
//        });
//        two.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (null != onchoosetv) {
//                    onchoosetv.setTextColor(Color.parseColor("#292929"));// 恢复
//                    onchoosetv = two;
//                    onchoosetv.setTextColor(Color.parseColor("#40a3fe"));// 蓝色
//                } else {
//                    onchoosetv = two;
//                    onchoosetv.setTextColor(Color.parseColor("#40a3fe"));// 蓝色
//                }
//            }
//        });
//    }
//
//    public interface OnChooseListener {
//        void onChooseOk(String choose);
//    }

    // 是否注销

    /**
     * 弹出一个含有"标题","内容","取消","确定"的对话框.
     *
     * @param title   对话框标题
     * @param content 对话框内容
     * @param context 上下文
     * @param onclick 点击"确定"后的监听
     */
    public static void showDialog(String title, String content, Context context, final OnClickListener onclick) {
        final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.show();
        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        alertDialog.setCanceledOnTouchOutside(true);
        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.layout_dialog);
        window.setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置
//        window.setWindowAnimations(R.style.AnimBottom); // 添加动画
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = lp.MATCH_PARENT; // 宽度
        lp.height = lp.WRAP_CONTENT; // 高度
        window.setAttributes(lp);
        TextView tv_dialog_title = (TextView) window.findViewById(R.id.tv_dialog_title);
        TextView tv_dialog_context = (TextView) window.findViewById(R.id.tv_dialog_context);
        Button btn_close = (Button) window.findViewById(R.id.btn_cancel);
        Button btn_ok = (Button) window.findViewById(R.id.btn_ok);
        if (!TextUtils.isEmpty(title)) {
            tv_dialog_title.setVisibility(View.VISIBLE);
            tv_dialog_title.setText(title);
        } else {
            tv_dialog_title.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(content)) {
            tv_dialog_context.setText(content);
            tv_dialog_context.setVisibility(View.VISIBLE);
        } else {
            tv_dialog_context.setVisibility(View.GONE);
        }

        //取消
        btn_close.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        //确定
        btn_ok.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                onclick.onClick(v);
            }
        });
    }

    // 是否注销

    /**
     * 弹出一个含有"标题","内容","确定"的对话框.
     *
     * @param title   对话框标题
     * @param content 对话框内容
     * @param context 上下文
     * @param onclick 点击"确定"后的监听
     */
    public static void showDialog2(String title, String content, Context context, final OnClickListener onclick) {
        final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.show();
        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        alertDialog.setCanceledOnTouchOutside(true);
        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.layout_dialog2);
        window.setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置
//        window.setWindowAnimations(R.style.AnimBottom); // 添加动画
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = lp.MATCH_PARENT; // 宽度
        lp.height = lp.WRAP_CONTENT; // 高度
        window.setAttributes(lp);
        TextView tv_dialog_title = (TextView) window.findViewById(R.id.tv_dialog_title);
        TextView tv_dialog_context = (TextView) window.findViewById(R.id.tv_dialog_context);
        Button btn_ok = (Button) window.findViewById(R.id.btn_ok);
        if (!TextUtils.isEmpty(title)) {
            tv_dialog_title.setVisibility(View.VISIBLE);
            tv_dialog_title.setText(title);
        } else {
            tv_dialog_title.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(content)) {
            tv_dialog_context.setText(content);
            tv_dialog_context.setVisibility(View.VISIBLE);
        } else {
            tv_dialog_context.setVisibility(View.GONE);
        }

        //确定
        btn_ok.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                onclick.onClick(v);
            }
        });
    }

}
