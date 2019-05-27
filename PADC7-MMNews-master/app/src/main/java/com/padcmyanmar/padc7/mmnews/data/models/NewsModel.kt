package com.padcmyanmar.padc7.mmnews.data.models

import androidx.lifecycle.LiveData

import com.padcmyanmar.padc7.mmnews.data.vos.CommentVO
import com.padcmyanmar.padc7.mmnews.data.vos.FavoriteVO
import com.padcmyanmar.padc7.mmnews.data.vos.NewsVO
import com.padcmyanmar.padc7.mmnews.data.vos.SendToVO

interface NewsModel {

    fun addCommentNews(news: NewsVO, comment: CommentVO)

    fun favoriteNews(news: NewsVO, favorite: FavoriteVO)

    fun sendNewsTo(news: NewsVO, sendTo: SendToVO)

    //Get News.
    fun getNews(isForce: Boolean, failure: (String,Int) -> Unit): LiveData<List<NewsVO>>

    fun loadMoreNews(failure: (String, Int) -> Unit): LiveData<List<NewsVO>>

    fun getNewsById(newsId: String): LiveData<NewsVO>

}
