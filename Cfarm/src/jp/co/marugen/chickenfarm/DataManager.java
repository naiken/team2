package jp.co.marugen.chickenfarm;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class DataManager {
    
	private static DataManager _instance = null;
	private SharedPreferences _pref = null;
	
	private static final String KEY_CP = "CP";
	private static final String KEY_GET_CP = "GET_CP";
	private static final String KEY_GUTS = "GUTS";
	private static final String KEY_TOTAL = "TOTAL_GUTS";
	private static final String KEY_TIME = "TIME";
	private static final String KEY_BLEEDING_POINT = "BLEEDING_POINT";
	private static final String KEY_FORWARD_JUDGE = "FORWARD_JUDGE";
	private static final String KEY_TRANING_JUDGE = "TRANING_JUDGE";
	private static final String KEY_EGG = "EGG";
	private static final String KEY_IKA = "IKA";
	private static final String KEY_EBI = "EBI";
	private static final String KEY_MAGURO = "MAGURO";
	private static final String KEY_SAMON = "SAMON";
	private static final String KEY_IKURA = "IKURA";
	private static final String KEY_CHIKEN = "CHIKEN";
	
	private DataManager(Context context) {
		_pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
	}
	
	public static void createInstance(Context context) {
		if(_instance == null) {
			_instance = new DataManager(context);
		}
	}
	
	public static DataManager getInstance(Context context) {
		if(_instance == null) {
			_instance = new DataManager(context);
		}
		return _instance;
	}
	
	/**
	 * CP保存用の関数
	 * @param data
	 */
	public void saveCP (int data) {
		Editor e = this._pref.edit();
		e.putInt(KEY_CP, data);
		e.commit();
	}
	
	/**
	 * CPのデータロード
	 * @return
	 */
	public int loadCP() {
		return this._pref.getInt(KEY_CP, 1000);
	}
	
	/**
	 * トレーニングでの獲得CP保存用の関数
	 * @param data
	 */
	public void saveGET_CP(int data) {
		Editor e = this._pref.edit();
		e.putInt(KEY_GET_CP, data);
		e.commit();
	}
	
	/**
	 * CPのデータロード
	 * @return
	 */
	public int loadGET_CP() {
		return this._pref.getInt(KEY_GET_CP, 0);
	}	
	
	/**
	 * ガッツ保存用の関数
	 * @param data
	 */
	public void saveGUTS(int data) {
		Editor e = this._pref.edit();
		e.putInt(KEY_GUTS, data);
		e.commit();
	}
	
	/**
	 * ガッツのデータロード
	 * @return
	 */
	public int loadGUTS() {
		return this._pref.getInt(KEY_GUTS, 0);
	}

	/**
	 * ガッツの合計保存用の関数
	 * @param data
	 */
	public void saveTOTALGUTS(int data) {
		Editor e = this._pref.edit();
		e.putInt(KEY_TOTAL, data);
		e.commit();
	}
	
	/**
	 * ガッツの合計のデータロード
	 * @return
	 */
	public int loadTOTALGUTS() {
		return this._pref.getInt(KEY_TOTAL, 0);
	}

	
	/**
	 * エサをあげた時間保存用の関数
	 * @param data
	 */
	public void saveTIME (int data) {
		Editor e = this._pref.edit();
		e.putInt(KEY_TIME, data);
		e.commit();
	}
	
	/**
	 * エサをあげた時間のデータロード
	 * @return
	 */
	public int loadTIME() {
		return this._pref.getInt(KEY_TIME, -1);
	}
	
	/**
	 * 成長度保存用の関数
	 * @param data
	 */
	public void saveBLEEDING_POINT (int data) {
		Editor e = this._pref.edit();
		e.putInt(KEY_BLEEDING_POINT, data);
		e.commit();
	}
	
	/**
	 * 成長度のデータロード
	 * @return
	 */
	public int loadBLEEDING_POINT() {
		return this._pref.getInt(KEY_BLEEDING_POINT, 0);
	}
	
	/**
	 * トレーニング可能判定の関数
	 * @param data
	 */
	public void saveTRANING_JUDGE (boolean data) {
		Editor e = this._pref.edit();
		e.putBoolean(KEY_TRANING_JUDGE, data);
		e.commit();
	}
	
	/**
	 * トレーニング可能判定のデータロード
	 * @return
	 */
	public boolean loadTRANING_JUDGE() {
		return this._pref.getBoolean(KEY_TRANING_JUDGE, false);
	}	

	/**
	 * 出荷可能判定の関数
	 * @param data
	 */
	public void saveFORWARD_JUDGE (boolean data) {
		Editor e = this._pref.edit();
		e.putBoolean(KEY_FORWARD_JUDGE, data);
		e.commit();
	}
	
	/**
	 * 出荷可能判定のデータロード
	 * @return
	 */
	public boolean loadFORWARD_JUDGE() {
		return this._pref.getBoolean(KEY_FORWARD_JUDGE, false);
	}	
	
	/**
	 * 卵の種類の保存
	 * @param data
	 */
	public void saveEGG (int data) {
		Editor e = this._pref.edit();
		e.putInt(KEY_EGG, data);
		e.commit();
	}
	
	/**
	 * 卵の種類のデータロード
	 * @return
	 */
	public int loadEGG() {
		return this._pref.getInt(KEY_EGG, 0);
	}
	
	/**
	 * いかの個数の保存
	 * @param data
	 */
	public void saveIKA (int data) {
		Editor e = this._pref.edit();
		e.putInt(KEY_IKA, data);
		e.commit();
	}
	
	/**
	 * いかの個数のデータロード
	 * @return
	 */
	public int loadIKA() {
		return this._pref.getInt(KEY_IKA, 0);
	}
	
	/**
	 * えびの個数の保存
	 * @param data
	 */
	public void saveEBI (int data) {
		Editor e = this._pref.edit();
		e.putInt(KEY_EBI, data);
		e.commit();
	}
	
	/**
	 * えびの個数のデータロード
	 * @return
	 */
	public int loadEBI() {
		return this._pref.getInt(KEY_EBI, 0);
	}
		
	/**
	 * まぐろの個数の保存
	 * @param data
	 */
	public void saveMAGURO (int data) {
		Editor e = this._pref.edit();
		e.putInt(KEY_MAGURO, data);
		e.commit();
	}
	
	/**
	 * まぐろの個数のデータロード
	 * @return
	 */
	public int loadMAGURO() {
		return this._pref.getInt(KEY_MAGURO, 0);
	}
	
	/**
	 * サーモンの個数の保存
	 * @param data
	 */
	public void saveSAMON (int data) {
		Editor e = this._pref.edit();
		e.putInt(KEY_SAMON, data);
		e.commit();
	}
	
	/**
	 * サーモンの個数のデータロード
	 * @return
	 */
	public int loadSAMON() {
		return this._pref.getInt(KEY_SAMON, 0);
	}
	
	/**
	 * いくらの個数の保存
	 * @param data
	 */
	public void saveIKURA (int data) {
		Editor e = this._pref.edit();
		e.putInt(KEY_IKURA, data);
		e.commit();
	}
	
	/**
	 * いくらの個数のデータロード
	 * @return
	 */
	public int loadIKURA() {
		return this._pref.getInt(KEY_IKURA, 0);
	}
	
	/**
	 * チキンの種類の保存
	 * @param data
	 */
	public void saveCHIKEN (int data) {
		Editor e = this._pref.edit();
		e.putInt(KEY_CHIKEN, data);
		e.commit();
	}
	
	/**
	 * チキンの種類のデータロード
	 * @return
	 */
	public int loadCHIKEN() {
		return this._pref.getInt(KEY_CHIKEN, 0);
	}
	
}

