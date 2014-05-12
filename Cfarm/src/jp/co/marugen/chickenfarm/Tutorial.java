package jp.co.marugen.chickenfarm;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

public class Tutorial extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // フルスクリーン、タイトルバーの非表示
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        setContentView(R.layout.tutorial);

        // たまごを買おうへの画面遷移
        ImageButton btn = (ImageButton) findViewById(R.id.button1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tutorial.this, EggScreen.class);
                startActivity(intent);
            }
        });

        // エサをあげようへの画面遷移
        ImageButton btn2 = (ImageButton) findViewById(R.id.button2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tutorial.this, BleedScreen.class);
                startActivity(intent);
            }
        });

        // トレーニングさせようへの画面遷移
        ImageButton btn3 = (ImageButton) findViewById(R.id.button3);

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tutorial.this, TraningScreen.class);
                startActivity(intent);
            }
        });

        // 出荷させようへの画面遷移
        ImageButton btn4 = (ImageButton) findViewById(R.id.button4);

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tutorial.this, GetoutScreen.class);
                startActivity(intent);
            }
        });

        // 図鑑をコンプリートさせようへの画面遷移
        ImageButton btn5 = (ImageButton) findViewById(R.id.button5);

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tutorial.this, GuideScreen.class);
                startActivity(intent);
            }
        });

        // メイン画面へ画面遷移
        ImageButton btn6 = (ImageButton) findViewById(R.id.button6);

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tutorial.this, GuideScreen.class);
                startActivity(intent);
            }
        });

    }

}
