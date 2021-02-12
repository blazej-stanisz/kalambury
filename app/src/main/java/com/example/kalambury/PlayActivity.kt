package com.example.kalambury

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.text.HtmlCompat

class PlayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

/*        val rulesTextView = findViewById<TextView>(R.id.rulesTextView)*/
        val nextermButton = findViewById<Button>(R.id.nextTermButton)

        /*rulesTextView.text = HtmlCompat.fromHtml(htmlText, 0);*/

        nextermButton.setOnClickListener{
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
    }
}