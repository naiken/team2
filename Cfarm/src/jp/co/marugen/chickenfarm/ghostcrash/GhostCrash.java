package jp.co.marugen.chickenfarm.ghostcrash;

import java.util.Timer;
import java.util.TimerTask;

import jp.co.marugen.chickenfarm.DataManager;
import jp.co.marugen.chickenfarm.R;
import jp.co.marugen.chickenfarm.TrainingResult;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class GhostCrash extends Activity {

    private MyRenderer mRenderer;
    private long mPauseTime = 0L;
    private MyGLSurfaceView glSurfaceView;
    private boolean gameWasStarted = false;

    public final static int TIMER_PERIOD = 20;
    // ハンドラを生成
    Handler handler = new Handler();

    private AlertDialog alert;

    private MyGgm myBgm; // BGM再生のため

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // フルスクリーン、タイトルバーの非表示
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);// ボリュームボタンによる音量変更可能

        // インスタンスを保持させる
        Global.gameActivity = this;

        this.mRenderer = new MyRenderer(this);
        glSurfaceView = new MyGLSurfaceView(this);// MyGLSurfaceViewの生成
        glSurfaceView.setRenderer(mRenderer);

        setContentView(glSurfaceView);
        
        MyRenderer.mScore = 0;

        this.myBgm = new MyGgm(this);// MyBgmの生成

        // 　保存した状態に戻す
        if (savedInstanceState != null) {
            long startTime = savedInstanceState.getLong("startTime");
            long pauseTime = savedInstanceState.getLong("pauseTime");
            int score = savedInstanceState.getInt("score");
            long pausedTime = pauseTime - startTime;
            mRenderer.subtractPausedTime(-pausedTime);
            mRenderer.setScore(score);
        }

    }

    // リトライボタンを表示する
    public void MoveToRecord() {
        // String view_obtain_cp = String.valueOf(MyRenderer.mScore);
        // Toast.makeText(this,view_obtain_cp,Toast.LENGTH_SHORT).show();
        // int getScore = MyRenderer.mScore;
        // スコアによる獲得ポイントの保存
        if (MyRenderer.mScore == 0) {
            saveGetPoint(1, 1);
        } else if (MyRenderer.mScore <= 10) {
            saveGetPoint(10, 5);
        } else if (MyRenderer.mScore <= 20) {
            saveGetPoint(30, 10);
        } else if (MyRenderer.mScore <= 25) {
            saveGetPoint(60, 20);
        } else if (MyRenderer.mScore <= 30) {
            saveGetPoint(150, 40);
        } else if (MyRenderer.mScore <= 35) {
            saveGetPoint(500, 100);
        } else if (MyRenderer.mScore >= 40) {
            saveGetPoint(1000, 300);
        }

        // インテントのインスタンス生成
        Intent intent = new Intent(this, GResult.class);
        // 次画面のアクティビティ起動
        startActivity(intent);
    }

    
    public void onResume() {
        super.onResume();
        myBgm.start(2);
        glSurfaceView.onResume();
        if (mPauseTime != 0L) {
            // バックグラウンドになっていた時間を計算する
            long pausedTime = System.currentTimeMillis() - mPauseTime;
            mRenderer.subtractPausedTime(pausedTime);
        }

        showTutorial();

        // タイマーを生成
        final Timer timer = new Timer(false);
        // スケジュールを設定
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        // チュートリアルが表示されていなければ
                        if (alert == null || !alert.isShowing()) {
                            
                            if (!gameWasStarted) {
                                
                                mRenderer.setStartTime(System.currentTimeMillis());
                                gameWasStarted = true;
                            }

                            if (!MyRenderer.canMoveRecordSceen) {
                                // 描画処理を指示
                                glSurfaceView.requestRender();
                            } else {
                                MoveToRecord();
                                timer.cancel();
                                // MyRenderer.canMoveRecordSceen = false;
                            }
                        }
                    }
                });
            }
        }, 1000, TIMER_PERIOD); // 初回起動の遅延(5sec)と周期(1sec)指定

    }

    @Override
    public void onPause() {
        super.onPause();
        myBgm.stop(2);
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
                GhostCrash.this.moveTaskToBack(true);
                return false;
            default:
            }
        }
        return super.dispatchKeyEvent(event);
    }

    public void saveGetPoint(int getGuts, int getCp) {
        DataManager.getInstance(this).saveGUTS(getGuts);
        DataManager.getInstance(this).saveGET_CP(getCp);
    }

    // //////////////////////チュートリアルの表示//////////////////////
    protected void showTutorial() {

        SharedPreferences pref = getSharedPreferences("user_data",
                Context.MODE_PRIVATE);
        final Editor editor = pref.edit();

        if (pref.getBoolean("ghostcrash_tutorial", false) == false) {

            // duringTutorial = true;

            // チェック表示用のアラートダイアログ
            final CharSequence[] chkItems = { "毎回表示する" };
            final boolean[] chkSts = { true, false, false };
            AlertDialog.Builder checkDlg = new AlertDialog.Builder(this);
            // 画像を入れるため
            LayoutInflater factory = LayoutInflater.from(this);
            final View view = factory.inflate(R.layout.ghostcrash_tutorial,
                    null);
            checkDlg.setView(view);
            checkDlg.setTitle("遊び方");
            checkDlg.setMultiChoiceItems(chkItems, chkSts,
                    new DialogInterface.OnMultiChoiceClickListener() {
                        public void onClick(DialogInterface dialog, int which,
                                boolean flag) {
                            // 項目選択時の処理
                            // i は、選択されたアイテムのインデックス
                            // flag は、チェック状態
                            // プリファレンスにtrueを入れる
                            if (flag == false)
                                // "user_name" というキーで名前を登録
                                editor.putBoolean("ghostcrash_tutorial", true);
                            // 書き込みの確定（実際にファイルに書き込む）
                            editor.commit();
                        }
                    });

            // 表示
            alert = checkDlg.create();
            alert.show();
        } else {
            // 何もしない
        }
    }
}
