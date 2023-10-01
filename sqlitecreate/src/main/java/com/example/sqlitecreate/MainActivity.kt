package com.example.sqlitecreate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val database = openOrCreateDatabase("mydb", MODE_PRIVATE, null)
//
//
//        database.execSQL("create table if not exists people (name text,age integer)")
//        database.execSQL("insert into people(name,age) values('Ali', 30)")
//        database.execSQL("insert into people(name,age) values('Umar', 50)")
//        database.execSQL("insert into people(name,age) values('Usmon', 20)")
//        database.execSQL("insert into people(name,age) values('Abbos', 60)")
//
//       // database.execSQL("update people set age = 40 where name = 'Ali'")
//
//        val cursor = database.rawQuery("select * from people where name = 'Ali'",null)
//        cursor.moveToFirst()
//        val nameIndex = cursor.getColumnIndex("name")
//        val ageIndex = cursor.getColumnIndex("age")
//        if (cursor.moveToFirst()){
//            do{
//                val name = cursor.getString(nameIndex)
//                val age = cursor.getInt(ageIndex)
//                Log.d("TAG","onCreate: $name,age: $age ")
//            }while (cursor.moveToNext())
//        }
//
//        cursor.close()
//        database.close()


        val dbManager = MyDbManager(this)
        dbManager.onCreate()
        dbManager.insert("Ali",20,"Namangan")
        dbManager.insert("Usmon",40,"Toshkent")
        dbManager.insert("Abbos",30,"Buxoro")

        dbManager.update(1,"Usmon",50,"Toshkent")

        val cursor = dbManager.fetch()
//        if (cursor != null){

//            val idIndex = cursor.getColumnIndex(MySQLiteHelper.id)
//
//            do {
//                val id = cursor.getInt(idIndex)
//
//                Log.d("TAG","onCreate")
//            }while (cursor.moveToNext())
            //tugirlash kere
       // }

    }
}