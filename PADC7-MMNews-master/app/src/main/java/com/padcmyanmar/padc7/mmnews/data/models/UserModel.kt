package com.padcmyanmar.padc7.mmnews.data.models

import com.padcmyanmar.padc7.mmnews.data.vos.LoginUserVO
import com.padcmyanmar.padc7.mmnews.delegates.LoginDelegate

interface UserModel {

    fun login(emailOrPassword: String, password: String, loginDelegate: LoginDelegate)

    fun getLoginUser(): LoginUserVO

    fun isUserLogin(): Boolean

    fun onUserLogout()
}
