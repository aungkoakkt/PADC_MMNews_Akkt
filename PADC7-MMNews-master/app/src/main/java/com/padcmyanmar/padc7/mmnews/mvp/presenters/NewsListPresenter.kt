package com.padcmyanmar.padc7.mmnews.mvp.presenters

import androidx.lifecycle.Observer
import com.padcmyanmar.padc7.mmnews.activities.BaseActivity
import com.padcmyanmar.padc7.mmnews.mvp.views.NewsListView


/**
 *Created by Aung Ko Ko Thet on 5/4/19
 */
class NewsListPresenter : BasePresenter<NewsListView>(), INewsListPresenter {

    override fun onTapNewsItem(newsId: String) {
        mView.displayNewsDetailScreen(newsId)
    }

    override fun onTapLogout() {
        mUserModel.onUserLogout()
        mView.displayScreenIfUserIsNotLogin()
    }

    override fun onUIReady(activity: BaseActivity) {

        when {
            mUserModel.isUserLogin() -> {

                mView.displayUserInformation(mUserModel.getLoginUser())

                mNewsModel.getNews(false) { message, _ ->

                    mView.displayFailToLoadData(message)

                }.observe(activity, Observer {

                    mView.displayNewsList(it)

                })
            }

            else -> mView.displayScreenIfUserIsNotLogin()
        }
    }

    override fun onRefreshPage(activity: BaseActivity) {

        mNewsModel.getNews(true) { message, _ ->

            mView.displayFailToLoadData(message)

        }.observe(activity, Observer {

            mView.displayNewsList(it)

        })
    }

    override fun onListEndReach(activity: BaseActivity) {

        mNewsModel.loadMoreNews {

            message, _ ->

            mView.displayFailToLoadData(message)

        }.observe(activity, Observer {

            mView.displayMoreNewsList(it)

        })
    }
}