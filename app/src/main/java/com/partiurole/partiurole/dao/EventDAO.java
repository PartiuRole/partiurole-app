package com.partiurole.partiurole.dao;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.partiurole.partiurole.DataBaseHelper;
import com.partiurole.partiurole.model.Event;

import java.util.ArrayList;

public class EventDAO {
    private static final String TABLE_NAME = "event";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_MIN_PRICE = "min_price";
    public static final String COLUMN_MAX_PRICE = "max_price";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_FAVORITE = "favorite";

    private static SQLiteDatabase db = null;

    public static final String SCRIPT_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_LOCATION + " TEXT,"
            + COLUMN_DATE + " TEXT,"
            + COLUMN_MIN_PRICE + " REAL,"
            + COLUMN_MAX_PRICE + " REAL,"
            + COLUMN_DESCRIPTION + " TEXT,"
            + COLUMN_IMAGE + " TEXT,"
            + COLUMN_FAVORITE + " INTEGER DEFAULT 0"
            + ")";

    public long insert(Event e){
        ContentValues cv = entidadeParacontentValues(e);
        DataBaseHelper persistenceHelper = DataBaseHelper.getInstance();
        db = persistenceHelper.getWritableDatabase();
        long id = db.insert(TABLE_NAME, null, cv);
//        db.close();
        return id;
    }

    public Event update(Event e){
        ContentValues cv = entidadeParacontentValues(e);
        String[] valuesToReplace = {
                String.valueOf(e.getId())
        };
        DataBaseHelper persistenceHelper = DataBaseHelper.getInstance();
        db = persistenceHelper.getWritableDatabase();
        db.update(TABLE_NAME, cv, COLUMN_ID + " = ?", valuesToReplace);
//        db.close();
        return e;
    }

    public Event delete(Event e){
        String[] valuesToReplace = {
                String.valueOf(e.getId())
        };
        DataBaseHelper persistenceHelper = DataBaseHelper.getInstance();
        db = persistenceHelper.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", valuesToReplace);
//        db.close();
        return e;
    }

    public ArrayList<Event> getAll(){
        DataBaseHelper persistenceHelper = DataBaseHelper.getInstance();
        db = persistenceHelper.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<Event> events = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                Event e = new Event();
                e.setId(c.getString(c.getColumnIndex(COLUMN_ID)));
                e.setName(c.getString(c.getColumnIndex(COLUMN_NAME)));
                e.setLocation(c.getString(c.getColumnIndex(COLUMN_LOCATION)));
                e.setDate(c.getString(c.getColumnIndex(COLUMN_DATE)));
                e.setMinPrice(c.getDouble(c.getColumnIndex(COLUMN_MIN_PRICE)));
                e.setMaxPrice(c.getDouble(c.getColumnIndex(COLUMN_MAX_PRICE)));
                e.setDescription(c.getString(c.getColumnIndex(COLUMN_DESCRIPTION)));
                e.setImageUrl(c.getString(c.getColumnIndex(COLUMN_IMAGE)));
                e.setIsFavorite(c.getInt(c.getColumnIndex(COLUMN_FAVORITE)) > 0);
                events.add(e);
            } while (c.moveToNext());
        }
//        db.close();
        return events;
    }

    public Event getById(String id){
        DataBaseHelper persistenceHelper = DataBaseHelper.getInstance();
        db = persistenceHelper.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = ?", new String[]{id});
        Event e = new Event();
        if (c.moveToFirst()) {
            do {
                e.setId(c.getString(c.getColumnIndex(COLUMN_ID)));
                e.setName(c.getString(c.getColumnIndex(COLUMN_NAME)));
                e.setLocation(c.getString(c.getColumnIndex(COLUMN_LOCATION)));
                e.setDate(c.getString(c.getColumnIndex(COLUMN_DATE)));
                e.setMinPrice(c.getDouble(c.getColumnIndex(COLUMN_MIN_PRICE)));
                e.setMaxPrice(c.getDouble(c.getColumnIndex(COLUMN_MAX_PRICE)));
                e.setDescription(c.getString(c.getColumnIndex(COLUMN_DESCRIPTION)));
                e.setImageUrl(c.getString(c.getColumnIndex(COLUMN_IMAGE)));
                e.setIsFavorite(c.getInt(c.getColumnIndex(COLUMN_FAVORITE)) > 0);
            } while (c.moveToNext());
        }
//        db.close();
        return e;
    }

    public ArrayList<Event> getAllFavorites() {
        DataBaseHelper persistenceHelper = DataBaseHelper.getInstance();
        db = persistenceHelper.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_FAVORITE + " = 1", null);
        ArrayList<Event> events = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                Event e = new Event();
                e.setId(c.getString(c.getColumnIndex(COLUMN_ID)));
                e.setName(c.getString(c.getColumnIndex(COLUMN_NAME)));
                e.setLocation(c.getString(c.getColumnIndex(COLUMN_LOCATION)));
                e.setDate(c.getString(c.getColumnIndex(COLUMN_DATE)));
                e.setMinPrice(c.getDouble(c.getColumnIndex(COLUMN_MIN_PRICE)));
                e.setMaxPrice(c.getDouble(c.getColumnIndex(COLUMN_MAX_PRICE)));
                e.setDescription(c.getString(c.getColumnIndex(COLUMN_DESCRIPTION)));
                e.setImageUrl(c.getString(c.getColumnIndex(COLUMN_IMAGE)));
                e.setIsFavorite(c.getInt(c.getColumnIndex(COLUMN_FAVORITE)) > 0);
                events.add(e);
            } while (c.moveToNext());
        }
        c.close();
