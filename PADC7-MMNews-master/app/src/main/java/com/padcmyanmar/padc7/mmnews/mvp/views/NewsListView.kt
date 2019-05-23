package com.padcmyanmar.padc7.mmnews.mvp.views

import com.padcmyanmar.padc7.mmnews.data.vos.LoginUserVO
import com.padcmyanmar.padc7.mmnews.data.vos.NewsVO


/**
 *Created by Aung Ko Ko Thet on 5/4/19
 */
interface NewsListView {

    fun displayNewsList(newsList: List<NewsVO>)
    fun displayMoreNewsList(newsList: List<NewsVO>)
    fun displayFailToLoadData(message: String)
    fun displayUserInformation(loginUser: LoginUserVO)
    fun displayNewsDetailScreen()
    fun displayScreenIfUserIsNotLogin()
}