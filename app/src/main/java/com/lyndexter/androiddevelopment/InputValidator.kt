package com.lyndexter.androiddevelopment

import android.util.Patterns

private const val EMPTY_EMAIL_FIELD_ERROR = "Email field can't be blank"
private const val EMAIL_FORMAT_ERROR = "Email is not properly formatted"
private const val MIN_PASSWORD_LENGTH = 8
private const val EMPTY_PASSWORD_FIELD_ERROR = "Password field can't be blank"
private const val MIN_PASSWORD_LENGTH_ERROR =
    "Password must be longer than $MIN_PASSWORD_LENGTH characters"
private const val EMPTY_NAME_FIELD_ERROR = "Name field can't be blank"
private const val PASSWORDS_MISMATCH_ERROR = "Passwords are not matching"

class InputValidator {
    companion object {

        private fun isEmpty(field: String?): Boolean {
            return field.isNullOrEmpty()
        }

        fun validateEmail(field: String?) {
            if (isEmpty(field)) {
                throw IllegalStateException(EMPTY_EMAIL_FIELD_ERROR)
            } else if (!Patterns.EMAIL_ADDRESS.matcher(field as CharSequence).matches()) {
                throw IllegalStateException(EMAIL_FORMAT_ERROR)
            }
        }

        fun validateName(field: String?) {
            if (isEmpty(field)) {
                throw IllegalStateException(EMPTY_NAME_FIELD_ERROR)
            }
        }

        fun validatePassword(field: String?) {
            if (isEmpty(field)) {
                throw IllegalStateException(EMPTY_PASSWORD_FIELD_ERROR)
            } else if (field!!.length < MIN_PASSWORD_LENGTH) {
                throw IllegalStateException(MIN_PASSWORD_LENGTH_ERROR)
            }
        }

        fun validateConfirmedPassword(password: String?, confirmedPassword: String?) {
            if (isEmpty(password) or isEmpty(confirmedPassword)) {
                throw IllegalStateException(EMPTY_PASSWORD_FIELD_ERROR)
            } else if (!password.equals(confirmedPassword)) {
                throw IllegalStateException(PASSWORDS_MISMATCH_ERROR)
            }
        }
    }
}
