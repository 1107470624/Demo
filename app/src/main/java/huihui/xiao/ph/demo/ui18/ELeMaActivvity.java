package huihui.xiao.ph.demo.ui18;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import huihui.xiao.ph.demo.R;
import huihui.xiao.ph.demo.ui18.shoppoing.ShoppingView;

public class ELeMaActivvity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xi_tong_activvity);

        ShoppingView mSv1 =  (ShoppingView) findViewById(R.id.sv_1);

        mSv1.setOnShoppingClickListener(new ShoppingView.ShoppingClickListener() {
            @Override
            public void onAddClick(int num) {
                Log.e("@=>", "add.......num=> " + num);
            }

            @Override
            public void onMinusClick(int num) {
                Log.e("@=>", "minus.......num=> " + num);
            }
        });
        ShoppingView mSv2 = (ShoppingView) findViewById(R.id.sv_2);
        mSv2.setTextNum(1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ELeMaActivvity.this.finish();
    }
}
