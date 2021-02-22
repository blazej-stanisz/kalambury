package com.example.kalambury

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.kalambury.helpers.NavigableFragment

class MainFragment : NavigableFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        val startGameButton = view.findViewById<Button>(R.id.startGameButton)
        val rulesButton = view.findViewById<Button>(R.id.rulesButton)
        val exitButton = view.findViewById<Button>(R.id.exitButton)

        startGameButton.setOnClickListener {
            val playFragment = PlayFragment()
            this.navigator?.navigateTo(playFragment)
        }

        rulesButton.setOnClickListener{
            val rulesFragment = RulesFragment()
            this.navigator?.navigateTo(rulesFragment)
        }

        exitButton.setOnClickListener {
            //System.exit(0);
            activity?.finishAffinity()

            //supportFragmentManager?.popBackStackImmediate()
        }

        return view
    }
}


