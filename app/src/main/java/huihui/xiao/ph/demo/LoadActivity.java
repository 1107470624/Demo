package huihui.xiao.ph.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import huihui.xiao.ph.demo.ui1.MainActivity;
import huihui.xiao.ph.demo.ui10.KuXuanActivity;
import huihui.xiao.ph.demo.ui11.TuPianActivity;
import huihui.xiao.ph.demo.ui12.WeiXinActivity;
import huihui.xiao.ph.demo.ui13.CardActivity;
import huihui.xiao.ph.demo.ui14.XiaHuaActivity;
import huihui.xiao.ph.demo.ui15.JianBianAcitivity;
import huihui.xiao.ph.demo.ui16.StereoviewActivity;
import huihui.xiao.ph.demo.ui17.menus.SuppressLintActivity;
import huihui.xiao.ph.demo.ui18.ELeMaActivvity;
import huihui.xiao.ph.demo.ui19.FloatingActivity;
import huihui.xiao.ph.demo.ui2.timer;
import huihui.xiao.ph.demo.ui2.timer2;
import huihui.xiao.ph.demo.ui20.CircularActivity;
import huihui.xiao.ph.demo.ui3.stackView;
import huihui.xiao.ph.demo.ui4.ExpandableList;
import huihui.xiao.ph.demo.ui5.GalleryActivity;
import huihui.xiao.ph.demo.ui6.TwoBtnActivity;
import huihui.xiao.ph.demo.ui7.EditViewActivity;
import huihui.xiao.ph.demo.ui8.DrawCanvasActivity;
import huihui.xiao.ph.demo.ui9.SlantedActivity;

public class LoadActivity extends Activity implements View.OnClickListener{

    private Button delete,clockview1,clockview2,stackview,expandable,gallery1,back_two;
    private Button cexiao,liushuideng,biaoqian,datu,tupian,keduxian,kapian,xiahua,jianbian;
    private Button xuanzhuan,biaoqin,eMa,qiuqiu,shuibo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
       initView();
    }

    private void initView() {
        delete = (Button) findViewById(R.id.delete);
        clockview1 = (Button) findViewById(R.id.clockview1);
        clockview2 = (Button) findViewById(R.id.clockview2);
        stackview = (Button) findViewById(R.id.stackview);
        expandable = (Button) findViewById(R.id.expandable);
        gallery1 = (Button) findViewById(R.id.gallery1);
        back_two = (Button) findViewById(R.id.back_two);
        cexiao = (Button) findViewById(R.id.cexiao);
        liushuideng = (Button) findViewById(R.id.liushuideng);
        biaoqian= (Button) findViewById(R.id.biaoqian);
        datu = (Button) findViewById(R.id.datu);
        tupian = (Button) findViewById(R.id.tupian);
        keduxian = (Button) findViewById(R.id.keduxian);
        kapian = (Button) findViewById(R.id.kapian);
        xiahua = (Button) findViewById(R.id.xiahua);
        jianbian = (Button) findViewById(R.id.jianbian);
        xuanzhuan = (Button) findViewById(R.id.xuanzhuan);
        biaoqin = (Button) findViewById(R.id.biaoqin);
        eMa = (Button) findViewById(R.id.eMa);
        qiuqiu  = (Button) findViewById(R.id.qiuqiu);
        shuibo = (Button) findViewById(R.id.shuibo);

        delete.setOnClickListener(this);
        clockview1.setOnClickListener(this);
        clockview2.setOnClickListener(this);
        stackview.setOnClickListener(this);
        expandable.setOnClickListener(this);
        gallery1.setOnClickListener(this);
        back_two.setOnClickListener(this);
        cexiao.setOnClickListener(this);
        liushuideng.setOnClickListener(this);
        biaoqian.setOnClickListener(this);
        datu.setOnClickListener(this);
        tupian.setOnClickListener(this);
        keduxian.setOnClickListener(this);
        kapian.setOnClickListener(this);
        xiahua.setOnClickListener(this);
        jianbian.setOnClickListener(this);
        xuanzhuan.setOnClickListener(this);
        biaoqin.setOnClickListener(this);
        eMa.setOnClickListener(this);
        qiuqiu.setOnClickListener(this);
        shuibo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.delete:
               startActivity(new Intent(LoadActivity.this, MainActivity.class));
                break;

            case R.id.clockview1:
                startActivity(new Intent(LoadActivity.this, timer.class));
                break;
            case R.id.clockview2:
                startActivity(new Intent(LoadActivity.this, timer2.class));
                break;
            case R.id.stackview:
                startActivity(new Intent(LoadActivity.this, stackView.class));
                break;
            case R.id.expandable:
                startActivity(new Intent(LoadActivity.this, ExpandableList.class));
                break;
            case R.id.gallery1:
                startActivity(new Intent(LoadActivity.this, GalleryActivity.class));
                break;
            case R.id.back_two:
                startActivity(new Intent(LoadActivity.this, TwoBtnActivity.class));
                break;
            case R.id.cexiao:
                startActivity(new Intent(LoadActivity.this, EditViewActivity.class));
                break;
            case R.id.liushuideng:
                startActivity(new Intent(LoadActivity.this, DrawCanvasActivity.class));
                break;
            case R.id.biaoqian:
                startActivity(new Intent(LoadActivity.this, SlantedActivity.class));
                break;
            case R.id.datu:
                startActivity(new Intent(LoadActivity.this, KuXuanActivity.class));
                break;
            case R.id.tupian:
                startActivity(new Intent(LoadActivity.this, TuPianActivity.class));
                break;
            case R.id.keduxian:
                startActivity(new Intent(LoadActivity.this, WeiXinActivity.class));
                break;
            case R.id.kapian:
                startActivity(new Intent(LoadActivity.this, CardActivity.class));
                break;
            case R.id.xiahua:
                startActivity(new Intent(LoadActivity.this, XiaHuaActivity.class));
                break;
            case R.id.jianbian:
                startActivity(new Intent(LoadActivity.this, JianBianAcitivity.class));
                break;
            case R.id.xuanzhuan:
                startActivity(new Intent(LoadActivity.this, StereoviewActivity.class));
                break;
            case R.id.biaoqin:
                startActivity(new Intent(LoadActivity.this, SuppressLintActivity.class));
                break;
            case R.id.eMa:
                startActivity(new Intent(LoadActivity.this, ELeMaActivvity.class));
                break;
            case R.id.qiuqiu:
                startActivity(new Intent(LoadActivity.this, FloatingActivity.class));
                break;
            case R.id.shuibo:
                startActivity(new Intent(LoadActivity.this, CircularActivity.class));
                break;
        }
    }
}
