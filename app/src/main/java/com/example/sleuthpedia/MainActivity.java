package com.example.sleuthpedia;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.sleuthpedia.fragments.HomeFragment;
import com.example.sleuthpedia.fragments.OptionsFragment;
import com.example.sleuthpedia.fragments.TeamFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        setupBottomNavigation();

//        logDatabaseVersion();

        db = new DatabaseHelper(this).init();
//        queryDataTest();
    }

    @Override
    protected void onDestroy() {
        if(db != null) {
            db.close();
        }
        super.onDestroy();
    }

    private void setupBottomNavigation() {
        HashMap<Integer, Fragment> fragmentMap = new HashMap<>();
            fragmentMap.put(R.id.menu_home, new HomeFragment());
            fragmentMap.put(R.id.menu_team, new TeamFragment());
            fragmentMap.put(R.id.menu_options, new OptionsFragment());

        BottomNavigationView bottomMenu = findViewById(R.id.bottomMenu);
        bottomMenu.setItemIconTintList(null);

        bottomMenu.setOnItemSelectedListener(item -> {
            Fragment fragment = fragmentMap.get(item.getItemId());
            if(fragment == null) return false;

            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
            return true;
        });
    }

    private void queryDataTest() {
        Cursor cursor = db.rawQuery("SELECT * FROM test", null);

        if(cursor.moveToFirst()) {
            do {
                String data = cursor.getString(cursor.getColumnIndexOrThrow("gold"));
                Log.d("PRINT", data);
            } while(cursor.moveToNext());
        }
        cursor.close();
    }

    private void logDatabaseVersion() {
        String dbPath = getDatabasePath(DatabaseHelper.DB_NAME).toString();
        SQLiteDatabase database = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
        Log.d("PRINT", "DB VERSION -> " + database.getVersion());
        database.close();
    }
}