package com.wei.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wei.jifenqi.R;

/**
 * @author qyl
 * @data 2016-06-18
 * 这里我自定义一个标题栏控件作为全局使用
 * 在其它布局直接引用次控件减少代码的重复性
 * <p>
 * 为了更好的解耦，这里除了进行必要的初始化操作，不进行进行任何设置
 * 给其它地方使用提供扩展空间
 * XML中属性设置:
 * app:titleBarTitle_st="标题"
 * app:titleBarRightText_st="右标题"
 * app:titleBarTextColor="@color/black_title"  //文字颜色[标题和右标题]
 * app:titleBarLeftImage_st="@drawable/left" //左图标
 * app:titleBarRightImage_st="@drawable/right"  //右图标
 * app:titleBarBackground_st="@color/black"  //背景色[设置layout_title控件的背景色]
 */
public class BaseTitleBar extends LinearLayout {

    /**
     * Filed Comment: 该BaseTitleBar最外层布局的对象.(沉浸式,应该传这个控件)
     */
    public FrameLayout layout_title;
    /**
     * Filed Comment: TitleBar左边的图形按钮.
     */
    private ImageView btn_title_left;
    /**
     * Filed Comment: TitleBar右边的图形按钮(默认空).
     */
    private ImageView btn_title_right;
    /**
     * Filed Comment: TitleBar右边的文字按钮(默认空).
     */
    private TextView btn_title_right2;
    /**
     * Filed Comment: TitleBar中间的文字标题.
     */
    private TextView title_text;

    public BaseTitleBar(Context context) {
        this(context, null);
    }

    public BaseTitleBar(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs);
    }

    public BaseTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    /**
     * 初始化控件
     */
    private void initView(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.head_title, this);//动态注入标题栏
        btn_title_left = (ImageView) findViewById(R.id.btn_title_left);
        btn_title_right = (ImageView) findViewById(R.id.btn_title_right);
        btn_title_right2 = (TextView) findViewById(R.id.btn_title_right2);
        title_text = (TextView) findViewById(R.id.title_text);
        layout_title = (FrameLayout) findViewById(R.id.layout_title);
        setAttrs(context, attrs);
    }

    /**
     * 设置自定义属性
     */
    private void setAttrs(Context context, AttributeSet attrs) {
        if (null != attrs) {
            //找到自定义属性资源文件
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
            //获取标题栏
            String title = ta.getString(R.styleable.TitleBar_titleBarTitle_st);
            if (!TextUtils.isEmpty(title)) {
                title_text.setText(title);
            }
            //获取右边标题
            String rightText = ta.getString(R.styleable.TitleBar_titleBarRightText_st);
            if (!TextUtils.isEmpty(rightText)) {
                btn_title_right2.setText(rightText);
            }
            //文字颜色(默认:0x292929 十进制为:2697513)
            int textColor = ta.getColor(R.styleable.TitleBar_titleBarTextColor, 0x292929);//这里的属性是:名字_属性名
//            LogUtil.d("textColor:" + textColor);
            if (textColor != 0x292929) {//布局中设置了字体颜色
                title_text.setTextColor(textColor);
                btn_title_right2.setTextColor(textColor);
            }
            //获取左边图片，并设置
            Drawable left_drawable = ta.getDrawable(R.styleable.TitleBar_titleBarLeftImage_st);
            if (null != left_drawable) {
                btn_title_left.setImageDrawable(left_drawable);
            }
            //获取右边图标并设置
            Drawable right_drawable = ta.getDrawable(R.styleable.TitleBar_titleBarRightImage_st);
            if (null != right_drawable) {
                btn_title_right.setImageDrawable(right_drawable);
            }
            //获取标题栏背景
            Drawable background = ta.getDrawable(R.styleable.TitleBar_titleBarBackground_st);
            if (null != background) {
                layout_title.setBackgroundDrawable(background);
            }
            ta.recycle(); //回收
        }
    }

    /**
     * 设置左边按钮图片资源
     */
    public void setLeftImageResource(int resId) {
        btn_title_left.setImageResource(resId);
    }

    /**
     * 设置右边按钮图片资源
     */
    public void setRightImageResource(int resId) {
        btn_title_right.setImageResource(resId);
    }

    /**
     * 左边按钮点击事件
     */
    public void setLeftLayoutClickListener(OnClickListener listener) {
        btn_title_left.setOnClickListener(listener);
    }

    /**
     * 右边图片按钮点击事件
     */
    public void setRightLayoutClickListener(OnClickListener listener) {
        btn_title_right.setOnClickListener(listener);
    }

    /**
     * 右边文字按钮点击事件2
     */
    public void setRightLayoutClickListener2(OnClickListener listener) {
        btn_title_right2.setOnClickListener(listener);
    }

    /**
     * 左边按钮可否显示
     */
    public void setLeftLayoutVisibility(int visibility) {
        btn_title_left.setVisibility(visibility);
    }

    /**
     * 右边图片按钮可否显示
     */
    public void setRightLayoutVisibility(int visibility) {
        btn_title_right.setVisibility(visibility);
    }

    /**
     * 右边文字按钮可否显示2
     */
    public void setRightLayoutVisibility2(int visibility) {
        btn_title_right2.setVisibility(visibility);
    }

    /**
     * 设置标题文字
     */
    public void setTitle(String title) {
        title_text.setText(title);
    }

    /**
     * 设置标题文字大小
     */
    public void setTitleSize(float size) {
        title_text.setTextSize(size);
    }

    /**
     * 设置标题右边文字
     */
    public void setRightTxt(String title) {
        btn_title_right2.setText(title);
    }

    /**
     * 获取右边文字
     */
    public String getRightTxt() {
        return btn_title_right2.getText() + "";
    }

    /**
     * 设置标题文字颜色
     */
    public void setTitleColor(int color) {
        title_text.setTextColor(color);
        btn_title_right2.setTextColor(color);
    }

    /**
     * 设置标题文字颜色
     */
    public void setTitleRigthColor(int color) {
        btn_title_right2.setTextColor(color);
    }

    /**
     * 设置标题栏背景
     */
    public void setBackgroundColor(int color) {
        layout_title.setBackgroundColor(color);
    }

    public TextView getTextView() {
        return btn_title_right2;
    }
}
