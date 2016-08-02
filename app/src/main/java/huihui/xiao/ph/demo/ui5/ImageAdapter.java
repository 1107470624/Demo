package huihui.xiao.ph.demo.ui5;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import huihui.xiao.ph.demo.R;

/**
 * Created by Administrator on 2016/7/12.
 */
public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    //  图片数组源
    public Integer[] imgs = { R.mipmap.img1, R.mipmap.img2,
            R.mipmap.img3, R.mipmap.img4, R.mipmap.img5,
            R.mipmap.img6, R.mipmap.img7};

    ImageAdapter(Context mContext){
        this.mContext = mContext;
    }
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
//         return imgs.length;
    }

    @Override
    public Object getItem(int position) {
        return imgs[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageview = new ImageView(mContext);
//            区余为了循环
        imageview.setImageResource(imgs[position%imgs.length]);
        // 设置布局 图片120×120显示
        imageview.setLayoutParams(new Gallery.LayoutParams(240, 94));
        // 设置显示比例类型（不缩放）
        imageview.setScaleType(ImageView.ScaleType.CENTER);
        imageview.setBackgroundColor(Color.alpha(1));
        return imageview;
    }
}
