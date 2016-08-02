package huihui.xiao.ph.demo.ui1;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import huihui.xiao.ph.demo.R;

/**
 * Created by Administrator on 2016/7/11.
 */
public class MyListView extends ListView {

    private static final String TAG = "MyListView";

    private LayoutInflater inflater;

    /**
     * 手指按下的x,y坐标,以及移动以后的x,y坐标
     */
    private int xDown;

    private int yDown;

    private int xMove;

    private int yMove;
    //    左移、右移
    private boolean isRightSliding;
    private boolean isLeftSliding;

    //    滑动的最小距离
    private int touchSlop;
    //    PopWindow弹窗
    private PopupWindow popupWindow;
    private int popWindowWidth;
    private int popWindowHeight;

    private Button delButton;

    private int mCurrentViewPosition;
    private View mCurrentView;

    //回调接口
    private DeleteItemListener deleteItemListener;

    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflater = LayoutInflater.from(context);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        View view = inflater.inflate(R.layout.delete_item, null);
        delButton = (Button) view.findViewById(R.id.id_item_btn);

        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.getContentView().measure(0, 0);
        popWindowWidth = popupWindow.getContentView().getMeasuredWidth();
        popWindowHeight = popupWindow.getContentView().getMeasuredHeight();
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
//       获取手势
        int action = event.getAction();
//        手势的坐标
        int x = (int) event.getX();
        int y = (int) event.getY();
//        MotionEvent.ACTION_UP:    // 抬起 = 1
        switch (action) {
//           按下 = 0
            case MotionEvent.ACTION_DOWN:
                xDown = x;
                yDown = y;
                if (popupWindow.isShowing()) {
                    dismissPopWindow();
                }
                mCurrentViewPosition = pointToPosition(xDown, yDown);
                View view = getChildAt(mCurrentViewPosition);
                mCurrentView = view;
                break;
//          移动 = 2
            case MotionEvent.ACTION_MOVE:
                xMove = x;
                yMove = y;
                int offsetX = xDown - xMove;
                int offsetY = yDown - yMove;
                if (xMove < xDown && Math.abs(offsetX) > touchSlop && Math.abs(offsetY) < touchSlop) {
                    Log.e(TAG,"isLeftSliding");
                    isLeftSliding = true;
                } else if(xMove >xDown && Math.abs(offsetX) >touchSlop
                        && Math.abs(offsetY) < touchSlop){
                    Log.e(TAG,"isRightSliding");
                    isRightSliding = true;
                }
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (isLeftSliding) {
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE:
                    int location[] = new int[2];
                    mCurrentView.getLocationOnScreen(location);
                    popupWindow.setAnimationStyle(R.style.popwindow_delete_btn_anim_style);
                    popupWindow.update();
                    popupWindow.showAtLocation(mCurrentView,
                            Gravity.LEFT | Gravity.TOP, location[0] + mCurrentView.getWidth(),
                            location[1] + mCurrentView.getHeight() / 2
                                    - popWindowHeight / 2);
                    delButton.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (deleteItemListener != null) {
                                deleteItemListener.DeleteItem(mCurrentViewPosition);
                                popupWindow.dismiss();
                            }
                        }
                    });
                    break;
                case MotionEvent.ACTION_UP:
                    isLeftSliding = false;
                    break;
            }
            //防止与Item点击事件冲突
            return true;
        } else if (isRightSliding) {
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (popupWindow.isShowing()) {
                        dismissPopWindow();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    isRightSliding = false;
                    break;
            }
            return true;
        }
        return super.onTouchEvent(event);
    }

    public void setDeleteItemListener(DeleteItemListener listener) {
        deleteItemListener = listener;
    }

    public interface DeleteItemListener {
        void DeleteItem(int position);
    }

    private void dismissPopWindow() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }
}
