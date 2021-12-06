package com.lyndexter.androiddevelopment.presentation.validator

open class InputValidator {
    companion object {

        @JvmStatic
        fun isEmpty(field: String?): Boolean {
            return field.isNullOrEmpty()
        }
    }
}
