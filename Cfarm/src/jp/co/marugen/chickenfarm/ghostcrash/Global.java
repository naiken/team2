package jp.co.marugen.chickenfarm.ghostcrash;

import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

public class Global {

    // MainActivity
    public static GhostCrash gameActivity;

    // GLコンテキストを保持する変数
    public static GL10 gl;

    // ランダムな値を生成する
    // 毎回完全に異なる乱数を取得するため
    public static Random rand = new Random(System.currentTimeMillis());
}
