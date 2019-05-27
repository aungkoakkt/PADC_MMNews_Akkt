package com.padcmyanmar.padc7.mmnews.mvp.presenters

import com.padcmyanmar.padc7.mmnews.activities.BaseActivity
import com.padcmyanmar.padc7.mmnews.delegates.NewsItemDelegate

/**
 *Created by Aung Ko Ko Thet on 5/4/19
 */
interface INewsListPresenter : NewsItemDelegate {

    fun onUIReady(activity: BaseActivity)
    fun onRefreshPage(activity: BaseActivity)
    fun onListEndReach(activity: BaseActivity)
    fun onTapLogout()
}