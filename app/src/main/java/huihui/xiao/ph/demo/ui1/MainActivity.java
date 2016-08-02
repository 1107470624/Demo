package huihui.xiao.ph.demo.ui1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import huihui.xiao.ph.demo.R;

public class MainActivity extends Activity {

    private MyListView myListView;
    private ArrayAdapter<String> adapter;
    private List<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        myListView = (MyListView) findViewById(R.id.MyListView);
        mData = new ArrayList<>(Arrays.asList("1","2","3"
        ,"4","5","6","7","8","9","10"));
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mData);
        myListView.setAdapter(adapter);

        myListView.setDeleteItemListener(new MyListView.DeleteItemListener() {
            @Override
            public void DeleteItem(int position) {
                adapter.remove(adapter.getItem(position));
            }
        });

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Item的点击事件
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MainActivity.this.finish();
    }
}
