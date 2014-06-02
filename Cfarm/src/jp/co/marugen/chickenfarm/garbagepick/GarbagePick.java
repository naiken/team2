package jp.co.marugen.chickenfarm.garbagepick;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import jp.co.marugen.chickenfarm.DataManager;
import jp.co.marugen.chickenfarm.R;
import jp.co.marugen.chickenfarm.ghostcrash.MyGgm;
import android.animation.ObjectAnimator;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

public class GarbagePick extends Activity implements OnClickListener {

    private ImageButton moveChickenBtn;
    private ObjectAnimator objectAnimator;
    private ImageView human, gameover, gameclear;

    private boolean nowMoving = true;
    private long passedTime;
    private long nextDuration = 15000;

    private boolean wasGammefinish = false;
    private boolean chickenWasStarted = false;
    public static boolean wasCleared = false;

    private AlertDialog alert;

    private Timer timer;
    private int TIMER_PERIOD = 10; // タイマーの周期
    private Handler handler = new Handler(); // タイマーのハンドラを生成

    private int humanState;// 0:後ろ, 1:横, 2:前
    private int cycleCounter;
    private int cycle;
    private int duringSide = 70;

    private MyGgm myBgm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // フルスクリーン、タイトルバーの非表示
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        setContentView(R.layout.garbage_pick);

        human = (ImageView) findViewById(R.id.auntie_img);
        gameover = (ImageView) findViewById(R.id.gp_gameover);
        gameclear = (ImageView) findViewById(R.id.gp_gameclear);
        moveChickenBtn = (ImageButton) findViewById(R.id.moving_ahiru);
        moveChickenBtn.setOnClickListener(this);

        myBgm = new MyGgm(this);
        GarbagePick.wasCleared = false;
    }

    @Override
    protected void onResume() {
        super.onResume();

        myBgm.start(1);

        showTutorial();

        // 初期化
        humanState = 0;// 0:後ろ, 1:横, 2:前
        cycleCounter = 0;
        nowMoving = true;

        // 初回時にバックを向いている時間を乱数で生成
        final Random rnd = new Random();
        cycle = rnd.nextInt(300) + 100; // 1-4秒

        timer = new Timer(false);// タイマーを生成

        // スケジュールを設定
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {

                    // ここに定期実行したい処理を書く
                    @Override
                    public void run() {

                        // チュートリアルが表示されていなければ
                        if (alert == null || !alert.isShowing()) {

                            // 初回のみ実行される
                            if (!chickenWasStarted) {
                                animateTranslationX(moveChickenBtn,
                                        nextDuration); // チキンスタート開始
                                chickenWasStarted = true;
                            }

                            if (!wasGammefinish) {

                                // 後ろを向いていたら
                                if (humanState == 0) {

                                    // 1-3秒経過したら
                                    if (cycleCounter > cycle) {
                                        humanState++; // 横向き
                                        cycleCounter = -1;
                                        cycle = duringSide;
                                        human.setImageResource(R.drawable.auntie_side);
                                    }

                                    // 横を向いていたら
                                } else if (humanState == 1) {

                                    // duringSide秒したら
                                    if (cycleCounter > cycle) {
                                        humanState++;// 前向き
                                        cycleCounter = -1;
                                        cycle = 100;
                                        human.setImageResource(R.drawable.auntie_front);
                                    }

                                    // 前を向いていたら
                                } else if (humanState == 2) {
                                    checkMoving();// チキンが動いているかチェック

                                    // 1秒したら
                                    if (cycleCounter > cycle) {
                                        humanState = 0;// 後ろ向き
                                        cycleCounter = -1;
                                        cycle = rnd.nextInt(300) + 100;// 1-4秒
                                        human.setImageResource(R.drawable.auntie_back);
                                    }

                                }

                                // チキンが最後まで到達したら
                                if (!objectAnimator.isRunning() && nowMoving) {
                                    gameclear();
                                }

                            } else {

                                if (cycleCounter == 0) {
                                    objectAnimator.cancel();
                                }

                                // 1秒したら
                                if (cycleCounter > 100) {
                                    wasGammefinish = false;
                                    cycleCounter = -1;
                                    timer.cancel();
                                    moveToResultScreen();// 結果画面へ遷移
                                }
                            }
                            cycleCounter++;

                        }
                    }

                });
            }
        }, 1000, TIMER_PERIOD); // 初回起動の遅延(1sec)と周期(5sec)指定
    }

    protected void onPause() {
        super.onPause();
        myBgm.stop(1);
        timer.cancel();// タイマーを止める
        objectAnimator.cancel();
        chickenWasStarted = false;
    }

    // 　アニメーション
    private void animateTranslationX(ImageButton target, long duration) {
        objectAnimator = ObjectAnimator.ofFloat(target, "translationX", -1300f);
        objectAnimator.setDuration(duration);// ○秒かけて実行させます
        objectAnimator.start();// アニメーションを開始します
    }

    // チキンが動いているかチェック
    private void checkMoving() {

        // Gameover
        if (nowMoving) {
            // objectAnimator.cancel();
            cycleCounter = -1;
            wasGammefinish = true;
            gameover.setVisibility(View.VISIBLE);

            // 獲得CP、ガッツの保存
            DataManager.getInstance(GarbagePick.this).saveGET_CP(0);
            DataManager.getInstance(GarbagePick.this).saveGUTS(0);

        }
    }

    // ゲームクリア
    private void gameclear() {
        cycleCounter = -1;
        wasGammefinish = true;
        GarbagePick.wasCleared = true;
        gameclear.setVisibility(View.VISIBLE);

        // 獲得CP、ガッツの保存
        DataManager.getInstance(GarbagePick.this).saveGET_CP(200);
        DataManager.getInstance(GarbagePick.this).saveGUTS(500);
    }

    @Override
    public void onClick(View v) {

        // 動いていたら
        if (nowMoving) {
            passedTime = objectAnimator.getCurrentPlayTime();
            nextDuration = nextDuration - passedTime;

            // アニメーションキャンセル
            objectAnimator.cancel();
            nowMoving = false;

            // 止まっていたら
        } else {
            animateTranslationX(moveChickenBtn, nextDuration);
            nowMoving = true;
        }
    }

    private void moveToResultScreen() {
        Intent intent = new Intent(this, GarbageResult.class);
        startActivity(intent);
    }

    // //////////////////////チュートリアルの表示//////////////////////
    protected void showTutorial() {

        SharedPreferences pref = getSharedPreferences("user_data",
                Context.MODE_PRIVATE);
        final Editor editor = pref.edit();

        if (pref.getBoolean("garbagepick_tutorial", false) == false) {

            // duringTutorial = true;

            // チェック表示用のアラートダイアログ
            final CharSequence[] chkItems = { "毎回表示する" };
            final boolean[] chkSts = { true, false, false };
            AlertDialog.Builder checkDlg = new AlertDialog.Builder(this);
            // 画像を入れるため
            LayoutInflater factory = LayoutInflater.from(this);
            final View view = factory.inflate(R.layout.garbagepick_tutorial,
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
                                editor.putBoolean("garbagepick_tutorial", true);
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
