package com.example.kalambury

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.kalambury.db.DatabaseHelper
import kotlin.random.Random

class PlayActivity : AppCompatActivity() {
    lateinit var databaseHelper : DatabaseHelper
    private var lastGeneratedNumbersList = mutableListOf<Int>()
    private lateinit var termTextView: TextView
    private lateinit var nextTermButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        initProperties()
        initListeners()

        // preform click to load first term
        this.nextTermButton.performClick()
    }

    private fun initProperties() {
        // controls
        this.termTextView = findViewById<TextView>(R.id.termTextView)
        this.nextTermButton = findViewById<Button>(R.id.nextTermButton)

        // other
        this.databaseHelper = DatabaseHelper(this)
    }

    private fun initListeners() {
        this.nextTermButton.setOnClickListener{
            this.getNextTerm()
        }
    }

    private fun getNextTerm()  {
        val data =  this.databaseHelper.readData()

        var generatedNumber = generateNextRandomUnusedNumber(data.count)

        Log.i("PlayActivity", generatedNumber.toString())
        data.moveToPosition(generatedNumber)
        this.termTextView.text = data.getString(1)
    }

    private fun generateNextRandomUnusedNumber(rowsCount: Int) : Int {
        var generatedNumber = 0

        // if all possible numbers have been generated clear list
        if(this.lastGeneratedNumbersList.count() ==  rowsCount){
            this.lastGeneratedNumbersList.clear()
        }

        do {
            generatedNumber = Random.nextInt(from = 0, until = rowsCount)
        } while(this.lastGeneratedNumbersList.contains(generatedNumber))

        this.lastGeneratedNumbersList.add(generatedNumber)

        return generatedNumber
    }
}