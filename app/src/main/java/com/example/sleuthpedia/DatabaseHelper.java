package com.example.sleuthpedia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.sleuthpedia.dscs.Digimon;
import com.example.sleuthpedia.dscs.Move;
import com.example.sleuthpedia.dscs.SupportSkill;
import com.example.sleuthpedia.enums.Attribute;
import com.example.sleuthpedia.enums.DigimonType;
import com.example.sleuthpedia.enums.MoveType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

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

    public List<Digimon> getAllDigimons() {
        ArrayList<Digimon> digimons = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("digimon",
                new String[]{"id", "name", "type_id", "attribute_id"},
                null, null, null, null, null);

        if(cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                int typeId = cursor.getInt(cursor.getColumnIndexOrThrow("type_id"));
                int attributeId = cursor.getInt(cursor.getColumnIndexOrThrow("attribute_id"));
                digimons.add(new Digimon(id, name, DigimonType.values()[typeId], Attribute.values()[attributeId]));
            } while(cursor.moveToNext());
        }

        GD.print("All Digimons queried successfully!");
        cursor.close();
        return digimons;
    }

    public List<Move> getAllMoves() {
        ArrayList<Move> moves = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("move",
                new String[]{"id", "name", "description", "attribute_id", "move_type_id", "sp_cost", "power", "inherit"},
                null, null, null, null, "move.name");

        if(cursor.moveToFirst()){
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
                int attributeId = cursor.getInt(cursor.getColumnIndexOrThrow("attribute_id"));
                int moveTypeId = cursor.getInt(cursor.getColumnIndexOrThrow("move_type_id"));
                int spCost = cursor.getInt(cursor.getColumnIndexOrThrow("sp_cost"));
                int power = cursor.getInt(cursor.getColumnIndexOrThrow("power"));
                boolean inherit = cursor.getInt(cursor.getColumnIndexOrThrow("inherit")) != 0;
                moves.add(new Move(id, name, description, Attribute.values()[attributeId], MoveType.values()[moveTypeId], spCost, power, inherit));
            } while (cursor.moveToNext());
        }

        GD.print("All Moves queried sucessfully!");
        cursor.close();
        return moves;
    }

    public List<SupportSkill> getAllSupportSkills() {
         ArrayList<SupportSkill> supportSkills = new ArrayList<>();
         SQLiteDatabase db = getReadableDatabase();
         Cursor cursor = db.query("support_skill",
                 new String[]{"id", "name", "description"},
                 null, null, null, null, "support_skill.name");

         if(cursor.moveToFirst()){
             do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
                supportSkills.add(new SupportSkill(id, name, description));
             } while (cursor.moveToNext());
         }

         GD.print("All Support Skills queried successfully!");
         cursor.close();
         return supportSkills;
    }

    private void copyDatabaseFromAssets(Context context) {
        String pathTo = context.getDatabasePath(DB_NAME).getPath();
        if(!new File(pathTo).exists()) return; // If DB already exists don't copy.

        try (InputStream input = context.getAssets().open(DB_NAME); OutputStream output = new FileOutputStream(pathTo)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            Log.d("PRINT", "Database copied successfully!");
        } catch(Exception e) {
            Log.d("PRINT", e.getMessage());
        }
    }
}