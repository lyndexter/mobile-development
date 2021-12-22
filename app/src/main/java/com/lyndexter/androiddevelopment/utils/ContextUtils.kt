package com.lyndexter.androiddevelopment.utils

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.LocaleList
import java.util.*

class ContextUtils(base: Context) : ContextWrapper(base) {

    companion object {

        fun updateLocale(c: Context, localeToSwitchTo: Locale): ContextWrapper {
            var context = c
            val resources: Resources = context.resources
            val configuration: Configuration = resources.configuration
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val localeList = LocaleList(localeToSwitchTo)
                LocaleList.setDefault(localeList)
                configuration.setLocales(localeList)
            } else {
                configuration.locale = localeToSwitchTo
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
                context = context.createConfigurationContext(configuration)
            } else {
                resources.updateConfiguration(configuration, resources.displayMetrics)
            }
            return ContextUtils(context)
        }

        fun updateLocale(c: Context, language: String?): ContextWrapper {
            var change = "en"
            if (language == "Ukrainian") {
                change = "uk"
            } else if (language == "English") {
                change = "en"
            } else {
                change = "en"
            }
            val locale = Locale(change)
            return this.updateLocale(c, locale)
        }
    }
}
