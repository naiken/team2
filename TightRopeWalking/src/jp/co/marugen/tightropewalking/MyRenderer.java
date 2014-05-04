package jp.co.marugen.tightropewalking;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.content.res.Resources;
import android.opengl.GLSurfaceView;

public class MyRenderer implements GLSurfaceView.Renderer {

    // コンテキスト
    private Context mContext;
    private MakeLayout makeLayout;

    private int mWidth;
    private int mHeight;

    // 描画するテクスチャ
    private int ropeTex, manTex, numberBg, scoreTex, numberTex, gameoverTex, clearTex;
    private int forestEntranceTex, forestSide, forestSide2Tex, forestBottomTex;
    private int forestPix1, forestPix2, forestPix3,
            forestPix4, forestPix5, forestPix6, forestPix7, forestPix8,
            forestPix9, forestPix10, forestPix11, forestPix12, forestPix13,
            forestPix14;
    private int caveEntrance, black, blackHole, caveHole, caveExit, cavePix1,
            cavePix2, cavePix3, cavePix4, cavePix5, cavePix6, cavePix7,
            cavePix8, cavePix9;
    private int ruinsBack, ruinsSide, ruinsBottom, ruinsPic1, ruinsPic2,
            ruinsPic3, ruinsPic4, ruinsPic5, ruinsPic6, ruinsPic7,
            treasureEntrance, treasure, coins;

    // 「Gameover」後に数秒待つため
    private int gameoverCounter;

    private Human human;

    public MyRenderer(Context context) {
        this.mContext = context;
        makeLayout = new MakeLayout();
        human = new Human(0.0f, -1.0f);
        gameoverCounter = 0;
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        // TODO Auto-generated method stub
        gl.glViewport(0, 0, mWidth, mHeight);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrthof(-1.0f, 1.0f, -1.5f, 1.5f, -1.5f, 1.5f);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        
        renderMain(gl);
        renderStates(gl);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        // TODO Auto-generated method stub
        this.mWidth = width;
        this.mHeight = height;

        loadTextures(gl);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // TODO Auto-generated method stub
    }

    public void renderMain(GL10 gl) {

        // 3D描画用に座標系を設定する
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glFrustumf(-0.3f, 0.3f, -0.2f, 0.2f, 0.5f, 20.0f);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();

        // パースアイビュー用に全体を回転および移動させる
        gl.glRotatef(-80.0f, 1.0f, 0.0f, 0.0f);
        gl.glTranslatef(0.0f, 0.0f, -0.3f);
        // gl.glTranslatef(0.0f, -(Human.mY + 0.5f) + 1.0f , 0.0f);
        gl.glTranslatef(0.0f, -(Human.mY + 0.5f) + 1.0f, 0.0f);

        // Humanを移動させる
        human.move();

        gl.glEnable(GL10.GL_BLEND);// 背景を透明にするためブレンドを有効化
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        // gl.glEnable(GL10.GL_DEPTH_TEST);// 深度テストの有効化

        // 遺跡ゾーンの描画
        makeLayout.makeRuins(gl, ruinsSide, ruinsBack, ruinsBottom, ruinsPic1,
                ruinsPic2, ruinsPic3, ruinsPic4, ruinsPic5, ruinsPic6,
                ruinsPic7, treasureEntrance, black, treasure, coins);

        // 洞窟ゾーンの描画
        makeLayout.makeCave(gl, caveEntrance, black, blackHole, caveHole,
                caveExit, cavePix1, cavePix2, cavePix3, cavePix4, cavePix5,
                cavePix6, cavePix7, cavePix8, cavePix9);
        // 森ゾーンの描画
        makeLayout.makeForest(gl, forestEntranceTex, forestSide,
                forestSide2Tex, forestBottomTex, forestPix1, forestPix2,
                forestPix3, forestPix4, forestPix5, forestPix6, forestPix7,
                forestPix8, forestPix9, forestPix10, forestPix11, forestPix12,
                forestPix13, forestPix14);

        gl.glPushMatrix();
        {
            // ロープを描画する
            for (int i = 0; i < 5; i++) {
                GraphicUtil.drawTexture2(gl, 0.0f, -1.5f + (i * 3.0f), 0.05f,
                        3.0f, ropeTex, 1.0f, 1.0f, 1.0f, 1.0f);
            }
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            // Humanの描画
            human.draw(gl, manTex);
        }
        gl.glPopMatrix();

        // gl.glDisable(GL10.GL_DEPTH_TEST);
        gl.glDisable(GL10.GL_BLEND);
    }

