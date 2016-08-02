package huihui.xiao.ph.demo.ui16;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import huihui.xiao.ph.demo.R;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImageActivity.this.finish();
    }
}
