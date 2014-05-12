package jp.co.marugen.chickenfarm;

import static android.provider.BaseColumns._ID;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static jp.co.marugen.chickenfarm.Constants.TABLE_NAME;
import static jp.co.marugen.chickenfarm.Constants.CHICKEN_NAME;
import static jp.co.marugen.chickenfarm.Constants.CHICKEN_TYPE;

public class ChikenDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Buble_ver1";// データベース名
    private static final int DATABASE_VERSION = 1;// データベースのバージョン
    
	public ChikenDBHelper(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	//テーブルの作成
	@Override
	public void onCreate(SQLiteDatabase db) {
	    
	    db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER, "
                + CHICKEN_NAME + " TEXT NOT NULL, " + CHICKEN_TYPE + " INTEGER);");

//		db.execSQL("CREATE TABLE chikentb (" +
//				"_id INTEGER PRIMARY KEY," +
//				"chiken_name TEXT NOT NULL" +
//				"chiken_type INTEGER NOT NULL DEFAULT 0 CHECK(chiken_type IN(0, 1)" +
//				");"
//		);
	
//		db.execSQL("insert into chikentb(chiken_name, chiken_type) values ('にわとり', 0);");
//		db.execSQL("insert into chikentb(chiken_name, chiken_type) values ('ハト', 0);");
//		db.execSQL("insert into chikentb(chiken_name, chiken_type) values ('スズメ', 0);");
//		db.execSQL("insert into chikentb(chiken_name, chiken_type) values ('インコ', 0);");
//		db.execSQL("insert into chikentb(chiken_name, chiken_type) values ('カモメ', 0);");
//		db.execSQL("insert into chikentb(chiken_name, chiken_type) values ('メジロ', 0);");
//		db.execSQL("insert into chikentb(chiken_name, chiken_type) values ('タカ', 0);");
//		db.execSQL("insert into chikentb(chiken_name, chiken_type) values ('キュウカンチョウ', 0);");
//		db.execSQL("insert into chikentb(chiken_name, chiken_type) values ('ウコッケイ', 0);");
//		db.execSQL("insert into chikentb(chiken_name, chiken_type) values ('オウム', 0);");
//		db.execSQL("insert into chikentb(chiken_name, chiken_type) values ('ツル', 0);");
//		db.execSQL("insert into chikentb(chiken_name, chiken_type) values ('フクロウ', 0);");
//		db.execSQL("insert into chikentb(chiken_name, chiken_type) values ('ペリカン', 0);");
//		db.execSQL("insert into chikentb(chiken_name, chiken_type) values ('キジ', 0);");
//		db.execSQL("insert into chikentb(chiken_name, chiken_type) values ('オニオオハシ', 0);");
//		db.execSQL("insert into chikentb(chiken_name, chiken_type) values ('ハゲワシ', 0);");
//		db.execSQL("insert into chikentb(chiken_name, chiken_type) values ('フラミンゴ', 0);");
//		db.execSQL("insert into chikentb(chiken_name, chiken_type) values ('ペンギン', 0);");
//		db.execSQL("insert into chikentb(chiken_name, chiken_type) values ('ダチョウ', 0);");
//		db.execSQL("insert into chikentb(chiken_name, chiken_type) values ('イワトビペンギン', 0);");
//		db.execSQL("insert into chikentb(chiken_name, chiken_type) values ('ヒクイドリ', 0);");
//		db.execSQL("insert into chikentb(chiken_name, chiken_type) values ('トキ', 0);");
//		db.execSQL("insert into chikentb(chiken_name, chiken_type) values ('からあげクン', 0);");
//		db.execSQL("insert into chikentb(chiken_name, chiken_type) values ('ツイッター', 0);");
		
	}
	
	//データベースのアップデート
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
	}

}
