package jp.co.marugen.chickenfarm;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

public class Shop extends Activity implements OnClickListener {

    private int id = 0;
    private int price;

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

        // CPのデータ取得
        price = DataManager.getInstance(Shop.this).loadCP();

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

    public void buyBait() {

        // 選択項目を準備する。
        String[] strItems = { "いか　10CP", "えび　20CP", "まぐろ　30CP", "サーモン　40CP",
                "いくら　50CP"};

        new AlertDialog.Builder(Shop.this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("えさ一覧")
                .setSingleChoiceItems(strItems, 0,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                    int whichButton) {
                                // ⇒アイテムを選択した時のイベント処理
                                // Toast.makeText(
                                // Shop.this,
                                // str_items[whichButton]+ "にする",
                                // Toast.LENGTH_SHORT).show();
                                //
                                id = whichButton;
                            }
                        })
                .setPositiveButton("買う", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // priceの値を保存
                        DataManager.getInstance(Shop.this).saveCP(price);
                        // ⇒OKボタンを押下した時のイベント処理
                        // 【NOTE】
                        // whichButtonには選択したアイテムのインデックスが入っているわけでは
                        // ないので注意
                        if (id == 0) {
                            if (DataManager.getInstance(Shop.this).loadCP() < 10) {
                                Toast.makeText(Shop.this, "CPが足りません",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                price -= 10;
                                // priceの値を保存
                                DataManager.getInstance(Shop.this)
                                        .saveCP(price);

                                // いかの個数を追加して保存
                                DataManager.getInstance(Shop.this).saveIKA(
                                        DataManager.getInstance(Shop.this)
                                                .loadIKA() + 1);
                                Toast.makeText(Shop.this, "えさを買いました"
                                        , Toast.LENGTH_SHORT).show();

                            }

                        } else if (id == 1) {
                            if (DataManager.getInstance(Shop.this).loadCP() < 20) {
                                Toast.makeText(Shop.this, "CPが足りません",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                price -= 20;
                                // priceの値を保存
                                DataManager.getInstance(Shop.this)
                                        .saveCP(price);

                                // えびの個数を追加して保存
                                DataManager.getInstance(Shop.this).saveEBI(
                                        DataManager.getInstance(Shop.this)
                                                .loadEBI() + 1);
                                Toast.makeText(Shop.this, "えさを買いました"
                                        , Toast.LENGTH_SHORT).show();
                            }

                        } else if (id == 2) {
                            if (DataManager.getInstance(Shop.this).loadCP() < 30) {
                                Toast.makeText(Shop.this, "CPが足りません",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                price -= 30;
                                // priceの値を保存
                                DataManager.getInstance(Shop.this)
                                        .saveCP(price);

                                // まぐろの個数を追加して保存
                                DataManager.getInstance(Shop.this).saveMAGURO(
                                        DataManager.getInstance(Shop.this)
                                                .loadMAGURO() + 1);
                                Toast.makeText(Shop.this, "えさを買いました"
                                        , Toast.LENGTH_SHORT).show();
                            }

                        } else if (id == 3) {
                            if (DataManager.getInstance(Shop.this).loadCP() < 40) {
                                Toast.makeText(Shop.this, "CPが足りません",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                price -= 40;
                                // priceの値を保存
                                DataManager.getInstance(Shop.this)
                                        .saveCP(price);

                                // サーモンの個数を追加して保存
                                DataManager.getInstance(Shop.this).saveSAMON(
                                        DataManager.getInstance(Shop.this)
                                                .loadSAMON() + 1);
                                Toast.makeText(Shop.this, "えさを買いました"
                                        , Toast.LENGTH_SHORT).show();
                            }

                        } else if (id == 4) {
                            if (DataManager.getInstance(Shop.this).loadCP() < 50) {
                                Toast.makeText(Shop.this, "CPが足りません",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                price -= 50;
                                // priceの値を保存
                                DataManager.getInstance(Shop.this)
                                        .saveCP(price);

                                // いくらの個数を追加して保存
                                DataManager.getInstance(Shop.this).saveIKURA(
                                        DataManager.getInstance(Shop.this)
                                                .loadIKURA() + 1);
                                Toast.makeText(Shop.this, "えさを買いました"
                                        , Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(Shop.this, "やめました"
                                // +
                                // Integer.toString(whichButton)
                                        , Toast.LENGTH_SHORT).show();
                            }
                        }).show();

    }

    public void buyEgg() {

        if (DataManager.getInstance(Shop.this).loadEGG() == 0) {
            // 選択項目を準備する。
            final String str_items[] = new String[] { "ふつうエッグ　100CP",
                    "しましまエッグ　1000CP", "にじいろエッグ　3000CP", "きんいろエッグ　10000CP" };

            new AlertDialog.Builder(Shop.this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("エッグ一覧")
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
                                                    .saveTIME(-1);
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
                                        }
                                    } else if (id == 1) {
                                        if (DataManager.getInstance(Shop.this)
                                                .loadCP() < 1000) {
                                            Toast.makeText(Shop.this,
                                                    "CPが足りません",
                                                    Toast.LENGTH_SHORT).show();
                                        } else {
                                            price -= 1000;
                                            // priceの値を保存
                                            DataManager.getInstance(Shop.this)
                                                    .saveCP(price);
                                            DataManager.getInstance(Shop.this)
                                                    .saveEGG(2);
                                            DataManager.getInstance(Shop.this)
                                                    .saveTIME(-1);
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
                                        }
                                    } else if (id == 2) {
                                        if (DataManager.getInstance(Shop.this)
                                                .loadCP() < 3000) {
                                            Toast.makeText(Shop.this,
                                                    "CPが足りません",
                                                    Toast.LENGTH_SHORT).show();
                                        } else {
                                            price -= 3000;
                                            // priceの値を保存
                                            DataManager.getInstance(Shop.this)
                                                    .saveCP(price);
                                            DataManager.getInstance(Shop.this)
                                                    .saveEGG(3);
                                            DataManager.getInstance(Shop.this)
                                                    .saveTIME(-1);
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
                                        }
                                    } else if (id == 3) {
                                        if (DataManager.getInstance(Shop.this)
                                                .loadCP() < 10000) {
                                            Toast.makeText(Shop.this,
                                                    "CPが足りません",
                                                    Toast.LENGTH_SHORT).show();
                                        } else {
                                            price -= 10000;
                                            // priceの値を保存
                                            DataManager.getInstance(Shop.this)
                                                    .saveCP(price);
                                            DataManager.getInstance(Shop.this)
                                                    .saveEGG(4);
                                            DataManager.getInstance(Shop.this)
                                                    .saveTIME(-1);
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
                                    if (Chiken_type() == 1) {
                                        price += 500;
                                    } else if (Chiken_type() == 2) {
                                        price += 600;
                                    } else if (Chiken_type() == 3) {
                                        price += 700;
                                    } else if (Chiken_type() == 4) {
                                        price += 800;
                                    } else if (Chiken_type() == 5) {
                                        price += 900;
                                    } else if (Chiken_type() == 6) {
                                        price += 1000;
                                    }
                                } else if (Egg_type() == 2) {
                                    if (Chiken_type() == 1) {
                                        price += 1500;
                                    } else if (Chiken_type() == 2) {
                                        price += 1600;
                                    } else if (Chiken_type() == 3) {
                                        price += 1700;
                                    } else if (Chiken_type() == 4) {
                                        price += 1800;
                                    } else if (Chiken_type() == 5) {
                                        price += 1900;
                                    } else if (Chiken_type() == 6) {
                                        price += 2000;
                                    }
                                } else if (Egg_type() == 3) {
                                    if (Chiken_type() == 1) {
                                        price += 3000;
                                    } else if (Chiken_type() == 2) {
                                        price += 4000;
                                    } else if (Chiken_type() == 3) {
                                        price += 4300;
                                    } else if (Chiken_type() == 4) {
                                        price += 4600;
                                    } else if (Chiken_type() == 5) {
                                        price += 5000;
                                    } else if (Chiken_type() == 6) {
                                        price += 6000;
                                    }
                                } else if (Egg_type() == 4) {
                                    if (Chiken_type() == 1) {
                                        price += 8000;
                                    } else if (Chiken_type() == 2) {
                                        price += 10000;
                                    } else if (Chiken_type() == 3) {
                                        price += 11500;
                                    } else if (Chiken_type() == 4) {
                                        price += 13000;
                                    } else if (Chiken_type() == 5) {
                                        price += 15000;
                                    } else if (Chiken_type() == 6) {
                                        price += 20000;
                                    }
                                }

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

                                Toast.makeText(Shop.this,
                                        "チキンを出荷して" + price + "CPを獲得しました。",
                                        Toast.LENGTH_SHORT).show();
                                
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
