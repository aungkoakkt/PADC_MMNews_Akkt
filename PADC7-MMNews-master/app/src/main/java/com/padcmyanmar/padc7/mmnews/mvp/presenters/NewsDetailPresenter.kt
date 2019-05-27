package com.padcmyanmar.padc7.mmnews.mvp.presenters

import androidx.lifecycle.Observer
import com.padcmyanmar.padc7.mmnews.activities.BaseActivity
import com.padcmyanmar.padc7.mmnews.mvp.views.NewsDetailView

/**
 *Created by Aung Ko Ko Thet on 5/27/19
 */
class NewsDetailPresenter : BasePresenter<NewsDetailView>(), INewsDetailPresenter {

    override fun onUIReady(activity: BaseActivity, newsId: String) {
        mNewsModel.getNewsById(newsId).observe(activity, Observer {
            mView.displayNewsDetail(it)
        })
    }
}