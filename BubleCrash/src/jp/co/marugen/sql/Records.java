package jp.co.marugen.sql;

import static android.provider.BaseColumns._ID;
import static jp.co.marugen.sql.Constants.SCORE_1;
import static jp.co.marugen.sql.Constants.SCORE_2;
import static jp.co.marugen.sql.Constants.SCORE_3;
import static jp.co.marugen.sql.Constants.SCORE_4;
import static jp.co.marugen.sql.Constants.SCORE_5;
import static jp.co.marugen.sql.Constants.TABLE_NAME;

import java.util.ArrayList;
import java.util.Collections;

import jp.co.marugen.bublecrash.Game;
import jp.co.marugen.bublecrash.MainActivity;
import jp.co.marugen.bublecrash.MyRenderer;
import jp.co.marugen.bublecrash.R;
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
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Records extends Activity implements OnClickListener {

    ArrayList<Integer> dataArray = new ArrayList<Integer>();

    private DBAdapter helper;
    private TextView scoreNow;
    private TextView score1;
    private TextView score2;
    private TextView score3;
    private TextView score4;
    private TextView score5;

    private static String[] FROM = { _ID, SCORE_1, SCORE_2, SCORE_3, SCORE_4,
            SCORE_5 };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // フルスクリーン、タイトルバーの非表示
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        setContentView(R.layout.records);

        // データベースのセット
        helper = new DBAdapter(this);

        Button replayBtn = (Button) findViewById(R.id.replay_btn);
        Button titleBtn = (Button) findViewById(R.id.title_btn);
        replayBtn.setOnClickListener(this);
        titleBtn.setOnClickListener(this);

        scoreNow = (TextView) findViewById(R.id.score_now);
        score1 = (TextView) findViewById(R.id.score1);
        score2 = (TextView) findViewById(R.id.score2);
        score3 = (TextView) findViewById(R.id.score3);
        score4 = (TextView) findViewById(R.id.score4);
        score5 = (TextView) findViewById(R.id.score5);

        // ///////// SQLiteに初期値を入力する/////////////////
        SharedPreferences preference = getSharedPreferences("Preference Name",
                MODE_PRIVATE);
        Editor editor = preference.edit();
        if (preference.getBoolean("preIni", false) == false) {
            // 初回起動時の処理
            SQLiteDatabase db = helper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(_ID, 0);
            values.put(SCORE_1, 0);
            values.put(SCORE_2, 0);
            values.put(SCORE_3, 0);
            values.put(SCORE_4, 0);
            values.put(SCORE_5, 0);
            db.insertOrThrow(TABLE_NAME, null, values);
            // プリファレンスの書き変え
            editor.putBoolean("preIni", true);
            editor.commit();

        } else {
            // 2回目以降の処理
        }

    }

    // データベースの更新処理
    public void onResume() {
        super.onResume();
        
        MyRenderer.canMoveRecordSceen = false;
        
        try {
            // 以下はデータベースの処理
            Cursor cursor = getData();
            showRank(cursor);
            cursor.close();
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

    // データベースから読み込んだデータを取得します．
    private void showRank(Cursor cursor) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        dataArray.clear();
        while (cursor.moveToNext()) {
            // 各列ごとにデータを取得する
            int id = cursor.getInt(0);
            int no1 = cursor.getInt(1);
            int no2 = cursor.getInt(2);
            int no3 = cursor.getInt(3);
            int no4 = cursor.getInt(4);
            int no5 = cursor.getInt(5);

            if (id == 0) {
                dataArray.add(no1);
                dataArray.add(no2);
                dataArray.add(no3);
                dataArray.add(no4);
                dataArray.add(no5);

                break;
            }
        }
        
        // 今のスコアを入れる
        dataArray.add(MyRenderer.mScore);
        // 昇順に並び替え
        Collections.sort(dataArray);
        // 降順に並び替え
        Collections.reverse(dataArray);
        // values.put(_ID, 0);
        values.put(SCORE_1, dataArray.get(0));
        values.put(SCORE_2, dataArray.get(1));
        values.put(SCORE_3, dataArray.get(2));
        values.put(SCORE_4, dataArray.get(3));
        values.put(SCORE_5, dataArray.get(4));
        db.update(TABLE_NAME, values, "_ID = 0", null);
        // db.insertOrThrow(TABLE_NAME, null, values);
        // db.delete(TABLE_NAME, null, null);
        
        scoreNow.setText("Score　" + String.valueOf(MyRenderer.mScore));
        score1.setText(String.valueOf(dataArray.get(0)));
        score2.setText(String.valueOf(dataArray.get(1)));
        score3.setText(String.valueOf(dataArray.get(2)));
        score4.setText(String.valueOf(dataArray.get(3)));
        score5.setText(String.valueOf(dataArray.get(4)));
        
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.replay_btn:
            // インテントのインスタンス生成
            Intent intent = new Intent(this, Game.class);
            // 次画面のアクティビティ起動
            startActivity(intent);
            
            break;
        case R.id.title_btn:
            // インテントのインスタンス生成
            Intent intent2 = new Intent(this, MainActivity.class);
            // 次画面のアクティビティ起動
            startActivity(intent2);
            
            break;
        }
    }
    
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {// キーが押された
            switch (event.getKeyCode()) {
            case KeyEvent.KEYCODE_BACK: // Backボタン
                // Game.this.finish();
                Records.this.moveTaskToBack(true);
                return false;
            default:
            }
        }
        return super.dispatchKeyEvent(event);
    }
}