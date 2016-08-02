package huihui.xiao.ph.demo.ui12;

import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import huihui.xiao.ph.demo.R;
import huihui.xiao.ph.demo.ui1.MainActivity;

public class CanmerActivity extends AppCompatActivity implements View.OnClickListener, SurfaceHolder.Callback {
    // 退出键.取消
    private ImageView back, chacha;
    // 返回和切换前后置摄像头.添加键
    private ImageView position, add;
    // 识图
    private SurfaceView surfaceView;
    private ImageButton shutter;// 快门
    private SurfaceHolder holder;
    private Camera camera;// 声明相机
    // 照片保存路径
    private String filepath = "";
    // 0代表前置摄像头，1代表后置摄像头
    private int cameraPosition = 1;
    // 图片名称，已时间为准
    String fileName;
    // 播放声音的对象
    private SoundPool player;
    //图片的对象
    Bitmap bitmap;
    //	图片保存的全部路径
    File MyFile;
    // 自定义文件保存路径 以拍摄时间区分命名
    String path = Environment.getExternalStorageDirectory() + File.separator + "Messages" + File.separator;
    MainActivity main = new MainActivity();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 没有标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置全屏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 拍照过程屏幕一直处于高亮
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        // 设置手机屏幕朝向，一共有7种
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // SCREEN_ORIENTATION_BEHIND： 继承Activity堆栈中当前Activity下面的那个Activity的方向
        // SCREEN_ORIENTATION_LANDSCAPE： 横屏(风景照) ，显示时宽度大于高度
        // SCREEN_ORIENTATION_PORTRAIT： 竖屏 (肖像照) ， 显示时高度大于宽度
        // SCREEN_ORIENTATION_SENSOR
        // 由重力感应器来决定屏幕的朝向,它取决于用户如何持有设备,当设备被旋转时方向会随之在横屏与竖屏之间变化
        // SCREEN_ORIENTATION_NOSENSOR：
        // 忽略物理感应器——即显示方向与物理感应器无关，不管用户如何旋转设备显示方向都不会随着改变("unspecified"设置除外)
        // SCREEN_ORIENTATION_UNSPECIFIED：
        // 未指定，此为默认值，由Android系统自己选择适当的方向，选择策略视具体设备的配置情况而定，因此不同的设备会有不同的方向选择
        // SCREEN_ORIENTATION_USER： 用户当前的首选方向
        setContentView(R.layout.activity_canmer);
        // 实例化对象
        infi();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void infi() {
        back = (ImageView) findViewById(R.id.camera_back);
        position = (ImageView) findViewById(R.id.camera_position);
        surfaceView = (SurfaceView) findViewById(R.id.surfaceview);
        shutter = (ImageButton) findViewById(R.id.camera_shutter);
        chacha = (ImageView) findViewById(R.id.chacha);
        add = (ImageView) findViewById(R.id.add);

        holder = surfaceView.getHolder();// 获得句柄
        holder.addCallback(this);// 添加回调
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);// surfaceview不维护自己的缓冲区，等待屏幕渲染引擎将内容推送到用户面前

        player = new SoundPool(1, AudioManager.STREAM_MUSIC, 100);
        // 上下文、数据路径、声音品质
        player.load(this, R.raw.beep, 1);
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        // 设置监听
        back.setOnClickListener(this);
        position.setOnClickListener(this);
        shutter.setOnClickListener(this);
        chacha.setOnClickListener(this);
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.camera_back:
                // 返回
                CanmerActivity.this.finish();
                break;

