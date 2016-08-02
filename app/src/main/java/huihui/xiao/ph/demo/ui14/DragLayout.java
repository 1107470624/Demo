package huihui.xiao.ph.demo.ui14;


import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * 杩欐槸涓�涓獀iewGroup瀹瑰櫒锛屽疄鐜颁笂涓嬩袱涓猣rameLayout鎷栧姩鍒囨崲
 * 
 * @author sistone.Zhang
 */
@SuppressLint("NewApi")
public class DragLayout extends ViewGroup {

	/* 鎷栨嫿宸ュ叿绫� */
	private final ViewDragHelper mDragHelper;
	private GestureDetectorCompat gestureDetector;

	/* 涓婁笅涓や釜frameLayout锛屽湪Activity涓敞鍏ragment */
	private View frameView1, frameView2;
	private int viewHeight;
	private static final int VEL_THRESHOLD = 100; // 婊戝姩閫熷害鐨勯槇鍊硷紝瓒呰繃杩欎釜缁濆鍊艰涓烘槸涓婁笅
	private static final int DISTANCE_THRESHOLD = 100; // 鍗曚綅鏄儚绱狅紝褰撲笂涓嬫粦鍔ㄩ�熷害涓嶅鏃讹紝閫氳繃杩欎釜闃堝�兼潵鍒ゅ畾鏄簲璇ョ矘鍒伴《閮ㄨ繕鏄簳閮�
	private int downTop1; // 鎵嬫寚鎸変笅鐨勬椂鍊欙紝frameView1鐨刧etTop鍊�
	private ShowNextPageNotifier nextPageListener; // 鎵嬫寚鏉惧紑鏄惁鍔犺浇涓嬩竴椤电殑notifier

	public DragLayout(Context context) {
		this(context, null);
	}

