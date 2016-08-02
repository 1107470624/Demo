package huihui.xiao.ph.demo.ui7;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.EditText;

import huihui.xiao.ph.demo.R;
import huihui.xiao.ph.demo.ui3.stackView;

public class EditViewActivity extends AppCompatActivity {

    private EditText mEditText;
    private PerformEdit mPerformEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_edit_view);

        mEditText = (EditText) findViewById(R.id.editText);
        mPerformEdit = new PerformEdit(mEditText){
            @Override
            protected void onTextChanged(Editable s) {
                super.onTextChanged(s);
            }
        };
        mPerformEdit.setDefaultText("这是初始值，不能撤销的值");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if(itemId ==R.id.action_undo){
            mPerformEdit.undo();
            return true;
        }else if(itemId == R.id.action_redo){
            mPerformEdit.redo();
            return true;
        }else if(itemId == R.id.action_clear){
            mPerformEdit.clearHistory();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EditViewActivity.this.finish();
    }
}
