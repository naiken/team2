package jp.co.marugen.chickenfarm;

import static android.provider.BaseColumns._ID;
import static jp.co.marugen.chickenfarm.Constants.CHICKEN_NAME;
import static jp.co.marugen.chickenfarm.Constants.CHICKEN_TYPE;
import static jp.co.marugen.chickenfarm.Constants.TABLE_NAME;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class BirdGuide extends Activity implements View.OnTouchListener,
        OnClickListener {

    // データベース関連
    private static String[] FROM = { _ID, CHICKEN_NAME, CHICKEN_TYPE };
    private ChikenDBHelper helper;

    private ViewFlipper viewFlipper;
    private float firstTouch;
    private boolean isFlip = false;

    // 鳥の画像配列
    private int[] maxBirdResorce = {

        // egg1
        R.drawable.niwatori_king,
        R.drawable.hato_front_king,
        R.drawable.suzume_front_king,
        R.drawable.inko_front_king,
        R.drawable.kamome_front_king,
        R.drawable.mejiro_front_king,

        // egg2
        R.drawable.taka_front_king,
        R.drawable.kyukantyo_front_king,
        R.drawable.ukokkei_front_king,
        R.drawable.oumu_front_king,
        R.drawable.turu_front_king,
        R.drawable.fukurou_front_king,

        // egg3
        R.drawable.perikan_front_king, 
        R.drawable.kiji_front_king,
        R.drawable.onioohashi_front_king, 
        R.drawable.hagewasi_front_king,
        R.drawable.furamingo_front_king,
        R.drawable.pengin_front_king,

        // egg4
        R.drawable.datyou_front_king, 
        R.drawable.iwatobi_pengin_front_king,
        R.drawable.hikuidori_front_king, 
        R.drawable.toki_front_king,
        R.drawable.karaage_kun_king, 
        R.drawable.twitter_front_king
    };
    
    // キャラクター画像
    private ImageView niwatori_img, hato_img, suzume_img, inko_img, kamome_img,
            meziro_img, taka_img, kyukantyo_img, ukokkei_img, omu_img,
            turu_img, hukuro_img, perikan_img, kizi_img, oniohashi_img,
            hagewashi_img, huramingo_img, pengin_img, datyo_img, iwatobi_img,
            hikuidori_img, toki_img, karaage_img, twitter_img;

    // キャラクターテキスト
    private TextView niwatori_text, hato_text, suzume_text, inko_text,
            kamome_text, meziro_text, taka_text, kyukantyo_text, ukokkei_text,
            omu_text, turu_text, hukuro_text, perikan_text, kizi_text,
            oniohashi_text, hagewashi_text, huramingo_text, pengin_text,
            datyo_text, iwatobi_text, hikuidori_text, toki_text, karaage_text,
            twitter_text;

    private ImageView[] imgsArray = new ImageView[24];
    private TextView[] textsArray = new TextView[24];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // フルスクリーン、タイトルバーの非表示
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        setContentView(R.layout.bird_guide);

        helper = new ChikenDBHelper(this); // ヘルパーの作成

        viewFlipper = (ViewFlipper) findViewById(R.id.flipper);
        findViewById(R.id.layout_first).setOnTouchListener(this);
        findViewById(R.id.layout_second).setOnTouchListener(this);
        findViewById(R.id.layout_third).setOnTouchListener(this);
        findViewById(R.id.layout_fourth).setOnTouchListener(this);

        // イメージビューの生成
        niwatori_img = (ImageView) findViewById(R.id.niwatori_img);
        hato_img = (ImageView) findViewById(R.id.hato_img);
        suzume_img = (ImageView) findViewById(R.id.suzume_img);
        inko_img = (ImageView) findViewById(R.id.inko_img);
        kamome_img = (ImageView) findViewById(R.id.kamome_img);
        meziro_img = (ImageView) findViewById(R.id.meziro_img);
        taka_img = (ImageView) findViewById(R.id.taka_img);
        kyukantyo_img = (ImageView) findViewById(R.id.kyukantyo_img);
        ukokkei_img = (ImageView) findViewById(R.id.ukokkei_img);
        omu_img = (ImageView) findViewById(R.id.omu_img);
        turu_img = (ImageView) findViewById(R.id.turu_img);
        hukuro_img = (ImageView) findViewById(R.id.hukuro_img);
        perikan_img = (ImageView) findViewById(R.id.perikan_img);
        kizi_img = (ImageView) findViewById(R.id.kizi_img);
        oniohashi_img = (ImageView) findViewById(R.id.oniohashi_img);
        hagewashi_img = (ImageView) findViewById(R.id.hagewashi_img);
        huramingo_img = (ImageView) findViewById(R.id.huramingo_img);
        pengin_img = (ImageView) findViewById(R.id.pengin_img);
        datyo_img = (ImageView) findViewById(R.id.datyo_img);
        iwatobi_img = (ImageView) findViewById(R.id.iwatobi_img);
        hikuidori_img = (ImageView) findViewById(R.id.hikuidori_img);
        toki_img = (ImageView) findViewById(R.id.toki_img);
        karaage_img = (ImageView) findViewById(R.id.karaage_img);
        twitter_img = (ImageView) findViewById(R.id.twitter_img);

        imgsArray[0] = niwatori_img;
        imgsArray[1] = hato_img;
        imgsArray[2] = suzume_img;
        imgsArray[3] = inko_img;
        imgsArray[4] = kamome_img;
        imgsArray[5] = meziro_img;
        imgsArray[6] = taka_img;
        imgsArray[7] = kyukantyo_img;
        imgsArray[8] = ukokkei_img;
        imgsArray[9] = omu_img;
        imgsArray[10] = turu_img;
        imgsArray[11] = hukuro_img;
        imgsArray[12] = perikan_img;
        imgsArray[13] = kizi_img;
        imgsArray[14] = oniohashi_img;
        imgsArray[15] = hagewashi_img;
        imgsArray[16] = huramingo_img;
        imgsArray[17] = pengin_img;
        imgsArray[18] = datyo_img;
        imgsArray[19] = iwatobi_img;
        imgsArray[20] = hikuidori_img;
        imgsArray[21] = toki_img;
        imgsArray[22] = karaage_img;
        imgsArray[23] = twitter_img;

        // テキストビューの生成
        niwatori_text = (TextView) findViewById(R.id.niwatori_text);
        hato_text = (TextView) findViewById(R.id.hato_text);
        suzume_text = (TextView) findViewById(R.id.suzume_text);
        inko_text = (TextView) findViewById(R.id.inko_text);
        kamome_text = (TextView) findViewById(R.id.kamome_text);
        meziro_text = (TextView) findViewById(R.id.meziro_text);
        taka_text = (TextView) findViewById(R.id.taka_text);
        kyukantyo_text = (TextView) findViewById(R.id.kyukantyo_text);
        ukokkei_text = (TextView) findViewById(R.id.ukokkei_text);
        omu_text = (TextView) findViewById(R.id.omu_text);
        turu_text = (TextView) findViewById(R.id.turu_text);
        hukuro_text = (TextView) findViewById(R.id.hukuro_text);
        perikan_text = (TextView) findViewById(R.id.perikan_text);
        kizi_text = (TextView) findViewById(R.id.kizi_text);
        oniohashi_text = (TextView) findViewById(R.id.oniohashi_text);
        hagewashi_text = (TextView) findViewById(R.id.hagewashi_text);
        huramingo_text = (TextView) findViewById(R.id.huramingo_text);
        pengin_text = (TextView) findViewById(R.id.pengin_text);
        datyo_text = (TextView) findViewById(R.id.datyo_text);
        iwatobi_text = (TextView) findViewById(R.id.iwatobi_text);
        hikuidori_text = (TextView) findViewById(R.id.hikuidori_text);
        toki_text = (TextView) findViewById(R.id.toki_text);
        karaage_text = (TextView) findViewById(R.id.karaage_text);
        twitter_text = (TextView) findViewById(R.id.twitter_text);

        textsArray[0] = niwatori_text;
        textsArray[1] = hato_text;
        textsArray[2] = suzume_text;
        textsArray[3] = inko_text;
        textsArray[4] = kamome_text;
        textsArray[5] = meziro_text;
        textsArray[6] = taka_text;
        textsArray[7] = kyukantyo_text;
        textsArray[8] = ukokkei_text;
        textsArray[9] = omu_text;
        textsArray[10] = turu_text;
        textsArray[11] = hukuro_text;
        textsArray[12] = perikan_text;
        textsArray[13] = kizi_text;
        textsArray[14] = oniohashi_text;
        textsArray[15] = hagewashi_text;
        textsArray[16] = huramingo_text;
        textsArray[17] = pengin_text;
        textsArray[18] = datyo_text;
        textsArray[19] = iwatobi_text;
        textsArray[20] = hikuidori_text;
        textsArray[21] = toki_text;
        textsArray[22] = karaage_text;
        textsArray[23] = twitter_text;

        // イメージボタンの生成
        ImageButton returnBtnPage1 = (ImageButton) findViewById(R.id.back_bleed);
        ImageButton returnBtnPage2 = (ImageButton) findViewById(R.id.back_bleed2);
        ImageButton returnBtnPage3 = (ImageButton) findViewById(R.id.back_bleed3);
        ImageButton returnBtnPage4 = (ImageButton) findViewById(R.id.back_bleed4);
        returnBtnPage1.setOnClickListener(this);
        returnBtnPage2.setOnClickListener(this);
        returnBtnPage3.setOnClickListener(this);
        returnBtnPage4.setOnClickListener(this);


    }

    public void onResume() {
        super.onResume();

        try {

            // 以下はデータベースの処理
            Cursor cursor = getData();
            hideChicken(cursor);
            cursor.close();            
//            Cursor cursor1 = getData();
//            cursor1.moveToFirst();
//            ChikenMAXimg(cursor1);
//            cursor1.close();
        } finally {

            // データベースを閉じる
            helper.close();
        }

    }

    // データベースから入力したデータの読み込み
    private Cursor getData() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db
                .query(TABLE_NAME, FROM, null, null, null, null, null);
        return cursor;
    }

    // データベースから読み込んだデータを取得
    // 飼ったことのないチキンの画像と名前を隠す
    private void hideChicken(Cursor cursor) {
        int counter = 0;
        while (cursor.moveToNext()) {

            // 各列ごとにデータを取得する
            int type = cursor.getInt(2);

            // タイプが０なら画像とテキストを隠す
            if (type == 0) {
                imgsArray[counter].setVisibility(View.INVISIBLE);
                textsArray[counter].setVisibility(View.INVISIBLE);
            } else if (type == 2) {
                imgsArray[counter].setImageResource(maxBirdResorce[counter]);
            }
            counter++;
        }
    }
    
    //チキンが最大まで成長した場合の処理
    private void ChikenMAXimg(Cursor cursor) {
        int counter = 0;
        while (cursor.moveToNext()) {

            // 各列ごとにデータを取得する
            int type = cursor.getInt(2);

            // タイプが０なら画像とテキストを隠す
            if (type == 2) {
            	Toast.makeText(BirdGuide.this, "チキン最大まで成長しました　出荷しましょう" + counter, Toast.LENGTH_SHORT)
                .show();
            	imgsArray[counter].setImageResource(maxBirdResorce[counter]);
//            	imgsArray[chikenMAXcounter].setVisibility(View.INVISIBLE);
            }
            counter++;
        }
    }

    
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int x = (int) event.getRawX();
        switch (v.getId()) {
        case R.id.layout_first:
        case R.id.layout_second:
        case R.id.layout_third:
        case R.id.layout_fourth:

            switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                firstTouch = event.getRawX();
                return true;
            case MotionEvent.ACTION_MOVE:
                if (!isFlip) {
                    if (x - firstTouch > 50) {
                        isFlip = true;
                        viewFlipper.setInAnimation(AnimationUtils
                                .loadAnimation(this, R.anim.move_in_left));
                        viewFlipper.setOutAnimation(AnimationUtils
                                .loadAnimation(this, R.anim.move_out_right));
                        viewFlipper.showNext();
                    } else if (firstTouch - x > 50) {
                        isFlip = true;
                        viewFlipper.setInAnimation(AnimationUtils
                                .loadAnimation(this, R.anim.move_in_right));
                        viewFlipper.setOutAnimation(AnimationUtils
                                .loadAnimation(this, R.anim.move_out_left));
                        viewFlipper.showPrevious();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                isFlip = false;
                break;
            }
        }

        return false;
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
    public void onClick(View v) {
        Intent intent = new Intent(this, Bleeding.class);
        startActivity(intent);
    }

}
