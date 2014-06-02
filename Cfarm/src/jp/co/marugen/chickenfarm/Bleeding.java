package jp.co.marugen.chickenfarm;

import static jp.co.marugen.chickenfarm.Constants.CHICKEN_TYPE;
import static jp.co.marugen.chickenfarm.Constants.TABLE_NAME;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import jp.co.marugen.chickenfarm.garbagepick.GarbagePick;
import jp.co.marugen.chickenfarm.ghostcrash.GhostCrash;
import jp.co.marugen.chickenfarm.ghostcrash.MyGgm;
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

    private int firstEvo = 150;
    private int secondEvo = 400;
    private int hungryTime = 100;//最初にゲージが減る時間 100だった
    private int birthTime = 3; //生まれる時間 3だった
    
    private int firstChange = 1500;
    private int secontChange = 3000;

    private int nowTime = 0;
    private int guts;
    private int BleedingPoint;
    private static boolean isHugry;
    private boolean canTraning;
    // private boolean nowEating;
    private int eating = 0;
    private TextView cpText;
    private TextView gutsText;
    private ImageView chikenImgV;
    private ImageView satietybarImgV;
    private ImageView chikenRankImgV;
    private ImageView feedImgV;

    private MyGgm myBgm;

    private int nowBirdFrontRescorce;
    private int nowBirdSideRescorce;
    // private int[] nowBirdImageResorce = new int[2];// 現在表示されている鳥の画像リソース
    ArrayList<Integer> array = new ArrayList<Integer>();

    // 鳥正面の画像配列　要変更
    private int[] birdFrontResorce = {

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
            R.drawable.datyou_front, R.drawable.iwatobi_pengin_front,
            R.drawable.hikuidori_front, R.drawable.toki_front,
            R.drawable.karaage_kun_front, R.drawable.twitter_front };

    // 鳥横の画像配列　
    private int[] birdSideResorce = {

            // egg1
            R.drawable.niwatori_side,
            R.drawable.hato_side,
            R.drawable.suzume_side,
            R.drawable.inko_side,
            R.drawable.kamome_side,
            R.drawable.mejiro_side,

            // egg2
            R.drawable.taka_side,
            R.drawable.kyukantyo_side,
            R.drawable.ukokkei_side,
            R.drawable.oumu_side,
            R.drawable.turu_side2,
            R.drawable.fukurou_side,

            // egg3
            R.drawable.perikan_side, R.drawable.kiji_side,
            R.drawable.onioohashi_side, R.drawable.hagewasi_side,
            R.drawable.furamingo_side,
            R.drawable.pengin_side,

            // egg4
            R.drawable.datyou_side, R.drawable.iwatobi_pengin_side,
            R.drawable.hikuidori_side, R.drawable.toki_side,
            R.drawable.karaage_kun_side, R.drawable.twitter_side };

    // エサを食べる画像の配列
    private int[] birdBaitResorce = {

            // egg1
            R.drawable.niwatori_bait,
            R.drawable.hato_bait,
            R.drawable.suzume_bait,
            R.drawable.inko_bait,
            R.drawable.kamome_bait,
            R.drawable.mejiro_bait,

            // egg2
            R.drawable.taka_bait,
            R.drawable.kyukantyo_bait,
            R.drawable.ukokkei_bait,
            R.drawable.oumu_bait,
            R.drawable.turu_bait,
            R.drawable.fukurou_bait,

            // egg3
            R.drawable.perikan_bait, R.drawable.kiji_bait,
            R.drawable.onioohashi_bait, R.drawable.hagewasi_bait,
            R.drawable.furamingo_bait,
            R.drawable.pengin_bait,

            // egg4
            R.drawable.datyou_bait, R.drawable.iwatobi_pengin_bait,
            R.drawable.hikuidori_bait, R.drawable.toki_bait,
            R.drawable.karaage_kun_bait, R.drawable.twitter_bait

    };

    // 王冠の画像の配列
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
            R.drawable.perikan_front_king, R.drawable.kiji_front_king,
            R.drawable.onioohashi_front_king, R.drawable.hagewasi_front_king,
            R.drawable.furamingo_front_king,
            R.drawable.pengin_front_king,

            // egg4
            R.drawable.datyou_front_king, R.drawable.iwatobi_pengin_front_king,
            R.drawable.hikuidori_front_king, R.drawable.toki_front_king,
            R.drawable.karaage_kun_king, R.drawable.twitter_front_king };

    // 現在時刻
    private int currentTime;

    // 前回の時間
    private int preTime;

    // タイマー
    private Timer timer;
    private Timer timer2;
    public final static int TIMER_PERIOD = 8000; // タイマーの周期
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

        myBgm = new MyGgm(this);

    }

    @Override
    public void onResume() {
        super.onResume();

        myBgm.start(0);

        // CPの表示
        cpText.setText(String.valueOf(DataManager.getInstance(this).loadCP()));

        // チキンがいたら、チキンの画像を取得
        if (DataManager.getInstance(this).loadEGG() != 0) {
            nowBirdFrontRescorce = DataManager.getInstance(this)
                    .loadFRONTCHIKEN();
            nowBirdSideRescorce = DataManager.getInstance(this)
                    .loadSIDECHIKEN();
        }

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

        /**
         * 前回エサをあげた時間と今回育成画面に入った時間の差の取得 データが無い場合、チキンがいない場合は差を0で計算
         */

        if (DataManager.getInstance(Bleeding.this).loadTIME() == -1
                || DataManager.getInstance(Bleeding.this).loadEGG() == 0) {
            nowTime = newTime();
            DataManager.getInstance(Bleeding.this).saveTIME(nowTime);
        }

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
                        } else if (DataManager.getInstance(Bleeding.this)
                                .loadBLEEDING_POINT() >= secondEvo) {
                            // チキンが最大まで成長していれば時間が経過しないようにする
                            changeSatietyBar(0);
                            growthJudge(0);
                        } else {
                            currentTime = newTime();

                            // 前回食事を与えた時間を取得
                            preTime = DataManager.getInstance(Bleeding.this)
                                    .loadTIME();
                            changeSatietyBar(currentTime - preTime);// 満腹度ゲージの変更
                            growthJudge(currentTime - preTime);// 成長の判定
                        }
                    }
                });
            }
        }, 10, TIMER_PERIOD); // 初回起動の遅延(1sec)と周期(5sec)指定

        // タイマーを生成
        timer2 = new Timer(false);
        timer2.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {

                    // ここに定期実行したい処理を書く
                    @Override
                    public void run() {

                        // アニメーション
                        if (eating == 1) {
                            chikenEatAnimation(R.drawable.ika);
                            fadingAnimation(R.drawable.ika);
                        } else if (eating == 2) {
                            chikenEatAnimation(R.drawable.ebi);
                            fadingAnimation(R.drawable.ebi);
                        } else if (eating == 3) {
                            chikenEatAnimation(R.drawable.maguro);
                            fadingAnimation(R.drawable.maguro);
                        } else if (eating == 4) {
                            chikenEatAnimation(R.drawable.samon);
                            fadingAnimation(R.drawable.samon);
                        } else if (eating == 5) {
                            chikenEatAnimation(R.drawable.ikura);
                            fadingAnimation(R.drawable.ikura);
                        } else {
                            rndAnimation();
                        }
                    }
                });
            }
        }, 100, 25000); // 初回起動の遅延(0sec)と周期(25sec)指定
    }

    @Override
    public void onPause() {
        super.onPause();
        myBgm.stop(0);
        timer.cancel();// タイマーを止める
        timer2.cancel();// タイマーを止める
        DataManager.getInstance(this).saveFRONTCHIKEN(nowBirdFrontRescorce);
        DataManager.getInstance(this).saveSIDECHIKEN(nowBirdSideRescorce);
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
                                                    eating = 1;
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
                                                    eating = 2;
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
                                                    eating = 3;
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
                                                    eating = 4;
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
                                                    eating = 5;
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
                String[] str_items = { "ロシアンルーレット", "おばけタッチ", "おばちゃんに見つかるな",
                        "キャンセル" };
                new AlertDialog.Builder(Bleeding.this)
                        .setTitle("トレーニング")
                        .setItems(str_items,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                            int which) {
                                        // 選択したアイテムの番号(0～)がwhichに格納される
                                        switch (which) {
                                        case 0:
                                            /**
                                             * インテントのインスタンス生成 ロシアンルーレットへ移動
                                             */
                                            Intent intent0 = new Intent(
                                                    Bleeding.this,
                                                    RussianRoulette.class);
                                            // 次画面のアクティビティ起動
                                            startActivity(intent0);
                                            break;
                                        case 1:
                                            /**
                                             * インテントのインスタンス生成
                                             * おばけタッチへ移動　テスト用にトップへ移動
                                             */
                                            Intent intent1 = new Intent(
                                                    Bleeding.this,
                                                    GhostCrash.class);
                                            // 次画面のアクティビティ起動
                                            startActivity(intent1);
                                            break;

                                        case 2:
                                            /**
                                             * インテントのインスタンス生成
                                             * おばけタッチへ移動　テスト用にトップへ移動
                                             */
                                            Intent intent2 = new Intent(
                                                    Bleeding.this,
                                                    GarbagePick.class);
                                            // 次画面のアクティビティ起動
                                            startActivity(intent2);
                                            break;
                                        default:
                                            Toast.makeText(Bleeding.this,
                                                    "キャンセルしました",
                                                    Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                    }
                                }).show();

            } else {
                Toast.makeText(Bleeding.this, "まだトレーニングできません",
                        Toast.LENGTH_SHORT).show();
            }
            break;

        // ショップボタン
        case R.id.tab_shop:
            Intent intent2 = new Intent(Bleeding.this, Shop.class);
            startActivity(intent2);
            break;

        case R.id.tab_book:
            Intent intent3 = new Intent(Bleeding.this, BirdGuide.class);
            startActivity(intent3);
            break;

        // トップボタン
        case R.id.tab_back:
            Intent intent4 = new Intent(Bleeding.this, ChikenActivity.class);
            startActivity(intent4);
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
        if (elapseTime <= hungryTime
                || DataManager.getInstance(this).loadBLEEDING_POINT() >= secondEvo) {
            satietybarImgV.setImageResource(R.drawable.satietybar100);
            Bleeding.isHugry = false;

        } else if (elapseTime <= 200) {
            satietybarImgV.setImageResource(R.drawable.satietybar80);
            Bleeding.isHugry = true;
        } else if (elapseTime <= 300) {
            satietybarImgV.setImageResource(R.drawable.satietybar60);
            Bleeding.isHugry = true;
        } else if (elapseTime <= 400) {
            satietybarImgV.setImageResource(R.drawable.satietybar40);
            Bleeding.isHugry = true;
        } else if (elapseTime <= 500) {
            satietybarImgV.setImageResource(R.drawable.satietybar20);
            Bleeding.isHugry = true;
        } else if (elapseTime < 1000) {
            satietybarImgV.setImageResource(R.drawable.satietybar0);
            Bleeding.isHugry = true;
        } else if (elapseTime >= 1000) {
            Bleeding.isHugry = false;
            satietybarImgV.setImageResource(R.drawable.satietybar0);
            chikenImgV.setImageResource(R.drawable.none);
            nowBirdFrontRescorce = R.drawable.none;
            nowBirdSideRescorce = R.drawable.none;
            DataManager.getInstance(this).saveTOTALGUTS(0);
            DataManager.getInstance(this).saveBLEEDING_POINT(0);
            DataManager.getInstance(this).saveEGG(0);
            Toast.makeText(Bleeding.this, "チキンが死んじゃったよ", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    // 成長ポイントの判定 need test
    public void growthJudge(int elapseTime) {

        // チキンも卵もなかったら
        if (DataManager.getInstance(this).loadEGG() == 0) {
            chikenImgV.setImageResource(R.drawable.none);

        } else {

            // 卵の状態なら
            if (DataManager.getInstance(this).loadBLEEDING_POINT() == 0) {

                // 10分経ってなかったら
                // elapseTime <= 3　だった
                if (elapseTime >= birthTime) {

                    DataManager.getInstance(this).saveBLEEDING_POINT(10);
                    if (DataManager.getInstance(this).loadEGG() == 1) {
                        nowBirdFrontRescorce = R.drawable.hina_front;
                        nowBirdSideRescorce = R.drawable.hina_side;
                    } else if (DataManager.getInstance(this).loadEGG() == 2) {
                        nowBirdFrontRescorce = R.drawable.hina_front_uzu;
                        nowBirdSideRescorce = R.drawable.hina_side_uzu;
                    } else if (DataManager.getInstance(this).loadEGG() == 3) {
                        nowBirdFrontRescorce = R.drawable.hina_front_rain;
                        nowBirdSideRescorce = R.drawable.hina_side_rain;
                    } else if (DataManager.getInstance(this).loadEGG() == 4) {
                        nowBirdFrontRescorce = R.drawable.hina_front_gold;
                        nowBirdSideRescorce = R.drawable.hina_side_gold;
                    }
                }

            } else if (DataManager.getInstance(this).loadBLEEDING_POINT() > firstEvo && 
                    DataManager.getInstance(this).loadBLEEDING_POINT() < secondEvo) {
                DataManager.getInstance(this).saveFORWARD_JUDGE(true);// 出荷可能にする
                
            } else if (DataManager.getInstance(this).loadBLEEDING_POINT() >= secondEvo) {
                Toast.makeText(this, "最大まで成長しました　出荷しましょう", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    // データベースに新しいチキンを登録する
    public void updateDB(int id, int normalOrKing) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        // フラグを1にする
        values.put(CHICKEN_TYPE, normalOrKing);
        db.update(TABLE_NAME, values, "_ID =" + id, null);
    }

    public void rndAnimation() {
        Random rnd = new Random();
        int animNum = rnd.nextInt(2);

        switch (animNum) {
        case 0:
            chikenImgV.setImageResource(nowBirdFrontRescorce);
            Animation1();
            break;

        case 1:
            chikenImgV.setImageResource(nowBirdSideRescorce);
            Animation2();
            break;

        // case 2:
        // // chikenImgV.setImageResource(nowBirdImageResorce[1]);
        // chikenImgV.setImageResource(a);
        // Animation3();
        // break;

        default:
            break;
        }
    }

    // チキンがエサを食べるアニメーション
    public void chikenEatAnimation(int rid) {

        /**
         * チキンが食事をする時の画像 チキンによって食べる画像を変える
         */
        // ひなの画像　BLEEDING_POINTが150以下の場合
        if (DataManager.getInstance(this).loadBLEEDING_POINT() < firstEvo) {
            if (DataManager.getInstance(this).loadEGG() == 1) {
                chikenImgV.setImageResource(R.drawable.hina_bait);
            } else if (DataManager.getInstance(this).loadEGG() == 2) {
                chikenImgV.setImageResource(R.drawable.hina_bait_uzu);
            } else if (DataManager.getInstance(this).loadEGG() == 3) {
                chikenImgV.setImageResource(R.drawable.hina_bait_rain);
            } else if (DataManager.getInstance(this).loadEGG() == 4) {
                chikenImgV.setImageResource(R.drawable.hina_bait_gold);
            }
            // チキンの画像
        } else if (DataManager.getInstance(this).loadBLEEDING_POINT() >= firstEvo) {
            for (int i = 0; i < 4; i++) {

                // 卵の種類は何か
                if (DataManager.getInstance(this).loadEGG() == i + 1) {

                    if (DataManager.getInstance(this).loadTOTALGUTS() < firstChange) {
                        chikenImgV.setImageResource(birdBaitResorce[6 * i + 0]);// チキンの画像をセット
                    } else if (DataManager.getInstance(this).loadTOTALGUTS() < secontChange) {
                        chikenImgV.setImageResource(birdBaitResorce[6 * i + 1]);
                    } else if (DataManager.getInstance(this).loadTOTALGUTS() < 4500) {
                        chikenImgV.setImageResource(birdBaitResorce[6 * i + 2]);
                    } else if (DataManager.getInstance(this).loadTOTALGUTS() < 6000) {
                        chikenImgV.setImageResource(birdBaitResorce[6 * i + 3]);
                    } else if (DataManager.getInstance(this).loadTOTALGUTS() < 7500) {
                        chikenImgV.setImageResource(birdBaitResorce[6 * i + 4]);
                    } else if (DataManager.getInstance(this).loadTOTALGUTS() >= 7500) {
                        chikenImgV.setImageResource(birdBaitResorce[6 * i + 5]);
                    }
                }
            }

        }

        feedImgV.setImageResource(rid);

        List<Animator> animatorList = new ArrayList<Animator>();

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(chikenImgV,
                "alpha", 0f, 1f);
        objectAnimator.setDuration(1000);
        animatorList.add(objectAnimator);

        PropertyValuesHolder holderX = PropertyValuesHolder.ofFloat(
                "translationX", 0, 0);
        PropertyValuesHolder holderY = PropertyValuesHolder.ofFloat(
                "translationY", 0, 0);
        // chikenに対してholderX, holderY
        ObjectAnimator translationXYAnimator = ObjectAnimator
                .ofPropertyValuesHolder(chikenImgV, holderX, holderY);

        // 7秒かけて実行させます
        translationXYAnimator.setDuration(7000);
        animatorList.add(translationXYAnimator);
        // alphaプロパティを0fから1fに変化させます
        // ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(feedImgV,
        // "alpha", 1f, 0f);
        // // 3秒かけて実行させます
        // objectAnimator.setDuration(4000);
        // animatorList.add(objectAnimator);
        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animatorList);
        // アニメーションスタート
        animatorSet.start();

        // 遅延させてeatingをfalseにする
        new Handler().postDelayed(delayFunc, 7000);
    }

    public void fadingAnimation(int rid) {

        feedImgV.setImageResource(rid);
        List<Animator> animatorList = new ArrayList<Animator>();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(feedImgV,
                "alpha", 1f, 0f);
        // 3秒かけて実行させます
        objectAnimator.setDuration(10000);
        animatorList.add(objectAnimator);
        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animatorList);
        // アニメーションスタート
        animatorSet.start();
    }

    // エサのアニメーション
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
        // // alphaプロパティを0fから1fに変化させます
        // ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(feedImgV,
        // "alpha", 1f, 0f);
        // // 3秒かけて実行させます
        // objectAnimator.setDuration(4000);
        // animatorList.add(objectAnimator);
        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animatorList);
        // アニメーションスタート
        animatorSet.start();

        // 遅延させてImageViewを消す
        // new Handler().postDelayed(delayFunc, 7000);
    }

    /**
     * エサを食べた後の処理
     */
    private final Runnable delayFunc = new Runnable() {
        @Override
        public void run() {

            DataManager.getInstance(Bleeding.this).saveBLEEDING_POINT(
                    BleedingPoint);
            DataManager.getInstance(Bleeding.this).saveTIME(newTime());

            // エサのイメージビューを消す
            feedImgV.setImageDrawable(null);
            // 0にしてエサの種類をなしにする
            eating = 0;

            // /////////////////////////////////////////////////////////////////////////////////
            // ひなの画像を元に戻す　BLEEDING_POINTが150以下の場合
            if (BleedingPoint < firstEvo) {
                if (DataManager.getInstance(Bleeding.this).loadEGG() == 1) {
                    chikenImgV.setImageResource(R.drawable.hina_front);
                } else if (DataManager.getInstance(Bleeding.this).loadEGG() == 2) {
                    chikenImgV.setImageResource(R.drawable.hina_front_uzu);
                } else if (DataManager.getInstance(Bleeding.this).loadEGG() == 3) {
                    chikenImgV.setImageResource(R.drawable.hina_front_rain);
                } else if (DataManager.getInstance(Bleeding.this).loadEGG() == 4) {
                    chikenImgV.setImageResource(R.drawable.hina_front_gold);
                }
                // チキンの画像を元に戻す

                // ///////////////////////////////////////////////////////////////////////
                // 150
            } else if (BleedingPoint >= firstEvo) {

                for (int i = 0; i < 4; i++) {

                    // 卵の種類は何か
                    if (DataManager.getInstance(Bleeding.this).loadEGG() == i + 1) {

                        if (DataManager.getInstance(Bleeding.this)
                                .loadTOTALGUTS() < firstChange) {
                            // ////////////////////////////////////////////////////////////////////////
                            if (BleedingPoint >= secondEvo) {
                                chikenImgV
                                        .setImageResource(maxBirdResorce[6 * i + 0]);// チキンの画像をセット
                                nowBirdFrontRescorce = maxBirdResorce[6 * i + 0];
                                nowBirdSideRescorce = maxBirdResorce[6 * i + 0];
                                DataManager.getInstance(Bleeding.this)
                                        .saveCHIKEN(7);
                                updateDB(6 * i + 0, 2);// データベースに保存
                            } else {
                                chikenImgV
                                        .setImageResource(birdSideResorce[6 * i + 0]);// チキンの画像をセット
                                DataManager.getInstance(Bleeding.this)
                                        .saveCHIKEN(1);
                                nowBirdFrontRescorce = birdFrontResorce[6 * i + 0];
                                nowBirdSideRescorce = birdSideResorce[6 * i + 0];
                                updateDB(6 * i + 0, 1);// データベースに保存
                            }

                        } else if (DataManager.getInstance(Bleeding.this)
                                .loadTOTALGUTS() < secontChange) {
                            if (BleedingPoint >= secondEvo) {
                                chikenImgV
                                        .setImageResource(maxBirdResorce[6 * i + 1]);// チキンの画像をセット
                                nowBirdFrontRescorce = maxBirdResorce[6 * i + 1];
                                nowBirdSideRescorce = maxBirdResorce[6 * i + 1];
                                DataManager.getInstance(Bleeding.this)
                                        .saveCHIKEN(8);
                                updateDB(6 * i + 1, 2);// データベースに保存
                            } else {
                                chikenImgV
                                        .setImageResource(birdSideResorce[6 * i + 1]);
                                DataManager.getInstance(Bleeding.this)
                                        .saveCHIKEN(2);
                                nowBirdFrontRescorce = birdFrontResorce[6 * i + 1];
                                nowBirdSideRescorce = birdSideResorce[6 * i + 1];
                                updateDB(6 * i + 1, 1);// データベースに保存
                            }

                        } else if (DataManager.getInstance(Bleeding.this)
                                .loadTOTALGUTS() < 4500) {
                            if (BleedingPoint >= secondEvo) {
                                chikenImgV
                                        .setImageResource(maxBirdResorce[6 * i + 2]);// チキンの画像をセット
                                nowBirdFrontRescorce = maxBirdResorce[6 * i + 2];
                                nowBirdSideRescorce = maxBirdResorce[6 * i + 2];
                                DataManager.getInstance(Bleeding.this)
                                        .saveCHIKEN(9);
                                updateDB(6 * i + 2, 2);// データベースに保存
                            } else {
                                chikenImgV
                                        .setImageResource(birdSideResorce[6 * i + 2]);
                                DataManager.getInstance(Bleeding.this)
                                        .saveCHIKEN(3);
                                nowBirdFrontRescorce = birdFrontResorce[6 * i + 2];
                                nowBirdSideRescorce = birdSideResorce[6 * i + 2];
                                updateDB(6 * i + 2, 1);// データベースに保存
                            }

                        } else if (DataManager.getInstance(Bleeding.this)
                                .loadTOTALGUTS() < 6000) {
                            if (BleedingPoint >= secondEvo) {
                                chikenImgV
                                        .setImageResource(maxBirdResorce[6 * i + 3]);// チキンの画像をセット
                                nowBirdFrontRescorce = maxBirdResorce[6 * i + 3];
                                nowBirdSideRescorce = maxBirdResorce[6 * i + 3];
                                DataManager.getInstance(Bleeding.this)
                                        .saveCHIKEN(10);
                                updateDB(6 * i + 3, 2);// データベースに保存
                            } else {
                                chikenImgV
                                        .setImageResource(birdSideResorce[6 * i + 3]);
                                DataManager.getInstance(Bleeding.this)
                                        .saveCHIKEN(4);
                                nowBirdFrontRescorce = birdFrontResorce[6 * i + 3];
                                nowBirdSideRescorce = birdSideResorce[6 * i + 3];
                                updateDB(6 * i + 3, 1);// データベースに保存
                            }

                        } else if (DataManager.getInstance(Bleeding.this)
                                .loadTOTALGUTS() < 7500) {
                            if (BleedingPoint >= secondEvo) {
                                chikenImgV
                                        .setImageResource(maxBirdResorce[6 * i + 4]);// チキンの画像をセット
                                nowBirdFrontRescorce = maxBirdResorce[6 * i + 4];
                                nowBirdSideRescorce = maxBirdResorce[6 * i + 4];
                                DataManager.getInstance(Bleeding.this)
                                        .saveCHIKEN(11);
                                updateDB(6 * i + 4, 2);// データベースに保存
                            } else {
                                chikenImgV
                                        .setImageResource(birdSideResorce[6 * i + 4]);
                                DataManager.getInstance(Bleeding.this)
                                        .saveCHIKEN(5);
                                nowBirdFrontRescorce = birdFrontResorce[6 * i + 4];
                                nowBirdSideRescorce = birdSideResorce[6 * i + 4];
                                updateDB(6 * i + 4, 1);// データベースに保存
                            }

                        } else if (DataManager.getInstance(Bleeding.this)
                                .loadTOTALGUTS() >= 7500) {
                            if (BleedingPoint >= secondEvo) {
                                chikenImgV
                                        .setImageResource(maxBirdResorce[6 * i + 5]);// チキンの画像をセット
                                nowBirdFrontRescorce = maxBirdResorce[6 * i + 5];
                                nowBirdSideRescorce = maxBirdResorce[6 * i + 5];
                                DataManager.getInstance(Bleeding.this)
                                        .saveCHIKEN(12);
                                updateDB(6 * i + 5, 2);// データベースに保存
                            } else {
                                chikenImgV
                                        .setImageResource(birdSideResorce[6 * i + 5]);
                                DataManager.getInstance(Bleeding.this)
                                        .saveCHIKEN(6);
                                nowBirdFrontRescorce = birdFrontResorce[6 * i + 5];
                                nowBirdSideRescorce = birdSideResorce[6 * i + 5];
                                updateDB(6 * i + 5, 1);// データベースに保存
                            }
                        }

                    }
                }

            }
        }
    };

    public void Animation1() {

        // AnimatorSetに渡すAnimatorのリスト
        List<Animator> animatorList = new ArrayList<Animator>();

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(chikenImgV,
                "alpha", 0f, 1f);
        objectAnimator.setDuration(1000);
        animatorList.add(objectAnimator);

        PropertyValuesHolder holderX = PropertyValuesHolder.ofFloat(
                "translationX", 0, -20);
        PropertyValuesHolder holderY = PropertyValuesHolder.ofFloat(
                "translationY", 0, -80);

        // chikenに対してholderX, holderY
        ObjectAnimator translationXYAnimator = ObjectAnimator
                .ofPropertyValuesHolder(chikenImgV, holderX, holderY);
        // 4秒かけて実行させます
        translationXYAnimator.setDuration(4000);

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
        translationXYAnimator2.setDuration(3000);

        // リストに追加します
        animatorList.add(translationXYAnimator2);

        PropertyValuesHolder holderX3 = PropertyValuesHolder.ofFloat(
                "translationX", -130, -100);
        PropertyValuesHolder holderY3 = PropertyValuesHolder.ofFloat(
                "translationY", -130, 0);
        ObjectAnimator translationXYAnimator3 = ObjectAnimator
                .ofPropertyValuesHolder(chikenImgV, holderX3, holderY3);
        translationXYAnimator3.setStartDelay(1000);
        translationXYAnimator3.setDuration(4000);
        animatorList.add(translationXYAnimator3);

        PropertyValuesHolder holderX4 = PropertyValuesHolder.ofFloat(
                "translationX", -100, 80);
        PropertyValuesHolder holderY4 = PropertyValuesHolder.ofFloat(
                "translationY", 0, 120);
        ObjectAnimator translationXYAnimator4 = ObjectAnimator
                .ofPropertyValuesHolder(chikenImgV, holderX4, holderY4);
        // エサを食べている間、アニメーションをストップ
        translationXYAnimator4.setStartDelay(2000);
        translationXYAnimator4.setDuration(4000);
        animatorList.add(translationXYAnimator4);

        PropertyValuesHolder holderX5 = PropertyValuesHolder.ofFloat(
                "translationX", 80, 0);
        PropertyValuesHolder holderY5 = PropertyValuesHolder.ofFloat(
                "translationY", 120, 0);
        ObjectAnimator translationXYAnimator5 = ObjectAnimator
                .ofPropertyValuesHolder(chikenImgV, holderX5, holderY5);
        // エサを食べている間、アニメーションをストップ
        translationXYAnimator5.setDuration(3000);
        animatorList.add(translationXYAnimator5);

        final AnimatorSet animatorSet = new AnimatorSet();

        // リストのAnimatorを順番に実行します
        animatorSet.playSequentially(animatorList);

        // アニメーションを開始します
        animatorSet.start();
    }

    public void Animation2() {
        // chikenImgV = (ImageView) findViewById(R.id.chiken);

        List<Animator> animatorList = new ArrayList<Animator>();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(chikenImgV,
                "alpha", 0f, 1f);
        objectAnimator.setDuration(1000);
        animatorList.add(objectAnimator);
        PropertyValuesHolder holderX = PropertyValuesHolder.ofFloat(
                "translationX", 0, -10);
        PropertyValuesHolder holderY = PropertyValuesHolder.ofFloat(
                "translationY", 0, 120);
        ObjectAnimator translationXYAnimator = ObjectAnimator
                .ofPropertyValuesHolder(chikenImgV, holderX, holderY);
        translationXYAnimator.setDuration(3000);
        animatorList.add(translationXYAnimator);

        PropertyValuesHolder holderX2 = PropertyValuesHolder.ofFloat(
                "translationX", -10, 110);
        PropertyValuesHolder holderY2 = PropertyValuesHolder.ofFloat(
                "translationY", 120, 30);
        ObjectAnimator translationXYAnimator2 = ObjectAnimator
                .ofPropertyValuesHolder(chikenImgV, holderX2, holderY2);
        translationXYAnimator2.setStartDelay(2000);
        translationXYAnimator2.setDuration(3000);
        animatorList.add(translationXYAnimator2);

        PropertyValuesHolder holderX3 = PropertyValuesHolder.ofFloat(
                "translationX", 110, -120);
        PropertyValuesHolder holderY3 = PropertyValuesHolder.ofFloat(
                "translationY", 30, -80);
        ObjectAnimator translationXYAnimator3 = ObjectAnimator
                .ofPropertyValuesHolder(chikenImgV, holderX3, holderY3);
        translationXYAnimator3.setDuration(4000);
        animatorList.add(translationXYAnimator3);

        PropertyValuesHolder holderX4 = PropertyValuesHolder.ofFloat(
                "translationX", -120, 40);
        PropertyValuesHolder holderY4 = PropertyValuesHolder.ofFloat(
                "translationY", -80, 40);
        ObjectAnimator translationXYAnimator4 = ObjectAnimator
                .ofPropertyValuesHolder(chikenImgV, holderX4, holderY4);
        translationXYAnimator4.setDuration(3000);
        animatorList.add(translationXYAnimator4);

        PropertyValuesHolder holderX5 = PropertyValuesHolder.ofFloat(
                "translationX", 40, 0);
        PropertyValuesHolder holderY5 = PropertyValuesHolder.ofFloat(
                "translationY", 40, 0);
        ObjectAnimator translationXYAnimator5 = ObjectAnimator
                .ofPropertyValuesHolder(chikenImgV, holderX5, holderY5);
        translationXYAnimator5.setStartDelay(2000);
        translationXYAnimator5.setDuration(4000);
        animatorList.add(translationXYAnimator5);

        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animatorList);
        animatorSet.start();
    }

    public void Animation3() {
        // chikenImgV = (ImageView) findViewById(R.id.chiken);

        List<Animator> animatorList = new ArrayList<Animator>();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(chikenImgV,
                "alpha", 0f, 1f);
        objectAnimator.setDuration(1000);
        animatorList.add(objectAnimator);
        PropertyValuesHolder holderX = PropertyValuesHolder.ofFloat(
                "translationX", 0, 80);
        PropertyValuesHolder holderY = PropertyValuesHolder.ofFloat(
                "translationY", 0, -40);
        ObjectAnimator translationXYAnimator = ObjectAnimator
                .ofPropertyValuesHolder(chikenImgV, holderX, holderY);
        translationXYAnimator.setStartDelay(2000);
        translationXYAnimator.setDuration(3000);
        animatorList.add(translationXYAnimator);

        PropertyValuesHolder holderX2 = PropertyValuesHolder.ofFloat(
                "translationX", 80, 40);
        PropertyValuesHolder holderY2 = PropertyValuesHolder.ofFloat(
                "translationY", -40, -80);
        ObjectAnimator translationXYAnimator2 = ObjectAnimator
                .ofPropertyValuesHolder(chikenImgV, holderX2, holderY2);
        translationXYAnimator2.setStartDelay(1000);
        translationXYAnimator2.setDuration(4000);
        animatorList.add(translationXYAnimator2);

        PropertyValuesHolder holderX3 = PropertyValuesHolder.ofFloat(
                "translationX", 40, 20);
        PropertyValuesHolder holderY3 = PropertyValuesHolder.ofFloat(
                "translationY", -80, -100);
        ObjectAnimator translationXYAnimator3 = ObjectAnimator
                .ofPropertyValuesHolder(chikenImgV, holderX3, holderY3);
        translationXYAnimator3.setDuration(3000);
        animatorList.add(translationXYAnimator3);

        PropertyValuesHolder holderX4 = PropertyValuesHolder.ofFloat(
                "translationX", 20, -60);
        PropertyValuesHolder holderY4 = PropertyValuesHolder.ofFloat(
                "translationY", -100, -20);
        ObjectAnimator translationXYAnimator4 = ObjectAnimator
                .ofPropertyValuesHolder(chikenImgV, holderX4, holderY4);
        translationXYAnimator4.setStartDelay(1000);
        translationXYAnimator4.setDuration(3000);
        animatorList.add(translationXYAnimator4);

        PropertyValuesHolder holderX5 = PropertyValuesHolder.ofFloat(
                "translationX", -60, 0);
        PropertyValuesHolder holderY5 = PropertyValuesHolder.ofFloat(
                "translationY", -20, 0);
        ObjectAnimator translationXYAnimator5 = ObjectAnimator
                .ofPropertyValuesHolder(chikenImgV, holderX5, holderY5);
        translationXYAnimator5.setDuration(4000);
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