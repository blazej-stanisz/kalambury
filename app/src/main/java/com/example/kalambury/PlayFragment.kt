package com.example.kalambury

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.kalambury.helpers.NavigableFragment
import com.example.kalambury.helpers.setTextAnimation
import com.example.kalambury.helpers.vibrate
import kotlin.random.Random

class PlayFragment : NavigableFragment() {
    private var lastGeneratedNumbersList = mutableListOf<Int>()
    private lateinit var termTextView: TextView
    private lateinit var categoryTextView: TextView
    private lateinit var nextTermButton: Button
    private lateinit var thisView : View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        thisView = inflater.inflate(R.layout.fragment_play, container, false)

        this.initProperties()
        this.initListeners()

        // preform click to load first term
        this.nextTermButton.performClick()

        return thisView
    }

    private fun initProperties() {
        // controls
        this.termTextView = thisView.findViewById<TextView>(R.id.termTextView)
        this.categoryTextView = thisView.findViewById<TextView>(R.id.categoryTextView)
        this.nextTermButton = thisView.findViewById<Button>(R.id.nextTermButton)
    }

    private fun initListeners() {
        this.nextTermButton.setOnClickListener{
            this.getNextTerm()
        }
    }

    private fun getNextTerm()  {
        val query = this.databaseHelper?.queryTermsCategoriesView() ?: return

        vibrate()

        var generatedNumber = generateNextRandomUnusedNumber(query.count)

        Log.i("PlayActivity", generatedNumber.toString())
        query.moveToPosition(generatedNumber)
        //this.termTextView.text = data.getString(1)
        this.termTextView.setTextAnimation(query.getString(1), 150, 300)
        this.categoryTextView.setTextAnimation(query.getString(3), 150, 300)
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