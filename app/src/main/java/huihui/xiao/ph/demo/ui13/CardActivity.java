package huihui.xiao.ph.demo.ui13;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import java.util.Arrays;

import huihui.xiao.ph.demo.R;
import huihui.xiao.ph.demo.ui13.cardstack.CardStackView;

public class CardActivity extends Activity {


    public static Integer[] TEST_DATAS = new Integer[]{
            R.color.color_1,
            R.color.color_2,
            R.color.color_3,
            R.color.color_4,
            R.color.color_5,
            R.color.color_6,
            R.color.color_7,
            R.color.color_8,
            R.color.color_9,
            R.color.color_10,
            R.color.color_11,
            R.color.color_12,
            R.color.color_13,
            R.color.color_14,
            R.color.color_15,
            R.color.color_16,
            R.color.color_17,
            R.color.color_18,
            R.color.color_19,
            R.color.color_20,
            R.color.color_21,
            R.color.color_22,
            R.color.color_23,
            R.color.color_24,
            R.color.color_25,
            R.color.color_26
    };
    private CardStackView mStackView;
    private TestStackAdapter mTestStackAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        mStackView = (CardStackView) findViewById(R.id.stackview_main);
        mTestStackAdapter = new TestStackAdapter(CardActivity.this);
        mStackView.setAdapter(mTestStackAdapter);

        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        mTestStackAdapter.updateData(Arrays.asList(TEST_DATAS));
                    }
                }
                , 200
        );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CardActivity.this.finish();
    }
}
