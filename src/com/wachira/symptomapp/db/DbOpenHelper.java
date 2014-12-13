package com.wachira.symptomapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper extends SQLiteOpenHelper {
	
	public final static String MEDS_TABLE_NAME = "medication";
	public final static String PATIENTS_TABLE_NAME = "patient";
	public final static String MED_NAME = "medName";
	public final static String PATIENT_COL_NAME = "username";
	public final static String _ID = "_id";
	public final static String[] MEDS_COLUMNS = { _ID, MED_NAME };
	public final static String[] PATIENTS_COLUMNS = {_ID,PATIENT_COL_NAME};
	final private static String CREATE_CMD =

	"CREATE TABLE medication (" + _ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ MED_NAME + " TEXT NOT NULL)";
	
	final private static String CREATE_PATIENTS_TABLE_CMD =

			"CREATE TABLE patient (" + _ID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ PATIENT_COL_NAME + " TEXT NOT NULL)";

	final private static String NAME = "symptom_db";
	final private static Integer VERSION = 1;
	final private Context mContext;

	public DbOpenHelper(Context context) {
		super(context, NAME, null, VERSION);
		this.mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_CMD);
		db.execSQL(CREATE_PATIENTS_TABLE_CMD);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// N/A
	}

	void deleteDatabase() {
		mContext.deleteDatabase(NAME);
	}
}
