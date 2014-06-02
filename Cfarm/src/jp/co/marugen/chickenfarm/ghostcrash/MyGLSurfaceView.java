package jp.co.marugen.chickenfarm.ghostcrash;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

public class MyGLSurfaceView extends GLSurfaceView {
    
    //画面サイズ
    private float mWidth;
    private float mHeight;
    
    //MyRendererを保持する
    private MyRenderer mMyRenderer;
    
    public MyGLSurfaceView(Context context) {
        super(context);
        setFocusable(true);//タッチイベントが取得できるようにする
    }
    
    @Override
    public void setRenderer(Renderer renderer) {
        super.setRenderer(renderer);
        setRenderMode(RENDERMODE_WHEN_DIRTY);
        this.mMyRenderer = (MyRenderer)renderer;
    }
    
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        super.surfaceChanged(holder, format, w, h);
        this.mWidth = w;
        this.mHeight = h;
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = (event.getX() / (float)mWidth) * 2.0f - 1.0f;
        float y = (event.getY() / (float)mHeight) * -3.0f + 1.5f;
        mMyRenderer.touched(x, y);
        return false;
    }
}