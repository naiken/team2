package jp.co.marugen.chickenfarm;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.KeyEvent;
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
    }

    public void onResume() {
        super.onResume();

        successCount = 0;
        Random rnd = new Random();
        failure_num = rnd.nextInt(6) + 1;
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
            dad.setImageResource(R.drawable.sucsess_img);
            successCount++;

            // 全正解なら
            if (successCount == 5) {
                saveScore();
                Intent intent = new Intent(this, TrainingResult.class);
                startActivity(intent); // 結果画面へ遷移
            }

            // 外れた
        } else {
            dad.setImageResource(R.drawable.failure_img);
            successCount = 0;
            saveScore();
            doClickablefalse();
            new Handler().postDelayed(sleep, 1000); // 1秒スリープ
        }
    }

    public void saveScore() {

        if (successCount == 1) {
            
            //////////////////////図鑑コンプ作業用////////////////
            guts_point = 1800;
//            guts_point = 10; 
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
}
