package com.example.monir.bdayreminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by monir on 30-Jul-18.
 */

public class BirthDayDataBaseAdapter {

    BirthDayBaseHelper birthDayBaseHelper;
    Context context;

    public BirthDayDataBaseAdapter(Context context)
    {
        this.context=context;
        birthDayBaseHelper=new BirthDayBaseHelper(context);
    }

    //-------------------------Insert function------------------------------------

    public void insertBirthDay(BirthDayModel birthDayModel)
    {
        SQLiteDatabase mydb= birthDayBaseHelper.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put(BirthDayBaseHelper.FRIEND_NAME,birthDayModel.getName());
        values.put(BirthDayBaseHelper.FRIEND_BDAY,birthDayModel.getDate());

        try
        {
            mydb.insert(BirthDayBaseHelper.BIRTHDAY_TABLE_NAME,null,values);
            Toast.makeText(context,"Data inserted successfully",Toast.LENGTH_LONG).show();

        }catch (SQLException e)
        {
            Toast.makeText(context,"SQLException :"+e,Toast.LENGTH_LONG).show();
        }


    }

    //===========Get all records from birth day table==============================================

    public ArrayList<BirthDayModel> getAllBirthdayRecords()
    {
        Cursor cursor=null;
        int count=0;
        ArrayList<BirthDayModel>birthdayList=new ArrayList<BirthDayModel>();
        SQLiteDatabase mydb = birthDayBaseHelper.getReadableDatabase();
        //----- select query---------
         String selectQuery="SELECT * FROM "+BirthDayBaseHelper.BIRTHDAY_TABLE_NAME;

         try
         {
             cursor=mydb.rawQuery(selectQuery,null);
             count=cursor.getCount();
             Toast.makeText(context,"total number of rows founded :"+count,Toast.LENGTH_LONG).show();

         }catch (SQLException e)
         {
             Toast.makeText(context,"SQLException :"+e,Toast.LENGTH_LONG).show();
         }
        //-----looping through all rows and add to arraylist-------------------

         if(cursor.moveToFirst())
         {
             do{
                   BirthDayModel birthDayModel=new BirthDayModel();

                   int id=cursor.getColumnIndex(BirthDayBaseHelper.ID);
                   birthDayModel.setId(cursor.getInt(id));

                   int nameIndex=cursor.getColumnIndex(BirthDayBaseHelper.FRIEND_NAME);
                   birthDayModel.setName(cursor.getString(nameIndex));

                   int bdayIndex=cursor.getColumnIndex(BirthDayBaseHelper.FRIEND_BDAY);
                   birthDayModel.setDate(cursor.getString(bdayIndex));

                   //------add to arraylist--------------

                 birthdayList.add(birthDayModel);
             }while (cursor.moveToNext());
         }


         return birthdayList;
    }


    //==============================Update BirthdayRecords=========================================

    public void UpdateBirthDayInfo(BirthDayModel birthDayModel,int id)
    {
        Toast.makeText(context,"UpdateBirthDayInfo() method is called",Toast.LENGTH_LONG).show();
        SQLiteDatabase mydb=birthDayBaseHelper.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put(BirthDayBaseHelper.FRIEND_NAME,birthDayModel.getName());
        values.put(BirthDayBaseHelper.FRIEND_BDAY,birthDayModel.getDate());
        String [] whereArgs={String.valueOf(id)};

        try
        {
            mydb.update(BirthDayBaseHelper.BIRTHDAY_TABLE_NAME,values,BirthDayBaseHelper.ID+"=?",whereArgs);
            Toast.makeText(context," Birth Day Records Updated Successfully",Toast.LENGTH_LONG).show();
        }catch (SQLException e)
        {
            Toast.makeText(context,"Exception : "+e,Toast.LENGTH_LONG).show();
        }
    }

    //================================Delete Birthday Records=======================================

    public void DeleteBirthDayInfo(int id)
    {
        Toast.makeText(context,"DeleteBirthDayInfo() is called ",Toast.LENGTH_LONG).show();
        SQLiteDatabase mydb=birthDayBaseHelper.getWritableDatabase();

        String [] whereArgs={String.valueOf(id)};

        try
        {
            mydb.delete(BirthDayBaseHelper.BIRTHDAY_TABLE_NAME,BirthDayBaseHelper.ID+"=?",whereArgs);
            Toast.makeText(context,"Birth Day Record is Deleted Successfully ",Toast.LENGTH_LONG).show();
        }catch (SQLException e)
        {
            Toast.makeText(context,"Exception : "+e,Toast.LENGTH_LONG).show();
        }

    }

    //==================================== database helper class====================================
    public class BirthDayBaseHelper extends SQLiteOpenHelper {

        private final static String DATABASE_NAME="birthdaydb";
        private  final static  int VERSION_NUMBER=1;
        private  Context context;

        //-------table name---------
        private  final static String BIRTHDAY_TABLE_NAME="birthdayTable";
        //-----birthdayTable column name-----------------

        private final static String ID="_id";
        private  final static  String FRIEND_NAME="name";
        private final  static  String FRIEND_BDAY="birthday";

        private  final static  String CREATE_TABLE_BIRTHDAY="CREATE TABLE "+BIRTHDAY_TABLE_NAME+"( "
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +FRIEND_NAME+" VARCHAR, "+FRIEND_BDAY+" VARCHAR);";


        private  final static  String DROP_TABLE_BIRTHDAY="DROP TABLE IF EXISTS"+BIRTHDAY_TABLE_NAME;


        public BirthDayBaseHelper(Context context) {
            super(context, BIRTHDAY_TABLE_NAME, null, VERSION_NUMBER);
            this.context=context;
        }

        //---------------------------creating table---------------------------
        @Override
        public void onCreate(SQLiteDatabase db) {

            try{

                Toast.makeText(context,"onCreate is called", Toast.LENGTH_LONG).show();
                db.execSQL(CREATE_TABLE_BIRTHDAY);
                Toast.makeText(context,"Table is created",Toast.LENGTH_LONG).show();

            }catch (SQLException e)
            {
                Toast.makeText(context,"SQLException :"+e,Toast.LENGTH_LONG).show();
            }

        }

        //-----------------------------Updating table------------------------------------
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            try{
                Toast.makeText(context,"onUpdate is called",Toast.LENGTH_LONG).show();
                db.execSQL(DROP_TABLE_BIRTHDAY);
                Toast.makeText(context,"Table is dropped",Toast.LENGTH_LONG).show();
                onCreate(db);

            }catch (SQLException e)
            {
                Toast.makeText(context,"SQLException "+e,Toast.LENGTH_LONG).show();
            }

        }
    }
}
