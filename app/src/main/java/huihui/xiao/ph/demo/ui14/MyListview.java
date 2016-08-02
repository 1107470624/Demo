package huihui.xiao.ph.demo.ui14;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class MyListview extends ListView{

	public MyListview(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public MyListview(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyListview(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	/**
	 * �Զ���gridview�Ĵ�С
	 * @param widthMeasureSpec
	 * @param heightMeasureSpec
     */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//		����һ��MeasureSpec��װ�˸����ִ��ݸ��Ӳ��ֵĲ���Ҫ��
// ÿ��MeasureSpec������һ���Ⱥ͸߶ȵ�Ҫ��һ��MeasureSpec�ɴ�С��ģʽ��ɡ�
// ��������ģʽ��UNSPECIFIED(δָ��),��Ԫ�ز�����Ԫ��ʩ���κ�������
// ��Ԫ�ؿ��Եõ�������Ҫ�Ĵ�С��EXACTLY(��ȫ)����Ԫ�ؾ�����Ԫ�ص�ȷ�д�С��
// ��Ԫ�ؽ����޶��ڸ����ı߽���������������С��AT_MOST(����)����Ԫ������ﵽָ����С��ֵ��
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
