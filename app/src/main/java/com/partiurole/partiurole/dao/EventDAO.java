package com.partiurole.partiurole.dao;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.partiurole.partiurole.DataBaseHelper;
import com.partiurole.partiurole.model.Event;

import java.util.ArrayList;

public class EventDAO {
    private static final String TABLE_NAME = "event";
    public static final String COLUMN_UUID = "uuid";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_LOCATION_URL = "location_url";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_URL = "url";
    public static final String COLUMN_CREATED_AT = "created_at";
    public static final String COLUMN_UPDATED_AT = "updated_at";
    public static final String COLUMN_FAVORITE = "favorite";
    public static final String COLUMN_IMAGE_BASE64 = "image_base64";
    private static SQLiteDatabase db = null;

    public static final String SCRIPT_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_UUID + " TEXT PRIMARY KEY,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_LOCATION + " TEXT,"
            + COLUMN_LOCATION_URL + " TEXT,"
            + COLUMN_DATE + " TEXT,"
            + COLUMN_PRICE + " REAL,"
            + COLUMN_DESCRIPTION + " TEXT,"
            + COLUMN_IMAGE + " TEXT,"
            + COLUMN_IMAGE_BASE64 + " TEXT,"
            + COLUMN_URL + " TEXT,"
            + COLUMN_CREATED_AT + " TEXT,"
            + COLUMN_UPDATED_AT + " TEXT,"
            + COLUMN_FAVORITE + " INTEGER DEFAULT 0"
            + ")";

    public long insert(Event e){
        ContentValues cv = entidadeParacontentValues(e);
        DataBaseHelper persistenceHelper = DataBaseHelper.getInstance();
        db = persistenceHelper.getWritableDatabase();
        long id = db.insert(TABLE_NAME, null, cv);
        return id;
    }

    public Event update(Event e){
        ContentValues cv = entidadeParacontentValues(e);
        String[] valuesToReplace = {
                String.valueOf(e.getUuid())
        };
        cv.remove(COLUMN_IMAGE_BASE64);
        DataBaseHelper persistenceHelper = DataBaseHelper.getInstance();
        db = persistenceHelper.getWritableDatabase();
        db.update(TABLE_NAME, cv, COLUMN_UUID + " = ?", valuesToReplace);
        return e;
    }

    public Event updateImageBase64(Event e){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_IMAGE_BASE64, e.getImageBase64());
        String[] valuesToReplace = {
                String.valueOf(e.getUuid())
        };
        DataBaseHelper persistenceHelper = DataBaseHelper.getInstance();
        db = persistenceHelper.getWritableDatabase();
        db.update(TABLE_NAME, cv, COLUMN_UUID + " = ?", valuesToReplace);
        return e;
    }

    public Event delete(Event e){
        String[] valuesToReplace = {
                String.valueOf(e.getUuid())
        };
        DataBaseHelper persistenceHelper = DataBaseHelper.getInstance();
        db = persistenceHelper.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_UUID + " = ?", valuesToReplace);
        return e;
    }

    public ArrayList<Event> getAll(){
        DataBaseHelper persistenceHelper = DataBaseHelper.getInstance();
        db = persistenceHelper.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<Event> events = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                events.add(cursorParaEntidade(c));
            } while (c.moveToNext());
        }
        return events;
    }

    public ArrayList<Event> getRandom(){
        DataBaseHelper persistenceHelper = DataBaseHelper.getInstance();
        db = persistenceHelper.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY RANDOM() LIMIT 5", null);
        ArrayList<Event> events = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                events.add(cursorParaEntidade(c));
            } while (c.moveToNext());
        }
        return events;
    }

    public ArrayList<Event> getAllFree(){
        DataBaseHelper persistenceHelper = DataBaseHelper.getInstance();
        db = persistenceHelper.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_PRICE + " = 0", null);
        ArrayList<Event> events = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                events.add(cursorParaEntidade(c));
            } while (c.moveToNext());
        }
        return events;
    }


    public Event getById(String id){
        DataBaseHelper persistenceHelper = DataBaseHelper.getInstance();
        db = persistenceHelper.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_UUID + " = ?", new String[]{id});
        Event e = new Event();
        if (c.moveToFirst()) {
            e = cursorParaEntidade(c);
        }
        c.close();
        return e;
    }

    public ArrayList<Event> getAllFavorites() {
        DataBaseHelper persistenceHelper = DataBaseHelper.getInstance();
        db = persistenceHelper.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_FAVORITE + " = 1", null);
        ArrayList<Event> events = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                events.add(cursorParaEntidade(c));
            } while (c.moveToNext());
        }
        c.close();
        return events;
    }

    public ContentValues entidadeParacontentValues(Event event) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_UUID, event.getUuid());
        contentValues.put(COLUMN_NAME, event.getName());
        contentValues.put(COLUMN_DATE, event.getDate());
        contentValues.put(COLUMN_PRICE, event.getPrice());
        contentValues.put(COLUMN_DESCRIPTION, event.getDescription());
        contentValues.put(COLUMN_IMAGE, event.getImage());
        contentValues.put(COLUMN_URL, event.getUrl());
        contentValues.put(COLUMN_CREATED_AT, event.getCreatedAt());
        contentValues.put(COLUMN_UPDATED_AT, event.getUpdatedAt());
        contentValues.put(COLUMN_FAVORITE, event.getIsFavorite() ? 1 : 0);
        contentValues.put(COLUMN_LOCATION_URL, event.getLocationUrl());
        contentValues.put(COLUMN_LOCATION, event.getLocation());
        contentValues.put(COLUMN_IMAGE_BASE64, event.getImageBase64());
        return contentValues;
    }

    public Event cursorParaEntidade(Cursor c) {
        Event e = new Event();
        e.setUuid(c.getString(c.getColumnIndex(COLUMN_UUID)));
        e.setName(c.getString(c.getColumnIndex(COLUMN_NAME)));
        e.setDate(c.getString(c.getColumnIndex(COLUMN_DATE)));
        e.setPrice(c.getDouble(c.getColumnIndex(COLUMN_PRICE)));
        e.setDescription(c.getString(c.getColumnIndex(COLUMN_DESCRIPTION)));
        e.setImage(c.getString(c.getColumnIndex(COLUMN_IMAGE)));
        e.setUrl(c.getString(c.getColumnIndex(COLUMN_URL)));
        e.setCreatedAt(c.getString(c.getColumnIndex(COLUMN_CREATED_AT)));
        e.setUpdatedAt(c.getString(c.getColumnIndex(COLUMN_UPDATED_AT)));
        e.setIsFavorite(c.getInt(c.getColumnIndex(COLUMN_FAVORITE)) > 0);
        e.setLocationUrl(c.getString(c.getColumnIndex(COLUMN_LOCATION_URL)));
        e.setLocation(c.getString(c.getColumnIndex(COLUMN_LOCATION)));
        e.setImageBase64(c.getString(c.getColumnIndex(COLUMN_IMAGE_BASE64)));
        return e;
    }
}
