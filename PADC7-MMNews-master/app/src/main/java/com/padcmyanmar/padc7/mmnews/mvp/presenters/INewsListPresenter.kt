package com.padcmyanmar.padc7.mmnews.mvp.presenters

import com.padcmyanmar.padc7.mmnews.delegates.NewsItemDelegate

/**
 *Created by Aung Ko Ko Thet on 5/4/19
 */
interface INewsListPresenter : IBasePresenter,NewsItemDelegate {

    fun onUIReady()
    fun onRefreshPage()
    fun onListEndReach()
    fun onTapLogout()
}