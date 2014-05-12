package jp.co.marugen.monochrocamera;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity {

    private ImageView sprite;
    private SurfaceView cameraSurV;
    private SurfaceView monochroSurV;
    private SurfaceHolder cameraHolder;
    private SurfaceHolder monochroHolder;
    private ImageButton shutterBtn;
    private MyCameraSurfaceView myCameraSurV;
    private MonochroSurfaceView monochroPreview;
    private VerticalSeekBar roughnessBar;
    private VerticalSeekBar brightnessBar;
    public static int roughnessProgressMax;
    public static int brightnessProgressMax;
    private MonochroThread monochroThread;

    public final static int TIMER_PERIOD = 20;
    // ハンドラを生成
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // フルスクリーン、タイトルバーの非表示
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        setContentView(R.layout.activity_main);

        // //////////////レイアウトファイルとのひも付け///////////////////////
        sprite = (ImageView) findViewById(R.id.sprite);
        cameraSurV = (SurfaceView) findViewById(R.id.camera_surfaceView); // カメラ用のプレビュー
        monochroSurV = (SurfaceView) findViewById(R.id.monochro_surfaceView); // モノクロ加工用のプレビュー
        shutterBtn = (ImageButton) findViewById(R.id.shutter_btn);
        roughnessBar = (VerticalSeekBar) findViewById(R.id.roughnessSeekBar);
        brightnessBar = (VerticalSeekBar) findViewById(R.id.brightnessSeekBar);

        // モノクロ用SurfaceViewをカメラ用SurfaceViewの手前に表示
        // xmlのFrameLayoutは無視される
        monochroSurV.setZOrderMediaOverlay(true);

        cameraHolder = cameraSurV.getHolder();
        monochroHolder = monochroSurV.getHolder();

        monochroThread = new MonochroThread();// モノクロ処理をするスレッド
        monochroPreview = new MonochroSurfaceView(this, monochroHolder);// モノクロ画像のView
        myCameraSurV = new MyCameraSurfaceView(this, monochroPreview,
                monochroThread);// カメラのView

        monochroThread.start();// モノクロ描画するスレッドの開始

        // // /////////////////各種コールバック///////////////////////
        // cameraHolder.addCallback(myCameraSurV);// カメラ用SurfaceViewのコールバック
        // monochroHolder.addCallback(monochroPreview);//
        // // モノクロ用SurfaceViewのコールバック
        // shutterBtn.setOnClickListener(myCameraSurV);// シャッター用ボタンのコールバック

        // ////////////////カスタムシークバー/////////////////////
        MainActivity.roughnessProgressMax = 7;
        MainActivity.brightnessProgressMax = 10;
        roughnessBar.setMax(MainActivity.roughnessProgressMax); // シークバーの最大値
        brightnessBar.setMax(MainActivity.brightnessProgressMax);// シークバーの最大値
        brightnessBar.setProgress(MainActivity.brightnessProgressMax / 2);// シークバーの初期値

        // シークバークリックリスナー
        roughnessBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                    boolean fromUser) {

                // seekBar.getProgress() + 1 = 1以上
                MonochroThread.roughnessLevel = seekBar.getProgress() + 1;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

        });

        brightnessBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                    boolean fromUser) {

                // seekBar.getProgress()の初期値 - 5 = 0
                MonochroThread.brightness = (seekBar.getProgress() - 5) * 10;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

        });

        // Sprite画像を表示するため
        // タイマーを生成
        final Timer timer = new Timer(false);
        // スケジュールを設定
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        sprite.setVisibility(View.GONE);
                        timer.cancel();
                    }
                });
            }
        }, 800, TIMER_PERIOD); // 初回起動の遅延(5sec)と周期(1sec)指定      
    }

    @Override
    protected void onResume() {
        super.onResume();

        // /////////////////各種コールバック///////////////////////
        cameraHolder.addCallback(myCameraSurV);// カメラ用SurfaceViewのコールバック
        monochroHolder.addCallback(monochroPreview);// モノクロ用SurfaceViewのコールバック
        shutterBtn.setOnClickListener(myCameraSurV);// シャッター用ボタンのコールバック

    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {// キーが押された
            switch (event.getKeyCode()) {
            case KeyEvent.KEYCODE_BACK: // Backボタン
                // Game.this.finish();
                MainActivity.this.moveTaskToBack(true);
                return false;
            default:
            }
        }
        return super.dispatchKeyEvent(event);
    }

}
