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
 * Created by Administrator on 2016/7/11.
 */
public class ClockView extends View {

    private int width;
    private int height;
    private Paint mPaint;
    private Paint mPaintLine;
    private Paint mPaintCircle;
    private Paint mPaintHour;
    private Paint mPaintMinute;
    private Paint mPaintSec;
    private Paint mPaintText;
    private Calendar mCalendar;
    public static final int NEED_INVALIDATE = 0X23;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case NEED_INVALIDATE:
                    mCalendar = Calendar.getInstance();
//                    invalidate()是用来刷新View的，
// 必须是在UI线程中进行工作。比如在修改某个view的显示时，
// 调用invalidate()才能看到重新绘制的界面。
// invalidate()的调用是把之前的旧的view从主UI线程队列中pop掉。
                    invalidate();//告诉UI主线程重新绘制
                    handler.sendEmptyMessageDelayed(NEED_INVALIDATE, 1000);
                    break;
                default:
                    break;
            }
        }
    };

    public ClockView(Context context) {
        super(context);
    }

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        mCalendar = Calendar.getInstance();

        mPaintLine = new Paint();
        mPaintLine.setColor(Color.BLUE);
        mPaintLine.setStrokeWidth(10);

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

        mPaintHour = new Paint();
        mPaintHour.setStrokeWidth(20);
        mPaintHour.setColor(Color.BLUE);

        mPaintMinute = new Paint();
        mPaintMinute.setStrokeWidth(15);
        mPaintMinute.setColor(Color.BLUE);

        mPaintSec = new Paint();
        mPaintSec.setStrokeWidth(10);
        mPaintSec.setColor(Color.BLUE);

        //向handler发送一个消息，让它开启重绘
        handler.sendEmptyMessage(NEED_INVALIDATE);
    }

    public ClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //    检测view组件以及它的子组件的大小
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        获取说及屏幕的宽和高
        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
//     设置这个布局占父布局的位置
        setMeasuredDimension(width, height);
    }

    //    绘制view的内容
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int circleRadius = 400;
//         画出大圆(圆的位置)
//        cx：圆心的x坐标。
//        cy：圆心的y坐标。
//        radius：圆的半径。
//        paint：绘制时所使用的画笔
        canvas.drawCircle(width / 2, height / 2, circleRadius, mPaintCircle);
        //画出圆中心(圆心的位置)
        canvas.drawCircle(width / 2, height / 2, 20, mPaintCircle);
        //依次旋转画布，画出每个刻度和对应数字
        for (int i = 1; i <= 60; i++) {
            canvas.save();//保存当前画布
            canvas.rotate(i*6, width / 2, height / 2);
            //左起：起始位置x坐标，起始位置y坐标，终止位置x坐标，终止位置y坐标，画笔(一个Paint对象)
//            public void drawLine (float startX, float startY, float stopX, float stopY, Paint paint)
//            startX：起始端点的X坐标。 startY：起始端点的Y坐标。
//            stopX：终止端点的X坐标。stopY：终止端点的Y坐标。
//            paint：绘制直线所使用的画笔。
            if (i%5==0) {
            canvas.drawLine(width / 2,
                    height / 2 - circleRadius, width / 2,
                    height / 2 - circleRadius + 30, mPaintCircle);
            //左起：文本内容，起始位置x坐标，起始位置y坐标，画笔
                canvas.drawText("" + i/5, width / 2, height / 2 - circleRadius + 70, mPaintText);
            }else{
                canvas.drawLine(width / 2,
                        height / 2 - circleRadius, width / 2,
                        height / 2 - circleRadius + 30, mPaint);
            }
            canvas.restore();
        }

        int minute = mCalendar.get(Calendar.MINUTE);//得到当前分钟数
        int hour = mCalendar.get(Calendar.HOUR);//得到当前小时数
        int sec = mCalendar.get(Calendar.SECOND);//得到当前秒数

        float minuteDegree = minute / 60f * 360;//得到分针旋转的角度
        canvas.save();
        canvas.rotate(minuteDegree, width / 2, height / 2);
        canvas.drawLine(width / 2, height / 2 - 250, width / 2, height / 2 + 40, mPaintMinute);
        canvas.restore();

        float hourDegree = (hour * 60 + minute) / 12f / 60 * 360;//得到时钟旋转的角度
        canvas.save();
        canvas.rotate(hourDegree, width / 2, height / 2);
        canvas.drawLine(width / 2, height / 2 - 200, width / 2, height / 2 + 30, mPaintHour);
        canvas.restore();

        float secDegree = sec / 60f * 360;//得到秒针旋转的角度
        canvas.save();
        canvas.rotate(secDegree, width / 2, height / 2);
        canvas.drawLine(width / 2, height / 2 - 300, width / 2, height / 2 + 40, mPaintSec);
        canvas.restore();
    }
}
