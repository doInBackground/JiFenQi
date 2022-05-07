package com.wei.base;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.wei.jifenqi.R;

/**
 * @author Administrator
 * 公共创建自定义对话框 方法
 * 该方法主要负责创建
 * 控件查找 及点击事件由自己方法实现
 */
public class BaseDialog {

    /**
     * Filed Comment: 全局Toast
     */
    private static Toast mToast = null;

    /**
     * 自定义 吐司
     *
     * @param layoutId 上下文
     */
    public static void creatToast(int layoutId) {
        Context context = MyApplication.getContext();
        View toastRoot = LayoutInflater.from(context).inflate(layoutId, null);//加载Toast布局
        if (null == mToast) {//非空判断防止重复创建多个
            mToast = new Toast(context);//Toast的初始化
        }
        //设置为全屏显示
        mToast.setGravity(Gravity.FILL, 0, 0);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setView(toastRoot);
        mToast.show();
    }

    /**
     * 自定义Dialog
     *
     * @param context  上下文
     * @param layoutId 对话框布局
     * @return AlertDialog 返回创建好的对话框实例
     * 用alertDialog.getWindow(); 获取布局视图
     */
    public static AlertDialog createDialog(Context context, int layoutId, int gravity) {
        switch (gravity) {
            case Gravity.TOP:
                gravity = Gravity.TOP;
                break;
            case Gravity.BOTTOM:
                gravity = Gravity.BOTTOM;
                break;
            case Gravity.CENTER:
                gravity = Gravity.CENTER;
                break;
            default:
                gravity = Gravity.CENTER;//默认居中
                break;
        }
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.show();
        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        alertDialog.setCanceledOnTouchOutside(true);
        Window window = alertDialog.getWindow();
        window.setContentView(layoutId);
        window.setGravity(gravity); // 此处可以设置dialog显示的位置
//        window.setWindowAnimations(R.style.AnimBottom); // 添加动画
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = lp.MATCH_PARENT; // 宽度
        lp.height = lp.WRAP_CONTENT; // 高度
        window.setAttributes(lp);
        return alertDialog;
    }

    /**
     * 非全屏显示
     * 自定义PopupWindow
     *
     * @param context  上下文
     * @param layoutId 对话框布局
     * @return PopupWindow 返回对话框实体
     * 使用pw.getContentView(); 获取视图布局
     */
    public static PopupWindow creatPopupWindow(View v, int layoutId, Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mMenuView = inflater.inflate(layoutId, null);
        PopupWindow pw = new PopupWindow(mMenuView);
        pw.setFocusable(true);
        pw.setBackgroundDrawable(new BitmapDrawable());
        pw.setWindowLayoutMode(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//        pw.setAnimationStyle(R.style.AnimBottom);
        pw.showAsDropDown(v, 0, 0);
        return pw;
    }


    /**
     * 全屏显示popWindow
     *
     * @param v
     * @param layoutId 对话框布局
     * @param context  上下文
     * @return
     */
    public static PopupWindow creatPopupWindow1(View v, int layoutId, Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mMenuView = inflater.inflate(layoutId, null);
        PopupWindow pw = new PopupWindow(mMenuView);
        pw.setFocusable(false);
        pw.setBackgroundDrawable(new BitmapDrawable());
        pw.setWindowLayoutMode(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
//		pw.setAnimationStyle(R.style.AnimBottom_2);
        pw.showAsDropDown(v, 0, 0);
        return pw;
    }
}