	public DragLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public DragLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mDragHelper = ViewDragHelper
				.create(this, 10f, new DragHelperCallback());
		mDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_BOTTOM);
		gestureDetector = new GestureDetectorCompat(context,
				new YScrollDetector());
	}

	@Override
	protected void onFinishInflate() {
		// 璺焒indviewbyId涓�鏍凤紝鍒濆鍖栦笂涓嬩袱涓獀iew
		frameView1 = getChildAt(0);
		frameView2 = getChildAt(1);
	}

	class YScrollDetector extends SimpleOnGestureListener {

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float dx,
				float dy) {
			// 鍨傜洿婊戝姩鏃禿y>dx锛屾墠琚瀹氭槸涓婁笅鎷栧姩
			return Math.abs(dy) > Math.abs(dx);
		}
	}

	@Override
	public void computeScroll() {
		if (mDragHelper.continueSettling(true)) {
			ViewCompat.postInvalidateOnAnimation(this);
		}
	}

	/**
	 * 杩欐槸鎷栨嫿鏁堟灉鐨勪富瑕侀�昏緫
	 */
	private class DragHelperCallback extends ViewDragHelper.Callback {

		@Override
		public void onViewPositionChanged(View changedView, int left, int top,
				int dx, int dy) {
			int childIndex = 1;
			if (changedView == frameView2) {
				childIndex = 2;
			}

			// 涓�涓獀iew浣嶇疆鏀瑰彉锛屽彟涓�涓獀iew鐨勪綅缃璺熻繘
			onViewPosChanged(childIndex, top);
		}

		@Override
		public boolean tryCaptureView(View child, int pointerId) {
			// 涓や釜瀛怴iew閮介渶瑕佽窡韪紝杩斿洖true
			return true;
		}

		@Override
		public int getViewVerticalDragRange(View child) {
			// 杩欎釜鐢ㄦ潵鎺у埗鎷栨嫿杩囩▼涓澗鎵嬪悗锛岃嚜鍔ㄦ粦琛岀殑閫熷害锛屾殏鏃剁粰涓�涓殢鎰忕殑鏁板��
			return 1;
		}

		@Override
		public void onViewReleased(View releasedChild, float xvel, float yvel) {
			// 婊戝姩鏉惧紑鍚庯紝闇�瑕佸悜涓婃垨鑰呬埂涓嬬矘鍒扮壒瀹氱殑浣嶇疆
			animTopOrBottom(releasedChild, yvel);
		}

		@Override
		public int clampViewPositionVertical(View child, int top, int dy) {
			int finalTop = top;
			if (child == frameView1) {
				// 鎷栧姩鐨勬椂绗竴涓獀iew
				if (top > 0) {
					// 涓嶈绗竴涓獀iew寰�涓嬫嫋锛屽洜涓洪《閮ㄤ細鐧芥澘
					finalTop = 0;
				}
			} else if (child == frameView2) {
				// 鎷栧姩鐨勬椂绗簩涓獀iew
				if (top < 0) {
					// 涓嶈绗簩涓獀iew缃戜笂鎷栵紝鍥犱负搴曢儴浼氱櫧鏉�
					finalTop = 0;
				}
			}

			// finalTop浠ｈ〃鐨勬槸鐞嗚涓婂簲璇ユ嫋鍔ㄥ埌鐨勪綅缃�傛澶勮绠楁嫋鍔ㄧ殑璺濈闄や互涓�涓弬鏁�(3)锛屾槸璁╂粦鍔ㄧ殑閫熷害鍙樻參銆傛暟鍊艰秺澶э紝婊戝姩鐨勮秺鎱�
			return child.getTop() + (finalTop - child.getTop()) / 3;
		}
	}

	/**
	 * 婊戝姩鏃秜iew浣嶇疆鏀瑰彉鍗忚皟澶勭悊
	 * 
	 * @param viewIndex
	 *            婊戝姩view鐨刬ndex(1鎴�2)
	 * @param posTop
	 *            婊戝姩View鐨則op浣嶇疆
	 */
	private void onViewPosChanged(int viewIndex, int posTop) {
		if (viewIndex == 1) {
			int offsetTopBottom = viewHeight + frameView1.getTop()
					- frameView2.getTop();
			frameView2.offsetTopAndBottom(offsetTopBottom);
		} else if (viewIndex == 2) {
			int offsetTopBottom = frameView2.getTop() - viewHeight
					- frameView1.getTop();
			frameView1.offsetTopAndBottom(offsetTopBottom);
		}

		// 鏈夌殑鏃跺�欎細榛樿鐧芥澘锛岃繖涓緢鎭跺績銆傚悗闈㈡湁鏃堕棿鍐嶄紭鍖�
		invalidate();
	}

	private void animTopOrBottom(View releasedChild, float yvel) {
		int finalTop = 0; // 榛樿鏄矘鍒版渶椤剁
		if (releasedChild == frameView1) {
			// 鎷栧姩绗竴涓獀iew鏉炬墜
			if (yvel < -VEL_THRESHOLD
					|| (downTop1 == 0 && frameView1.getTop() < -DISTANCE_THRESHOLD)) {
				// 鍚戜笂鐨勯�熷害瓒冲澶э紝灏辨粦鍔ㄥ埌椤剁
				// 鍚戜笂婊戝姩鐨勮窛绂昏秴杩囨煇涓槇鍊硷紝灏辨粦鍔ㄥ埌椤剁
				finalTop = -viewHeight;

				// 涓嬩竴椤靛彲浠ュ垵濮嬪寲浜�
				if (null != nextPageListener) {
					nextPageListener.onDragNext();
				}
			}
		} else {
			// 鎷栧姩绗簩涓獀iew鏉炬墜
			if (yvel > VEL_THRESHOLD
					|| (downTop1 == -viewHeight && releasedChild.getTop() > DISTANCE_THRESHOLD)) {
				// 淇濇寔鍘熷湴涓嶅姩
				finalTop = viewHeight;
			}
		}

		if (mDragHelper.smoothSlideViewTo(releasedChild, 0, finalTop)) {
			ViewCompat.postInvalidateOnAnimation(this);
		}
	}

	/* touch浜嬩欢鐨勬嫤鎴笌澶勭悊閮戒氦缁檓Draghelper鏉ュ鐞� */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {

		if (frameView1.getBottom() > 0 && frameView1.getTop() < 0) {
			// view绮樺埌椤堕儴鎴栧簳閮紝姝ｅ湪鍔ㄧ敾涓殑鏃跺�欙紝涓嶅鐞唗ouch浜嬩欢
			return false;
		}

		boolean yScroll = gestureDetector.onTouchEvent(ev);
		boolean shouldIntercept = mDragHelper.shouldInterceptTouchEvent(ev);
		int action = ev.getActionMasked();

		if (action == MotionEvent.ACTION_DOWN) {
			// action_down鏃跺氨璁﹎DragHelper寮�濮嬪伐浣滐紝鍚﹀垯鏈夋椂鍊欏鑷村紓甯� 浠栧ぇ鐖风殑
			mDragHelper.processTouchEvent(ev);
			downTop1 = frameView1.getTop();
		}

		return shouldIntercept && yScroll;
	}

	@Override
	public boolean onTouchEvent(MotionEvent e) {
		// 缁熶竴浜ょ粰mDragHelper澶勭悊锛岀敱DragHelperCallback瀹炵幇鎷栧姩鏁堟灉
		mDragHelper.processTouchEvent(e); // 璇ヨ浠ｇ爜鍙兘浼氭姏寮傚父锛屾寮忓彂甯冩椂璇峰皢杩欒浠ｇ爜鍔犱笂try catch
		return true;
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// 鍙湪鍒濆鍖栫殑鏃跺�欒皟鐢�
		// 涓�浜涘弬鏁颁綔涓哄叏灞�鍙橀噺淇濆瓨璧锋潵
		
		if (frameView1.getTop() == 0) {
			// 鍙湪鍒濆鍖栫殑鏃跺�欒皟鐢�
			// 涓�浜涘弬鏁颁綔涓哄叏灞�鍙橀噺淇濆瓨璧锋潵
			frameView1.layout(l, 0, r, b - t);
			frameView2.layout(l, 0, r, b - t);

			viewHeight = frameView1.getMeasuredHeight();
			frameView2.offsetTopAndBottom(viewHeight);
		} else {
			// 濡傛灉宸茶鍒濆鍖栵紝杩欐onLayout鍙渶瑕佸皢涔嬪墠鐨勭姸鎬佸瓨鍏ュ嵆鍙�
			frameView1.layout(l, frameView1.getTop(), r, frameView1.getBottom());
			frameView2.layout(l, frameView2.getTop(), r, frameView2.getBottom());
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		measureChildren(widthMeasureSpec, heightMeasureSpec);

		int maxWidth = MeasureSpec.getSize(widthMeasureSpec);
		int maxHeight = MeasureSpec.getSize(heightMeasureSpec);
		setMeasuredDimension(
				resolveSizeAndState(maxWidth, widthMeasureSpec, 0),
				resolveSizeAndState(maxHeight, heightMeasureSpec, 0));
	}

	/**
	 * 杩欐槸View鐨勬柟娉曪紝璇ユ柟娉曚笉鏀寔android浣庣増鏈紙2.2銆�2.3锛夌殑鎿嶄綔绯荤粺锛屾墍浠ユ墜鍔ㄥ鍒惰繃鏉ヤ互鍏嶅己鍒堕��鍑�
	 */
	public static int resolveSizeAndState(int size, int measureSpec,
			int childMeasuredState) {
		int result = size;
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);
		switch (specMode) {
		case MeasureSpec.UNSPECIFIED:
			result = size;
			break;
		case MeasureSpec.AT_MOST:
			if (specSize < size) {
				result = specSize | MEASURED_STATE_TOO_SMALL;
			} else {
				result = size;
			}
			break;
		case MeasureSpec.EXACTLY:
			result = specSize;
			break;
		}
		return result | (childMeasuredState & MEASURED_STATE_MASK);
	}

	public void setNextPageListener(ShowNextPageNotifier nextPageListener) {
		this.nextPageListener = nextPageListener;
	}

	public interface ShowNextPageNotifier {
		public void onDragNext();
	}
}
