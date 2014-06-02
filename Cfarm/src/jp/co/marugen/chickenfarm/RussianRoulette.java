package jp.co.marugen.chickenfarm;

import java.util.Random;

import jp.co.marugen.chickenfarm.ghostcrash.MyGgm;
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
import android.view.View;
import android.view.View.OnClickListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

public class RussianRoulette extends Activity implements OnClickListener {

    private int failure_num;
    private int successCount;
    private int guts_point;
    private int cp_point;
    private ImageView dad;
    private ImageButton ika;
    private ImageButton ebi;
    private ImageButton aji;
    private ImageButton maguro;
    private ImageButton samon;
    private ImageButton ikura;
    private ImageButton ChikenButton;
    
    private AlertDialog alert;
    private MyGgm myBgm;

    private boolean wasHit;// 当たったかどうかの判定

    // レイアウトの取得
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // フルスクリーン、タイトルバーの非表示
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        setContentView(R.layout.russian);

        // ImageViewの生成
        dad = (ImageView) findViewById(R.id.dad);

        // ImageButtonの生成
        ika = (ImageButton) findViewById(R.id.ika);
        ika.setOnClickListener(this);
        ebi = (ImageButton) findViewById(R.id.ebi);
        ebi.setOnClickListener(this);
        aji = (ImageButton) findViewById(R.id.aji);
        aji.setOnClickListener(this);
        maguro = (ImageButton) findViewById(R.id.maguro);
        maguro.setOnClickListener(this);
        samon = (ImageButton) findViewById(R.id.samon);
        samon.setOnClickListener(this);
        ikura = (ImageButton) findViewById(R.id.ikura);
        ikura.setOnClickListener(this);

        // イメージボタン生成
        ChikenButton = (ImageButton) findViewById(R.id.chiken_button);
        ChikenButton.setOnClickListener(this);
        
        myBgm = new MyGgm(this);
    }

    public void onResume() {
        super.onResume();
        myBgm.start(3);
        successCount = 0;
        Random rnd = new Random();
        failure_num = rnd.nextInt(6) + 1;
        
        showTutorial();
    }
    
    public void onPause() {
        super.onPause();
        myBgm.stop(3);
    }

    // OnClickListenerのイベント
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        // イカ
        case R.id.ika:
            if (failure_num == 1) {
                wasHit = false;
                ika.setImageResource(R.drawable.bad);
            } else {
                wasHit = true;
                ika.setImageResource(R.drawable.lucky);
                ika.setClickable(false);
            }
            break;

        // エビ
        case R.id.ebi:
            if (failure_num == 2) {
                wasHit = false;
                ebi.setImageResource(R.drawable.bad);
            } else {
                wasHit = true;
                ebi.setImageResource(R.drawable.lucky);
                ebi.setClickable(false);
            }
            break;

        // アジ
        case R.id.aji:
            if (failure_num == 3) {
                wasHit = false;
                aji.setImageResource(R.drawable.bad);
            } else {
                wasHit = true;
                aji.setImageResource(R.drawable.lucky);
                aji.setClickable(false);
            }
            break;

        // まぐろ
        case R.id.maguro:
            if (failure_num == 4) {
                wasHit = false;
                maguro.setImageResource(R.drawable.bad);
            } else {
                wasHit = true;
                maguro.setImageResource(R.drawable.lucky);
                maguro.setClickable(false);
            }
            break;

        // サーモン
        case R.id.samon:
            if (failure_num == 5) {
                wasHit = false;
                samon.setImageResource(R.drawable.bad);
            } else {
                wasHit = true;
                samon.setImageResource(R.drawable.lucky);
                samon.setClickable(false);
            }
            break;

        // いくら
        case R.id.ikura:
            if (failure_num == 6) {
                wasHit = false;
                ikura.setImageResource(R.drawable.bad);
            } else {
                wasHit = true;
                ikura.setImageResource(R.drawable.lucky);
                ikura.setClickable(false);
            }
            break;

        // チキンボタン　途中でトレーニングをやめられてもポイントを得られる
        case R.id.chiken_button:
            saveScore();
            Intent intent = new Intent(this, TrainingResult.class);
            startActivity(intent); // 結果画面へ遷移
            return;
        }

        // 当たったら
        if (wasHit) {
            dad.setImageResource(R.drawable.success);
            successCount++;

            // 全正解なら
            if (successCount == 5) {
                saveScore();
                Intent intent = new Intent(this, TrainingResult.class);
                startActivity(intent); // 結果画面へ遷移
            }

            // 外れた
        } else {
            dad.setImageResource(R.drawable.failure);
            successCount = 0;
            saveScore();
            doClickablefalse();
            new Handler().postDelayed(sleep, 1000); // 1秒スリープ
        }
    }

    public void saveScore() {

        if (successCount == 1) {          
            guts_point = 10; 
            cp_point = 5;
        } else if (successCount == 2) {
            guts_point = 30;
            cp_point = 10;
        } else if (successCount == 3) {
            guts_point = 60;
            cp_point = 20;
        } else if (successCount == 4) {
            guts_point = 150;
            cp_point = 40;
        } else if (successCount == 5) {
            guts_point = 500;
            cp_point = 100;
        } else if (successCount == 0) {
            guts_point = 0;
            cp_point = 0;
        }

        DataManager.getInstance(this).saveGUTS(guts_point);
        DataManager.getInstance(this).saveGET_CP(cp_point);
    }

    public void doClickablefalse() {
        ika.setClickable(false);
        ebi.setClickable(false);
        aji.setClickable(false);
        maguro.setClickable(false);
        samon.setClickable(false);
        ikura.setClickable(false);
    }

    // スリープ
    private final Runnable sleep = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(RussianRoulette.this,
                    TrainingResult.class);
            startActivity(intent);// 結果画面へ遷移
        }
    };

    // backキーの動作を規定
    // backキーが押されても、Homeキーが押されたときと同じ動作をする
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
    // //////////////////////チュートリアルの表示//////////////////////
    protected void showTutorial() {

        SharedPreferences pref = getSharedPreferences("user_data",
                Context.MODE_PRIVATE);
        final Editor editor = pref.edit();

        if (pref.getBoolean("russian_tutorial", false) == false) {

            // duringTutorial = true;

            // チェック表示用のアラートダイアログ
            final CharSequence[] chkItems = { "毎回表示する" };
            final boolean[] chkSts = { true, false, false };
            AlertDialog.Builder checkDlg = new AlertDialog.Builder(this);
            // 画像を入れるため
            LayoutInflater factory = LayoutInflater.from(this);
            final View view = factory.inflate(R.layout.russian_tutorial,
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
                                editor.putBoolean("russian_tutorial", true);
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

