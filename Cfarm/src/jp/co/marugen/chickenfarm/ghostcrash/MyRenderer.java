package jp.co.marugen.chickenfarm.ghostcrash;

import java.util.Random;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import jp.co.marugen.chickenfarm.R;
import android.content.Context;
import android.content.res.Resources;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.provider.Settings.Global;
import android.util.Log;

public class MyRenderer implements GLSurfaceView.Renderer {

    public static final int TARGET_NUM = 20;// 標的の数
    private static final int GAME_INTERVAL = 15;// 制限時間は60秒
    // ハンドラを生成
    Handler handler = new Handler();

    public static int mScore;// 得点

    // コンテキスト
    private Context mContext;

    private int mWidth;
    private int mHeight;

    // テクスチャ
    private int mBgTexture;
    private int mTargetTexture;// 標的用テクスチャ
    private int mNumberTexture;
    private int mTimeBgTexture;
    private int mTimeTexture;
    private int mScoreTexture;
    private int mGameOverTexture;// ゲームオーバー用テクスチャ
    private int mParticleTexture;// パーティクル用テクスチャ

    private ParticleSystem mParticleSystem;// パーティクルシステム

    // 標的
    private MyTarget[] mTargets = new MyTarget[TARGET_NUM];

    private long mStartTime;// 開始時間
    private boolean mGameOverFlag;// ゲームオーバーであるか

    public static boolean canMoveRecordSceen = false;
    private int waitingTime = 2 * (1000 / GhostCrash.TIMER_PERIOD);// 2秒まつ
    private int waitingCounter = 0;
    
    private MySe mySe;// クラッシュしたときのサウンド

    public MyRenderer(Context context) {
        this.mContext = context;
        this.mParticleSystem = new ParticleSystem(300, 30);// 生成します
        startNewGame();
    }

    public void startNewGame() {
        
        this.mySe = new MySe(mContext);//クラッシュ音の再生のため

        Random rand = jp.co.marugen.chickenfarm.ghostcrash.Global.rand;
        // 標的の状態を初期化します
        for (int i = 0; i < TARGET_NUM; i++) {
            float x = rand.nextFloat() * 2.0f - 1.0f;
            float y = rand.nextFloat() * 2.0f - 1.0f;
            float angle = rand.nextInt(360);
            float size = rand.nextFloat() * 0.25f + 0.25f;
            float speed = rand.nextFloat() * 0.01f + 0.01f;
            float turnAngle = rand.nextFloat() * 4.0f - 2.0f;
            mTargets[i] = new MyTarget(x, y, angle, size, speed, turnAngle);
        }

        MyRenderer.mScore = 0;
        this.mStartTime = System.currentTimeMillis();// 開始時間を保持します
        this.mGameOverFlag = false;// ゲームオーバー状態ではない
    }

    // 描画を行う部分を記述するメソッドを追加する
    public void renderMain(GL10 gl) {
        // 経過時間を計算する
        int passedTime = (int) (System.currentTimeMillis() - mStartTime) / 1000;
        int remainTime = GAME_INTERVAL - passedTime; // 残り時間を計算する
        if (remainTime <= 0) {
            remainTime = 0;// 残り時間がマイナスにならないようにする
            if (!mGameOverFlag) {
                mGameOverFlag = true;// ゲームオーバー状態にする
            }
        }
        Random rand = jp.co.marugen.chickenfarm.ghostcrash.Global.rand;
        MyTarget[] targets = mTargets;
        
        // 全ての標的を1つずつ動かします
        for (int i = 0; i < TARGET_NUM; i++) {
            
            // ランダムなタイミングで方向転換するようにします
            if (rand.nextInt(100) == 0) {// 100回に1回の確率で方向転換させます
                // 旋回する角度を -2.0〜2.0の間でランダムに設定します。
                targets[i].mTurnAngle = rand.nextFloat() * 4.0f - 2.0f;
            }
            // ここで標的を旋回させます
            targets[i].mAngle = targets[i].mAngle + targets[i].mTurnAngle;
            // 標的を動かします
            targets[i].move();
            // パーティクルを使って軌跡を描画します
            float moveX = (rand.nextFloat() - 0.5f) * 0.01f;
            float moveY = (rand.nextFloat() - 0.5f) * 0.01f;
            mParticleSystem.add(targets[i].mX + 10 * moveX, targets[i].mY + 10
                    * moveY, 0.1f, moveX, moveY);
        }

        // 背景を描画する
        GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 2.0f, 3.0f, mBgTexture, 1.0f,
                1.0f, 1.0f, 1.0f);