//        db.close();
        return events;
    }

    public ContentValues entidadeParacontentValues(Event event) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, event.getId());
        contentValues.put(COLUMN_NAME, event.getName());
        contentValues.put(COLUMN_LOCATION, event.getLocation());
        contentValues.put(COLUMN_DATE, event.getDate());
        contentValues.put(COLUMN_MIN_PRICE, event.getMinPrice());
        contentValues.put(COLUMN_MAX_PRICE, event.getMaxPrice());
        contentValues.put(COLUMN_DESCRIPTION, event.getDescription());
        contentValues.put(COLUMN_IMAGE, event.getImageUrl());
        contentValues.put(COLUMN_FAVORITE, event.getIsFavorite());
        return contentValues;
    }

    public Event contentValuesParaEntidade(ContentValues contentValues) {
        Event event = new Event();
        event.setId(contentValues.getAsString(COLUMN_ID));
        event.setName(contentValues.getAsString(COLUMN_NAME));
        event.setLocation(contentValues.getAsString(COLUMN_LOCATION));
        event.setDate(contentValues.getAsString(COLUMN_DATE));
        event.setMinPrice(contentValues.getAsDouble(COLUMN_MIN_PRICE));
        event.setMaxPrice(contentValues.getAsDouble(COLUMN_MAX_PRICE));
        event.setDescription(contentValues.getAsString(COLUMN_DESCRIPTION));
        event.setImageUrl(contentValues.getAsString(COLUMN_IMAGE));
        event.setIsFavorite(contentValues.getAsBoolean(COLUMN_FAVORITE));
        return event;
    }

    public static final String SCRIPT_INSERT_EVENT1 =
            "INSERT INTO " + TABLE_NAME + " VALUES (1, 'Rock in Rio', 'Rio de Janeiro', '2017-09-15', 350.00, 700.00, 'O Rock in Rio é um festival de música idealizado pelo empresário brasileiro Roberto Medina pela primeira vez em 1985, sendo, desde sua criação, reconhecidamente, como o maior festival musical do planeta.', 'https://s3-sa-east-1.amazonaws.com/ingresso-certo/images/eventos/rock-in-rio-2017.jpg', 0);";
    public static final String SCRIPT_INSERT_EVENT2 =
            "INSERT INTO " + TABLE_NAME + " VALUES (2, 'Lollapalooza', 'São Paulo', '2017-03-25', 350.00, 700.00, 'O Lollapalooza Brasil é um festival de música anual que acontece na cidade de São Paulo, Brasil. Criado em 2011 como ramificação do festival norte-americano Lollapalooza, teve sua primeira edição brasileira realizada em 7 e 8 de abril de 2012, no Jockey Club de São Paulo.', 'https://s3-sa-east-1.amazonaws.com/ingresso-certo/images/eventos/lollapalooza-2017.jpg', 0);";
    public static final String SCRIPT_INSERT_EVENT3 =
            "INSERT INTO " + TABLE_NAME + " VALUES (3, 'Tomorrowland', 'Itu', '2017-04-21', 350.00, 700.00, 'Tomorrowland é um festival de música eletrônica realizado anualmente na Bélgica, realizado pela ID&T Belgium. É considerado um dos maiores festivais de música eletrônica do mundo, tendo recebido 180 mil pessoas em 2014, de 214 países diferentes.', 'https://s3-sa-east-1.amazonaws.com/ingresso-certo/images/eventos/tomorrowland-2017.jpg', 0);";

    }
