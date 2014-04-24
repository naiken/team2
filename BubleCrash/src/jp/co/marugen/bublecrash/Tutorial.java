package jp.co.marugen.bublecrash;

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

public class Tutorial extends Activity implements OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // フルスクリーン、タイトルバーの非表示
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        setContentView(R.layout.tutorial);

        Button backBtn = (Button) findViewById(R.id.back_btn);
        backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        case R.id.back_btn:
            // インテントのインスタンス生成
            Intent intent = new Intent(this, MainActivity.class);
            // 次画面のアクティビティ起動F
            startActivity(intent);
            break;
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {// キーが押された
            switch (event.getKeyCode()) {
            case KeyEvent.KEYCODE_BACK: // Backボタン
                // Game.this.finish();
                Tutorial.this.moveTaskToBack(true);
                return false;
            default:
            }
        }
        return super.dispatchKeyEvent(event);
    }

}
