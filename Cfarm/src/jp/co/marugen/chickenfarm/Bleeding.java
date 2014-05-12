package jp.co.marugen.chickenfarm;

import static jp.co.marugen.chickenfarm.Constants.CHICKEN_TYPE;
import static jp.co.marugen.chickenfarm.Constants.TABLE_NAME;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.Time;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Bleeding extends Activity implements OnClickListener {

    private int nowTime = 0;
    private int guts;
    private int BleedingPoint;

    // private int animNum;
    private static boolean isHugry;
    private boolean canTraning;
    private TextView cpText;
    private TextView gutsText;
    private ImageView chikenImgV;
    private ImageView satietybarImgV;
    private ImageView chikenRankImgV;
    private ImageView feedImgV;

    // 鳥の画像配列
    private int[] birdResorce = {

            // egg1
            R.drawable.niwatori,
            R.drawable.hato_front,
            R.drawable.suzume_front,
            R.drawable.inko_front,
            R.drawable.kamome_front,
            R.drawable.mejiro_front,

            // egg2
            R.drawable.taka_front,
            R.drawable.kyukantyo_front,
            R.drawable.ukokkei_front,
            R.drawable.oumu_front,
            R.drawable.turu_front,
            R.drawable.fukurou_front,

            // egg3
            R.drawable.perikan_front, R.drawable.kiji_front,
            R.drawable.onioohashi_front, R.drawable.hagewasi_front,
            R.drawable.furamingo_front,
            R.drawable.pengin_front,

            // egg4
            R.drawable.datyou_front, R.drawable.iwatobi_paengin_front,
            R.drawable.hikuidori_front, R.drawable.toki_front,
            R.drawable.karaage_kun_front, R.drawable.twitter_front };

    // 現在時刻
    private int currentTime;

    // 前回の時間
    private int preTime;

    // タイマー
    private Timer timer;
    public final static int TIMER_PERIOD = 5000; // タイマーの周期
    private Handler handler = new Handler(); // タイマーのハンドラを生成

    // データベース
    private ChikenDBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // フルスクリーン、タイトルバーの非表示
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        setContentView(R.layout.bleeding);

        helper = new ChikenDBHelper(this); // ヘルパーの作成

        // テキストビューの生成
        cpText = (TextView) findViewById(R.id.having_cp);
        gutsText = (TextView) findViewById(R.id.guts);

        // イメージビューの生成
        chikenImgV = (ImageView) findViewById(R.id.chiken);
        satietybarImgV = (ImageView) findViewById(R.id.satietybar);
        chikenRankImgV = (ImageView) findViewById(R.id.chiken_rank);
        feedImgV = (ImageView) findViewById(R.id.buit);

        // ImageButton
        ImageButton tab_buit = (ImageButton) findViewById(R.id.tab_bait);
        tab_buit.setOnClickListener(this);
        ImageButton tab_traing = (ImageButton) findViewById(R.id.tab_training);
        tab_traing.setOnClickListener(this);
        ImageButton tab_shop = (ImageButton) findViewById(R.id.tab_shop);
        tab_shop.setOnClickListener(this);
        ImageButton tab_book = (ImageButton) findViewById(R.id.tab_book);
        tab_book.setOnClickListener(this);
        ImageButton tab_back = (ImageButton) findViewById(R.id.tab_back);
        tab_back.setOnClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();

        // CPの表示
        cpText.setText(String.valueOf(DataManager.getInstance(this).loadCP()));

        // ガッツの表示
        guts = DataManager.getInstance(this).loadTOTALGUTS();
        gutsText.setText(String.valueOf(guts));

        // ガッツポイントによりチキンランクがかわる
        if (guts <= 999) {
            chikenRankImgV.setImageResource(R.drawable.chiken_rank1);
        } else if (guts >= 1000 && guts <= 1999) {
            chikenRankImgV.setImageResource(R.drawable.chiken_rank2);
        } else if (guts >= 2000 && guts <= 2999) {
            chikenRankImgV.setImageResource(R.drawable.chiken_rank3);
        } else if (guts >= 3000 && guts <= 3999) {
            chikenRankImgV.setImageResource(R.drawable.chiken_rank4);
        } else if (guts >= 4000 && guts <= 4999) {
            chikenRankImgV.setImageResource(R.drawable.chiken_rank5);
        } else if (guts >= 5000 && guts <= 5999) {
            chikenRankImgV.setImageResource(R.drawable.chiken_rank6);
        } else if (guts >= 6000 && guts <= 6999) {
            chikenRankImgV.setImageResource(R.drawable.chiken_rank7);
        } else if (guts >= 7000) {
            chikenRankImgV.setImageResource(R.drawable.chiken_rank8);
        } else {

        }

        // アニメーション
        rndAnimation();

        /**
         * 前回エサをあげた時間と今回育成画面に入った時間の差の取得 データが無い場合、チキンがいない場合は差を0で計算
         */

        if (DataManager.getInstance(Bleeding.this).loadTIME() == -1
                || DataManager.getInstance(Bleeding.this).loadEGG() == 0) {
            nowTime = newTime();
            DataManager.getInstance(Bleeding.this).saveTIME(nowTime);
        }

        // 前回食事を与えた時間を取得
        preTime = DataManager.getInstance(this).loadTIME();

        // タイマーを生成
        timer = new Timer(false);

        // スケジュールを設定
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {

                    // ここに定期実行したい処理を書く
                    @Override
                    public void run() {

                        // トレーニング可能かの判定
                        if (DataManager.getInstance(Bleeding.this)
                                .loadBLEEDING_POINT() >= 10) {
                            canTraning = true;
                        }

                        // チキンがちゃんといるかどうか
                        if (DataManager.getInstance(Bleeding.this).loadTIME() == -1
                                || DataManager.getInstance(Bleeding.this)
                                        .loadEGG() == 0) {
                            // チキンいなければなにもしない
                        } else {
                            currentTime = newTime();
                            changeSatietyBar(currentTime - preTime);// 満腹度ゲージの変更
                            growthJudge(currentTime - preTime);// 成長の判定
                        }
                    }
                });
            }
        }, 1000, TIMER_PERIOD); // 初回起動の遅延(1sec)と周期(5sec)指定
    }

    @Override
    public void onStop() {
        super.onStop();
        timer.cancel();// タイマーを止める
    }

    @Override
    public void onClick(View v) {

        // /////////////////////// 事前準備///////////////////////////
        // エサの個数の取得
        final int ikaNum = DataManager.getInstance(this).loadIKA();
        final int ebiNum = DataManager.getInstance(this).loadEBI();
        final int maguroNum = DataManager.getInstance(this).loadMAGURO();
        final int samonNum = DataManager.getInstance(this).loadSAMON();
        final int ikuraNum = DataManager.getInstance(this).loadIKURA();

        // 選択項目を準備する。
        String[] strItems = { "いか" + "\t" + String.valueOf(ikaNum) + "コ",
                "えび" + "\t" + String.valueOf(ebiNum) + "コ",
                "まぐろ" + "\t" + String.valueOf(maguroNum) + "コ",
                "サーモン" + "\t" + String.valueOf(samonNum) + "コ",
                "いくら" + "\t" + String.valueOf(ikuraNum) + "コ", "キャンセル" };

        switch (v.getId()) {

        // エサボタン
        case R.id.tab_bait:
            if (DataManager.getInstance(this).loadEGG() == 0) {
                Toast.makeText(Bleeding.this, "チキンがいません", Toast.LENGTH_SHORT)
                        .show();
            } else {

                // 満腹なら
                if (!Bleeding.isHugry) {
                    Toast.makeText(Bleeding.this, "満腹です", Toast.LENGTH_SHORT)
                            .show();

                    // 空腹なら
                } else {

                    // 選択項目を準備する。
                    new AlertDialog.Builder(Bleeding.this)
                            .setTitle("どのエサをあげますか？")
                            .setItems(strItems,
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(
                                                DialogInterface dialog,
                                                int which) {

                                            boolean wasNoFeed = false; // 選択された餌を所持しているかのフラグ

                                            // 選択したアイテムの番号(0～)がwhichに格納される
                                            switch (which) {
                                            case 0:

                                                // 餌をもってなかったら
                                                if (ikaNum == 0) {
                                                    wasNoFeed = true;
                                                } else {
                                                    BleedingPoint = DataManager
                                                            .getInstance(
                                                                    Bleeding.this)
                                                            .loadBLEEDING_POINT() + 10;
                                                    DataManager
                                                            .getInstance(
                                                                    Bleeding.this)
                                                            .saveIKA(ikaNum - 1);
                                                    eatAnimation(R.drawable.ika);
                                                }
                                                break;

                                            case 1:
                                                if (ebiNum == 0) {
                                                    wasNoFeed = true;
                                                } else {
                                                    BleedingPoint = DataManager
                                                            .getInstance(
                                                                    Bleeding.this)
                                                            .loadBLEEDING_POINT() + 20;
                                                    DataManager
                                                            .getInstance(
                                                                    Bleeding.this)
                                                            .saveEBI(ebiNum - 1);
                                                    eatAnimation(R.drawable.ebi);
                                                }
                                                break;

                                            case 2:
                                                if (maguroNum == 0) {
                                                    wasNoFeed = true;
                                                } else {
                                                    BleedingPoint = DataManager
                                                            .getInstance(
                                                                    Bleeding.this)
                                                            .loadBLEEDING_POINT() + 30;
                                                    DataManager
                                                            .getInstance(
                                                                    Bleeding.this)
                                                            .saveMAGURO(
                                                                    maguroNum - 1);
                                                    eatAnimation(R.drawable.maguro);
                                                }
                                                break;

                                            case 3:
                                                if (samonNum == 0) {
                                                    wasNoFeed = true;
                                                } else {
                                                    BleedingPoint = DataManager
                                                            .getInstance(
                                                                    Bleeding.this)
                                                            .loadBLEEDING_POINT() + 40;
                                                    DataManager
                                                            .getInstance(
                                                                    Bleeding.this)
                                                            .saveSAMON(
                                                                    samonNum - 1);
                                                    eatAnimation(R.drawable.samon);
                                                }
                                                break;

                                            case 4:
                                                if (ikuraNum == 0) {
                                                    wasNoFeed = true;
                                                } else {
                                                    BleedingPoint = DataManager
                                                            .getInstance(
                                                                    Bleeding.this)
                                                            .loadBLEEDING_POINT() + 50;
                                                    DataManager
                                                            .getInstance(
                                                                    Bleeding.this)
                                                            .saveIKURA(
                                                                    ikuraNum - 1);
                                                    eatAnimation(R.drawable.ikura);
                                                }
                                                break;

                                            default:
                                                // キャンセル
                                                Toast.makeText(Bleeding.this,
                                                        "キャンセルしました",
                                                        Toast.LENGTH_SHORT)
                                                        .show();

                                                return;
                                            }

                                            // 餌を所持していれば
                                            if (!wasNoFeed) {
                                                DataManager.getInstance(
                                                        Bleeding.this)
                                                        .saveBLEEDING_POINT(
                                                                BleedingPoint);
                                                nowTime = newTime();
                                                preTime = nowTime;
                                                DataManager.getInstance(
                                                        Bleeding.this)
                                                        .saveTIME(nowTime);
                                                // changeSatietyBar(0);
                                                Bleeding.isHugry = false;

                                            } else {
                                                Toast.makeText(Bleeding.this,
                                                        "エサを所持していません",
                                                        Toast.LENGTH_SHORT)
                                                        .show();
                                            }
                                        }
                                    }).show();

                }
            }
            break;

        // トレーニングボタン
        case R.id.tab_training:
            // トレーニングができるかの判定
            if (canTraning) {
                // インテントのインスタンス生成
                Intent intent0 = new Intent(Bleeding.this,
                        RussianRoulette.class);
                // 次画面のアクティビティ起動
                startActivity(intent0);
            } else {
                Toast.makeText(Bleeding.this, "まだトレーニングできません",
                        Toast.LENGTH_SHORT).show();
            }

            break;

        // ショップボタン
        case R.id.tab_shop:
            // インテントのインスタンス生成
            Intent intent1 = new Intent(Bleeding.this, Shop.class);
            // 次画面のアクティビティ起動
            startActivity(intent1);
            break;

        case R.id.tab_book:
            // インテントのインスタンス生成
            Intent intent2 = new Intent(Bleeding.this, BirdGuide.class);
            // 次画面のアクティビティ起動
            startActivity(intent2);
            break;

        // トップボタン
        case R.id.tab_back:

            // ////////////////////////////図鑑コンプ//////////////////////////////
            DataManager.getInstance(this).saveCP(
                    DataManager.getInstance(this).loadCP() + 10000);

            // インテントのインスタンス生成
            Intent intent3 = new Intent(Bleeding.this, ChikenActivity.class);
            // 次画面のアクティビティ起動
            startActivity(intent3);
            break;
        }
    }

    // 現在時刻の取得
    public static int newTime() {
        int nowtime = 0;
        Time time = new Time("Asia/Tokyo");
        time.setToNow();
        nowtime = (time.monthDay * 10000) + (time.hour * 100) + time.minute;
        return nowtime;
    }

    // 満腹度の変更
    public void changeSatietyBar(int elapseTime) {

        if (elapseTime < 1) {
            satietybarImgV.setImageResource(R.drawable.satietybar100);
            Bleeding.isHugry = false;
        } else if (elapseTime >= 1 && elapseTime <= 3) {
            satietybarImgV.setImageResource(R.drawable.satietybar80);
            Bleeding.isHugry = true;
        } else if (elapseTime >= 3 && elapseTime <= 4) {
            satietybarImgV.setImageResource(R.drawable.satietybar0);
            Bleeding.isHugry = true;
        } else if (elapseTime >= 20) {
            Bleeding.isHugry = true;
            satietybarImgV.setImageResource(R.drawable.satietybar0);
            chikenImgV.setImageResource(R.drawable.chiken_rank8);
            DataManager.getInstance(this).saveTOTALGUTS(0);
            DataManager.getInstance(this).saveBLEEDING_POINT(0);
            DataManager.getInstance(this).saveEGG(0);
            Toast.makeText(Bleeding.this, "チキンが死んじゃったよ", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    // 成長ポイントの判定 need test
    public void growthJudge(int elapseTime) {

        // チキンも玉子もなかったら
        if (DataManager.getInstance(this).loadEGG() == 0) {
            chikenImgV.setImageResource(R.drawable.none);
        } else {

            // 卵の状態なら
            if (DataManager.getInstance(this).loadBLEEDING_POINT() == 0) {

                // 10分経ってなかったら
                // elapseTime <= 9　だった
                if (elapseTime < 1) {
                    DataManager.getInstance(this).saveTRANING_JUDGE(false);
                    if (DataManager.getInstance(this).loadEGG() == 1) {
                        chikenImgV.setImageResource(R.drawable.egg_nomal);
                    } else if (DataManager.getInstance(this).loadEGG() == 2) {
                        chikenImgV.setImageResource(R.drawable.egg_green);
                    } else if (DataManager.getInstance(this).loadEGG() == 3) {
                        chikenImgV.setImageResource(R.drawable.egg_rain);
                    } else if (DataManager.getInstance(this).loadEGG() == 4) {
                        chikenImgV.setImageResource(R.drawable.egg_gold);
                    }

                    // elapseTime >= 10　だった
                } else if (elapseTime >= 1) {
                    DataManager.getInstance(this).saveBLEEDING_POINT(10);
                    chikenImgV.setImageResource(R.drawable.chiken_child);
                }

                // ひなが十分育ったら (400だった)
            } else if (DataManager.getInstance(this).loadBLEEDING_POINT() >= 40) {

                DataManager.getInstance(this).saveFORWARD_JUDGE(true);// 出荷可能にする

                // 卵は全部で４種類ある
                for (int i = 0; i < 4; i++) {

                    // 卵の種類は何か
                    if (DataManager.getInstance(this).loadEGG() == i + 1) {

                        if (DataManager.getInstance(this).loadTOTALGUTS() <= 1499) {
                            saveChikenType(1);
                            chikenImgV.setImageResource(birdResorce[6 * i + 0]);// チキンの画像をセット
                            updateDB(6 * i + 0);// データベースに保存
                        } else if (DataManager.getInstance(this)
                                .loadTOTALGUTS() < 3000) {
                            saveChikenType(2);
                            chikenImgV.setImageResource(birdResorce[6 * i + 1]);
                            updateDB(6 * i + 1);// データベースに保存
                        } else if (DataManager.getInstance(this)
                                .loadTOTALGUTS() < 4500) {
                            saveChikenType(3);
                            chikenImgV.setImageResource(birdResorce[6 * i + 2]);
                            updateDB(6 * i + 2);// データベースに保存
                        } else if (DataManager.getInstance(this)
                                .loadTOTALGUTS() < 6000) {
                            saveChikenType(4);
                            chikenImgV.setImageResource(birdResorce[6 * i + 3]);
                            updateDB(6 * i + 3);// データベースに保存
                        } else if (DataManager.getInstance(this)
                                .loadTOTALGUTS() < 7500) {
                            saveChikenType(5);
                            chikenImgV.setImageResource(birdResorce[6 * i + 4]);
                            updateDB(6 * i + 4);// データベースに保存
                        } else if (DataManager.getInstance(this)
                                .loadTOTALGUTS() >= 7500) {
                            saveChikenType(6);
                            chikenImgV.setImageResource(birdResorce[6 * i + 5]);
                            updateDB(6 * i + 5);// データベースに保存
                        }
                    }
                }

            } else {
                chikenImgV.setImageResource(R.drawable.chiken_child);
            }
        }
    }

    // データベースに新しいチキンを登録する
    public void updateDB(int id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        // フラグを１にする
        values.put(CHICKEN_TYPE, 1);
        db.update(TABLE_NAME, values, "_ID =" + id, null);
    }

    // すべてのデータの初期化
    public void allClear() {
        DataManager.getInstance(this).saveCHIKEN(0);
        DataManager.getInstance(this).saveBLEEDING_POINT(0);
        DataManager.getInstance(this).saveCP(3000);
        DataManager.getInstance(this).saveTOTALGUTS(0);
        DataManager.getInstance(this).saveFORWARD_JUDGE(false);
        DataManager.getInstance(this).saveEGG(0);
        DataManager.getInstance(this).saveTIME(-1);
        DataManager.getInstance(this).saveIKA(0);
        DataManager.getInstance(this).saveEBI(0);
        DataManager.getInstance(this).saveMAGURO(0);
        DataManager.getInstance(this).saveSAMON(0);
        DataManager.getInstance(this).saveIKURA(0);
        DataManager.getInstance(this).saveGUTS(0);
        DataManager.getInstance(this).saveGET_CP(0);
    }

    // public void datainsert(String dataname, int value) {
    // データベースヘルパーの生成
    // helper = new ChikenDBHelper(this);
    // db = helper.getWritableDatabase();
    // 最初の値を入れる
    // ContentValues values = new ContentValues();
    // values.put(dataname, value);
    // db.insert("chikentb", null, values);
    // db.close();
    // }

    // public void dataselect_cp(String dataname, char textname) {
    // //データベースヘルパーの生成
    // ChikenDBHelper helper = new ChikenDBHelper(this);
    // SQLiteDatabase db = helper.getWritableDatabase();
    // //テーブルの最後の値を表示
    // Cursor c = db.query("chikentb", new String[] {"cp"}, null, null, null,
    // null, null);
    // c.moveToLast();
    // textname.setText(c.getString(c.getColumnIndex("cp")));
    // db.close();
    // }

    // チキンの種類保存
    public void saveChikenType(int data) {
        DataManager.getInstance(this).saveCHIKEN(data);
    }

    public void rndAnimation() {
        Random rnd = new Random();
        int animNum = rnd.nextInt(3);

        switch (animNum) {
        case 1:
            Animation1();
            System.out.println("非常に不満");
            break;

        case 2:
            Animation2();
            System.out.println("2に不満");
            break;

        case 3:
            Animation3();
            System.out.println("3に不満");
            break;

        default:
            break;
        }
        // rndAnimation();
    }

    public void eatAnimation(int rid) {
        feedImgV.setImageResource(rid);

        List<Animator> animatorList = new ArrayList<Animator>();
        PropertyValuesHolder holderX = PropertyValuesHolder.ofFloat(
                "translationX", 0, 0);
        PropertyValuesHolder holderY = PropertyValuesHolder.ofFloat(
                "translationY", -70, 50);
        ObjectAnimator translationXYAnimator = ObjectAnimator
                .ofPropertyValuesHolder(feedImgV, holderX, holderY);
        translationXYAnimator.setDuration(2000);
        animatorList.add(translationXYAnimator);
        // alphaプロパティを0fから1fに変化させます
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(feedImgV,
                "alpha", 1f, 0f);
        // 3秒かけて実行させます
        objectAnimator.setDuration(4000);
        animatorList.add(objectAnimator);
        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animatorList);
        animatorSet.start();

        // 遅延させてImageViewを消す
        new Handler().postDelayed(delayFunc, 7000);
    }

    // 餌用のイメージビューを消すため
    private final Runnable delayFunc = new Runnable() {
        @Override
        public void run() {
            feedImgV.setImageDrawable(null);
        }
    };

    public void Animation1() {

        // イメージビューの生成
        chikenImgV = (ImageView) findViewById(R.id.chiken);

        // AnimatorSetに渡すAnimatorのリスト
        List<Animator> animatorList = new ArrayList<Animator>();

        PropertyValuesHolder holderX = PropertyValuesHolder.ofFloat(
                "translationX", 0, -20);
        PropertyValuesHolder holderY = PropertyValuesHolder.ofFloat(
                "translationY", 0, -80);

        // chikenに対してholderX, holderY
        ObjectAnimator translationXYAnimator = ObjectAnimator
                .ofPropertyValuesHolder(chikenImgV, holderX, holderY);

        // 2秒かけて実行させます
        translationXYAnimator.setDuration(8000);

        // リストに追加します
        animatorList.add(translationXYAnimator);

        PropertyValuesHolder holderX2 = PropertyValuesHolder.ofFloat(
                "translationX", -20, -130);
        PropertyValuesHolder holderY2 = PropertyValuesHolder.ofFloat(
                "translationY", -80, -130);

        // chikenに対してholderX2, holderY2
        ObjectAnimator translationXYAnimator2 = ObjectAnimator
                .ofPropertyValuesHolder(chikenImgV, holderX2, holderY2);

        // 2秒かけて実行させます
        translationXYAnimator2.setDuration(10000);

        // リストに追加します
        animatorList.add(translationXYAnimator2);

        PropertyValuesHolder holderX3 = PropertyValuesHolder.ofFloat(
                "translationX", -130, -100);
        PropertyValuesHolder holderY3 = PropertyValuesHolder.ofFloat(
                "translationY", -130, 0);
        ObjectAnimator translationXYAnimator3 = ObjectAnimator
                .ofPropertyValuesHolder(chikenImgV, holderX3, holderY3);
        translationXYAnimator3.setStartDelay(4000);
        translationXYAnimator3.setDuration(7000);
        animatorList.add(translationXYAnimator3);

        PropertyValuesHolder holderX4 = PropertyValuesHolder.ofFloat(
                "translationX", -100, 80);
        PropertyValuesHolder holderY4 = PropertyValuesHolder.ofFloat(
                "translationY", 0, 120);
        ObjectAnimator translationXYAnimator4 = ObjectAnimator
                .ofPropertyValuesHolder(chikenImgV, holderX4, holderY4);
        translationXYAnimator4.setStartDelay(4000);
        translationXYAnimator4.setDuration(8000);
        animatorList.add(translationXYAnimator4);

        PropertyValuesHolder holderX5 = PropertyValuesHolder.ofFloat(
                "translationX", 80, 0);
        PropertyValuesHolder holderY5 = PropertyValuesHolder.ofFloat(
                "translationY", 120, 0);
        ObjectAnimator translationXYAnimator5 = ObjectAnimator
                .ofPropertyValuesHolder(chikenImgV, holderX5, holderY5);
        translationXYAnimator5.setStartDelay(6000);
        translationXYAnimator5.setDuration(6000);
        animatorList.add(translationXYAnimator5);

        final AnimatorSet animatorSet = new AnimatorSet();

        // リストのAnimatorを順番に実行します
        animatorSet.playSequentially(animatorList);

        // アニメーションを開始します
        animatorSet.start();
    }

    public void Animation2() {
        chikenImgV = (ImageView) findViewById(R.id.chiken);

        List<Animator> animatorList = new ArrayList<Animator>();
        PropertyValuesHolder holderX = PropertyValuesHolder.ofFloat(
                "translationX", 0, -10);
        PropertyValuesHolder holderY = PropertyValuesHolder.ofFloat(
                "translationY", 0, 120);
        ObjectAnimator translationXYAnimator = ObjectAnimator
                .ofPropertyValuesHolder(chikenImgV, holderX, holderY);
        translationXYAnimator.setDuration(10000);
        animatorList.add(translationXYAnimator);

        PropertyValuesHolder holderX2 = PropertyValuesHolder.ofFloat(
                "translationX", -10, 110);
        PropertyValuesHolder holderY2 = PropertyValuesHolder.ofFloat(
                "translationY", 120, 30);
        ObjectAnimator translationXYAnimator2 = ObjectAnimator
                .ofPropertyValuesHolder(chikenImgV, holderX2, holderY2);
        translationXYAnimator2.setStartDelay(6000);
        translationXYAnimator2.setDuration(8000);
        animatorList.add(translationXYAnimator2);

        PropertyValuesHolder holderX3 = PropertyValuesHolder.ofFloat(
                "translationX", 110, -120);
        PropertyValuesHolder holderY3 = PropertyValuesHolder.ofFloat(
                "translationY", 30, -80);
        ObjectAnimator translationXYAnimator3 = ObjectAnimator
                .ofPropertyValuesHolder(chikenImgV, holderX3, holderY3);
        translationXYAnimator3.setDuration(8000);
        animatorList.add(translationXYAnimator3);

        PropertyValuesHolder holderX4 = PropertyValuesHolder.ofFloat(
                "translationX", -120, 40);
        PropertyValuesHolder holderY4 = PropertyValuesHolder.ofFloat(
                "translationY", -80, 40);
        ObjectAnimator translationXYAnimator4 = ObjectAnimator
                .ofPropertyValuesHolder(chikenImgV, holderX4, holderY4);
        translationXYAnimator4.setStartDelay(5000);
        translationXYAnimator4.setDuration(9000);
        animatorList.add(translationXYAnimator4);

        PropertyValuesHolder holderX5 = PropertyValuesHolder.ofFloat(
                "translationX", 40, 0);
        PropertyValuesHolder holderY5 = PropertyValuesHolder.ofFloat(
                "translationY", 40, 0);
        ObjectAnimator translationXYAnimator5 = ObjectAnimator
                .ofPropertyValuesHolder(chikenImgV, holderX5, holderY5);
        translationXYAnimator5.setStartDelay(10000);
        translationXYAnimator5.setDuration(8000);
        animatorList.add(translationXYAnimator5);

        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animatorList);
        animatorSet.start();
    }

    public void Animation3() {
        chikenImgV = (ImageView) findViewById(R.id.chiken);

        List<Animator> animatorList = new ArrayList<Animator>();
        PropertyValuesHolder holderX = PropertyValuesHolder.ofFloat(
                "translationX", 0, 80);
        PropertyValuesHolder holderY = PropertyValuesHolder.ofFloat(
                "translationY", 0, -40);
        ObjectAnimator translationXYAnimator = ObjectAnimator
                .ofPropertyValuesHolder(chikenImgV, holderX, holderY);
        translationXYAnimator.setStartDelay(10000);
        translationXYAnimator.setDuration(6000);
        animatorList.add(translationXYAnimator);

        PropertyValuesHolder holderX2 = PropertyValuesHolder.ofFloat(
                "translationX", 80, 40);
        PropertyValuesHolder holderY2 = PropertyValuesHolder.ofFloat(
                "translationY", -40, -80);
        ObjectAnimator translationXYAnimator2 = ObjectAnimator
                .ofPropertyValuesHolder(chikenImgV, holderX2, holderY2);
        translationXYAnimator2.setStartDelay(6000);
        translationXYAnimator2.setDuration(10000);
        animatorList.add(translationXYAnimator2);

        PropertyValuesHolder holderX3 = PropertyValuesHolder.ofFloat(
                "translationX", 40, 20);
        PropertyValuesHolder holderY3 = PropertyValuesHolder.ofFloat(
                "translationY", -80, -100);
        ObjectAnimator translationXYAnimator3 = ObjectAnimator
                .ofPropertyValuesHolder(chikenImgV, holderX3, holderY3);
        translationXYAnimator3.setDuration(7000);
        animatorList.add(translationXYAnimator3);

        PropertyValuesHolder holderX4 = PropertyValuesHolder.ofFloat(
                "translationX", 20, -60);
        PropertyValuesHolder holderY4 = PropertyValuesHolder.ofFloat(
                "translationY", -100, -20);
        ObjectAnimator translationXYAnimator4 = ObjectAnimator
                .ofPropertyValuesHolder(chikenImgV, holderX4, holderY4);
        translationXYAnimator4.setStartDelay(5000);
        translationXYAnimator4.setDuration(11000);
        animatorList.add(translationXYAnimator4);

        PropertyValuesHolder holderX5 = PropertyValuesHolder.ofFloat(
                "translationX", -60, 0);
        PropertyValuesHolder holderY5 = PropertyValuesHolder.ofFloat(
                "translationY", -20, 0);
        ObjectAnimator translationXYAnimator5 = ObjectAnimator
                .ofPropertyValuesHolder(chikenImgV, holderX5, holderY5);
        translationXYAnimator5.setDuration(8000);
        animatorList.add(translationXYAnimator5);

        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animatorList);
        animatorSet.start();
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

}