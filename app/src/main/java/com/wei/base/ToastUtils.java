package com.wei.base;

import android.content.Context;
import android.widget.Toast;

/**
 * toast工具
 *
 * @author Administrator
 * 防止重复多次点击显示多个toast
 */
public class ToastUtils {

    private static Toast mToast = null;

    public static void showToast(String text, int duration) {
        Context context = MyApplication.getContext();
        if (mToast == null) {
            mToast = Toast.makeText(context, text, duration);
        } else {
            mToast.setText(text);
            mToast.setDuration(duration);
        }
        mToast.show();
    }

    public static void showToast(int strId, int duration) {
        Context context = MyApplication.getContext();
        showToast(context.getString(strId), duration);
    }

}

