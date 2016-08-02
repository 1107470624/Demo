package huihui.xiao.ph.demo.ui12;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import huihui.xiao.ph.demo.R;

public class WeiXinActivity extends Activity implements AdapterView.OnItemClickListener {

    private GridView gridview;
    List<Map<String, Object>> mData = new ArrayList<Map<String,Object>>();
    Map<String, Object> map;
    MyGridView myGridView;

    // 图片的抽象路径
    String filepath = Environment.getExternalStorageDirectory() + File.separator + "weixinshuju" + File.separator;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_wei_xin);

        gridview = (GridView) findViewById(R.id.gridview);
        context = WeiXinActivity.this;
        myGridView = new MyGridView(this, mData);
        map = new HashMap<String, Object>();
        anniu();
        gridview.setAdapter(myGridView);
        gridview.setOnItemClickListener(WeiXinActivity.this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mData.size()>0){
            mData = mData.subList(0, 1);
            myGridView.setList(mData);
        }else{
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Bl(filepath);
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(), CanmerActivity.class);
            startActivity(intent);
        }
		/*
		 * if(position == 0){ map.put("image", R.drawable.a1); mData.add(map);
		 * Toast.makeText(getApplicationContext(), "00",
		 * Toast.LENGTH_SHORT).show();
		 *
		 * }else{ Toast.makeText(getApplicationContext(), "11",
		 * Toast.LENGTH_SHORT).show(); } myGridView.notifyDataSetChanged();
		 */
    }

    public void readBitmap(String name) {
        Bitmap bitmap = null;
        File file = new File(filepath + name);
        InputStream fis = null;
        try {
            fis = new FileInputStream(file);
            bitmap = BitmapFactory.decodeStream(fis);
            map.put("bitmap", bitmap);
            mData.add(map);
            fis.close();
            // 刷新数据
            myGridView.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //	将按钮写进去
    public void anniu(){
        Bitmap bitmap = getBitmap();
        File myFile = new File(filepath);
        if (!myFile.exists()){
            myFile.mkdir();
        }
        File file = new File(filepath + "anniu");
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            readBitmap("anniu");
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
    }

    // 实例化图片对象
    public  Bitmap getBitmap() {
        Resources res = context.getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(res, R.mipmap.a1);
        return bitmap;
    }

    public void Bl(String filePath){
        File root = new File(filePath);
        File[] files = root.listFiles();
        for(File file : files){
//				判断是否为文件夹
            if(file.isDirectory()){
                Bl(file.getAbsolutePath());
            }else{
                String str = file.getAbsolutePath();
                String [] ing = str.split("/");
                if(ing[5].equals("anniu")){
                    break ;
                }
//					将图片读取出来
                readBitmap(ing[5]);
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        WeiXinActivity.this.finish();
    }
}
