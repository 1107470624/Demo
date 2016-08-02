package huihui.xiao.ph.demo.ui3;

import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2016/7/11.
 */
public class StackItem {

    public String text;
    public Drawable img;

    public StackItem(String text,Drawable photo)
    {
        this.img = photo;
        this.text = text;
    }
}
