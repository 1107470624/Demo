package huihui.xiao.ph.demo.ui14;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import huihui.xiao.ph.demo.R;
@SuppressLint("NewApi")
public class Vertical1 extends Fragment {

	private ImageView imageView;

	private TextView textView;

	private ScrollView scrollView;

	float mPosx,mPosy,mCurPosx,mCurPosy;
	public Vertical1(TextView textView) {
		this.textView = textView;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.vertion, null);
		imageView = (ImageView) view.findViewById(R.id.imageview);
		scrollView = (ScrollView) view.findViewById(R.id.scrollview);

		scrollView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					mPosx  = event.getX();
					mPosy  = event.getY();
					break;

				case MotionEvent.ACTION_MOVE:
					mCurPosx  = event.getX();
					mCurPosy  = event.getY();
					break;
				case MotionEvent.ACTION_UP:
					if(mCurPosy - mPosy >0&&(Math.abs(mCurPosy - mPosy)) > 25 ){
						Log.e("����", "����");
					}
					if(mCurPosy - mPosy <0&&(Math.abs(mCurPosy - mPosy)) > 25 ){
						Log.e("����", "����");
					}
					if(mCurPosx - mPosx >0&&(Math.abs(mCurPosx - mPosx)) > 25 ){
						Log.e("����", "����");
					}
					if(mCurPosx - mPosx >0&&(Math.abs(mCurPosx - mPosx)) > 25 ){
						Log.e("����", "����");
					}
					break;
				}
				return false;
			}
		});
		return view;
	}

}
