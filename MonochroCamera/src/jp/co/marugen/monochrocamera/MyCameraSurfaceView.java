package jp.co.marugen.monochrocamera;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.os.Environment;
import android.provider.MediaStore.Images;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MyCameraSurfaceView implements SurfaceHolder.Callback,
        Camera.PreviewCallback, OnClickListener, Camera.AutoFocusCallback {

    private int prevW;
    private int prevH;
    protected Camera camera;
    private Context context;

    // カメラの起動にしっぱいしたら
    // Failed to connect to cameraというバグに対する対処のため
    private boolean failedToConectCamera = false;

    private MonochroSurfaceView monoChroSurV;
    private MonochroThread monochroThread;

    public MyCameraSurfaceView(Context context, MonochroSurfaceView monochro,
            MonochroThread monochroThread) {
        super();
        this.context = context;
        this.monoChroSurV = monochro;
        this.monochroThread = monochroThread;
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {

        // モノクロ処理をしたBitmapを生成する。(bmpAfterを作る)
        monochroThread.doMonochro(data, prevW, prevH);

        // モノクロ用のSurfaceViewにモノクロ処理したBitmapを表示させる
        monoChroSurV.drawMonochro(monochroThread.bmpAfter);

    }

    protected void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        // アラートダイアログのタイトルを設定します
        alertDialogBuilder.setTitle("カメラの起動に失敗しました");
        // アラートダイアログのメッセージを設定します
        alertDialogBuilder.setMessage("一旦アプリを終了し、時間を空けてから再起動してください。");
        // アラートダイアログのキャンセルが可能かどうかを設定します
        alertDialogBuilder.setCancelable(true);
        AlertDialog alertDialog = alertDialogBuilder.create();
        // アラートダイアログを表示します
        alertDialog.show();

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        // カメラをオープン
        if (camera == null) {

            try {
                camera = Camera.open(0);
                failedToConectCamera = false;
         
                // カメラの起動に失敗したら
            } catch (RuntimeException e) {
                failedToConectCamera = true;
                showDialog();
                return;
            }
        }

        // Autofoucus APIレベルは10以上でないと動かない
        // http://helpdesk.metaio.com/questions/6328/camera-does-not-autofocus-in-any-android-phone
        Camera.Parameters params = camera.getParameters();
        params.setFocusMode("continuous-picture");
        camera.setParameters(params);

        // プレビュー先を指定
        try {
            camera.setPreviewDisplay(holder);
        } catch (IOException e) {
            camera.release();
            camera = null;
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
            int height) {

        if (camera != null) {
            camera.stopPreview(); // プレビューを停止
            setPreviewSize(width, height); // プレビューサイズを設定する
        }

        // カメラの起動が正常に起動されていれば
        if (!failedToConectCamera) {
            camera.setDisplayOrientation(90); // ポートレート用に90度回転
            camera.startPreview(); // プレビューを開始

            // YUV形式のデータをRGB形式に変換→結局使わなかった。
            // Camera.Parameters parameters = camera.getParameters();
            // parameters.setPreviewFormat(ImageFormat.RGB_565);

            prevW = camera.getParameters().getPreviewSize().width;
            prevH = camera.getParameters().getPreviewSize().height;

            camera.setPreviewCallback(this);
        }

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        if (camera != null) {
            camera.stopPreview();
            camera.setPreviewCallback(null);
            camera.release();
            camera = null;
        }
    }

    // プレビューサイズ指定する
    private void setPreviewSize(int width, int height) {

        float a1 = (float) width / (float) height; // SurfaceVieての縦横比を取得
        Camera.Parameters params = camera.getParameters(); // カメラパラメータを取得

        int tmpW = 0, tmpH = 0;
        float tmpD = 10.0f; // 縦横比が一番近いプレビューサイズをセット
        List<Camera.Size> previewSize = params.getSupportedPreviewSizes();
        for (Camera.Size sz : previewSize) {
            float a2 = (float) sz.height / (float) sz.width;
            float diff = Math.abs(a1 - a2);
            if (diff < tmpD) {
                tmpW = sz.width;
                tmpH = sz.height;
                tmpD = diff;
            }
        }
        params.setPreviewSize(tmpW, tmpH);
        camera.setParameters(params);
    }

    // シャッターボタンが押されたら
    @Override
    public void onClick(View v) {
        camera.autoFocus(this);// オートフォーカス機能を呼び出す
    }

    // オートフォーカス機能が呼ばれたら。
    @Override
    public void onAutoFocus(boolean success, Camera camera) {
        camera.stopPreview();
        saveImage(monoChroSurV.bmpResized);// 表示中のビットマップを保存する。
        Toast.makeText(context, "写真が保存されました", Toast.LENGTH_SHORT).show();
        camera.startPreview();
    }

    // 画像ファイルを保存する
    @SuppressLint("SimpleDateFormat")
    private void saveImage(Bitmap imageToSave) {

        // Pictureフォルダのパス
        File folder = Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        // File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(new Date());// ファイル名をセット
        String fileName = "monochro_" + timestamp + ".jpg";// ファイルの名前
        File file = new File(folder, fileName);// NewFolderに保存する画像のパス
        if (file.exists()) {
            file.delete();
        }

        try {
            FileOutputStream out = new FileOutputStream(file);
            imageToSave.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // // Exif情報に「オリエンテーション」を登録
        // // 写真を縦向きで保存するため
        // try {
        // ExifInterface ei = new ExifInterface(fileName.toString());
        // ei.setAttribute(ExifInterface.TAG_ORIENTATION,
        // ExifInterface.ORIENTATION_ROTATE_90 + "");
        // ei.saveAttributes();
        // } catch (Exception e) {
        // e.printStackTrace();
        // }

        try {

            // これをしないと、新規フォルダは端末をシャットダウンするまで更新されない
            showFolder(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ContentProviderに新しいイメージファイルが作られたことを通知する
    private void showFolder(File path) throws Exception {
        try {
            ContentValues values = new ContentValues();
            ContentResolver contentResolver = context.getContentResolver();
            values.put(Images.Media.MIME_TYPE, "image/jpeg");
            values.put(Images.Media.DATE_MODIFIED,
                    System.currentTimeMillis() / 1000);
            values.put(Images.Media.SIZE, path.length());
            values.put(Images.Media.TITLE, path.getName());
            values.put(Images.Media.DATA, path.getPath());
            contentResolver.insert(Media.EXTERNAL_CONTENT_URI, values);
        } catch (Exception e) {
            throw e;
        }
    }

}
