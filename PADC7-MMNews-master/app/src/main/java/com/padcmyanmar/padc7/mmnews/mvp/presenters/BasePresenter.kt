package com.padcmyanmar.padc7.mmnews.mvp.presenters

import androidx.lifecycle.ViewModel
import com.padcmyanmar.padc7.mmnews.data.models.NewsModel
import com.padcmyanmar.padc7.mmnews.data.models.NewsModelImpl
import com.padcmyanmar.padc7.mmnews.data.models.UserModel
import com.padcmyanmar.padc7.mmnews.data.models.UserModelImpl
import com.padcmyanmar.padc7.mmnews.mvp.views.BaseView

/**
 *Created by Aung Ko Ko Thet on 5/4/19
 */
abstract class BasePresenter<T : BaseView> : ViewModel() {

    protected val mNewsModel: NewsModel = NewsModelImpl
    protected val mUserModel: UserModel = UserModelImpl

    lateinit var mView: T

    fun initPresenter(view: T) {
        this.mView = view
    }

}