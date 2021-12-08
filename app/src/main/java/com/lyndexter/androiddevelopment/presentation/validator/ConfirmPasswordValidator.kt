package com.lyndexter.androiddevelopment.presentation.validator

private const val EMPTY_PASSWORD_FIELD_ERROR = "Password field can't be blank"

private const val PASSWORDS_MISMATCH_ERROR = "Passwords are not matching"

class ConfirmPasswordValidator : InputValidator() {

    companion object {
        @JvmStatic
        fun validate(password: String?, confirmedPassword: String?) {
            if (isEmpty(password) or isEmpty(confirmedPassword)) {
                throw IllegalStateException(EMPTY_PASSWORD_FIELD_ERROR)
            } else if (!password.equals(confirmedPassword)) {
                throw IllegalStateException(PASSWORDS_MISMATCH_ERROR)
            }
        }
    }
}
