package com.github.ticktakclock.billinglibrarysample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.github.ticktakclock.billinglibrarysample.R
import com.github.ticktakclock.billinglibrarysample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        replaceFragment(MainFragment.newInstance(), binding.container.id)
    }

    private fun replaceFragment(fragment: Fragment, id: Int) {
        supportFragmentManager.beginTransaction().apply {
            replace(id, fragment)
        }.commit()
    }
}