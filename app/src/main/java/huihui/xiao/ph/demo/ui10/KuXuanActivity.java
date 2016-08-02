package huihui.xiao.ph.demo.ui10;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import huihui.xiao.ph.demo.R;

public class KuXuanActivity extends AppCompatActivity {

    String TAG = "MainActivity";
    ShineButton shineButton;
    ShineButton porterShapeImageView1;
    ShineButton porterShapeImageView2;
    ShineButton porterShapeImageView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ku_xuan);

        shineButton = (ShineButton) findViewById(R.id.po_image0);
        porterShapeImageView1 = (ShineButton) findViewById(R.id.po_image1);
        porterShapeImageView2 = (ShineButton) findViewById(R.id.po_image2);
        porterShapeImageView3 = (ShineButton) findViewById(R.id.po_image3);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.wrapper);

        if (shineButton != null)
            shineButton.init(this);

        if (porterShapeImageView1 != null)
            porterShapeImageView1.init(this);

        if (porterShapeImageView2 != null)
            porterShapeImageView2.init(this);

        if (porterShapeImageView3 != null)
            porterShapeImageView3.init(this);
/**
 * 动态添加Button
 */
        ShineButton shineButtonJava = new ShineButton(this);

        //未点击时的颜色
        shineButtonJava.setBtnColor(Color.GRAY);
        //点击时的颜色
        shineButtonJava.setBtnFillColor(Color.RED);
        //需要点击的图标
        shineButtonJava.setShapeResource(R.mipmap.heart);

        shineButtonJava.setAllowRandomColor(true);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 100);
        shineButtonJava.setLayoutParams(layoutParams);

        if (linearLayout != null) {
            linearLayout.addView(shineButtonJava);
        }
        //系统的点击事件
        shineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "click");
            }
        });

        //自定义的点击事件，并且返回返回点击的状态
        shineButton.setOnCheckStateChangeListener(new ShineButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(View view, boolean checked) {
                Log.e(TAG, "click "+checked );
            }
        });

        porterShapeImageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "click");
            }
        });
        porterShapeImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "click");
            }
        });
        porterShapeImageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "click");
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        KuXuanActivity.this.finish();
    }
}
