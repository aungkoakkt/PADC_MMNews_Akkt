package com.padcmyanmar.padc7.mmnews.mvp.presenters

import com.padcmyanmar.padc7.mmnews.data.models.NewsModel
import com.padcmyanmar.padc7.mmnews.data.models.NewsModelImpl
import com.padcmyanmar.padc7.mmnews.data.models.UserModel
import com.padcmyanmar.padc7.mmnews.data.models.UserModelImpl

/**
 *Created by Aung Ko Ko Thet on 5/4/19
 */
abstract class BasePresenter : IBasePresenter {

    protected lateinit var mNewsModel: NewsModel
    protected lateinit var mUserModel: UserModel

    override fun onCreate() {
        mNewsModel = NewsModelImpl.getInstance()
        mUserModel = UserModelImpl.getInstance()
    }

    override fun onDestroy() {

    }
}