package com.example.sqlite01.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.sqlite01.models.Student

class MyDbHelper(context: Context):SQLiteOpenHelper(
    context, DB_NAME,null, DB_VERSION
),DatabaseService {
   companion object {
       const val DB_NAME  = "BIRINCHI DARS"
       const val DB_VERSION = 1

       //jadval create qilamiz
       const val TABLE_NAME = "student"
       const val STUDENT_ID = "id"
       const val STUDENT_NAME = "name"
       const val STUDENT_AGE = "age"
       const val PHONE_NUMBER = "phone_number"
   }

    override fun onCreate(db: SQLiteDatabase?) {
        //so'rov
        val query = "create table $TABLE_NAME($STUDENT_ID integer not null primary key autoincrement," +
                "$STUDENT_NAME text not null, $STUDENT_AGE integer not null,$PHONE_NUMBER text not null)"
        //create qilib ber
        db?.execSQL(query)
    }
//migration
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
      //  "alter table product add column skidka default 0"
    }

    override fun addStudent(student: Student) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        //content valuesda biza ozimizni qiymatlarimizni
        //set qilishimiz kere boladi
        contentValues.put(STUDENT_NAME, student.name)
        contentValues.put(STUDENT_AGE, student.age)
        contentValues.put(PHONE_NUMBER, student.phoneNumber)
        database.insert(
            TABLE_NAME,null,contentValues
        )
    }

    override fun listStudents(): List<Student> {
        val list = ArrayList<Student>()
        //xamma studentlarni chaqirish
        val database = this.readableDatabase
        val query = "select * from $TABLE_NAME"
        val cursor = database.rawQuery(query,null)
        if (cursor.moveToFirst()){
            do {
   //birinchi qatordagi malumotni oqish kere
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val age = cursor.getInt(2)
                val phoneNumber = cursor.getString(3)
                val student = Student(id,name,age,phoneNumber)
                list.add(student)
            }while (cursor.moveToNext())
        }
        return list
    }

    override fun editStudent(student: Student) {


    }

    override fun deleteStudent(student: Student) {


    }

    override fun getStudentsCount(): Int {
       return 0
    }

//    override fun getStudentById(id: Int): Student {
//   return 0
//    }
}