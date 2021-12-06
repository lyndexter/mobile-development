package com.lyndexter.androiddevelopment.presentation.validator

private const val EMPTY_NAME_FIELD_ERROR = "Name field can't be blank"

class NameValidator : InputValidator() {
    companion object {
        @JvmStatic
        fun validate(field: String?) {
            if (isEmpty(field)) {
                throw IllegalStateException(EMPTY_NAME_FIELD_ERROR)
            }
        }
    }
}
