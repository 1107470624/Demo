package huihui.xiao.ph.demo.ui19;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import huihui.xiao.ph.demo.R;

public class ShotActivity extends AppCompatActivity {
    private String TAG = "ShotActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shot);
        Intent intent = new Intent(ShotActivity.this,FxService.class);
        stopService(intent);
        screenshot();
    }

    private void screenshot() {
        // 获取屏幕
        View dView = getWindow().getDecorView();
        dView.setDrawingCacheEnabled(true);
        dView.buildDrawingCache();
        Bitmap bmp = dView.getDrawingCache();
        Log.e(TAG,"截屏");
        FileOutputStream os = null;
        if (bmp!=null){
            try {
            // 获取内置SD卡路径
            String sdCardPath = Environment.getExternalStorageDirectory().getPath();
            // 图片文件路径
            String filePath = sdCardPath + File.separator + "screenshot.png";
            Log.e(TAG , "保存中");

            File file = new File(filePath);
                os = null;
                os = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG,100,os);
                os.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (bmp!=null){
                    try {
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ShotActivity.this.finish();
    }
}
