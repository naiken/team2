package jp.co.marugen.chickenfarm;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ViewFlipper;

public class BleedScreen extends Activity implements View.OnTouchListener {

    private ViewFlipper viewFlipper;
    private float firstTouch;
    private boolean isFlip = false;

    // private ImageView firstImageView;
    // private ImageView secondImageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // フルスクリーン、タイトルバーの非表示
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        setContentView(R.layout.bleed_screen);

        // 遊び方に画面遷移
        ImageButton btn = (ImageButton) findViewById(R.id.button1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BleedScreen.this, Tutorial.class);
                startActivity(intent);
            }
        });

        viewFlipper = (ViewFlipper) findViewById(R.id.flipper);
        // firstImageView = (ImageView)findViewById(R.id.imageview_first);
        // secondImageView = (ImageView)findViewById(R.id.imageview_second);
        findViewById(R.id.layout_first).setOnTouchListener(this);
        findViewById(R.id.layout_second).setOnTouchListener(this);
        findViewById(R.id.layout_third).setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int x = (int) event.getRawX();
        switch (v.getId()) {
        case R.id.layout_first:
        case R.id.layout_second:
        case R.id.layout_third:
            switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                firstTouch = event.getRawX();
                return true;
            case MotionEvent.ACTION_MOVE:
                if (!isFlip) {
                    if (x - firstTouch > 50) {
                        isFlip = true;
                        viewFlipper.setInAnimation(AnimationUtils
                                .loadAnimation(this, R.anim.move_in_left));
                        viewFlipper.setOutAnimation(AnimationUtils
                                .loadAnimation(this, R.anim.move_out_right));
                        viewFlipper.showNext();
                    } else if (firstTouch - x > 50) {
                        isFlip = true;
                        viewFlipper.setInAnimation(AnimationUtils
                                .loadAnimation(this, R.anim.move_in_right));
                        viewFlipper.setOutAnimation(AnimationUtils
                                .loadAnimation(this, R.anim.move_out_left));
                        viewFlipper.showPrevious();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                isFlip = false;
                break;
            }
        }

        return false;
    }
}