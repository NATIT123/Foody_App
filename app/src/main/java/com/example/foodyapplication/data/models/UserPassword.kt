package com.example.foodyapplication.data.models

class UserPassword(
    var password: String? = null,
    var newPassword: String? = null,
    var confirmPassword: String? = null,
) {
    fun isValidatePassword(): Boolean {
        return (password?.isNotEmpty() == true)
    }

    fun isValidateNewPassword(): Boolean {
        return (newPassword?.isNotEmpty() == true)
    }


    fun isValidateConfirmPassword(): Boolean {
        return (confirmPassword?.isNotEmpty() == true)
    }

    fun isValidateMatchPassword(): Boolean {
        if (isValidateConfirmPassword() && isValidateConfirmPassword()) {
            return confirmPassword == newPassword
        }
        return true
    }

    fun isValidateData(): Boolean {
        return isValidateConfirmPassword() && isValidateNewPassword() && isValidatePassword() && isValidateMatchPassword()
    }


}