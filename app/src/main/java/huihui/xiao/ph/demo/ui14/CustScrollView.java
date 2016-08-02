package huihui.xiao.ph.demo.ui14;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

public class CustScrollView extends ScrollView{

	private static final int TOUCH_IDLE = 0;
	// touch�¼���ScrollView�ڲ�����
	private static final int TOUCH_INNER_CONSIME = 1;
	// touch�¼����ϲ��DragLayoutȥ����
	private static final int TOUCH_DRAG_LAYOUT = 2;
	
	 // ���µ�ʱ���Ƿ��ڵײ�
	boolean isAtBottom;
	// �ж�Ϊ��������ֵ����λ������
	private int mTouchSlop = 4;
	private int scrollMode;
	private float downY;
	

	
	  public interface ScrollViewListener {  
		  
	        void onScrollChanged(CustScrollView scrollView, int x, int y,
								 int oldx, int oldy);
	  
	    }  
	  
	    private ScrollViewListener scrollViewListener = null; 
	
	public CustScrollView(Context context) {
		this(context,null);
	}
	public CustScrollView(Context context, AttributeSet attrs) {
		this(context, attrs,0);
	}
	public CustScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		ViewConfiguration configuration = ViewConfiguration.get(context);
		mTouchSlop = configuration.getScaledTouchSlop();
	}
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
//		�ж�����
		if(ev.getAction() == MotionEvent.ACTION_DOWN){
			downY = ev.getRawY();
			isAtBottom = isAtBottom();
			getParent().requestDisallowInterceptTouchEvent(true);
		}else if(ev.getAction() == MotionEvent.ACTION_MOVE){
			if (scrollMode == TOUCH_IDLE) {
                float yOffset = downY - ev.getRawY();
                float yDistance = Math.abs(yOffset);
                if (yDistance > mTouchSlop) {
                    if (yOffset > 0 && isAtBottom) {
                        scrollMode = TOUCH_DRAG_LAYOUT;
                        getParent().requestDisallowInterceptTouchEvent(false);
                        return true;
                    } else {
                        scrollMode = TOUCH_INNER_CONSIME;
                    }
                }
            }
		}
		return super.dispatchTouchEvent(ev);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if(scrollMode == TOUCH_DRAG_LAYOUT){
			return false;
		}
		return super.onTouchEvent(ev);
	}
	
	private boolean isAtBottom(){
		return getScrollY() + getMeasuredHeight() >= 
				computeVerticalScrollRange();
	}
	
	
	public void setScrollViewListener(ScrollViewListener scrollViewListener) {  
        this.scrollViewListener = scrollViewListener;  
    }  
  
    @Override  
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {  
        super.onScrollChanged(x, y, oldx, oldy);  
        if (scrollViewListener != null) {  
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);  
        }  
    }  

}
