package com.example.sleuthpedia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper instance;

    public static final String DB_NAME = "sleuth.db";

    private DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        copyDatabaseFromAssets(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {Log.d("PRINT", "ONCREATE CALLED");}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {Log.d("PRINT", "UPGRADE CALLED | " + oldVersion + " -> " + newVersion);}

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {Log.d("PRINT", "DOWNGRADE CALLED | " + oldVersion + " -> " + newVersion);}

    public static void init(Context context) {
        if(instance == null) {
            instance = new DatabaseHelper(context);
        }
    }

    public static DatabaseHelper getInstance() {
        if(instance == null) {
            throw new IllegalStateException("Database not initialized. Call init() first");
        }
        return instance;
    }

    private void copyDatabaseFromAssets() {
        try (InputStream input = context.getAssets().open(DB_NAME); OutputStream output = new FileOutputStream(dbPath)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
        } catch(Exception e) {
            Log.d("PRINT", e.getMessage());
        }
    }
}