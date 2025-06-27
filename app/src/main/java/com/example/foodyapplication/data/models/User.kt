package com.example.foodyapplication.data.models


class User(
    val id: Long = -1,
    var email: String? = null,
    var fullname: String? = null,
    var phone: String? = null,
    var address: String? = null,
    var password: String? = null,
    var confirmPassword: String? = null
) {

//    fun toUserEntity(): CustomerEntity {
//        val customerId = if (id == -1L) 0 else id
//        return CustomerEntity(
//            id = customerId,
//            firstName = firstName ?: "",
//            lastName = lastName ?: "",
//            shortDescription = shortDescription ?: "",
//            stars = stars,
//            certificates = certificates
//        )
//    }

    fun isValidateEmail(): Boolean {
        return (email?.isNotEmpty() == true)
    }

    fun isValidateFullName(): Boolean {
        return (fullname?.isNotEmpty() == true)
    }


    fun isValidatePhone(): Boolean {
        return (phone?.isNotEmpty() == true)
    }

    fun isValidateAddress(): Boolean {
        return (address?.isNotEmpty() == true)
    }

    fun isValidatePassword(): Boolean {
        return (password?.isNotEmpty() == true)
    }

    fun isValidateConfirmPassword(): Boolean {
        return (confirmPassword?.isNotEmpty() == true)
    }

    fun isMatchPasswordAndConfirmPassword(): Boolean {
        return isValidateConfirmPassword() && isValidateConfirmPassword() && password == confirmPassword

    }

    fun isValidateDataLogin(): Boolean {
        return isValidateEmail() && isValidatePassword()
    }

    fun isValidateDataRegister(): Boolean {
        return isValidateEmail() && isValidateFullName() && isValidatePhone() && isValidateAddress() && isValidatePassword() && isValidateConfirmPassword() && isMatchPasswordAndConfirmPassword()
    }

    fun isValidateNameChanged(currentFullName: String): Boolean {
        return isValidateFullName() && fullname != currentFullName

    }

    fun isValidatePhoneChanged(currentPhone: String): Boolean {
        return isValidatePhone() && phone != currentPhone

    }

    fun isValidateAddressChanged(currentAddress: String): Boolean {
        return isValidateAddress() && address != currentAddress

    }

}




