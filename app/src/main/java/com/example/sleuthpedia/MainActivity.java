package com.example.sleuthpedia;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.sleuthpedia.fragments.menu.HomeFragment;
import com.example.sleuthpedia.fragments.menu.OptionsFragment;
import com.example.sleuthpedia.fragments.menu.TeamFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        setupBottomNavigation();


        DatabaseHelper.init(this);

        queryDataTest();
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

            getSupportFragmentManager().beginTransaction().replace(R.id.menuFragmentContainer, fragment).commit();
            return true;
        });

        bottomMenu.setSelectedItemId(R.id.menu_home);
    }

    }

    private void queryDataTest() {
        Cursor cursor = DatabaseHelper.getInstance().getReadableDatabase().rawQuery("SELECT * FROM digimon", null);
        StringBuilder sb = new StringBuilder();

        if(cursor.moveToFirst()) {
            do {
                String data = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                sb.append(data + " | ");
            } while(cursor.moveToNext());
        }
        Log.d("PRINT", sb.toString());
        cursor.close();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }
}