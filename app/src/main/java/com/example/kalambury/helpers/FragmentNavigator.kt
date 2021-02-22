package com.example.kalambury.helpers

import androidx.fragment.app.Fragment

open interface FragmentNavigator {
    fun navigateTo(fragment: Fragment)
}