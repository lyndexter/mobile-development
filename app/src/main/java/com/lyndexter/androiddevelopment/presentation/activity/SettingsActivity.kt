package com.lyndexter.androiddevelopment.presentation.activity

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.lyndexter.androiddevelopment.R
import com.lyndexter.androiddevelopment.presentation.fragment.ProfileFragment
import com.lyndexter.androiddevelopment.utils.ContextUtils

private const val PREFS_FILE_NAME = "user_data"
private const val LANGUAGE_NAME = "language"

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings_container, ProfileFragment.newInstance())
                .commit()
        }
    }

    override fun attachBaseContext(newBase: Context) {
        val sharedPreferences = newBase.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        val language = sharedPreferences?.getString(LANGUAGE_NAME, "English")
        val localeUpdatedContext: ContextWrapper = ContextUtils.updateLocale(newBase, language)
        super.attachBaseContext(localeUpdatedContext)
    }

    fun goToFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings_container, fragment)
            .commit()
    }

    fun goToWelcomeActivity() {
        startActivity(Intent(this, WelcomeActivity::class.java))
    }
}