    private void renderStates(GL10 gl) {
        // 2D描画用に座標系を設定する
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrthof(-1.0f, 1.0f, -1.5f, 1.5f, -0.5f, 0.5f);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glEnable(GL10.GL_BLEND);// 背景を透明にするためブレンドを有効化
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        // 移動した距離の描画
        int distance = (int) ((Human.mY + 1.0f) * 10);
        GraphicUtil.drawTexture2(gl, 0.0f, 1.35f, 2.0f, 0.3f, numberBg,
                1.0f, 1.0f, 1.0f, 1.0f);
        GraphicUtil.drawTexture2(gl, -0.35f, 1.35f, 0.6f, 0.25f, scoreTex,
                1.0f, 1.0f, 1.0f, 1.0f);
        GraphicUtil.drawNumbers(gl, 0.35f, 1.35f, 0.2f, 0.20f, numberTex,
                distance, 3, 1.0f, 1.0f, 1.0f, 1.0f);

        
        if (Game.isGameOver || Game.isClear) {

            if (Game.isGameOver) {
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 2.0f, 3.0f,
                        gameoverTex, 1.0f, 1.0f, 1.0f, 1.0f);
            } else {
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 2.0f, 3.0f, clearTex,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gameoverCounter++;

            if (gameoverCounter > 60) {
                Game.canMovePage = true;
            }
        }

        gl.glDisable(GL10.GL_BLEND);
    }

