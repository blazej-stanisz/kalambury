package com.example.kalambury

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.kalambury.db.DatabaseHelper
import com.example.kalambury.helpers.FragmentNavigator
import com.example.kalambury.helpers.NavigableFragment


class MainActivity : AppCompatActivity(), FragmentNavigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT

        val mainFragment = MainFragment()
        navigateTo(mainFragment)
    }

    override fun navigateTo(fragment: Fragment) {
        var transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.fade_enter, R.anim.fade_exit)
        transaction.replace(R.id.fragmentPlaceholderView, fragment).addToBackStack(null).commit()
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        if(fragment is NavigableFragment) {
            fragment.navigator = this

            if(fragment.databaseHelper == null){
                fragment.databaseHelper = DatabaseHelper(this)
            }
        }
    }

    private var doubleBackToExitPressedOnce = false

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        if (supportFragmentManager.backStackEntryCount > 1) {
            //Go back to previous Fragment
            fragmentManager.popBackStackImmediate()
        } else {
            if (doubleBackToExitPressedOnce) {
                this.finishAffinity()
            }

            this.doubleBackToExitPressedOnce = true
            Toast.makeText(this, "Naciśnij dwukrotnie przycisk Wstecz, aby wyjść", Toast.LENGTH_SHORT).show()

            Handler(Looper.getMainLooper()).postDelayed({
                doubleBackToExitPressedOnce = false
            }, 3000)
        }

        /*val fragment: MainFragment = supportFragmentManager.findFragmentByTag(TAG_FRAGMENT) as Myfragment
        if (fragment.allowBackPressed()) { // and then you define a method allowBackPressed with the logic to allow back pressed or not
            super.onBackPressed()
        }*/
    }
}

