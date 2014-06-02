package jp.co.marugen.chickenfarm.ghostcrash;

import jp.co.marugen.chickenfarm.R;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class MySe {
    
    private SoundPool mSoundPool;//SoundPool
    private int hitSound;// 読み込んだ効果音オブジェクト
    
    public MySe(Context context) {
        this.mSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);// サウンドプールの生成
        this.hitSound = mSoundPool.load(context, R.raw.bublecrash_crashed, 1);
    }
    
    public void playHitSound() {
        mSoundPool.play(hitSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

}
