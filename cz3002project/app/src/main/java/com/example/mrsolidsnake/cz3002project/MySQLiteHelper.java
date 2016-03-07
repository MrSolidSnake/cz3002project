package com.example.mrsolidsnake.cz3002project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by User on 07/03/2016.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    private static MySQLiteHelper instance = null;
    public static final String TABLE_PERSON = "persons";
    public static final String COLUMN_ID ="_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PICTURE = "picture";
    public static final String COLUMN_ANSWERS = "answers";

    private static final String DATABASE_NAME = "CZ3002";
    private static final int DATABASE_VERSION = 1;

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static MySQLiteHelper getInstance(Context context){
        if(instance == null){
            instance = new MySQLiteHelper(context);
        }
        return instance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createPersonTable = "create table "
                + TABLE_PERSON + "(" + COLUMN_ID
                + " integer primary key autoincrement, " + COLUMN_NAME
                + " text not null, " + COLUMN_PICTURE + " text not null, " + COLUMN_ANSWERS + " text not null" + ");";
        db.execSQL(createPersonTable);
        populateSampleData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String deletePersonTable = "DROP TABLE IF EXISTS " + TABLE_PERSON;
        db.execSQL(deletePersonTable);
        onCreate(db);
    }

    /**
     *
     * @param db
     * This method to populate sample data
     */
    public void populateSampleData(SQLiteDatabase db){
        String sampleName [] = {"Barack Obama" , "Donald Trump" , "Mark Zuckerberg" , "Hideo Kojima" , "Bill Gates"};

        String sampleAnswers [] = {  "Barack Obama-George W.Bush-John F.Kennedy-" ,
                "Bill Clinton-Donald Trump-Richard M.Nixon-",
                "Mark Zuckerberg-Bill Gates-Johnny Walker-",
                "Kazuo Hirai-Hideo Kojima-Shigeru Miyamoto-",
                "Steve Jobs-Steve Wozniak-Bill Gates-"
        };

        String sampleImages [] = {"mode1_1", "mode1_2", "mode1_3", "mode1_4", "mode1_5"};

        for(int i = 0; i < 5; i++){
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, sampleName[i]);
            values.put(COLUMN_PICTURE, sampleImages[i]);
            values.put(COLUMN_ANSWERS, sampleAnswers[i]);
            db.insert(TABLE_PERSON, null, values);
        }
    }

    /**
     *
     * @param person
     * @return person with id
     * Call this method to add a person to database, it will return the same object with id inserted
     */
    public Person addPerson(Person person){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(this.COLUMN_NAME, person.getName());
        values.put(this.COLUMN_PICTURE, person.getPicture());
        //value of each possible answers will be separated by "-" symbol
        String answersValue = "";
        for(String str : person.getAnswers()){
            answersValue = answersValue + str +"-";
        }
        answersValue = answersValue.substring(0, answersValue.length() - 1);
        values.put(this.COLUMN_ANSWERS, answersValue);
        long insertId = database.insert(this.TABLE_PERSON, null, values);
        person.setId(insertId);
        return person;

    }

    public int deletePerson(Person person){
        SQLiteDatabase database = this.getWritableDatabase();
        long deleteId = person.getId();
        return database.delete(this.TABLE_PERSON, this.COLUMN_ID + " = " + deleteId, null);
    }


    public ArrayList<Person> getAllPerson(){
        SQLiteDatabase database = this.getReadableDatabase();
        ArrayList<Person> output = new ArrayList<Person>();
        String query = "SELECT * FROM " + this.TABLE_PERSON;
        Cursor cursor = database.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                output.add(cursorToPerson(cursor));
            }while(cursor.moveToNext());
        }
        return output;
    }

    private Person cursorToPerson(Cursor cursor){
        String answersStr = cursor.getString(3);
        Person person = new Person(cursor.getLong(0), cursor.getString(1), cursor.getString(2), answersStr.split("-"));
        return person;
    }
}
