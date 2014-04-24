package jp.co.marugen.bublecrash;

import jp.co.marugen.sql.Records;
import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // フルスクリーン、タイトルバーの非表示
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        setContentView(R.layout.activity_main);

        Button tutorialBtn = (Button) findViewById(R.id.tutorial_btn);
        Button playBtn = (Button) findViewById(R.id.play_btn);
        Button recordsBtn = (Button) findViewById(R.id.records_btn);
        tutorialBtn.setOnClickListener(this);
        playBtn.setOnClickListener(this);
        recordsBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        case R.id.tutorial_btn:
            // インテントのインスタンス生成
            Intent intent = new Intent(this, Tutorial.class);
            // 次画面のアクティビティ起動F
            startActivity(intent);
            break;
        case R.id.play_btn:
            // インテントのインスタンス生成
            Intent intent2 = new Intent(this, Game.class);
            // 次画面のアクティビティ起動F
            startActivity(intent2);
            break;
        case R.id.records_btn:
            // インテントのインスタンス生成
            Intent intent3 = new Intent(this, Records.class);
            // 次画面のアクティビティ起動F
            startActivity(intent3);
            break;
        }
    }

    // こうしないと、タイトルとランキングを行き来きしたときに、
    // DBによけいななスコアが書き込まれる
    @Override
    public void onPause() {
        super.onPause();
        MyRenderer.mScore = 0;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {// キーが押された
            switch (event.getKeyCode()) {
            case KeyEvent.KEYCODE_BACK: // Backボタン
                // Game.this.finish();
                MainActivity.this.moveTaskToBack(true);
                return false;
            default:
            }
        }
        return super.dispatchKeyEvent(event);
    }
}