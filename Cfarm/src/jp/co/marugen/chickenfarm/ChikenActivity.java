package jp.co.marugen.chickenfarm;

import static android.provider.BaseColumns._ID;
import static jp.co.marugen.chickenfarm.Constants.CHICKEN_NAME;
import static jp.co.marugen.chickenfarm.Constants.CHICKEN_TYPE;
import static jp.co.marugen.chickenfarm.Constants.TABLE_NAME;
import jp.co.marugen.chickenfarm.ghostcrash.MyGgm;
import net.app_c.cloud.sdk.AppCCloud;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ChikenActivity extends Activity implements OnClickListener,
        AnimationListener {

    private ImageView titleImg;
    private ChikenDBHelper helper;
    // Appc広告変数
    private AppCCloud appCCloud;

    private MyGgm myBgm;

    private Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // フルスクリーン、タイトルバーの非表示
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        setContentView(R.layout.chiken_activity);

        ImageButton btn = (ImageButton) findViewById(R.id.start_button);
        btn.setOnClickListener(this);
        ImageButton btn2 = (ImageButton) findViewById(R.id.tutorial_button);
        btn2.setOnClickListener(this);

        anim = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.custom);
        anim.setAnimationListener(this);

        titleImg = (ImageView) findViewById(R.id.title_img);
        titleImg.startAnimation(anim);

        myBgm = new MyGgm(this);

        // ///////// SQLiteに初期値を入力する/////////////////
        SharedPreferences preference = getSharedPreferences("Preference Name",
                MODE_PRIVATE);
        Editor editor = preference.edit();
        String[] chickenNames = { "にわとり", "ハト", "スズメ", "インコ", "カモメ", "メジロ",
                "タカ", "キュウカンチョウ", "ウコッケイ", "オウム", "ツル", "フクロウ", "ペリカン", "キジ",
                "オニオオワシ", "ハゲワシ", "フラミンゴ", "ペンギン", "ダチョウ", "イワトビペンギン", "ヒクイドリ",
                "トキ", "からあげクン", "ツイッター" };

        helper = new ChikenDBHelper(this); // ヘルパーの作成

        if (preference.getBoolean("preIni", false) == false) {
            // 初回起動時の処理
            SQLiteDatabase db = helper.getWritableDatabase();
            ContentValues values = new ContentValues();
            for (int i = 0; i < 24; i++) {
                values.put(_ID, i);
                values.put(CHICKEN_NAME, chickenNames[i]);
                values.put(CHICKEN_TYPE, 0);

                db.insertOrThrow(TABLE_NAME, null, values);
            }
            // プリファレンスの書き変え
            editor.putBoolean("preIni", true);
            editor.commit();

        } else {
            // 2回目以降の処理
        }

    }

    protected void onResume() {
        super.onResume();
        myBgm.start(0);
    }
    
    protected void onPause() {
        super.onPause();
        myBgm.stop(0);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

        case R.id.tutorial_button:
            Intent intent = new Intent(ChikenActivity.this, Tutorial.class);
            startActivity(intent);
            break;

        case R.id.start_button:
            Intent intent2 = new Intent(ChikenActivity.this, Bleeding.class);
            startActivity(intent2);
            break;

        }
    }

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

    @Override
    public void onAnimationEnd(Animation animation) {
        titleImg.startAnimation(animation);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
    }

    @Override
    public void onAnimationStart(Animation animation) {
    }
}
