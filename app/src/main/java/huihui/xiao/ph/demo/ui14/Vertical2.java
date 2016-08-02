package huihui.xiao.ph.demo.ui14;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import huihui.xiao.ph.demo.R;

@SuppressLint("NewApi")
public class Vertical2 extends Fragment {

    private View progressBar;
    private CustWebView webview;
    private boolean hasInited = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.verticalfra, null);
        webview = (CustWebView) view.findViewById(R.id.fragment3_webview);
        progressBar = view.findViewById(R.id.progressbar);
        webview.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress==100){
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
        return view;
    }

    public void initView() {
        if (webview != null && !hasInited) {
            hasInited = true;
            webview.loadUrl("http://www.qq.com/");
        }
    }


}
