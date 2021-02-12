package com.example.kalambury

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.core.text.HtmlCompat
import com.example.kalambury.db.DatabaseHelper
import kotlin.random.Random

class PlayActivity : AppCompatActivity() {
    lateinit var databaseHelper : DatabaseHelper
    var lastGeneratedNumber : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        val nextermButton = findViewById<Button>(R.id.nextTermButton)
        databaseHelper = DatabaseHelper(this)
        this.getNextTerm()

        nextermButton.setOnClickListener{
            this.getNextTerm()
        }
    }

    private fun getNextTerm()  {
        val data =  databaseHelper.readData()
        val rowsCount = data.count

        var termTextView = findViewById<TextView>(R.id.termTextView)
        var generatedNumber = generateNextRandomNumber(rowsCount)
        lastGeneratedNumber = generatedNumber

        Log.i("PlayActivity", generatedNumber.toString())
        data.moveToPosition(generatedNumber)
        termTextView.text = data.getString(1)
    }

    private fun generateNextRandomNumber(rowsCount: Int) : Int {
        var generatedNumber = Random.nextInt(from = 0, until = rowsCount)

        if(generatedNumber != lastGeneratedNumber){
            return generatedNumber
        }

        return generateNextRandomNumber(rowsCount)
    }

}