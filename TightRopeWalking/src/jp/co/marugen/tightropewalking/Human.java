package jp.co.marugen.tightropewalking;

import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

public class Human {

    public static float mR, mY, mZ;
    private boolean isRight;
    private int cycleCounter;
    private Random rand;

    public Human(float r, float y) {
        Human.mR = r;
        Human.mY = y;
        Human.mZ = 0.1f;
        isRight = true;
        cycleCounter = 0;
        rand = new Random();
    }

    // 標的を移動させます
    public void move() {

        if (!Game.isGameOver && !Game.isClear) {
            // Humanを周期で傾ける
            if (isRight) {
                if (cycleCounter < 40) {
                    // 遠く行くほど難しくなる。
                    if (Human.mY < 1.0f) {
                        cycleCounter += rand.nextInt(2);
                    } else if (Human.mY < 3.0f) {
                        cycleCounter += rand.nextInt(3);
                    } else if (Human.mY < 5.0f) {
                        cycleCounter += rand.nextInt(4);
                    } else if (Human.mY < 7.0f) {
                        cycleCounter += rand.nextInt(5);
                    } else if (Human.mY < 9.0f) {
                        cycleCounter += rand.nextInt(6);
                    } else if (Human.mY < 11.0f) {
                        cycleCounter += rand.nextInt(7);
                    }
                } else {
                    isRight = false;
                }
            } else {
                if (cycleCounter > -40) {
                    // 遠く行くほど難しくなる。
                    if (Human.mY < 1.0f) {
                        cycleCounter -= rand.nextInt(2);
                    } else if (Human.mY < 2.0f) {
                        cycleCounter -= rand.nextInt(3);
                    } else if (Human.mY < 5.0f) {
                        cycleCounter -= rand.nextInt(4);
                    } else if (Human.mY < 7.0f) {
                        cycleCounter -= rand.nextInt(5);
                    } else if (Human.mY < 10.0f) {
                        cycleCounter -= rand.nextInt(6);
                    } else if (Human.mY < 11.0f) {
                        cycleCounter -= rand.nextInt(7);
                    }
                } else {
                    isRight = true;
                }
            }
            mR = cycleCounter;

            // if (Game.deviceX >= -1.0 && Game.deviceX <= -0.3)
            // 一定速度で前に進む
            mY += 0.005f;

            // Humanの傾き角度
            for (int i = 0; i < 60; i++) {
                if (Game.deviceY <= (1.0 * (i + 1) / 60)
                        && Game.deviceY >= (1.0 * i / 60)) {
                    mR += (float) i / 2;
                    break;
                } else if (Game.deviceY >= -(1.0 * (i + 1) / 60)
                        && Game.deviceY <= -(1.0 * i / 60)) {
                    mR += -(float) i / 2;
                    break;
                }
            }
            // 一定以上傾いたらゲームオーバー
            if (mR < -39.0f || mR > 39.0f) {
//                Game.isGameOver = true;
            }
            // 一定以上進んだらクリア
            if ((Human.mY + 1.0f) * 10 > 123.0f) {
                Game.isClear = true;
            }
        } else if (Game.isGameOver) {
            mZ -= 0.01f;
        }
    }

    // 標的を描画します
    public void draw(GL10 gl, int texture) {

        gl.glPushMatrix();
        {
            if (Game.isGameOver) {
                // 右なら
                if (Human.mR > 0) {
                    gl.glTranslatef(0.08f, mY, mZ);
                } else {
                    gl.glTranslatef(-0.08f, mY, mZ);
                }
                gl.glRotatef(mR, 0.0f, 1.0f, 0.0f);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
            } else if (Game.isClear) {
                gl.glTranslatef(0.0f, mY, mZ);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
            } else {
                gl.glRotatef(mR, 0.0f, 1.0f, 0.0f);
                gl.glTranslatef(0.0f, mY, mZ);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
            }

            GraphicUtil.drawTexture2(gl, 0.0f, 0.0f, 0.3f, 0.2f, texture, 1.0f,
                    1.0f, 1.0f, 1.0f);
        }
        gl.glPopMatrix();
    }
}