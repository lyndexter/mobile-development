package com.lyndexter.androiddevelopment.validator

import android.util.Patterns

private const val EMPTY_EMAIL_FIELD_ERROR = "Email field can't be blank"
private const val EMAIL_FORMAT_ERROR = "Email is not properly formatted"

class EmailValidator : InputValidator() {
    companion object {
        @JvmStatic
        fun validate(field: String?) {
            if (isEmpty(field)) {
                throw IllegalStateException(EMPTY_EMAIL_FIELD_ERROR)
            } else if (!Patterns.EMAIL_ADDRESS.matcher(field as CharSequence).matches()) {
                throw IllegalStateException(EMAIL_FORMAT_ERROR)
            }
        }
    }
}
