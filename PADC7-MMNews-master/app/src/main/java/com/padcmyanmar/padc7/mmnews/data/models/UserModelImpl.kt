package com.padcmyanmar.padc7.mmnews.data.models

import android.util.Log

import com.padcmyanmar.padc7.mmnews.data.vos.LoginUserVO
import com.padcmyanmar.padc7.mmnews.delegates.LoginDelegate

object UserModelImpl: BaseModel(), UserModel {

    override fun login(emailOrPassword: String, password: String, loginDelegate: LoginDelegate) {
        mDataAgent.login(emailOrPassword, password, object : LoginDelegate {
            override fun onSuccess(loginUser: LoginUserVO) {
                val userId = mNewsDB.loginUserDao().inserLoginUser(loginUser)
                Log.d("", "userId : $userId")
                loginDelegate.onSuccess(loginUser)
            }

            override fun onFail(msg: String, code: Int) {
                loginDelegate.onFail(msg, code)
            }
        })
    }

    override fun getLoginUser(): LoginUserVO {
        return mNewsDB.loginUserDao().loginUser
    }

    override fun isUserLogin(): Boolean {
        return mNewsDB.loginUserDao().loginUser != null
    }

    override fun onUserLogout() {
        mNewsDB.loginUserDao().deleteLoginUser()
    }
}
