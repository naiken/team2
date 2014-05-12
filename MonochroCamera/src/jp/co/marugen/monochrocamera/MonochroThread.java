package jp.co.marugen.monochrocamera;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;

public class MonochroThread extends Thread {

    
    
    private int smoothness = 255;
    protected Bitmap bmpBefore;
    protected Bitmap bmpAfter;
    private int bmpW;
    private int bmpH;
    private int[] pixcel;
    
    protected static int roughnessLevel = 1; // モノクロ処理の粗さレベル
    public static int brightness = 0;// 明度の調整

    // モノクロ処理
    protected void doMonochro(byte[] data, int prevW, int prevH) {
        
     // byte形式からBitmap形式のに変換
        bmpBefore = getBitmapImageFromYUV(data, prevW, prevH);        

        bmpW = bmpBefore.getWidth();
        bmpH = bmpBefore.getHeight();

        // 画像の全ピクセル情報を取得し、配列pixelsに格納
        pixcel = new int[bmpW * bmpH];
        bmpBefore.getPixels(pixcel, 0, bmpW, 0, 0, bmpW, bmpH);

        // 粗さのレベルが1なら
        if (MonochroThread.roughnessLevel == 1) {
            smoothness = 255;
        } else {

            // 粗さの閾値を２の累乗で設定
            smoothness = (int) Math.pow(2,
                    (9 - MonochroThread.roughnessLevel));
        }

        int brightness = MonochroThread.brightness;
        int threshold = 0; // しきい値
        int minThreshold = (255 / smoothness) + 1; // しきい値の最小値　割ってから繰り上げのため１を足す
        int r = 0, g = 0, b = 0;
        int lv = 0;

        for (int i = 0; i < pixcel.length; i++) {

            // r,g,bの平均値の取得
            r = ((pixcel[i] & 0x00FF0000) >> 16);
            g = ((pixcel[i] & 0x0000FF00) >> 8);
            b = ((pixcel[i] & 0x000000FF));
            lv = (int) ((r + g + b) / 3.0f);

            // モノクロ処理(粗さに応じて閾値を変えている)
            for (int j = 1; j <= smoothness; j++) {
                threshold = minThreshold * j;
                if (lv <= threshold) {

                    // 黒を表示させたいため
                    if (j != 1) {

                        // 255以上の場合
                        if (threshold + brightness > 255) {
                            pixcel[i] = Color.rgb(255, 255, 255); // 真っ白
                        } else if (threshold + brightness < 0) {
                            pixcel[i] = Color.rgb(0, 0, 0); // 真っ黒
                        } else {
                            pixcel[i] = Color.rgb(threshold + brightness,
                                    threshold + brightness, threshold
                                            + brightness);
                        }
                    } else {

                        // 0以下なら
                        if (threshold + brightness < 0) {
                            pixcel[i] = Color.rgb(0, 0, 0); // 真っ黒
                        } else {
                            pixcel[i] = Color.rgb(threshold + brightness,
                                    threshold + brightness, threshold
                                            + brightness);
                        }
                    }
                    break;

                } else {

                    // 余り、こうしないと一部モノクロ加工されない
                    pixcel[i] = Color.rgb(255, 255, 255); // 真っ白
                }
            }
        }

        // 2値後の画像を生成
        bmpAfter = Bitmap.createBitmap(pixcel, 0, bmpW, bmpW, bmpH,
                Bitmap.Config.ARGB_8888);
    }
    
 // YUV形式のデータからBitmapを作成する
    // http://qiita.com/JunSuzukiJapan@github/items/b5484ad8ad9f2de757be
    public Bitmap getBitmapImageFromYUV(byte[] data, int width, int height) {
        YuvImage yuvimage = new YuvImage(data, ImageFormat.NV21, width, height,
                null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        yuvimage.compressToJpeg(new Rect(0, 0, width, height), 80, baos);
        byte[] jdata = baos.toByteArray();
        BitmapFactory.Options bitmapFatoryOptions = new BitmapFactory.Options();
        bitmapFatoryOptions.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bmp = BitmapFactory.decodeByteArray(jdata, 0, jdata.length,
                bitmapFatoryOptions);
        return bmp;
    }

    @Override
    public void run() {
    }

}
