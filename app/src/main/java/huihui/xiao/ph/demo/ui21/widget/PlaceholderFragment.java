package huihui.xiao.ph.demo.ui21.widget;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import huihui.xiao.ph.demo.R;

/**
 * Created by Administrator on 2016/8/4.
 */
public class PlaceholderFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.wheel_fragment,container,false);
        return view;
    }
}