            case R.id.camera_position:
                // 切换前后摄像头
                int cameraCount = 0;
                Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
                // 得到摄像头的个数
                cameraCount = Camera.getNumberOfCameras();
                for (int i = 0; i < cameraCount; i++) {
                    // 现在是后置，变更为前置
                    // 代表摄像头的方位，CAMERA_FACING_FRONT前置 CAMERA_FACING_BACK后置
                    if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                        // 停掉原来摄像头的预览
                        camera.stopPreview();
                        // 释放资源
                        camera.release();
                        // 取消原来摄像头
                        camera = null;
                        // 打开当前选中的摄像头
                        camera = Camera.open(i);
                        // 设置拍照方向
                        camera.setDisplayOrientation(getPreviewDegree());
                        try {
                            // 通过surfaceview显示取景画面
                            camera.setPreviewDisplay(holder);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        // 现在是前置， 变更为后置
                        if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                            // 停掉原来摄像头的预览
                            camera.stopPreview();
                            // 释放资源
                            camera.release();
                            // 取消原来摄像头
                            camera = null;
                            // 打开当前选中的摄像头
                            camera = Camera.open(i);
                            camera.setDisplayOrientation(getPreviewDegree());

                            try {
                                // 通过surfaceview显示取景画面
                                camera.setPreviewDisplay(holder);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            camera.startPreview();
                            cameraPosition = 1;
                            break;
                        }
                    }
                }
                break;
            case R.id.camera_shutter:
                // 开始播放
                player.play(1, 0.5f, 0.5f, 0, 0, 1f);
//			设置返回和前后摄像头的隐藏性，以及保存和取消的现实性
                setHidden(back, position, add, chacha);
                // 快门
                camera.autoFocus(new Camera.AutoFocusCallback() {// 自动对焦

                    @Override
                    public void onAutoFocus(boolean success, Camera camera) {
                        if (success) {
                            // 设置参数，并拍照
                            Camera.Parameters params = camera.getParameters();
                            // 图片格式
                            params.setPictureFormat(PixelFormat.JPEG);
                            // 图片大小
                            params.setPreviewSize(800, 480);
                            // 将参数设置到我的camera
                            camera.setParameters(params);
                            camera.takePicture(null, null, jpeg);
                        }

                    }
                });

                break;
            case R.id.chacha:
//			设置返回和前后摄像头的隐藏性，以及保存和取消的现实性
                setHidden(add, chacha, back, position);
                // 关闭预览 处理数据
                camera.stopPreview();
                // 数据处理完后继续开始预览
                camera.startPreview();
                // 回收bitmap空间
                bitmap.recycle();
                break;
            case R.id.add:
                try {

                    fileName = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date()) + ".jpg";

                    MyFile = new File(path + fileName);

                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(MyFile));
                    Toast.makeText(getApplicationContext(), MyFile + "", Toast.LENGTH_SHORT).show();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                    // 刷新此缓冲区的输出流
                    bos.flush();
                    // 关闭此输出流并释放与此流有关的所有系统资源
                    bos.close();
                    // 关闭预览 处理数据
                    camera.stopPreview();
                    // 数据处理完后继续开始预览
                    camera.startPreview();
                    // 回收bitmap空间
                    bitmap.recycle();
                    setHidden(add, chacha, back, position);
                /*Intent intent = new Intent();
				intent.setClass(getApplicationContext(), MainActivity.class);*/

                    // 返回
                    CanmerActivity.this.finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void setHidden(ImageView ima1, ImageView ima2, ImageView ima3, ImageView ima4) {
//		设置返回和前后摄像头的隐藏性，以及保存和取消的现实性
        ima1.setVisibility(View.GONE);
        ima2.setVisibility(View.GONE);
        ima3.setVisibility(View.VISIBLE);
        ima4.setVisibility(View.VISIBLE);
    }


    Camera.PictureCallback jpeg = new Camera.PictureCallback() {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
//			相机拍摄之后读取到的数据
            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        }
    };

    // SurfaceView的回调函数

	/*
	 * surfaceHolder他是系统提供的一个用来设置surfaceView的一个对象，而它通过surfaceView.getHolder()
	 * 这个方法来获得。 Camera提供一个setPreviewDisplay(SurfaceHolder)的方法来连接
	 */


    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    // 创建相机时自动调用
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // 当surfaceview创建时开启相机
        if (camera == null) {
            camera = Camera.open();
            // 设置相机方向
            camera.setDisplayOrientation(getPreviewDegree());
            // 通过surfaceview显示取景画面
            try {
                camera.setPreviewDisplay(holder);
                // 开始预览
                camera.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    // 相机消亡时调用
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // 当surfaceview关闭时，关闭预览并释放资源
        camera.stopPreview();
        camera.release();
        camera = null;
        holder = null;
        surfaceView = null;
    }

    private int getPreviewDegree() {
        // 获取屏幕方向
        int retation = getWindowManager().getDefaultDisplay().getRotation();
        int degree = 0;
        // 根据手机屏幕的方向，计算照相机的方法
        switch (retation) {
            case Surface.ROTATION_0:
                degree = 90;
                break;
            case Surface.ROTATION_90:
                degree = 0;
                break;
            case Surface.ROTATION_180:
                degree = 270;
                break;
            case Surface.ROTATION_270:
                degree = 180;
                break;
        }

        return degree;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CanmerActivity.this.finish();
    }

}

