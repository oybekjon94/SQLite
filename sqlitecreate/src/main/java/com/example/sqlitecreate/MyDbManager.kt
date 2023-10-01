package com.example.sqlitecreate

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

@Suppress("UNUSED_EXPRESSION")
class MyDbManager(private val context: Context) {

    private lateinit var sqLiteHelper: MySQLiteHelper
    private lateinit var database:SQLiteDatabase

    fun onCreate(){
        sqLiteHelper = MySQLiteHelper(context)
        database = sqLiteHelper.writableDatabase

    }

    fun insert(name:String,age:Int,address:String){
        val contentValues = ContentValues()
        contentValues.put(MySQLiteHelper.name,name)
        contentValues.put(MySQLiteHelper.age,age)
        contentValues.put(MySQLiteHelper.address,address)
        database.insert(MySQLiteHelper.tableName,null,contentValues)
    }

    fun fetch(){
        val cursor = database.query(MySQLiteHelper.tableName, arrayOf(MySQLiteHelper.id,MySQLiteHelper.name,MySQLiteHelper.address),null,null,null,null,null)

        if (cursor.moveToFirst()){
           cursor
        }else{
            null
        }

    }

    fun update(id:Int, name:String, age:Int, address: String):Int {
        val contentValues = ContentValues()
        contentValues.put(MySQLiteHelper.name,name)
        contentValues.put(MySQLiteHelper.age,age)
        contentValues.put(MySQLiteHelper.address,address)
        return database.update(
            MySQLiteHelper.tableName,
            contentValues,
            "${MySQLiteHelper.id} = $id",
            null
        )
    }

    fun delete(id: Int){
        database.delete(MySQLiteHelper.tableName,"${MySQLiteHelper.id} = $id",null)
    }
}