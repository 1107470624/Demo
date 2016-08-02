package huihui.xiao.ph.demo.ui14;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import huihui.xiao.ph.demo.R;

public class XiaHuaActivity extends AppCompatActivity {

    private DragLayout draglayout;
    private Vertical1 ver1;
    private Vertical2 ver2;

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xia_hua);
        textView = (TextView) findViewById(R.id.textview);
        initView();
    }
    /**
     * 初始化View
     */
    private void initView() {
        ver1 = new Vertical1(textView);
        ver2 = new Vertical2();
        // 添加事务
        getSupportFragmentManager().beginTransaction().add(R.id.first, ver1).add(R.id.second, ver2).commit();

        DragLayout.ShowNextPageNotifier nextIntf = new DragLayout.ShowNextPageNotifier() {
            @Override
            public void onDragNext() {
                ver2.initView();
            }
        };
        draglayout = (DragLayout) findViewById(R.id.draglayout);
        draglayout.setNextPageListener(nextIntf);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        XiaHuaActivity.this.finish();
    }
}

