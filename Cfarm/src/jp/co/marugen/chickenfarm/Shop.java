package jp.co.marugen.chickenfarm;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.audiofx.NoiseSuppressor;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.Toast;

public class Shop extends Activity implements OnClickListener {

    private int id = 0;
    private int price;

    private NumberPicker npIka;
    private NumberPicker npEbi;
    private NumberPicker npMaguro;
    private NumberPicker npSarmon;
    private NumberPicker npIkura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // フルスクリーン、タイトルバーの非表示
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        setContentView(R.layout.shop);

        // イメージボタン
        ImageButton buyBaitBtn = (ImageButton) findViewById(R.id.buy_bait);
        ImageButton buyEggBtn = (ImageButton) findViewById(R.id.buy_egg);
        ImageButton forwardChikenBtn = (ImageButton) findViewById(R.id.forward_chiken);
        ImageButton backBtn = (ImageButton) findViewById(R.id.back_bleed);

        // ボタンのクリックリスナーを設置
        buyBaitBtn.setOnClickListener(this);
        buyEggBtn.setOnClickListener(this);
        forwardChikenBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

        case R.id.buy_bait:
            buyBait();
            break;

        case R.id.buy_egg:
            buyEgg();
            break;

        case R.id.forward_chiken:
            forwardChiken();
            break;