        if (!mGameOverFlag) {

            // パーティクルを描画します
            mParticleSystem.update();
            gl.glEnable(GL10.GL_BLEND);
            gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE);
            mParticleSystem.draw(gl, mParticleTexture);
            // 標的を描画します
            for (int i = 0; i < TARGET_NUM; i++) {
                targets[i].draw(gl, mTargetTexture);
            }
            gl.glDisable(GL10.GL_BLEND);
            // 時間とスコアの背景
            GraphicUtil.drawTexture2(gl, 0.0f, 1.35f, 2.0f, 0.3f,
                    mTimeBgTexture, 1.0f, 1.0f, 1.0f, 1.0f);
            // 残り時間を描画します
            GraphicUtil.drawTexture2(gl, -0.7f, 1.34f, 0.5f, 0.25f,
                    mTimeTexture, 1.0f, 1.0f, 1.0f, 1.0f);
            GraphicUtil.drawNumbers(gl, -0.25f, 1.34f, 0.2f, 0.20f,
                    mNumberTexture, remainTime, 2, 1.0f, 1.0f, 1.0f, 1.0f);
            // // 得点を描画します
            GraphicUtil.drawTexture2(gl, 0.15f, 1.3f, 0.25f, 0.125f,
                    mScoreTexture, 1.0f, 1.0f, 1.0f, 1.0f);
            GraphicUtil.drawNumbers(gl, 0.65f, 1.3f, 0.125f, 0.125f,
                    mNumberTexture, mScore, 5, 1.0f, 1.0f, 1.0f, 1.0f);

        } else {

            if (waitingCounter < waitingTime) {
                // ゲームオーバーテクスチャを描画します。
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 1.5f, 1.3f,
                        mGameOverTexture, 1.0f, 1.0f, 1.0f, 0.5f);
                waitingCounter++;

            } else {
                canMoveRecordSceen = true;
                gl.glFinish();
            }
        }
    }

    @Override
    public void onDrawFrame(GL10 gl) {

        gl.glViewport(0, 0, mWidth, mHeight);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrthof(-1.0f, 1.0f, -1.5f, 1.5f, 0.5f, -0.5f);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        renderMain(gl);
    }

    // テクスチャを読み込むメソッド
    private void loadTextures(GL10 gl) {

        Resources res = mContext.getResources();

        this.mBgTexture = GraphicUtil.loadTexture(gl, res, R.drawable.bg);

        this.mTargetTexture = GraphicUtil
                .loadTexture(gl, res, R.drawable.buble);

        this.mTimeBgTexture = GraphicUtil.loadTexture(gl, res,
                R.drawable.navi_bg);

        this.mNumberTexture = GraphicUtil.loadTexture(gl, res,
                R.drawable.number_texture);

        this.mTimeTexture = GraphicUtil.loadTexture(gl, res,
                R.drawable.time_letter);

        this.mScoreTexture = GraphicUtil.loadTexture(gl, res,
                R.drawable.score_letter);
        this.mGameOverTexture = GraphicUtil.loadTexture(gl, res,
                R.drawable.gc_gameover);

        this.mParticleTexture = GraphicUtil.loadTexture(gl, res,
                R.drawable.particle_lightblue);

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        this.mWidth = width;
        this.mHeight = height;

        jp.co.marugen.chickenfarm.ghostcrash.Global.gl = gl;// GLコンテキストを保持する
        // テクスチャをロードする
        loadTextures(gl);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

    }

    // 画面がタッチされたときに呼ばれるメソッド
    public void touched(float x, float y) {
        Log.i(getClass().toString(),
                String.format("touched! x = %f, y = %f", x, y));
        MyTarget[] targets = mTargets;
        Random rand = jp.co.marugen.chickenfarm.ghostcrash.Global.rand;

        if (!mGameOverFlag) {
            // すべての標的との当たり判定をします
            for (int i = 0; i < TARGET_NUM; i++) {
                if (targets[i].isPointInside(x, y)) {
                    // パーティクルを放出します
                    for (int j = 0; j < 40; j++) {
                        float moveX = (rand.nextFloat() - 0.5f) * 0.05f;
                        float moveY = (rand.nextFloat() - 0.5f) * 0.05f;
                        mParticleSystem.add(targets[i].mX, targets[i].mY, 0.2f,
                                moveX, moveY);
                    }
                    // 標的をランダムな位置に移動します
                    float dist = 2.0f;// 画面中央から2.0fはなれた円周上の点
                    float theta = (float) jp.co.marugen.chickenfarm.ghostcrash.Global.rand.nextInt(360) / 180.0f
                            * (float) Math.PI;// 適当な位置
                    targets[i].mX = (float) Math.cos(theta) * dist;
                    targets[i].mY = (float) Math.sin(theta) * dist;
                    mScore += 1;// 100点加算します
                    
                    mySe.playHitSound();
                }
            }
        }
    }

    public void subtractPausedTime(long pausedTime) {
        mStartTime += pausedTime;
    }

    public long getStartTime() {
        return mStartTime;
    }

    public void setStartTime(long start) {
         mStartTime = start;
    }
    
    public int getScore() {
        return mScore;
    }

    public void setScore(int score) {
        mScore = score;
    }
}