package huihui.xiao.ph.demo.ui12;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;
import java.util.Map;

import huihui.xiao.ph.demo.R;

public class MyGridView extends BaseAdapter {
	ImageView imageview;
	private LayoutInflater mInflater;
	private Context context;
	private List<Map<String, Object>> mData;
	public MyGridView(Context context,
			List<Map<String, Object>> mData){
		this.context = context;
		this.mData = mData;
		mInflater = LayoutInflater.from(context);
	}
	
	
	public void setList(List<Map<String, Object>> mData){
		this.mData = mData;
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
//		���ֶ���ʶͼ���󡣡���
		if(convertView == null){
			convertView = mInflater.inflate(R.layout.item_layout,
					parent , false);
			imageview = (ImageView) convertView.findViewById(R.id.imageview);
			convertView.setTag(imageview);
		}else{
			imageview = (ImageView) convertView.getTag();
		}
		imageview.setImageBitmap( (Bitmap) mData.get(position).get("bitmap"));
		return convertView;
	}

}
