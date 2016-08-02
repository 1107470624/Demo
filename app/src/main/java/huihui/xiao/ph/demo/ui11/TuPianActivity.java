package huihui.xiao.ph.demo.ui11;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import huihui.xiao.ph.demo.R;
import huihui.xiao.ph.demo.ui3.stackView;

/**
 * Created by Administrator on 2016/7/19.
 */
public class TuPianActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_tupian);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TuPianActivity.this.finish();
    }
}
