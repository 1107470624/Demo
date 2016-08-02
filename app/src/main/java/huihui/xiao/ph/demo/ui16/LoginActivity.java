package huihui.xiao.ph.demo.ui16;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import huihui.xiao.ph.demo.R;
import huihui.xiao.ph.demo.ui16.custom.CustomEdittext;
import huihui.xiao.ph.demo.ui16.custom.CustomTextView;
import huihui.xiao.ph.demo.ui16.custom.RippleView;
import huihui.xiao.ph.demo.ui16.custom.StereoView;
import huihui.xiao.ph.demo.ui16.utils.LogUtil;
import huihui.xiao.ph.demo.ui16.utils.ToastUtil;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private CustomEdittext etUsername;
    private CustomEdittext etEmail;
    private CustomEdittext etPassword;
    private RippleView rvUsername;
    private RippleView rvEmail;
    private RippleView rvPassword;
    private StereoView stereoView;
    private LinearLayout lyWelcome;
    private CustomTextView tvWelcome;
    private int translateY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
//      设置显示第几页
        stereoView.setStartScreen(2);
//        设置页数
        stereoView.post(new Runnable() {
            @Override
            public void run() {
                int[] location = new int[2];
                stereoView.getLocationOnScreen(location);
                translateY = location[1];
            }
        });
//       监听滑动
        stereoView.setiStereoListener(new StereoView.IStereoListener() {
//            滑动到前一页
            @Override
            public void toPre(int curScreen) {
                LogUtil.m("跳转到前一页 "+curScreen);
            }
//            滑动到下一页
            @Override
            public void toNext(int curScreen) {
                LogUtil.m("跳转到下一页 "+curScreen);
            }
        });
    }

    private void initView() {
        stereoView = (StereoView) findViewById(R.id.stereoView);
        etUsername = (CustomEdittext) findViewById(R.id.et_username);
        etEmail = (CustomEdittext) findViewById(R.id.et_email);
        etPassword = (CustomEdittext) findViewById(R.id.et_password);
        rvUsername = (RippleView) findViewById(R.id.rv_username);
        rvEmail = (RippleView) findViewById(R.id.rv_email);
        rvPassword = (RippleView) findViewById(R.id.rv_password);
        lyWelcome = (LinearLayout) findViewById(R.id.ly_welcome);
        tvWelcome = (CustomTextView) findViewById(R.id.tv_welcome);
        rvUsername.setOnClickListener(this);
        rvEmail.setOnClickListener(this);
        rvPassword.setOnClickListener(this);
        tvWelcome.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rv_username:
                rvUsername.setiRippleAnimListener(new RippleView.IRippleAnimListener() {
                    @Override
                    public void onComplete(View view) {
                        stereoView.toPre();
                    }
                });
                break;
            case R.id.rv_email:
                rvEmail.setiRippleAnimListener(new RippleView.IRippleAnimListener() {
                    @Override
                    public void onComplete(View view) {
                        stereoView.toPre();
                    }
                });
                break;
            case R.id.rv_password:
                rvPassword.setiRippleAnimListener(new RippleView.IRippleAnimListener() {
                    @Override
                    public void onComplete(View view) {
//                        下一个
                        stereoView.toPre();
                    }
                });
                break;
            case R.id.tv_welcome:
                if (TextUtils.isEmpty(etUsername.getText())) {
                    ToastUtil.showInfo(LoginActivity.this,"请输入用户名!");
//                    显示第几个
                    stereoView.setItem(2);
                    return;
                }
                if (TextUtils.isEmpty(etEmail.getText())) {
                    ToastUtil.showInfo(LoginActivity.this,"请输入邮箱!");
                    stereoView.setItem(1);
                    return;
                }
                if (TextUtils.isEmpty(etPassword.getText())) {
                    ToastUtil.showInfo(LoginActivity.this,"请输入密码!");
                    stereoView.setItem(0);
                    return;
                }
                startExitAnim();
                break;
        }
    }
    private void startExitAnim() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(stereoView, "translationY", 0, 100, -translateY);
        animator.setDuration(500).start();
        ToastUtil.showInfo(LoginActivity.this,"登录成功 =.=");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LoginActivity.this.finish();
    }
}
