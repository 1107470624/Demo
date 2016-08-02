package huihui.xiao.ph.demo.ui6;

import android.os.Bundle;
import android.view.View;

import huihui.xiao.ph.demo.R;

public class TwoBtnActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_two_btn);
        //设置标题
        setTitle("两个按钮");
        // 为左边的按钮增加监听事件
        getbtn_left().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TwoBtnActivity.this.finish();
    }
}
