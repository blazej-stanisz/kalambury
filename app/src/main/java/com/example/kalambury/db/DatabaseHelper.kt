package com.example.kalambury.db

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.util.Log
import java.io.InputStream

class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context,
    DatabaseSettings.DATABASE_NAME,
    null,
    DatabaseSettings.DATABASE_VERSION
) {

    override fun onCreate(db: SQLiteDatabase?) {
        Log.i("DatabaseHelper", "onCreate")

        val createTermsTable =
            "CREATE TABLE IF NOT EXISTS  ${DatabaseModels.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "${DatabaseModels.COLUMN_TERM} TEXT, " +
            "${DatabaseModels.COLUMN_TYPE} INTEGER)"

        db!!.execSQL(createTermsTable)

        Log.i("DatabaseHelper", "Table ${DatabaseModels.TABLE_NAME} created.")

        insertDatatoDatabase(db);
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS ${DatabaseModels.TABLE_NAME}")
        onCreate(db);
    }

    fun readData() : Cursor {
        Log.i("DatabaseHelper", "readData")

        val db : SQLiteDatabase = this.writableDatabase
        val read : Cursor = db.rawQuery("SELECT * FROM ${DatabaseModels.TABLE_NAME}", null)

        return read
    }

    fun insertDatatoDatabase(db: SQLiteDatabase) {
        Log.i("DatabaseHelper", "Inserting data to  ${DatabaseModels.TABLE_NAME} table")

        val insertContent = getInsertContent();
        db!!.execSQL(insertContent)

        Log.i("DatabaseHelper", "Insert to  ${DatabaseModels.TABLE_NAME} table done..")
    }

    private fun getInsertContent() : String {
        val fileContent = DatabaseHelper::class.java.getResource("/res/raw/terms_dictionary.txt")
        val fileText = fileContent.readText()

        val splittedRows = fileText.split("\r\n");
        val sb = StringBuilder()
        sb.append("INSERT INTO ${DatabaseModels.TABLE_NAME} (${DatabaseModels.COLUMN_TERM}, ${DatabaseModels.COLUMN_TYPE}) VALUES ")

        splittedRows.forEach  {
            val splittedRow = it.split(";")

            if(splittedRow.size != 2){
                return@forEach
            }

            sb.append("('${splittedRow[0]}',${splittedRow[1]}),")
        }

        var stringValue = sb.toString()

        stringValue = stringValue.substring(0, stringValue.length - 1)
        stringValue += ";"

        return stringValue
    }
}