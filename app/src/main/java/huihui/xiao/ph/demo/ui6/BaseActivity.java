package huihui.xiao.ph.demo.ui6;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import huihui.xiao.ph.demo.R;

public class BaseActivity extends Activity {

    private TextView title_tv;
    private Button anniu1, anniu2;
    private LinearLayout ly_content;

    // 内容区域的布局
    private View contentView;
    private View titleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_base);

        title_tv = (TextView) findViewById(R.id.title_tv);
        anniu1 = (Button) findViewById(R.id.anniu1);
        anniu2 = (Button) findViewById(R.id.anniu2);
        ly_content = (LinearLayout) findViewById(R.id.ly_content);
    }

    /***
     * 设置内容区域
     *
     * @param resId 资源文件ID
     */
    public void setContentLayout(int resId) {

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(resId, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT);
        contentView.setBackgroundDrawable(null);
        if (null != ly_content) {
            ly_content.addView(contentView,layoutParams);
        }
    }
    /***
     * 设置内容区域
     *
     * @param view
     *            View对象
     */
    public void setContentLayout(View view) {
        if (null != ly_content) {
            ly_content.addView(view);
        }
    }


    /**
     * 得到内容的View
     *
     * @return
     */
    public View getLyContentView() {

        return contentView;
    }

    /**
     * 得到左边的按钮
     *
     * @return
     */
    public Button getbtn_left() {
        return anniu1;
    }

    /**
     * 得到右边的按钮
     *
     * @return
     */
    public Button getbtn_right() {
        return anniu2;
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        if (null != title_tv) {
            title_tv.setText(title);
        }
    }

    /**
     * 设置标题
     *
     * @param resId
     */
    public void setTitle(int resId) {
        title_tv.setText(getString(resId));
    }

    /**
     * 设置左边按钮的图片资源
     *
     * @param resId
     */
    public void setbtn_leftRes(int resId) {
        if (null != anniu1) {
            anniu1.setBackgroundResource(resId);
        }

    }

    /**
     * 设置左边按钮的图片资源
     *
     */
    public void setbtn_leftRes(Drawable drawable) {
        if (null != anniu1) {
            anniu1.setBackgroundDrawable(drawable);
        }

    }

    /**
     * 设置右边按钮的图片资源
     *
     * @param resId
     */
    public void setbtn_rightRes(int resId) {
        if (null != anniu2) {
            anniu2.setBackgroundResource(resId);
        }
    }

    /**
     * 设置右边按钮的图片资源
     *
     * @param drawable
     */
    public void setbtn_rightRes(Drawable drawable) {
        if (null != anniu2) {
            anniu2.setBackgroundDrawable(drawable);
        }
    }

    /**
     * 隐藏上方的标题栏
     */
    public void hideTitleView() {

        if (null != titleView) {
            titleView.setVisibility(View.GONE);
        }
    }

    /**
     * 隐藏左边的按钮
     */
    public void hidebtn_left() {

        if (null != anniu1) {
            anniu1.setVisibility(View.GONE);
        }

    }

    /***
     * 隐藏右边的按钮
     */
    public void hidebtn_right() {
        if (null != anniu2) {
            anniu2.setVisibility(View.GONE);
        }

    }

    public BaseActivity() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseActivity.this.finish();
    }
}
