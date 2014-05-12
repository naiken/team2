package jp.co.marugen.monochrocamera;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.SurfaceHolder;

public class MonochroSurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder holder;
    private Canvas canvas;
    protected Bitmap bmpResized;

    public MonochroSurfaceView(Context context, SurfaceHolder holer) {
        super();
        this.holder = holer;

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
            int height) {
        this.holder = holder;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public void drawMonochro(Bitmap bmp) {

        // BitmapをSurfaceViewにフィットさせるため
        Matrix matrix = new Matrix();
        matrix.postRotate(90);// 90度回転
        matrix.postScale(2.5f, 2.5f);// 縦横に2.5倍拡大
        bmpResized = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(),
                bmp.getHeight(), matrix, true);
        
        canvas = holder.lockCanvas();// Canvasをロック
        canvas.drawBitmap(bmpResized, 0, 0, null);//CanvasにBitmapを描画
        holder.unlockCanvasAndPost(canvas);//Canvasのロックを解除

    }

}
