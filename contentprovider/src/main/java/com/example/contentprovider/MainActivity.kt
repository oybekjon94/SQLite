package com.example.contentprovider

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.UserDictionary
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val search = findViewById<EditText>(R.id.search)

        search.setOnEditorActionListener { _, action,_ ->
            if (action == EditorInfo.IME_ACTION_SEARCH){
                show(search.text.toString())

                Toast.makeText(this,search.text.toString(), Toast.LENGTH_SHORT).show()
                return@setOnEditorActionListener true
            }

            return@setOnEditorActionListener false
        }

        val add = findViewById<FloatingActionButton>(R.id.add)
        add.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Add word")

            val wordEt = EditText(this)
            dialog.setView(wordEt)

            dialog.setPositiveButton("ADD"){ dialog, _ ->
                dialog.dismiss()

                val word = wordEt.text.toString()
                insert(word)
                Toast.makeText(this, word,Toast.LENGTH_SHORT).show()
            }
            dialog.show()
        }
    }
    private fun insert(word:String){
        val values = ContentValues().apply {
            put(UserDictionary.Words.APP_ID,packageName)
            put(UserDictionary.Words.WORD,word)
            put(UserDictionary.Words.LOCALE,"en_US")
            put(UserDictionary.Words.FREQUENCY,"100")
        }

        val uri = contentResolver.insert(
            UserDictionary.Words.CONTENT_URI,
            values
        )
        print(uri)
    }

    private fun show(query:String){
        val project = arrayOf(
            UserDictionary.Words._ID,
            UserDictionary.Words.WORD,
            UserDictionary.Words.LOCALE
        )

        var selectionClause:String? = null
        val selectionArgs = if (query.isNotBlank()){
            selectionClause = "${UserDictionary.Words.WORD} = ?"
            arrayOf(query)
        }else{
            emptyArray()
        }

        val sortOrder = "${UserDictionary.Words.WORD} ASC"

        val cursor = contentResolver.query(
            UserDictionary.Words.CONTENT_URI,
            project,
            selectionClause,
            selectionArgs,
            sortOrder
        )

        val words = findViewById<ListView>(R.id.words)
        when(cursor?.count){
            null -> {
                Toast.makeText(this,"Error occurred",Toast.LENGTH_SHORT).show()
            }
            0 -> {
                words.adapter = null
            }
            else -> {
                val columns = arrayOf(
                    UserDictionary.Words.WORD,
                    UserDictionary.Words.LOCALE
                )
                val ids = intArrayOf(
                    R.id.word,
                    R.id.locale
                )
                val adapter = SimpleCursorAdapter(
                    this,
                    R.layout.item_words,
                    cursor,
                    columns,
                    ids,
                    0
                )
                words.adapter = adapter
            }
        }
        cursor?.close()
    }
}