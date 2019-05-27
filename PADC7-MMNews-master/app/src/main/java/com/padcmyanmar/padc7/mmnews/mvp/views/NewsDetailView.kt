package com.padcmyanmar.padc7.mmnews.mvp.views

import com.padcmyanmar.padc7.mmnews.data.vos.NewsVO

/**
 *Created by Aung Ko Ko Thet on 5/27/19
 */
interface NewsDetailView : BaseView {

    fun displayNewsDetail(news: NewsVO)
}