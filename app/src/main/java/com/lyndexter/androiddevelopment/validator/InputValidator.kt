package com.lyndexter.androiddevelopment.validator

open class InputValidator {
    companion object {

        @JvmStatic
        fun isEmpty(field: String?): Boolean {
            return field.isNullOrEmpty()
        }
    }
}
