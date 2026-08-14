package com.abhi.amplify_store.domain.usecase.util

import javax.inject.Inject

class EmailValidator @Inject constructor(){
    fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$".toRegex()
        return email.matches(emailRegex)
    }
}


