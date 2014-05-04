package jp.co.marugen.tightropewalking;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

public class Game extends Activity implements SensorEventListener {

    private MyRenderer mRenderer;
    private GLSurfaceView glSurfaceView;
    // ハンドラを生成
    private Handler handler = new Handler();
    public final static int TIMER_PERIOD = 20;

    // public static float deviceX;
    public static float deviceY, deviceX;
    public static boolean isGameOver;
    public static boolean isClear;
    public static boolean canMovePage;

    // /////////////////// センサー /////////////////////
    public static final String TAG = "ORIENTATION_APP";

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Sensor magneticField;

    private float[] accelerometerValues;
    private float[] geomagneticMatrix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // フルスクリーン、タイトルバーの非表示
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        this.mRenderer = new MyRenderer(this);
        this.glSurfaceView = new GLSurfaceView(this);

        glSurfaceView.setRenderer(mRenderer);
        glSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

        setContentView(glSurfaceView);

        // タイマーを生成
        final Timer timer = new Timer(false);
        // スケジュールを設定
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (!Game.canMovePage) {
                            // 描画処理を指示
                            glSurfaceView.requestRender();
                        } else {
                            timer.cancel();
                            MoveToRecord();
                        }
                    }
                });
            }
        }, 1000, TIMER_PERIOD); // 初回起動の遅延(5sec)と周期(1sec)指定

        /* センサ・マネージャを取得する */
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() > 0
                && sensorManager.getSensorList(Sensor.TYPE_MAGNETIC_FIELD)
                        .size() > 0) {
            // センサの取得
            accelerometer = sensorManager
                    .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            // センサの取得
            magneticField = sensorManager
                    .getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        } else {
            accelerometer = null;
            magneticField = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        glSurfaceView.onResume();
        Game.isGameOver = false;
        Game.isClear = false;
        Game.canMovePage = false;
        // センサーを取得できたら
        if (accelerometer != null && magneticField != null) {
            // センサマネージャへリスナーを登録
            sensorManager.registerListener(this, accelerometer,
                    SensorManager.SENSOR_DELAY_UI);
            // センサマネージャへリスナーを登録
            sensorManager.registerListener(this, magneticField,
                    SensorManager.SENSOR_DELAY_UI);
        }
    }

    public void MoveToRecord() {
        // インテントのインスタンス生成
        Intent intent = new Intent(this, Records.class);
        // 次画面のアクティビティ起動
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        glSurfaceView.onPause();
        // センサーを取得していたら
        if (accelerometer != null && magneticField != null) {
            // センサーマネージャのリスナ登録破棄
            sensorManager.unregisterListener(this, accelerometer);
            // センサーマネージャのリスナ登録破棄
            sensorManager.unregisterListener(this, magneticField);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // TODO Auto-generated method stub
        switch (event.sensor.getType()) {
        case Sensor.TYPE_ACCELEROMETER:
            accelerometerValues = event.values.clone();
            break;
        case Sensor.TYPE_MAGNETIC_FIELD:
            geomagneticMatrix = event.values.clone();
            break;
        }

        if (geomagneticMatrix != null && accelerometerValues != null) {
            float[] inR = new float[16];

            if (SensorManager.getRotationMatrix(inR, null, accelerometerValues,
                    geomagneticMatrix)) {
                float[] outR = new float[16];
                SensorManager.remapCoordinateSystem(inR, SensorManager.AXIS_X,
                        SensorManager.AXIS_Y, outR);
                float[] ori = SensorManager.getOrientation(outR, new float[3]);
                Game.deviceX = ori[1];
                Game.deviceY = ori[2];
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub
    }
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {// キーが押された
            switch (event.getKeyCode()) {
            case KeyEvent.KEYCODE_BACK: // Backボタン
                // Game.this.finish();
                this.moveTaskToBack(true);
                return false;
            default:
            }
        }
        return super.dispatchKeyEvent(event);
    }
}
