package huihui.xiao.ph.demo.ui3;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.StackView;

import java.util.ArrayList;

import huihui.xiao.ph.demo.R;

public class stackView extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_stackview);

        StackView stk = (StackView)this.findViewById(R.id.stackView1);

        ArrayList<StackItem> items = new ArrayList<StackItem>();
        items.add(new StackItem("text1", this.getResources().getDrawable(R.mipmap.ic_launcher)));
        items.add(new StackItem("text2", this.getResources().getDrawable(R.mipmap.ic_launcher)));
        items.add(new StackItem("text3", this.getResources().getDrawable(R.mipmap.ic_launcher)));
        items.add(new StackItem("text4", this.getResources().getDrawable(R.mipmap.ic_launcher)));
        items.add(new StackItem("text5", this.getResources().getDrawable(R.mipmap.ic_launcher)));

        StackAdapter adapt = new StackAdapter(this, R.layout.item, items);
        stk.setAdapter(adapt);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stackView.this.finish();
    }
}
