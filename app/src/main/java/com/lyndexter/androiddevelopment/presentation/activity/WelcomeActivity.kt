package com.lyndexter.androiddevelopment.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lyndexter.androiddevelopment.R
import com.lyndexter.androiddevelopment.presentation.fragment.WelcomeFragment

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.welcome_container, WelcomeFragment.newInstance())
            .commit()
    }
}
