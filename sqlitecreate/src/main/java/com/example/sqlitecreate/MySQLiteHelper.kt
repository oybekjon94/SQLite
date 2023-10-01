package com.example.sqlitecreate

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MySQLiteHelper(context: Context):SQLiteOpenHelper(context,"mydb.db",null,1) {

    companion object{
        const val tableName = "People"
        const val id = "_id"
        const val name = "name"
        const val age = "age"
        const val address = "adress"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table $tableName ($id integer primary key autoincrement, $name text,$age integer,$address text);")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists $tableName")
        onCreate(db)
    }
}