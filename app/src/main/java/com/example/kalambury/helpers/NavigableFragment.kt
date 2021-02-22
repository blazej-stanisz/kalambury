package com.example.kalambury.helpers

import androidx.fragment.app.Fragment
import com.example.kalambury.db.DatabaseHelper

open class NavigableFragment: Fragment() {
    var navigator: FragmentNavigator? = null
    var databaseHelper : DatabaseHelper? = null

    override fun onDetach() {
        super.onDetach()
        navigator = null
    }
}