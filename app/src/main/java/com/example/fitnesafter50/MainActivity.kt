package com.example.fitnesafter50

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.fitnesafter50.fragments.DaysFragment
import utils.FragmentManager
import utils.MainViewModel

class MainActivity : AppCompatActivity() {
    private val model: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //lesson 24 6-22 ЗАПИСЬ ПЕРЕМ В ТАБЛ MAIN НА ТЕЛЕФОНЕ
        model.pref = getSharedPreferences("main", MODE_PRIVATE)

        FragmentManager.setFragment(DaysFragment.newInstance(), this)
    }

    override fun onBackPressed() {
        if(FragmentManager.currentFragment is DaysFragment) super.onBackPressed()

    else FragmentManager.setFragment(DaysFragment.newInstance(), this)}
}