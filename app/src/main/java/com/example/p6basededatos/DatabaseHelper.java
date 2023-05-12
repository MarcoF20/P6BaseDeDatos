package com.example.p6basededatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.security.PrivateKey;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private  static final String DATABASE_NAME="contactManager";
    private static final String DATABASE_TABLE = "contacts";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PhoneNumber = "phoneNumber";

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACT_TABLE = "CREATE TABLE " + DATABASE_TABLE+"("+KEY_ID+"INTEGER PRIMARY KEY,"+KEY_NAME+"TEXT,"+KEY_PhoneNumber+"TEXT)";
        db.execSQL(CREATE_CONTACT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE);
    }

    //CRUD OPERATIONS
    //Agregar contacto

    public void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PhoneNumber, contact.getPhoneNumber());
        db.insert(DATABASE_TABLE,null, values);
        db.close();
    }
    public Contact readContact(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DATABASE_TABLE, new String[]{KEY_ID,KEY_NAME,KEY_PhoneNumber}, KEY_ID+"=?",
                new String[]{String.valueOf(id)}, null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2));
        return contact;
    }
}
