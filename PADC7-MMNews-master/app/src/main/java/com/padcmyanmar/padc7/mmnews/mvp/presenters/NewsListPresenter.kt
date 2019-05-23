package com.padcmyanmar.padc7.mmnews.mvp.presenters

import com.padcmyanmar.padc7.mmnews.mvp.views.NewsListView


/**
 *Created by Aung Ko Ko Thet on 5/4/19
 */
class NewsListPresenter(private val mNewsListView: NewsListView) : BasePresenter(), INewsListPresenter {

    override fun onTapNewsItem() {
        mNewsListView.displayNewsDetailScreen()
    }

    override fun onTapLogout() {
        mUserModel.onUserLogout()
        mNewsListView.displayScreenIfUserIsNotLogin()
    }

    override fun onUIReady() {

        when{
            mUserModel.isUserLogin->{
                mNewsListView.displayUserInformation(mUserModel.loginUser)

                mNewsModel.getNews(false).observeForever {
                    if (it.isEmpty()){
                        mNewsListView.displayFailToLoadData("No data to display")
                    }else{
                        mNewsListView.displayNewsList(it)
                    }
                }

            }
            else->mNewsListView.displayScreenIfUserIsNotLogin()
        }
    }

    override fun onRefreshPage() {


        mNewsModel.getNews(true).observeForever {
            if (it.isEmpty()){
                mNewsListView.displayFailToLoadData("No data to display")
            }else{
                mNewsListView.displayNewsList(it)
            }
        }

    }

    override fun onListEndReach() {

        mNewsModel.loadMoreNews().observeForever{
            if (it.isEmpty()){
                mNewsListView.displayFailToLoadData("No data to display")
            }else{
                mNewsListView.displayMoreNewsList(it)
            }
        }
    }

    override fun onStart() {

    }

    override fun onStop() {

    }

}