package com.example.kalambury

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.kalambury.db.DatabaseHelper


class MainActivity : AppCompatActivity() {
    lateinit var databaseHelper : DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // dba access
        val someTextLabel = findViewById<TextView>(R.id.textView2)
        databaseHelper = DatabaseHelper(this)
        val data =  databaseHelper.readData()

        while (data.moveToNext()){
            someTextLabel.text = data.getString(1)
        }

        val exitButton = findViewById<Button>(R.id.exitButton)
        val rulesButton = findViewById<Button>(R.id.rulesButton)
        val startGameButton = findViewById<Button>(R.id.startGameButton)

        exitButton.setOnClickListener {
            this.finishAffinity()
        }

        rulesButton.setOnClickListener{
            val i = Intent(this, RulesActivity::class.java)
            startActivity(i)
        }

        startGameButton.setOnClickListener {
            val i = Intent(this, PlayActivity::class.java)
            startActivity(i)
        }
    }
}

