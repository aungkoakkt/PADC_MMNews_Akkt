package com.padcmyanmar.padc7.mmnews.persistence.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.padcmyanmar.padc7.mmnews.data.vos.CommentVO
import com.padcmyanmar.padc7.mmnews.data.vos.NewsVO

import java.util.ArrayList

@Dao
abstract class NewsDao {

    @get:Query("SELECT * FROM news ORDER BY news_id_pk DESC LIMIT 1")
    abstract val latestNews: NewsVO

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertNews(newsList: List<NewsVO>): LongArray

    @Query("SELECT * FROM news WHERE news_id = :newsId")
    abstract fun getNewsById(newsId: String): LiveData<NewsVO>

    @Query("SELECT * FROM news")
    abstract fun loadNews(): LiveData<List<NewsVO>>

    /**
     * Save news after preparing the foreign key constraints between NewsVO & CommentVO.
     *
     * @param newsList
     * @return size of the news that are being saved.
     */
    fun saveNews(newsList: List<NewsVO>, commentDao: CommentDao): Int {
        //Data preparation for foreign key constraints.
        val allComments = ArrayList<CommentVO>()
        for (news in newsList) {
            for (comment in news.comments) {
                comment.newsId = news.newsId
            }
            //To save all the comment at once with single DB execution statement.
            allComments.addAll(news.comments)
        }

        val ids = insertNews(newsList)
        val commentIds = commentDao.insertComments(allComments)

        return ids.size
    }

    /**
     * Load a news along with its related comments, favorites & sent-tos - by using newsId.
     * @param newsId
     * @param commentDao
     * @return loaded news.
     */
    /*    public NewsVO loadNewsById(String newsId, CommentDao commentDao) {
        NewsVO news = getNewsById(newsId);
        List<CommentVO> commentList = commentDao.getCommentsByNewsId(newsId);
        news.setComments(commentList);

        return news;
    }*/
}
