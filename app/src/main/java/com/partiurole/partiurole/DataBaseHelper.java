package com.partiurole.partiurole;



import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.partiurole.partiurole.dao.EventDAO;
import com.partiurole.partiurole.util.MyApplication;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int VERSAO_BANCO_DADOS = 1;
    public static final String NOME_BANCO_DADOS = "PartiuRole.bak";

    private static DataBaseHelper instance;

    public static DataBaseHelper getInstance() {
        if(instance == null)
            instance = new DataBaseHelper();
        return instance;
    }

    private DataBaseHelper() {
        super(MyApplication.getInstance(), NOME_BANCO_DADOS, null, VERSAO_BANCO_DADOS);
    }

    @Override
    public void onOpen(SQLiteDatabase database) {
        super.onOpen(database);
        if (!database.isReadOnly()) {
            database.execSQL("PRAGMA foreign_keys=OFF;"); // Disable foreign key constraints
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("DATABASE", "CREATING TABLES");
        db.execSQL(EventDAO.SCRIPT_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("DATABASE", "UPDATING TABELA");
        if(oldVersion < 1){
            db.execSQL(EventDAO.SCRIPT_CREATE_TABLE);
        }
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}