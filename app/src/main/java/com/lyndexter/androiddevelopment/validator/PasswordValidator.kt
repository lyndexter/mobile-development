package com.lyndexter.androiddevelopment.validator

private const val MIN_PASSWORD_LENGTH = 8
private const val EMPTY_PASSWORD_FIELD_ERROR = "Password field can't be blank"
private const val MIN_PASSWORD_LENGTH_ERROR =
    "Password must be longer than $MIN_PASSWORD_LENGTH characters"

class PasswordValidator : InputValidator() {
    companion object {
        @JvmStatic
        fun validate(field: String?) {
            if (isEmpty(field)) {
                throw IllegalStateException(EMPTY_PASSWORD_FIELD_ERROR)
            } else if (field!!.length < MIN_PASSWORD_LENGTH) {
                throw IllegalStateException(MIN_PASSWORD_LENGTH_ERROR)
            }
        }
    }
}
