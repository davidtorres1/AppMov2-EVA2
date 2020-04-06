package com.example.eva2_6_sqlite1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteDatabase db = this.openOrCreateDatabase(
                "gamesDB",
                MODE_PRIVATE,
                null);
        db.beginTransaction();
        try {
            //perform your database operations here ...
            db.execSQL("create table Juego ("
                    + " ID integer PRIMARY KEY autoincrement, "
                    + " name text, "
                    + " year text ); " );

            db.execSQL( "insert into Juego(name, year) values ('Super Mario', '1992');" );
            db.execSQL( "insert into Juego(name, year) values ('The Legend Of Zelda', '1985');" );
            db.execSQL( "insert into Juego(name, year) values ('Castlevania', '1989');" );
            db.setTransactionSuccessful(); //commit your changes
            Toast.makeText(this,"SUCCEED",Toast.LENGTH_SHORT).show();
        }
        catch (SQLiteException e) {
            //report problem
            Toast.makeText(this,"FAILED",Toast.LENGTH_SHORT).show();
        }
        finally {
            db.endTransaction();
        }
    }
}
