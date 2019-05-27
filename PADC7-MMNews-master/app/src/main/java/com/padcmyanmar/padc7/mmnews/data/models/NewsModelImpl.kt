package com.padcmyanmar.padc7.mmnews.data.models

import androidx.lifecycle.LiveData

import com.padcmyanmar.padc7.mmnews.data.vos.CommentVO
import com.padcmyanmar.padc7.mmnews.data.vos.FavoriteVO
import com.padcmyanmar.padc7.mmnews.data.vos.NewsVO
import com.padcmyanmar.padc7.mmnews.data.vos.SendToVO
import com.padcmyanmar.padc7.mmnews.delegates.GetNewsDelegate

object NewsModelImpl : BaseModel(), NewsModel {

    private const val DUMMY_ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916"
    private const val KEY_NEWS_PAGE = "KEY_NEWS_PAGE"

    private var newsPage: Int
        get() {
            return sharedPreferences.getInt(KEY_NEWS_PAGE, 1)
        }
        set(newPageNumber) {
            sharedPreferences.edit().putInt(KEY_NEWS_PAGE, newPageNumber).apply()
        }

    override fun addCommentNews(news: NewsVO, comment: CommentVO) {

    }

    override fun favoriteNews(news: NewsVO, favorite: FavoriteVO) {

    }

    override fun sendNewsTo(news: NewsVO, sendTo: SendToVO) {

    }

    override fun getNews(isForce: Boolean, failure: (String, Int) -> Unit): LiveData<List<NewsVO>> {
        val newsList = mNewsDB.newsDao().loadNews()

        var newsPage = newsPage
        if (isForce) {
            newsPage = 1
        }

        if (mNewsDB.isNewsEmpty || isForce) {
            mDataAgent.loadNews(newsPage,
                    DUMMY_ACCESS_TOKEN,
                    object : GetNewsDelegate {
                        override fun onSuccess(newsList: List<NewsVO>) {
                            if (!isForce) {
                                newsPage += 1
                            }

                            val insertedNewsCount = mNewsDB.newsDao().saveNews(newsList, mNewsDB.commentDao())

                        }

                        override fun onFail(msg: String, code: Int) {
                            failure.invoke(msg,code)
                        }
                    })
        }
        return newsList
    }

    override fun loadMoreNews(failure: (String, Int) -> Unit): LiveData<List<NewsVO>> {

        val newsList = mNewsDB.newsDao().loadNews()

        mDataAgent.loadNews(newsPage,
                DUMMY_ACCESS_TOKEN,
                object : GetNewsDelegate {
                    override fun onSuccess(newsList: List<NewsVO>) {
                        newsPage = newsPage + 1
                        val insertedNewsCount = mNewsDB.newsDao().saveNews(newsList, mNewsDB.commentDao())

                    }

                    override fun onFail(msg: String, code: Int) {
                        failure(msg,code)
                    }
                })

        return newsList
    }

    override fun getNewsById(newsId: String): LiveData<NewsVO> {
        return mNewsDB.newsDao().getNewsById(newsId)
    }

}
