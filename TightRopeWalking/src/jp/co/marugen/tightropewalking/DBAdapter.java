package jp.co.marugen.tightropewalking;

import static android.provider.BaseColumns._ID;
import static jp.co.marugen.tightropewalking.Constants.SCORE_1;
import static jp.co.marugen.tightropewalking.Constants.SCORE_2;
import static jp.co.marugen.tightropewalking.Constants.SCORE_3;
import static jp.co.marugen.tightropewalking.Constants.SCORE_4;
import static jp.co.marugen.tightropewalking.Constants.SCORE_5;
import static jp.co.marugen.tightropewalking.Constants.TABLE_NAME;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Buble_ver1";
    private static final int DATABASE_VERSION = 1;

    /** Create a helper object for the Events database */
    public DBAdapter(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER, "
                + SCORE_1 + " INTEGER, " + SCORE_2 + " INTEGER, " + SCORE_3
                + " INTEGER, " + SCORE_4 + " INTEGER, " + SCORE_5
                + " INTEGER);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
