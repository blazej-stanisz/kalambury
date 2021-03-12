package com.example.kalambury.db

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.util.Log
import com.example.kalambury.db.models.TermsTableModel

class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context,
    DatabaseSettings.DATABASE_NAME,
    null,
    DatabaseSettings.DATABASE_VERSION
) {

    override fun onCreate(db: SQLiteDatabase?) {
        Log.i("DatabaseHelper", "onCreate")

        val createTermsTable =
            "CREATE TABLE IF NOT EXISTS  ${TermsTableModel.TERMS_TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "${TermsTableModel.COLUMN_TERM} TEXT, " +
            "${TermsTableModel.COLUMN_TYPE} INTEGER)"

        db!!.execSQL(createTermsTable)

        Log.i("DatabaseHelper", "Table ${TermsTableModel.TERMS_TABLE_NAME} created.")

        insertDatatoDatabase(db);
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS ${TermsTableModel.TERMS_TABLE_NAME}")
        onCreate(db);
    }

    fun readData() : Cursor {
        Log.i("DatabaseHelper", "readData")

        val db : SQLiteDatabase = this.writableDatabase
        val read : Cursor = db.rawQuery("SELECT * FROM ${TermsTableModel.TERMS_TABLE_NAME}", null)

        return read
    }

    fun insertDatatoDatabase(db: SQLiteDatabase) {
        Log.i("DatabaseHelper", "Inserting data to  ${TermsTableModel.TERMS_TABLE_NAME} table")

        val insertContent = getInsertContent();
        db!!.execSQL(insertContent)

        Log.i("DatabaseHelper", "Insert to  ${TermsTableModel.TERMS_TABLE_NAME} table done..")
    }

    private fun getInsertContent() : String {
        val fileContent = DatabaseHelper::class.java.getResource("/res/raw/terms_dictionary.txt")
        val fileText = fileContent.readText()

        val splittedRows = fileText.split("\r\n");
        val sb = StringBuilder()
        sb.append("INSERT INTO ${TermsTableModel.TERMS_TABLE_NAME} (${TermsTableModel.COLUMN_TERM}, ${TermsTableModel.COLUMN_TYPE}) VALUES ")

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