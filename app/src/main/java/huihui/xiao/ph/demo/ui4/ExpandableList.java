package huihui.xiao.ph.demo.ui4;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import huihui.xiao.ph.demo.R;
import huihui.xiao.ph.demo.ui3.stackView;

public class ExpandableList extends ExpandableListActivity {

    //创建以及条目容器
    List<Map<String, String>> gruops = new ArrayList<>();
    //存放内容，以便显示在列表中
    List<List<Map<String, String>>> childs = new ArrayList<>();
    SimpleExpandableListAdapter sela;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_expandable_list);
        setListData();
        // 得到屏幕的大小
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //图标设置在右边
        // 设置指示图标的位置
        getExpandableListView().setIndicatorBounds
                (dm.widthPixels-60, dm.widthPixels);
    }

    private void setListData() {
        // 创建二个一级条目标题
        Map<String, String> title1 = new HashMap<>();
        Map<String, String> title2 = new HashMap<>();
        title1.put("group", "条目1");
        title2.put("group", "条目2");
        gruops.add(title1);
        gruops.add(title2);

//        创建二级目录
        Map<String, String> title1_content1 = new HashMap<>();
        Map<String, String> title1_content2 = new HashMap<>();
        Map<String, String> title1_content3 = new HashMap<>();

        title1_content1.put("child", "工人");
        title1_content2.put("child", "学生");
        title1_content3.put("child", "弄明");
        List<Map<String, String>> childs_1 = new ArrayList<Map<String, String>>();
        childs_1.add(title1_content1);
        childs_1.add(title1_content2);
        childs_1.add(title1_content3);

        // 内容二
        Map<String, String> title_2_content_1 = new HashMap<String, String>();
        Map<String, String> title_2_content_2 = new HashMap<String, String>();
        Map<String, String> title_2_content_3 = new HashMap<String, String>();
        title_2_content_1.put("child", "猩猩");
        title_2_content_2.put("child", "老虎");
        title_2_content_3.put("child", "狮子");
        List<Map<String, String>> childs_2 = new ArrayList<Map<String, String>>();
        childs_2.add(title_2_content_1);
        childs_2.add(title_2_content_2);
        childs_2.add(title_2_content_3);

        childs.add(childs_1);
        childs.add(childs_2);

        /**
         * 创建ExpandableList的Adapter容器 参数:
         * 1.上下文 2.一级集合 (数据源)
         * 3.一级样式文件（布局） 4. 一级条目键值（集合里面的KEY）
         * 5.一级显示控件名 6. 二级集合
         * 7. 二级样式 8.二级条目键值 9.二级显示控件名
         *
         */
        sela = new SimpleExpandableListAdapter(
                this, gruops, R.layout.expandablelist1, new String[]{"group"},
                new int[]{R.id.textGroup}, childs,  R.layout.expandablelist2,
                new String[]{"child"}, new int[]{R.id.textChild}
        );
        // 加入列表
        setListAdapter(sela);
    }
    /**
     * 列表内容按下
     */
    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        Toast.makeText(this,
                "您选择了"
                        + gruops.get(groupPosition).toString()
                        + "子编号"
                        + childs.get(groupPosition).get(childPosition)
                        .toString(), Toast.LENGTH_SHORT).show();
        return super.onChildClick(parent, v, groupPosition, childPosition, id);
    }

    /**
     * 二级标题按下
     */
    @Override
    public boolean setSelectedChild(int groupPosition, int childPosition, boolean shouldExpandGroup) {
        return super.setSelectedChild(groupPosition, childPosition, shouldExpandGroup);
    }
    /**
     * 一级标题按下
     */
    @Override
    public void setSelectedGroup(int groupPosition) {
        super.setSelectedGroup(groupPosition);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ExpandableList.this.finish();
    }
}
