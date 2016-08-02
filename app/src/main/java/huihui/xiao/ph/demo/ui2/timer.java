package huihui.xiao.ph.demo.ui2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import huihui.xiao.ph.demo.R;

public class timer extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_timer);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.this.finish();
    }
}
