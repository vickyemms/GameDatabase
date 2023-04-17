package com.example.gamedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GameDao extends SQLiteOpenHelper {

    public static final String GAME_TABLE = "GAME_TABLE";
    public static final String COLUMN_GAME_NAME = "GAME_NAME";
    public static final String COLUMN_GAME_YEAR = "GAME_YEAR";
    public static final String COLUMN_GAME_MIN_PLAYERS = "GAME_MIN_PLAYERS";
    public static final String COLUMN_GAME_MAX_PLAYERS = "GAME_MAX_PLAYERS";
    public static final String COLUMN_GAME_GENRE = "GAME_GENRE";
    public static final String COLUMN_ID = "ID";


    public GameDao(@Nullable Context context) {
        super(context, "game.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement = "CREATE TABLE " + GAME_TABLE +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
        + COLUMN_GAME_NAME + " TEXT, " + COLUMN_GAME_YEAR + " INT, " + COLUMN_GAME_MIN_PLAYERS +
                " INT, " + COLUMN_GAME_MAX_PLAYERS + " INT, " + COLUMN_GAME_GENRE + " TEXT)";

        sqLiteDatabase.execSQL(createTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addGame(Game game){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_GAME_NAME, game.getName());
        cv.put(COLUMN_GAME_YEAR, game.getYear());
        cv.put(COLUMN_GAME_MIN_PLAYERS, game.getMinPlayers());
        cv.put(COLUMN_GAME_MAX_PLAYERS, game.getMaxPlayers());
        cv.put(COLUMN_GAME_GENRE, game.getGenre());

        long insert = db.insert(GAME_TABLE, null, cv);
        if(insert == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean deleteGame(Game game){
        SQLiteDatabase db = this.getWritableDatabase();

        String queryString = "DELETE FROM " + GAME_TABLE + " WHERE " + COLUMN_ID + " = " + game.getId();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            return true;
        } else {
            return false;
        }

    }

    public List<Game> getAllGames(){

        List<Game> gameList = new ArrayList<>();

        String queryString = "SELECT * FROM " + GAME_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            do {
                int gameID = cursor.getInt(0);
                String name = cursor.getString(1);
                int year = cursor.getInt(2);
                int minPlayers = cursor.getInt(3);
                int maxPlayers = cursor.getInt(4);
                String genre = cursor.getString(5);

                Game game = new Game(gameID, name, year, minPlayers, maxPlayers, genre);

                gameList.add(game);
            } while(cursor.moveToNext());

        } else {

        }

        cursor.close();
        db.close();

        return gameList;

    }



}