        case R.id.back_bleed:
            Intent intent = new Intent(Shop.this, Bleeding.class); // 育成画面へ遷移
            startActivity(intent);
            break;
        }
    }

    private void buyBait() {

        final Dialog d = new Dialog(Shop.this);
        d.setTitle("えさ一覧 　所持CP:" + DataManager.getInstance(Shop.this).loadCP());
        d.setContentView(R.layout.buy_feed);

        // NumberPicker
        npIka = (NumberPicker) d.findViewById(R.id.np_ika);
        npEbi = (NumberPicker) d.findViewById(R.id.np_ebi);
        npMaguro = (NumberPicker) d.findViewById(R.id.np_maguro);
        npSarmon = (NumberPicker) d.findViewById(R.id.np_sarmon);
        npIkura = (NumberPicker) d.findViewById(R.id.np_ikura);

        // こうしないとキーボードが現れる。
        npIka.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        npEbi.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        npMaguro.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        npSarmon.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        npIkura.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        npIka.setMaxValue(10);
        npIka.setMinValue(0);
        npEbi.setMaxValue(10);
        npEbi.setMinValue(0);
        npMaguro.setMaxValue(10);
        npMaguro.setMinValue(0);
        npSarmon.setMaxValue(10);
        npSarmon.setMinValue(0);
        npIkura.setMaxValue(10);
        npIkura.setMinValue(0);

        // Button
        Button noBtn = (Button) d.findViewById(R.id.buy_feed_no_btn);
        Button yesBtn = (Button) d.findViewById(R.id.buy_feed_yes_btn);
        noBtn.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        yesBtn.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                int cp = DataManager.getInstance(Shop.this).loadCP();
                int totalPrice = npIka.getValue() * 10 + npEbi.getValue() * 50
                        + npMaguro.getValue() * 100 + npSarmon.getValue() * 300
                        + npIkura.getValue() * 500;
                if (totalPrice < cp) {

                    DataManager.getInstance(Shop.this).saveCP(cp - totalPrice);

                    // いかの個数を追加して保存
                    DataManager.getInstance(Shop.this).saveIKA(
                            DataManager.getInstance(Shop.this).loadIKA()
                                    + npIka.getValue());
                    // えびの個数を追加して保存
                    DataManager.getInstance(Shop.this).saveEBI(
                            DataManager.getInstance(Shop.this).loadEBI()
                                    + npEbi.getValue());
                    // まぐろの個数を追加して保存
                    DataManager.getInstance(Shop.this).saveMAGURO(
                            DataManager.getInstance(Shop.this).loadMAGURO()
                                    + npMaguro.getValue());
                    // サーモンの個数を追加して保存
                    DataManager.getInstance(Shop.this).saveSAMON(
                            DataManager.getInstance(Shop.this).loadSAMON()
                                    + npSarmon.getValue());
                    // いくらの個数を追加して保存
                    DataManager.getInstance(Shop.this).saveIKURA(
                            DataManager.getInstance(Shop.this).loadIKURA()
                                    + npIkura.getValue());

                    Toast.makeText(Shop.this, "えさを買いました", Toast.LENGTH_SHORT)
                            .show();
                    d.dismiss();
                } else {
                    Toast.makeText(Shop.this, "CPが足りません", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
        d.show();
    }

    public void buyEgg() {

        if (DataManager.getInstance(Shop.this).loadEGG() == 0) {

            // CPのデータ取得
            price = DataManager.getInstance(Shop.this).loadCP();

            // 選択項目を準備する。
            final String str_items[] = new String[] { "ふつうエッグ　100CP",
                    "しましまエッグ　1500CP", "にじいろエッグ　8000CP", "きんいろエッグ　20000CP" };

            new AlertDialog.Builder(Shop.this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle(
                            "エッグ一覧  所持CP:"
                                    + DataManager.getInstance(Shop.this)
                                            .loadCP())

                    .setSingleChoiceItems(str_items, 0,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                        int whichButton) {
                                    // priceの値を保存
                                    DataManager.getInstance(Shop.this).saveCP(
                                            price);
                                    // ⇒アイテムを選択した時のイベント処理
                                    Toast.makeText(Shop.this,
                                            str_items[whichButton] + "にする",
                                            Toast.LENGTH_SHORT).show();

                                    id = whichButton;
                                }
                            })
                    .setPositiveButton("買う",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                        int whichButton) {
                                    // priceの値を保存
                                    DataManager.getInstance(Shop.this).saveCP(
                                            price);
                                    // ⇒OKボタンを押下した時のイベント処理
                                    // 【NOTE】
                                    // whichButtonには選択したアイテムのインデックスが入っているわけでは
                                    // ないので注意

                                    if (id == 0) {
                                        if (DataManager.getInstance(Shop.this)
                                                .loadCP() < 100) {
                                            Toast.makeText(Shop.this,
                                                    "CPが足りません",
                                                    Toast.LENGTH_SHORT).show();
                                        } else {
                                            price -= 100;
                                            // priceの値を保存
                                            DataManager.getInstance(Shop.this)
                                                    .saveCP(price);
                                            DataManager.getInstance(Shop.this)
                                                    .saveEGG(1);
                                            DataManager.getInstance(Shop.this)
                                                    .saveBLEEDING_POINT(0);
                                            DataManager.getInstance(Shop.this)
                                                    .saveTOTALGUTS(0);
                                            Toast.makeText(Shop.this,
                                                    "エッグを買いました",
                                                    Toast.LENGTH_SHORT).show();
                                            // 卵を買った時間を保存
                                            DataManager.getInstance(Shop.this)
                                                    .saveTIME(
                                                            Bleeding.newTime());
                                            DataManager
                                                    .getInstance(Shop.this)
                                                    .saveFRONTCHIKEN(
                                                            R.drawable.egg_nomal);
                                            DataManager
                                                    .getInstance(Shop.this)
                                                    .saveSIDECHIKEN(
                                                            R.drawable.egg_nomal);
                                        }
                                    } else if (id == 1) {
                                        if (DataManager.getInstance(Shop.this)
                                                .loadCP() < 1500) {
                                            Toast.makeText(Shop.this,
                                                    "CPが足りません",
                                                    Toast.LENGTH_SHORT).show();
                                        } else {
                                            price -= 1500;
                                            // priceの値を保存
                                            DataManager.getInstance(Shop.this)
                                                    .saveCP(price);
                                            DataManager.getInstance(Shop.this)
                                                    .saveEGG(2);
                                            DataManager.getInstance(Shop.this)
                                                    .saveBLEEDING_POINT(0);
                                            DataManager.getInstance(Shop.this)
                                                    .saveTOTALGUTS(0);
                                            Toast.makeText(Shop.this,
                                                    "エッグを買いました",
                                                    Toast.LENGTH_SHORT).show();
                                            // 卵を買った時間を保存
                                            DataManager.getInstance(Shop.this)
                                                    .saveTIME(
                                                            Bleeding.newTime());
                                            DataManager
                                                    .getInstance(Shop.this)
                                                    .saveFRONTCHIKEN(
                                                            R.drawable.egg_green);
                                            DataManager
                                                    .getInstance(Shop.this)
                                                    .saveSIDECHIKEN(
                                                            R.drawable.egg_green);
                                        }
                                    } else if (id == 2) {
                                        if (DataManager.getInstance(Shop.this)
                                                .loadCP() < 8000) {
                                            Toast.makeText(Shop.this,
                                                    "CPが足りません",
                                                    Toast.LENGTH_SHORT).show();
                                        } else {
                                            price -= 8000;
                                            // priceの値を保存
                                            DataManager.getInstance(Shop.this)
                                                    .saveCP(price);
                                            DataManager.getInstance(Shop.this)
                                                    .saveEGG(3);
                                            DataManager.getInstance(Shop.this)
                                                    .saveBLEEDING_POINT(0);
                                            DataManager.getInstance(Shop.this)
                                                    .saveTOTALGUTS(0);
                                            Toast.makeText(Shop.this,
                                                    "エッグを買いました",
                                                    Toast.LENGTH_SHORT).show();
                                            // 卵を買った時間を保存
                                            DataManager.getInstance(Shop.this)
                                                    .saveTIME(
                                                            Bleeding.newTime());
                                            DataManager
                                                    .getInstance(Shop.this)
                                                    .saveFRONTCHIKEN(
                                                            R.drawable.egg_rain);
                                            DataManager
                                                    .getInstance(Shop.this)
                                                    .saveSIDECHIKEN(
                                                            R.drawable.egg_rain);
                                        }
                                    } else if (id == 3) {
                                        if (DataManager.getInstance(Shop.this)
                                                .loadCP() < 20000) {
                                            Toast.makeText(Shop.this,
                                                    "CPが足りません",
                                                    Toast.LENGTH_SHORT).show();
                                        } else {
                                            price -= 20000;
                                            // priceの値を保存
                                            DataManager.getInstance(Shop.this)
                                                    .saveCP(price);
                                            DataManager.getInstance(Shop.this)
                                                    .saveEGG(4);
                                            DataManager.getInstance(Shop.this)
                                                    .saveBLEEDING_POINT(0);
                                            DataManager.getInstance(Shop.this)
                                                    .saveTOTALGUTS(0);
                                            Toast.makeText(Shop.this,
                                                    "エッグを買いました",
                                                    Toast.LENGTH_SHORT).show();
                                            // 卵を買った時間を保存
                                            DataManager.getInstance(Shop.this)
                                                    .saveTIME(
                                                            Bleeding.newTime());
                                            DataManager
                                                    .getInstance(Shop.this)
                                                    .saveFRONTCHIKEN(
                                                            R.drawable.egg_gold);
                                            DataManager
                                                    .getInstance(Shop.this)
                                                    .saveSIDECHIKEN(
                                                            R.drawable.egg_gold);
                                        }
                                    }
                                }
                            })
                    .setNegativeButton("やめる",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                        int whichButton) {
                                    // ⇒キャンセルボタンを押下した時のイベント処理
                                    // 【NOTE】
                                    // whichButtonには選択したアイテムのインデックスが入っているわけでは
                                    // ないので注意
                                    Toast.makeText(Shop.this, "やめました",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }).show();

        } else {
            Toast.makeText(Shop.this, "チキンがいるので買えません", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    public void forwardChiken() {

        // CPのデータ取得
        price = DataManager.getInstance(Shop.this).loadCP();

        if (DataManager.getInstance(Shop.this).loadFORWARD_JUDGE()) {
            // 選択項目を準備する。
            String[] str_items = { "する", "やめる" };

            new AlertDialog.Builder(Shop.this).setTitle("出荷しますか？")
                    .setItems(str_items, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // 選択したアイテムの番号(0～)がwhichに格納される
                            switch (which) {
                            case 0:
                                // 選択１
                                if (Egg_type() == 1) {
                                    if (Chiken_type() == 1) {   //にわとり
                                        price += 100;
                                    } else if (Chiken_type() == 2) {    //ハト
                                        price += 200;
                                    } else if (Chiken_type() == 3) {    //スズメ
                                        price += 300;
                                    } else if (Chiken_type() == 4) {    //インコ
                                        price += 400;
                                    } else if (Chiken_type() == 5) {    //カモメ
                                        price += 500;
                                    } else if (Chiken_type() == 6) {    //メジロ
                                        price += 600;
                                    } else if (Chiken_type() == 7) {    //にわとりキング
                                        price += 1000;
                                    } else if (Chiken_type() == 8) {    //ハトキング
                                        price += 1200;
                                    } else if (Chiken_type() == 9) {    //スズメキング
                                        price += 1400;
                                    } else if (Chiken_type() == 10) {   //インコキング
                                        price += 1600;
                                    } else if (Chiken_type() == 11) {   //カモメキング
                                        price += 1800;
                                    } else if (Chiken_type() == 12) {   //メジロキング
                                        price += 2000;
                                    } 
                                } else if (Egg_type() == 2) {
                                    if (Chiken_type() == 1) {   //タカ
                                        price += 1500;
                                    } else if (Chiken_type() == 2) {    //キュウカンチョウ
                                        price += 1650;
                                    } else if (Chiken_type() == 3) {    //ウコッケイ
                                        price += 1800;
                                    } else if (Chiken_type() == 4) {    //オウム
                                        price += 1950;
                                    } else if (Chiken_type() == 5) {    //ツル
                                        price += 2100;
                                    } else if (Chiken_type() == 6) {    //フクロウ
                                        price += 2250;
                                    } else if (Chiken_type() == 7) {    //タカキング
                                        price += 3000;
                                    } else if (Chiken_type() == 8) {    //キュウカンチョウキング
                                        price += 3200;
                                    } else if (Chiken_type() == 9) {    //ウコッケイキング
                                        price += 3500;
                                    } else if (Chiken_type() == 10) {   //オウムキング
                                        price += 4000;
                                    } else if (Chiken_type() == 11) {   //ツルキング
                                        price += 4500;
                                    } else if (Chiken_type() == 12) {   //フクロウキング
                                        price += 5000;
                                    }
                                } else if (Egg_type() == 3) {
                                    if (Chiken_type() == 1) {   //ペリカン
                                        price += 5000;
                                    } else if (Chiken_type() == 2) {    //キジ
                                        price += 6000;
                                    } else if (Chiken_type() == 3) {    //オニオオハシ
                                        price += 7500;
                                    } else if (Chiken_type() == 4) {    //ハゲワシ
                                        price += 5000;
                                    } else if (Chiken_type() == 5) {    //フラミンゴ
                                        price += 9000;
                                    } else if (Chiken_type() == 6) {    //ペンギン
                                        price += 12000;
                                    } else if (Chiken_type() == 7) {    //ペリカンキング
                                        price += 7500;
                                    } else if (Chiken_type() == 8) {    //キジキング
                                        price += 9000;
                                    } else if (Chiken_type() == 9) {    //オニオオハシキング
                                        price += 11000;
                                    } else if (Chiken_type() == 10) {   //ハゲワシキング
                                        price += 7500;
                                    } else if (Chiken_type() == 11) {   //フラミンゴキング
                                        price += 15000;
                                    } else if (Chiken_type() == 12) {   //ペンギンキング
                                        price += 20000;
                                    }
                                } else if (Egg_type() == 4) {
                                    if (Chiken_type() == 1) {   //ダチョウ
                                        price += 20000;
                                    } else if (Chiken_type() == 2) {    //イワトビペンギン
                                        price += 25000;
                                    } else if (Chiken_type() == 3) {    //ヒクイドリ
                                        price += 30000;
                                    } else if (Chiken_type() == 4) {    //トキ
                                        price += 50000;
                                    } else if (Chiken_type() == 5) {    //カラアゲドリ
                                        price += 200;
                                    } else if (Chiken_type() == 6) {    //ツイッチョ
                                        price += 0;
                                    } else if (Chiken_type() == 7) {    //ダチョウキング
                                        price += 25000;
                                    } else if (Chiken_type() == 8) {    //イワトビペンギンキング
                                        price += 30000;
                                    } else if (Chiken_type() == 9) {    //ヒクイドリキング
                                        price += 35000;
                                    } else if (Chiken_type() == 10) {   //トキキング
                                        price += 70000;
                                    } else if (Chiken_type() == 11) {   //カラアゲキング
                                        price += 200;
                                    } else if (Chiken_type() == 12) {   //ツイッチョキング
                                        price += 0;
                                    }

                                }

                                Toast.makeText(
                                        Shop.this,
                                        "チキンを出荷して"
                                                + (price - DataManager
                                                        .getInstance(Shop.this)
                                                        .loadCP())
                                                + "CPを獲得しました。",
                                        Toast.LENGTH_SHORT).show();

                                // priceの値を保存
                                DataManager.getInstance(Shop.this)
                                        .saveCP(price);
                                DataManager.getInstance(Shop.this)
                                        .saveFORWARD_JUDGE(false);
                                DataManager.getInstance(Shop.this).saveEGG(0);
                                DataManager.getInstance(Shop.this)
                                        .saveBLEEDING_POINT(0);
                                DataManager.getInstance(Shop.this)
                                        .saveTOTALGUTS(0);

                                break;
                            case 1:
                                // 選択２
                                Toast.makeText(Shop.this, "やめました",
                                        Toast.LENGTH_SHORT).show();
                                break;
                            }
                        }
                    }).show();

        } else {
            Toast.makeText(Shop.this, "まだ出荷できません", Toast.LENGTH_SHORT).show();
        }

    }

    // 卵の種類
    public int Egg_type() {
        return DataManager.getInstance(this).loadEGG();
    }

    // チキンの種類
    public int Chiken_type() {
        return DataManager.getInstance(this).loadCHIKEN();
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
