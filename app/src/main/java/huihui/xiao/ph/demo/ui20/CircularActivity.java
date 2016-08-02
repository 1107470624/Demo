package huihui.xiao.ph.demo.ui20;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import huihui.xiao.ph.demo.R;

public class CircularActivity extends AppCompatActivity {

    ProgressBar mProgressBar;
    Button mChangeBtn, mActivityImageBtn, mActivityColorBtn, mActivityFinishBtn;
    ImageView mLogoBtnIv;
    LinearLayout mContentLayout;
    boolean isContentVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular);

        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        mChangeBtn = (Button) findViewById(R.id.change_btn);
        mActivityImageBtn = (Button) findViewById(R.id.activity_image_btn);
        mActivityColorBtn = (Button) findViewById(R.id.activity_color_btn);
        mActivityFinishBtn = (Button) findViewById(R.id.activity_finish_btn);
        mLogoBtnIv = (ImageView) findViewById(R.id.logoBtn_iv);
        mContentLayout = (LinearLayout) findViewById(R.id.content_layout);


        mChangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setEnabled就相当于总开关一样，只有总开关打开了，才能使用其他事件
                mChangeBtn.setEnabled(false);
                //收缩按钮
                CircularAnimUtil.hide(mChangeBtn);
            }
        });
    /*    空指针，还没找到原因。。。
    mProgressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressBar.setVisibility(View.GONE);
                mChangeBtn.setEnabled(true);
                // 伸展按钮
                CircularAnimUtil.show(mChangeBtn);
            }
        });*/

        mActivityImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 先将图片展出铺满，然后启动新的Activity
                CircularAnimUtil.startActivity(CircularActivity.this, EmptyActivity.class, view, R.mipmap.img_huoer_black);
            }
        });

        mActivityColorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 先将颜色展出铺满，然后启动新的Activity
                CircularAnimUtil.startActivity(CircularActivity.this, EmptyActivity.class, view, R.color.colorPrimary);
            }
        });

        mActivityFinishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 先将颜色展出铺满，然后启动新的Activity并finish当前Activity.
                Intent intent = new Intent(CircularActivity.this, EmptyActivity.class);
                CircularAnimUtil.startActivityThenFinish(CircularActivity.this, intent, view, R.color.colorPrimary);
            }
        });

        mLogoBtnIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.animate().rotationBy(90);
                // 以 @mLogoBtnIv 为中心，收缩或伸展 @mContentLayout
                if (isContentVisible)
                    CircularAnimUtil.hideOther(mLogoBtnIv, mContentLayout);
                else
                    CircularAnimUtil.showOther(mLogoBtnIv, mContentLayout);

                isContentVisible = !isContentVisible;
            }
        });
    }
}
