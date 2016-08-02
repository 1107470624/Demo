package huihui.xiao.ph.demo.ui2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;

/**
 * Created by Administrator on 2016/7/12.
 */

public class ClockView2 extends View{

    private int width;
    private int height;
//    画笔对象
    private Paint mPaint;
    private Paint mPaintLinr;
    private Paint mPaintCircle;
    private Paint mPaintText;
//   获取当前时间
    private Calendar mCalendar;
    public static final int NEED_INVALIDATE = 0X23;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case NEED_INVALIDATE:
                    mCalendar = Calendar.getInstance();
                    invalidate();
                    handler.sendEmptyMessageDelayed(NEED_INVALIDATE, 1000);
                    break;
                default:
                    break;
            }
        }
    };
    public ClockView2(Context context) {
        super(context);
    }

    public ClockView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ClockView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView(){
        mCalendar = Calendar.getInstance();

        mPaintLinr = new Paint();
        mPaintLinr.setColor(Color.BLUE);
        mPaintLinr.setStrokeWidth(10);

        mPaintCircle = new Paint();
        mPaintCircle.setColor(Color.GREEN);//设置颜色
        mPaintCircle.setStrokeWidth(10);//设置线宽
        mPaintCircle.setAntiAlias(true);//设置是否抗锯齿
        mPaintCircle.setStyle(Paint.Style.STROKE);//设置绘制风格

        mPaint = new Paint();
        mPaint.setColor(Color.RED);//设置颜色
        mPaint.setStrokeWidth(5);//设置线宽
        mPaint.setAntiAlias(true);//设置是否抗锯齿
        mPaint.setStyle(Paint.Style.STROKE);//设置绘制风格

        mPaintText = new Paint();
        mPaintText.setColor(Color.BLUE);
        mPaintText.setStrokeWidth(10);
        mPaintText.setTextAlign(Paint.Align.CENTER);
        mPaintText.setTextSize(40);

        //向handler发送一个消息，让它开启重绘
        handler.sendEmptyMessage(NEED_INVALIDATE);
    }



    //    检测view组件以及它的子组件的大小
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        获取说及屏幕的宽和高
        width = getDefaultSize(getSuggestedMinimumWidth(),widthMeasureSpec);
        height = getDefaultSize(getSuggestedMinimumHeight(),heightMeasureSpec);
        //     设置这个布局占父布局的位置
        setMeasuredDimension(width,height);
    }

    //    绘制view的内容

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//      绘制圆的半径
        int circleRadius = 400;
//         画出大圆(圆的位置)
//        cx：圆心的x坐标。
//        cy：圆心的y坐标。
//        radius：圆的半径。
//        paint：绘制时所使用的画笔
        canvas.drawCircle(width/2,height/2,circleRadius,mPaintCircle);
        //画出圆中心(圆心的位置)
        canvas.drawCircle(width/2,height/2,20,mPaintCircle);

        //依次旋转画布，画出每个刻度和对应数字
        for (int i =1;i<=60;i++){
            //保存当前画布
            canvas.save();
//          画笔的偏移度
            canvas.rotate(i*6,width/2,height/2);
            if (i%5==0){
                //          左起：起始位置x坐标，起始位置y坐标，终止位置x坐标，终止位置y坐标，画笔(一个Paint对象)
//            public void drawLine (float startX, float startY, float stopX, float stopY, Paint paint)
//            startX：起始端点的X坐标。 startY：起始端点的Y坐标。
//            stopX：终止端点的X坐标。stopY：终止端点的Y坐标。
//            paint：绘制直线所使用的画笔。
                canvas.drawLine(width/2,height/2-circleRadius,
                        width/2,height/2-circleRadius+30,mPaintCircle);
                canvas.drawText("" + (i/5),width / 2, height / 2 - circleRadius + 70, mPaintText);
            }else{
                canvas.drawLine(width/2,height/2-circleRadius,width/2
                ,height/2-circleRadius+30,mPaint);
            }
            canvas.restore();
        }

        int minute =mCalendar.get(Calendar.MINUTE);//得到当前分钟数
        int hour = mCalendar.get(Calendar.HOUR);//得到当前小时数
        int sec = mCalendar.get(Calendar.SECOND);//得到当前秒数

        float minuteDegree = minute /60f*30;//得到分针旋转的角度
        canvas.save();
        canvas.rotate(minuteDegree,width/2,height/2);
        canvas.drawLine(width / 2, height / 2 - 250, width / 2, height / 2 + 40, mPaintText);
        canvas.restore();

        float hourDegree = (hour * 60 + minute) / 12f / 60 * 360;//得到时钟旋转的角度
        canvas.save();
        canvas.rotate(hourDegree, width / 2, height / 2);
        canvas.drawLine(width / 2, height / 2 - 200, width / 2, height / 2 + 30, mPaintText);
        canvas.restore();

        float secDegree = sec / 60f * 360;//得到秒针旋转的角度
        canvas.save();
        canvas.rotate(secDegree, width / 2, height / 2);
        canvas.drawLine(width / 2, height / 2 - 300, width / 2, height / 2 + 40, mPaintText);
        canvas.restore();
    }
}
