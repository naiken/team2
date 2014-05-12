package jp.co.marugen.chickenfarm;

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

public class ChikenActivity extends Activity implements OnClickListener {

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

}