    // テクスチャを読み込むメソッド
    private void loadTextures(GL10 gl) {
        Resources res = mContext.getResources();

        this.ropeTex = GraphicUtil.loadTexture(gl, res, R.drawable.rope1);
        this.manTex = GraphicUtil.loadTexture(gl, res, R.drawable.human);
        this.numberBg = GraphicUtil.loadTexture(gl, res, R.drawable.navi_bg);
        this.scoreTex = GraphicUtil.loadTexture(gl, res, R.drawable.score);
        this.clearTex = GraphicUtil.loadTexture(gl, res, R.drawable.clear);
        this.numberTex = GraphicUtil.loadTexture(gl, res, R.drawable.number_texture);
        this.gameoverTex = GraphicUtil
                .loadTexture(gl, res, R.drawable.gameover);
        this.clearTex = GraphicUtil.loadTexture(gl, res, R.drawable.clear);

        // 森ゾーン
        this.forestEntranceTex = GraphicUtil.loadTexture(gl, res,
                R.drawable.forest_entrance);
        this.forestSide = GraphicUtil.loadTexture(gl, res,
                R.drawable.forest_side);
        this.forestBottomTex = GraphicUtil.loadTexture(gl, res,
                R.drawable.forest_bottom);
        this.forestPix1 = GraphicUtil.loadTexture(gl, res,
                R.drawable.forest_pix1);
        this.forestPix2 = GraphicUtil.loadTexture(gl, res,
                R.drawable.forest_pic2);
        this.forestPix3 = GraphicUtil.loadTexture(gl, res,
                R.drawable.forest_pic3);
        this.forestPix4 = GraphicUtil.loadTexture(gl, res,
                R.drawable.forest_pix4);
        this.forestPix5 = GraphicUtil.loadTexture(gl, res,
                R.drawable.forest_pix5);
        this.forestPix6 = GraphicUtil.loadTexture(gl, res,
                R.drawable.forest_pic6);
        this.forestPix7 = GraphicUtil.loadTexture(gl, res,
                R.drawable.forest_pic7);
        this.forestPix8 = GraphicUtil.loadTexture(gl, res,
                R.drawable.forest_pic8);
        this.forestPix9 = GraphicUtil.loadTexture(gl, res,
                R.drawable.forest_pic9);
        this.forestPix10 = GraphicUtil.loadTexture(gl, res,
                R.drawable.forest_pic10);
        this.forestPix11 = GraphicUtil.loadTexture(gl, res,
                R.drawable.forest_pic11);
        this.forestPix12 = GraphicUtil.loadTexture(gl, res,
                R.drawable.forest_pic12);
        this.forestPix13 = GraphicUtil.loadTexture(gl, res,
                R.drawable.forest_pic13);
        this.forestPix14 = GraphicUtil.loadTexture(gl, res,
                R.drawable.forest_pic14);

        // 洞窟ゾーン
        this.caveEntrance = GraphicUtil.loadTexture(gl, res,
                R.drawable.cave_entrance2);
        this.black = GraphicUtil.loadTexture(gl, res, R.drawable.black);
        this.blackHole = GraphicUtil
                .loadTexture(gl, res, R.drawable.black_hole);
        this.caveHole = GraphicUtil.loadTexture(gl, res, R.drawable.cave_hole);
        this.caveExit = GraphicUtil.loadTexture(gl, res, R.drawable.cave_exit);
        this.cavePix1 = GraphicUtil.loadTexture(gl, res, R.drawable.cave_pic1);
        this.cavePix2 = GraphicUtil.loadTexture(gl, res, R.drawable.cave_pic2);
        this.cavePix3 = GraphicUtil.loadTexture(gl, res, R.drawable.cave_pic3);
        this.cavePix4 = GraphicUtil.loadTexture(gl, res, R.drawable.cave_pic4);
        this.cavePix6 = GraphicUtil.loadTexture(gl, res, R.drawable.cave_pic6);
        this.cavePix8 = GraphicUtil.loadTexture(gl, res, R.drawable.cave_pic8);
        this.cavePix9 = GraphicUtil.loadTexture(gl, res, R.drawable.cave_pic9);

        // 遺跡ゾーン
        this.ruinsBack = GraphicUtil
                .loadTexture(gl, res, R.drawable.ruins_back);
        this.ruinsSide = GraphicUtil
                .loadTexture(gl, res, R.drawable.ruins_side);
        this.ruinsBottom = GraphicUtil.loadTexture(gl, res,
                R.drawable.ruins_bottom);
        this.ruinsPic1 = GraphicUtil
                .loadTexture(gl, res, R.drawable.ruins_pic1);
        this.ruinsPic2 = GraphicUtil
                .loadTexture(gl, res, R.drawable.ruins_pic2);
        this.ruinsPic3 = GraphicUtil
                .loadTexture(gl, res, R.drawable.ruins_pic3);
        this.ruinsPic4 = GraphicUtil
                .loadTexture(gl, res, R.drawable.ruins_pic4);
        this.ruinsPic5 = GraphicUtil
                .loadTexture(gl, res, R.drawable.ruins_pic5);
        this.ruinsPic6 = GraphicUtil
                .loadTexture(gl, res, R.drawable.ruins_pic6);
        this.ruinsPic7 = GraphicUtil
                .loadTexture(gl, res, R.drawable.ruins_pic7);

        this.treasureEntrance = GraphicUtil.loadTexture(gl, res,
                R.drawable.treasure_entrance);
        this.treasure = GraphicUtil.loadTexture(gl, res, R.drawable.treasure);
        this.coins = GraphicUtil.loadTexture(gl, res, R.drawable.coins);
    }
}
