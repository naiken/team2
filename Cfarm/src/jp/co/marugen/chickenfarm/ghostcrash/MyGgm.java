package jp.co.marugen.chickenfarm.ghostcrash;

import jp.co.marugen.chickenfarm.R;
import android.content.Context;
import android.media.MediaPlayer;

public class MyGgm {
    
    private MediaPlayer bleedingBgm;
    private MediaPlayer garabepickBgm;
    private MediaPlayer ghostcrashBgm;
    private MediaPlayer russianBgm;
    
    public MyGgm(Context context) {
        this.bleedingBgm = MediaPlayer.create(context, R.raw.bleeding_bgm);
        this.garabepickBgm = MediaPlayer.create(context, R.raw.garbagepick_bgm);
        this.ghostcrashBgm = MediaPlayer.create(context, R.raw.ghostcrash_bgm);
        this.russianBgm = MediaPlayer.create(context, R.raw.russian_bgm);
    }
    
    public void start(int musicNum) {
        switch (musicNum) {
        case 0:
            if (!bleedingBgm.isPlaying()) {
                bleedingBgm.seekTo(0);
                bleedingBgm.start();
            }
            break;
        case 1:
            if (!garabepickBgm.isPlaying()) {
                garabepickBgm.seekTo(0);
                garabepickBgm.start();
            }
            break;
        case 2:
            if (!ghostcrashBgm.isPlaying()) {
                ghostcrashBgm.seekTo(0);
                ghostcrashBgm.start();
            }
            break;

        case 3:
            if (!russianBgm.isPlaying()) {
                russianBgm.seekTo(0);
                russianBgm.start();
            }
            break;
        }
    }

    
    public void stop(int musicNum) {
        
        switch (musicNum) {
        case 0:
            if (bleedingBgm.isPlaying()) {
                bleedingBgm.stop();
                bleedingBgm.prepareAsync();
            }
            break;
        case 1:
            if (garabepickBgm.isPlaying()) {
                garabepickBgm.stop();
                garabepickBgm.prepareAsync();
            }
            break;
        case 2:
            if (ghostcrashBgm.isPlaying()) {
                ghostcrashBgm.stop();
                ghostcrashBgm.prepareAsync();
            }
            break;

        case 3:
            if (russianBgm.isPlaying()) {
                russianBgm.stop();
                russianBgm.prepareAsync();
            }
            break;
        }
        
    }
}
