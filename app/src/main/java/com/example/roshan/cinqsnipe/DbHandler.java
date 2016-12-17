package com.example.roshan.cinqsnipe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;

public class DbHandler extends SQLiteOpenHelper {
    public static final String database_name="News";
    public static final String table_name="news";
    public static final String col_1=" _id";
    public static final String col_2="title";
    public static final String col_3="description";
    public static final String col_4="link";
    public static final String col_5="category";
    public DbHandler(Context context) {
        super(context, database_name, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + table_name + "( _id integer primary key autoincrement  ,title text,description text,link text,category text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists" + table_name);
       // db.execSQL("ALTER TABLE "+ table_name +" ADD "+ col_1 +" INTEGER PRIMARY KEY AUTOINCREMENT");
        onCreate(db);
    }

    public boolean insertData(String id,String title,String description,String link,String category){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(col_1, id);
        values.put(col_2,title);
        values.put(col_3,description);
        values.put(col_4, link);
        values.put(col_5, category);
        long result= db.insert(table_name,null,values);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Cursor viewData(){
        SQLiteDatabase db=this.getWritableDatabase();
      // Cursor cr=db.rawQuery("select * from " + table_name,null);
      Cursor cr =  db.rawQuery( "select _id as _id,  title, description, link, category from news",null);

       // Cursor cr =  db.rawQuery( "select " + col_1 + "," + col_2 + "," + col_3 + "," + col_4 + "," + col_5 + " from " + table_name, null);
        Log.d("Cursor Size ", cr.getCount() +"");
        cr.getCount();
        return cr;

    }


//    public List<BlogDetail> Viewdata() {
//        List<BlogDetail> alldata = new ArrayList<BlogDetail>();
//        // Select All Query
//        String selectQuery = "SELECT  * FROM " + table_name;
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//       Log.d("Cursor Size ", cursor.getCount() +"");
//        cursor.getCount();
//        if (cursor.moveToFirst()) {
//            do {
//                BlogDetail blogDetail = new BlogDetail();
//                blogDetail.setId(cursor.getString(0));
//                blogDetail.setTitle(cursor.getString(1));
//                blogDetail.setDescription(cursor.getString(2));
//                blogDetail.setLink(cursor.getString(3));
//                blogDetail.setCategory(cursor.getString(4));
//                alldata.add(blogDetail);
//            } while (cursor.moveToNext());
//        }
//
//        return alldata;
//    }

//    public boolean updateData(String title,String description,String link,String category){
//        SQLiteDatabase db=this.getWritableDatabase();
//        ContentValues values=new ContentValues();
//        values.put(col_1,title);
//        values.put(col_2,description);
//        values.put(col_3,link);
//        values.put(col_4,category);
//        int result  = db.update(table_name,values,col_1 + "=?",new String[]{id});
//
//        if(result == 1){
//            return true;
//        }
//        return false;
//    }
//    public Integer deleteData(String id){
//        SQLiteDatabase db=this.getWritableDatabase();
//        return db.delete(table_name,"id=?",new String[] {id});
//
//    }
//public void deletedata(String table_name) {
//    SQLiteDatabase db=this.getWritableDatabase();
//    String clearDBQuery = "drop table "+ table_name;
//    db.execSQL(clearDBQuery);
//}
}