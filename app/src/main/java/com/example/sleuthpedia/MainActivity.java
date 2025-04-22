package com.example.sleuthpedia;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

//        String dbPath = getDatabasePath(DatabaseHelper.DB_NAME).toString();
//        SQLiteDatabase database = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
//        Log.d("PRINT", "DB VERSION -> " + database.getVersion());

        db = new DatabaseHelper(this).init();
        queryData();
    }

    @Override
    protected void onDestroy() {
        if(db != null) {
            db.close();
        }
        super.onDestroy();
    }

    private void queryData() {
        Cursor cursor = db.rawQuery("SELECT * FROM test", null);

        if(cursor.moveToFirst()) {
            do {
                String data = cursor.getString(cursor.getColumnIndexOrThrow("gold"));
                Log.d("PRINT", data);
            } while(cursor.moveToNext());
        }
        cursor.close();
    }
}