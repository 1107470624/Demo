package huihui.xiao.ph.demo.ui19;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import huihui.xiao.ph.demo.R;

/**
 * Created by Administrator on 2016/7/31.
 */
public class FxService extends Service {

    //定义浮动窗口布局
    LinearLayout mFloatLayout;
    WindowManager.LayoutParams wmParams;
    //创建浮动窗口设置布局参数的对象
    WindowManager mWindowManager;

    //创建浮动窗口显示的图片
    ImageView mFloatView;

    //Log日志的TAG
    private static final String TAG = "FxService";
    //浮动窗口是否长按
    private boolean longClick = false;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate");
        createFloatView();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void createFloatView() {
        wmParams = new WindowManager.LayoutParams();
        //获取WindowManagerImpl.CompatModeWrapper
        mWindowManager = (WindowManager) getApplication().getSystemService(
                getApplication().WINDOW_SERVICE
        );
        //设置window type
        wmParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        //设置图片格式，效果为背景透明
        wmParams.format = PixelFormat.RGBA_8888;
        //设置浮动窗口不可聚焦（实现操作除浮动窗口外的其他可见窗口的操作）
        wmParams.flags =
                //          LayoutParams.FLAG_NOT_TOUCH_MODAL |
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
//          LayoutParams.FLAG_NOT_TOUCHABLE
        ;

        //调整悬浮窗显示的停靠位置为左侧置顶
        wmParams.gravity = Gravity.LEFT | Gravity.TOP;

        // 以屏幕左上角为原点，设置x、y初始值
        wmParams.x = 0;
        wmParams.y = 200;
         /*// 设置悬浮窗口长宽数据
        wmParams.width = 200;
        wmParams.height = 80;*/
        //设置悬浮窗口长宽数据
        wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        LayoutInflater inflater = LayoutInflater.from(getApplication());
        //获取浮动窗口视图所在布局
        mFloatLayout = (LinearLayout) inflater.inflate(R.layout.float_layout, null);
        //添加mFloatLayout
        mWindowManager.addView(mFloatLayout, wmParams);
       /* Log.e(TAG, "mFloatLayout-->left" + mFloatLayout.getLeft());
        Log.e(TAG, "mFloatLayout-->right" + mFloatLayout.getRight());
        Log.e(TAG, "mFloatLayout-->top" + mFloatLayout.getTop());
        Log.e(TAG, "mFloatLayout-->bottom" + mFloatLayout.getBottom());
   */

        //浮动窗口按钮
        mFloatView = (ImageView) mFloatLayout.findViewById(R.id.float_id);

        mFloatLayout.measure(View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED), View.MeasureSpec
                .makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
       /* Log.e(TAG, "Width/2--->" + mFloatView.getMeasuredWidth() / 2);
        Log.e(TAG, "Height/2--->" + mFloatView.getMeasuredHeight() / 2);
  */
        //设置监听浮动窗口的触摸移动
        mFloatView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (longClick){
                   // Log.e(TAG, "移动");
                    //getRawX是触摸位置相对于屏幕的坐标，getX是相对于按钮的坐标
                    wmParams.x = (int) (event.getRawX() - mFloatLayout.getMeasuredWidth() / 2);
                    //25为状态栏的高度
                    wmParams.y = (int) (event.getRawY() - mFloatLayout.getMeasuredHeight() / 2 - getStatusBarHeight());
                    Log.e(TAG, "wmParams.x" + wmParams.x);
                    Log.e(TAG, "wmParams.x" + wmParams.y);
                    //刷新
                    mWindowManager.updateViewLayout(mFloatLayout, wmParams);
                }
                return false;
            }
        });
        mFloatView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Log.i(TAG, "长按");
                longClick = true;
                return false;
            }
        });
        mFloatView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "点击");
                longClick = false;
//                Intent dialogIntent=new Intent(getBaseContext(),ShotActivity.class);
//                dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                getApplication().startActivity(dialogIntent);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mFloatLayout != null) {
            mWindowManager.removeView(mFloatLayout);
        }
    }

    /**
     * 在应用开发中，有时我们需要用代码计算布局的高度，
     * 可能需要减去状态栏(status bar)的高度。
     * 状态栏高度定义在Android系统尺寸资源中status_bar_height,
     * 但这并不是公开可直接使用的，
     * 例如像通常使用系统资源那样android.R.dimen.status_bar_height。
     * 但是系统给我们提供了一个Resource类，通过这个类我们可以获取资源文件。
     * @return
     */
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
       if (resourceId>0){
           result = resourceId;
       }
        return result;
    }
}
