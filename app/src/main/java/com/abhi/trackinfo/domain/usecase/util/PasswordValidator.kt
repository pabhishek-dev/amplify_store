package com.abhi.trackinfo.domain.usecase.util

import javax.inject.Inject

class PasswordValidator @Inject constructor() {
    fun isPasswordValid(pwd: String): Boolean {
        return pwd.length >= 6
    }
}
