package jp.co.marugen.tightropewalking;

import javax.microedition.khronos.opengles.GL10;

public class MakeLayout {

    protected void makeForest(GL10 gl, int forestEntranceTex, int forestSide,
            int forestSide2Tex, int forestBottomTex, int pic1, int pic2,
            int pic3, int pic4, int pic5, int pic6, int pic7, int pic8,
            int pic9, int pic10, int pic11, int pic12, int pic13, int pic14) {

        // 負荷軽減のため一定以上すすんだら描画しない
        if (((Human.mY + 1.0f) * 10) <= 55.0f) {
            gl.glPushMatrix();
            {
                // 左の背景を描画する
                gl.glTranslatef(-3.0f, 3.0f, 0.0f);
                gl.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
                gl.glRotatef(90.0f, 0.0f, 0.0f, 1.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 6.0f, 3.0f,
                        forestSide, 1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 右の背景を描画する
                gl.glTranslatef(3.0f, 3.0f, 0.0f);
                gl.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
                gl.glRotatef(90.0f, 0.0f, 0.0f, 1.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 6.0f, 3.0f,
                        forestSide, 1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 下の背景を描画する
                gl.glTranslatef(0.0f, 3.0f, -1.5f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 6.0f, 6.0f,
                        forestBottomTex, 1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 　ひょう　
                gl.glTranslatef(-0.2f, 3.7f, 0.2f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 0.4f, 0.25f, pic14,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 　草　
                gl.glTranslatef(0.4f, 3.6f, 0.0f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 0.8f, 0.4f, pic3,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 　草　
                gl.glTranslatef(-0.3f, 3.4f, 0.1f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 0.8f, 0.4f, pic7,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 　インコ　
                gl.glTranslatef(0.1f, 3.2f, 0.2f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 0.3f, 0.2f, pic8,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 　草　
                gl.glTranslatef(-0.4f, 3.0f, 0.1f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 0.8f, 0.4f, pic12,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 　草　
                gl.glTranslatef(0.3f, 2.9f, -0.5f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 0.8f, 0.4f, pic10,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 　草
                gl.glTranslatef(0.1f, 2.7f, 0.3f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 0.8f, 0.4f, pic7,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 　猿　
                gl.glTranslatef(0.2f, 2.5f, 0.2f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 0.4f, 0.15f, pic9,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 　草　
                gl.glTranslatef(0.0f, 2.3f, 0.1f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 0.8f, 0.4f, pic10,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 　草　
                gl.glTranslatef(-0.2f, 2.0f, -0.2f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 0.9f, 0.4f, pic12,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 草
                gl.glTranslatef(-0.2f, 2.0f, -0.2f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 0.9f, 0.4f, pic7,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 草
                gl.glTranslatef(0.2f, 1.7f, 0.0f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 0.7f, 0.3f, pic10,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 7 蛇
                gl.glTranslatef(-0.3f, 1.5f, 0.2f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 0.6f, 0.3f, pic4,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 7 虎
                gl.glTranslatef(-0.4f, 1.1f, 0.1f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 1.0f, 0.3f, pic2,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 　草
                gl.glTranslatef(0.3f, 0.9f, 0.4f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 0.7f, 0.4f, pic7,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 　滝
                gl.glTranslatef(-0.2f, 0.8f, 0.2f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 0.8f, 0.4f, pic1,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 3　猿
                gl.glTranslatef(-0.3f, 0.5f, 0.3f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 0.4f, 0.2f, pic11,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 2　草
                gl.glTranslatef(0.3f, 0.4f, 0.2f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 0.5f, 0.3f, pic1,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 1 滝
                gl.glTranslatef(0.2f, 0.20f, -0.0f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 0.5f, 0.3f, pic5,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 森への入り口を描画する
                gl.glTranslatef(0.0f, -0.1f, 0.1f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 1.9f, 1.4f,
                        forestEntranceTex, 1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();
        }

    }

    protected void makeCave(GL10 gl, int caveEntrance, int black,
            int blackHole, int caveHole, int caveExit, int cavePix1,
            int cavePix2, int cavePix3, int cavePix4, int cavePix5,
            int cavePix6, int cavePix7, int cavePix8, int cavePic9) {

        // 負荷軽減のため一定以上進んだら描画する
        if (((Human.mY + 1.0f) * 10) > 30.0f && ((Human.mY + 1.0f) * 10) < 80) {
            gl.glPushMatrix();
            {
                // 奥の背景を描画する
                gl.glTranslatef(0.0f, 7.0f, 0.0f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 6.0f, 3.0f, blackHole,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 奥の穴を背景を描画する
                gl.glTranslatef(0.04f, 7.0f, 0.15f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 1.0f, 1.0f, caveExit,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 左の背景を描画する
                gl.glTranslatef(-3.0f, 6.0f, 0.0f);
                gl.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
                gl.glRotatef(90.0f, 0.0f, 0.0f, 1.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 3.0f, 3.0f, black,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 右の背景を描画する
                gl.glTranslatef(3.0f, 6.0f, 0.0f);
                gl.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
                gl.glRotatef(90.0f, 0.0f, 0.0f, 1.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 3.0f, 3.0f, black,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 下の背景を描画する
                gl.glTranslatef(0.0f, 6.0f, -1.5f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 6.0f, 3.0f, black,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 階段状乳石を描画する
                gl.glTranslatef(0.1f, 6.4f, 0.25f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 0.6f, 0.3f, cavePix4,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // コウモリを描画する
                gl.glTranslatef(-0.3f, 6.1f, 0.3f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 0.3f, 0.1f, cavePix8,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 典型的鍾乳石を描画する
                gl.glTranslatef(-0.1f, 5.8f, -0.0f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 0.6f, 0.3f, cavePic9,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 青い湖を描画する
                gl.glTranslatef(0.2f, 5.6f, -0.1f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 0.6f, 0.3f, cavePix3,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 天井の鍾乳石を描画する
                gl.glTranslatef(-0.3f, 5.3f, 0.4f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 0.5f, 0.2f, cavePix2,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 団子状鍾乳石を描画する
                gl.glTranslatef(0.2f, 5.1f, 0.1f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 0.8f, 0.4f, cavePix1,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 岩を描画する
                gl.glTranslatef(-0.2f, 4.8f, 0.1f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 3.0f, 1.0f, cavePix6,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();
        }

        if (((Human.mY + 1.0f) * 10) < 55.0f) {

            gl.glPushMatrix();
            {
                // 洞窟の入り口を描画する
                gl.glTranslatef(0.0f, 4.5f, 0.0f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 6.0f, 3.0f, caveHole,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 洞窟の入り口2を描画する
                gl.glTranslatef(0.1f, 4.5f, 0.2f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 4.0f, 2.0f,
                        caveEntrance, 1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();
        }
    }

    protected void makeRuins(GL10 gl, int ruinsSide, int ruinsBack,
            int ruinsBottom, int ruinsPic1, int ruinsPic2, int ruinsPic3,
            int ruinsPic4, int ruinsPic5, int ruinsPic6, int ruinsPic7,
            int treasure_entrance, int black, int treasure, int coins) {

        // 負荷軽減のため一定以上すすんだら描画する
        if (((Human.mY + 1.0f) * 10) > 70.0f) {
            gl.glPushMatrix();
            {
                // 奥の背景を描画する
                gl.glTranslatef(0.0f, 12.0f, 0.3f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 6.0f, 2.5f, ruinsBack,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 左の背景を描画する
                gl.glTranslatef(-3.0f, 9.5f, 0.0f);
                gl.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
                gl.glRotatef(90.0f, 0.0f, 0.0f, 1.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 5.0f, 3.0f, ruinsSide,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 右の背景を描画する
                gl.glTranslatef(3.0f, 9.5f, 0.0f);
                gl.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
                gl.glRotatef(90.0f, 0.0f, 0.0f, 1.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 5.0f, 3.0f, ruinsSide,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 下の背景を描画する
                gl.glTranslatef(0.0f, 9.5f, -0.5f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 6.0f, 5.0f,
                        ruinsBottom, 1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 宝島奥の背景を描画する
                gl.glTranslatef(0.0f, 12.0f, 0.0f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 1.0f, 1.2f, black,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 宝島左の背景を描画する
                gl.glTranslatef(-0.5f, 11.5f, 0.3f);
                gl.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
                gl.glRotatef(90.0f, 0.0f, 0.0f, 1.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 1.0f, 0.6f, black,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 宝島右の背景を描画する
                gl.glTranslatef(0.5f, 11.5f, 0.3f);
                gl.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
                gl.glRotatef(90.0f, 0.0f, 0.0f, 1.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 1.0f, 0.6f, black,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 宝部屋の下背景を描画する
                gl.glTranslatef(0.0f, 11.5f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 1.0f, 1.0f, coins,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 宝を描画する
                gl.glTranslatef(0.0f, 11.5f, 0.2f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 0.6f, 0.3f, treasure,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 宝への入り口を描画する
                gl.glTranslatef(0.13f, 11.0f, 0.2f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 3.0f, 1.6f,
                        treasure_entrance, 1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 遺跡7を描画する
                gl.glTranslatef(0.6f, 10.5f, -0.2f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 0.8f, 0.3f, ruinsPic7,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 遺跡6を描画する
                gl.glTranslatef(-0.4f, 9.9f, -0.2f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 0.8f, 0.3f, ruinsPic6,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 遺跡5を描画する
                gl.glTranslatef(0.7f, 9.5f, -0.2f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 0.6f, 0.3f, ruinsPic5,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 遺跡4を描画する
                gl.glTranslatef(0.2f, 9.1f, -0.2f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 0.6f, 0.3f, ruinsPic4,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 遺跡３を描画する
                gl.glTranslatef(-0.8f, 8.7f, -0.1f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 0.5f, 0.4f, ruinsPic3,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 遺跡２描画する
                gl.glTranslatef(0.6f, 8.3f, -0.2f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 0.6f, 0.3f, ruinsPic2,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                // 下の背景を描画する
                gl.glTranslatef(-0.4f, 7.7f, -0.2f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 0.6f, 0.3f, ruinsPic1,
                        1.0f, 1.0f, 1.0f, 1.0f);
            }
            gl.glPopMatrix();

        }
    }

}
