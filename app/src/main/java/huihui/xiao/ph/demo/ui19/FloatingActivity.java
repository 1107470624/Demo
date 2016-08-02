package huihui.xiao.ph.demo.ui19;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import huihui.xiao.ph.demo.R;

public class FloatingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating);
        //FloatingWindowActivity的布局视图按钮
        Button start = (Button)findViewById(R.id.start_id);

        Button remove = (Button)findViewById(R.id.remove_id);

      start.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
// TODO Auto-generated method stub
              Intent intent = new Intent(FloatingActivity.this, FxService.class);
              startService(intent);
              finish();
          }
      });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub
                Intent intent = new Intent(FloatingActivity.this, FxService.class);
                stopService(intent);
            }
        });
    }
}
