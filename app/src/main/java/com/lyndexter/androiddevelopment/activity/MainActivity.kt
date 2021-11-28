package com.lyndexter.androiddevelopment.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lyndexter.androiddevelopment.R
import com.lyndexter.androiddevelopment.fragment.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commit()
        }
    }
}
