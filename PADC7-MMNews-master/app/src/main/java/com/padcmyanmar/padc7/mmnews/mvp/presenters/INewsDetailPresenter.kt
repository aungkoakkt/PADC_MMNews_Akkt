package com.padcmyanmar.padc7.mmnews.mvp.presenters

import com.padcmyanmar.padc7.mmnews.activities.BaseActivity

/**
 *Created by Aung Ko Ko Thet on 5/27/19
 */
interface INewsDetailPresenter {

    fun onUIReady(activity: BaseActivity, newsId: String)
}