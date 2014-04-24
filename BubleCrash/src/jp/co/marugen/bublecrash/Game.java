package jp.co.marugen.bublecrash;

import java.util.Timer;
import java.util.TimerTask;

import jp.co.marugen.sql.Records;
import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

public class Game extends Activity {

    private MyRenderer mRenderer;
    private long mPauseTime = 0L;
    private MyGLSurfaceView glSurfaceView;

    public final static int TIMER_PERIOD = 20;
    // ハンドラを生成
    Handler handler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // フルスクリーン、タイトルバーの非表示
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        // インスタンスを保持させる
        Global.gameActivity = this;

        this.mRenderer = new MyRenderer(this);
        glSurfaceView = new MyGLSurfaceView(this);// MyGLSurfaceViewの生成
        glSurfaceView.setRenderer(mRenderer);

        setContentView(glSurfaceView);

        // 　保存した状態に戻す
        if (savedInstanceState != null) {
            long startTime = savedInstanceState.getLong("startTime");
            long pauseTime = savedInstanceState.getLong("pauseTime");
            int score = savedInstanceState.getInt("score");
            long pausedTime = pauseTime - startTime;
            mRenderer.subtractPausedTime(-pausedTime);
            mRenderer.setScore(score);
        }
        
        // タイマーを生成
        final Timer timer = new Timer(false);
        // スケジュールを設定
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (!MyRenderer.canMoveRecordSceen) {
                            // 描画処理を指示
                            glSurfaceView.requestRender();
                        } else {
                            MoveToRecord();
                            timer.cancel();
                            // MyRenderer.canMoveRecordSceen = false;
                        }
                    }
                });
            }
        }, 1000, TIMER_PERIOD); // 初回起動の遅延(5sec)と周期(1sec)指定
    }

    // リトライボタンを表示する
    public void MoveToRecord() {
        // インテントのインスタンス生成
        Intent intent = new Intent(this, Records.class);
        // 次画面のアクティビティ起動F
        startActivity(intent);
        // mRetryButton.setVisibility(View.VISIBLE);
    }

    public void onResume() {
        super.onResume();
        glSurfaceView.onResume();
        if (mPauseTime != 0L) {
            // バックグラウンドになっていた時間を計算する
            long pausedTime = System.currentTimeMillis() - mPauseTime;
            mRenderer.subtractPausedTime(pausedTime);
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        glSurfaceView.onPause();
        if (!MyRenderer.canMoveRecordSceen) {
            // テクスチャを削除する
            TextureManager.deleteAll(Global.gl);
        }
        mPauseTime = System.currentTimeMillis();// バックグラウンドになった時刻を覚えておく
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // 状態を保存する
        outState.putLong("startTime", mRenderer.getStartTime());// 開始時間
        outState.putLong("pauseTime", System.currentTimeMillis());// onPauseした時間
        outState.putInt("score", mRenderer.getScore());// スコア
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {// キーが押された
            switch (event.getKeyCode()) {
            case KeyEvent.KEYCODE_BACK: // Backボタン
                // Game.this.finish();
                Game.this.moveTaskToBack(true);
                return false;
            default:
            }
        }
        return super.dispatchKeyEvent(event);
    }
}