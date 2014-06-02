package jp.co.marugen.chickenfarm.garbagepick;

import jp.co.marugen.chickenfarm.Bleeding;
import jp.co.marugen.chickenfarm.DataManager;
import jp.co.marugen.chickenfarm.R;
import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class GarbageResult extends Activity implements OnClickListener {

    private int obtain_cp;
    private int obtain_guts;
    private int total_guts;
    private TextView obtain_cp_text;
    private TextView obtain_guts_text;
    private TextView total_guts_text;
    private ImageView chiken_rank;
    private ImageButton back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // フルスクリーン、タイトルバーの非表示
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        setContentView(R.layout.training_result3);

        back_btn = (ImageButton) findViewById(R.id.backbutton3);
        back_btn.setOnClickListener(this);

        /**
         * 獲得CPの表示 獲得ガッツの表示 累計ガッツの表示
         */
        obtain_cp = DataManager.getInstance(this).loadGET_CP();
        obtain_guts = DataManager.getInstance(this).loadGUTS();
        total_guts = DataManager.getInstance(this).loadGUTS()
                + DataManager.getInstance(this).loadTOTALGUTS();

        // 累計ガッツの保存
        DataManager.getInstance(this).saveTOTALGUTS(total_guts);
        // 獲得CPと所持CPの合計を保存
        DataManager.getInstance(this).saveCP(
                obtain_cp + DataManager.getInstance(this).loadCP());

        // テキストビューの取得
        obtain_cp_text = (TextView) findViewById(R.id.obtain_cp3);
        obtain_guts_text = (TextView) findViewById(R.id.obtain_guts3);
        total_guts_text = (TextView) findViewById(R.id.total_guts3);

        // テキストサイズの変更
        obtain_cp_text.setTextSize(30);
        obtain_guts_text.setTextSize(30);
        total_guts_text.setTextSize(30);

        // テキストビューへ値の入れる
        String view_obtain_cp = String.valueOf(obtain_cp);
        obtain_cp_text.setText(view_obtain_cp);
        String view_obtain_guts = String.valueOf(obtain_guts);
        obtain_guts_text.setText(view_obtain_guts);
        String view_total_guts = String.valueOf(total_guts);
        total_guts_text.setText(view_total_guts);

        // イメージボタンの生成
        chiken_rank = (ImageView) findViewById(R.id.chiken_rank);
        back_btn = (ImageButton) findViewById(R.id.backbutton3);
        
        // クリックリスナーを登録
        back_btn.setOnClickListener(this);

        if (total_guts <= 999) {
            chiken_rank.setImageResource(R.drawable.chiken_rank1);
        } else if (total_guts >= 1000 && total_guts <= 1999) {
            chiken_rank.setImageResource(R.drawable.chiken_rank2);
        } else if (total_guts >= 2000 && total_guts <= 2999) {
            chiken_rank.setImageResource(R.drawable.chiken_rank3);
        } else if (total_guts >= 3000 && total_guts <= 3999) {
            chiken_rank.setImageResource(R.drawable.chiken_rank4);
        } else if (total_guts >= 4000 && total_guts <= 4999) {
            chiken_rank.setImageResource(R.drawable.chiken_rank5);
        } else if (total_guts >= 5000 && total_guts <= 5999) {
            chiken_rank.setImageResource(R.drawable.chiken_rank6);
        } else if (total_guts >= 6000 && total_guts <= 6999) {
            chiken_rank.setImageResource(R.drawable.chiken_rank7);
        } else if (total_guts >= 7000) {
            chiken_rank.setImageResource(R.drawable.chiken_rank8);
        } else {
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    // クリック時イベント
    public void onClick(View v) {
        Intent intent = new Intent(this, Bleeding.class);
        startActivity(intent);
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
