package huihui.xiao.ph.demo.ui5;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.Toast;

import huihui.xiao.ph.demo.R;
import huihui.xiao.ph.demo.ui3.stackView;

public class GalleryActivity extends Activity {

    private ImageAdapter imageAdapter = null;
    private Gallery gallery = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_gallery);
        gallery = (Gallery) findViewById(R.id.gallery);
        imageAdapter = new ImageAdapter(this);
        // gallery添加ImageAdapter图片资源(适配器)
        gallery.setAdapter(imageAdapter);


//        高级的显示
        // 设置水平居中显示
        gallery.setGravity(Gravity.CENTER_HORIZONTAL);
        // 设置起始图片显示位置（可以用来制作gallery循环显示效果）
        gallery.setSelection(imageAdapter.imgs.length*100);
        // 设置点击图片的监听事件（需要用手点击才触发，滑动时不触发）
        // gallery设置点击图片资源的事件
        gallery.setOnItemClickListener(listener);
        // 设置选中图片的监听事件（当图片滑到屏幕正中，则视为自动选中）
        gallery.setOnItemSelectedListener(selectedListener);
        // 设置未选中图片的透明度
        gallery.setUnselectedAlpha(0.3f);
        // 设置图片之间的间距
        gallery.setSpacing(40);

    }
    AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(GalleryActivity.this, "图片 " + (position + 1), Toast.LENGTH_SHORT).show();
        }
    };
    // 选中图片的监听事件
    AdapterView.OnItemSelectedListener selectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(GalleryActivity.this,"选中图片"+(position+1),Toast.LENGTH_SHORT).show();;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GalleryActivity.this.finish();
    }
}
