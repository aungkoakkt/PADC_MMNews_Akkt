package com.padcmyanmar.padc7.mmnews.data.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.lifecycle.LiveData;

import com.padcmyanmar.padc7.mmnews.data.vos.CommentVO;
import com.padcmyanmar.padc7.mmnews.data.vos.FavoriteVO;
import com.padcmyanmar.padc7.mmnews.data.vos.NewsVO;
import com.padcmyanmar.padc7.mmnews.data.vos.SendToVO;
import com.padcmyanmar.padc7.mmnews.delegates.GetNewsDelegate;

import java.util.List;

public class NewsModelImpl extends BaseModel implements NewsModel {

    private static final String DUMMY_ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916";
    private static final String KEY_NEWS_PAGE = "KEY_NEWS_PAGE";

    private static NewsModelImpl instance;

    private NewsModelImpl(Context context) {
        super(context);
    }

    public static void initNewsModel(Context context) {
        instance = new NewsModelImpl(context);
    }

    public static NewsModelImpl getInstance() {
        if (instance == null) {
            throw new RuntimeException("NewsModelImpl should be initialized before using it.");
        }
        return instance;
    }

    @Override
    public void addCommentNews(NewsVO news, CommentVO comment) {

    }

    @Override
    public void favoriteNews(NewsVO news, FavoriteVO favorite) {

    }

    @Override
    public void sendNewsTo(NewsVO news, SendToVO sendTo) {

    }

    @Override
    public LiveData<List<NewsVO>> getNews(final boolean isForce) {

        LiveData<List<NewsVO>> newsList = mNewsDB.newsDao().loadNews();

        int newsPage = getNewsPage();
        if (isForce) {
            newsPage = 1;
        }

        if (mNewsDB.isNewsEmpty() || isForce) {
            mDataAgent.loadNews(newsPage,
                    DUMMY_ACCESS_TOKEN,
                    new GetNewsDelegate() {
                        @Override
                        public void onSuccess(List<NewsVO> newsList) {
                            if (!isForce) {
                                setNewsPage(getNewsPage() + 1);
                            }

                            int insertedNewsCount = mNewsDB.newsDao().saveNews(newsList, mNewsDB.commentDao());

                        }

                        @Override
                        public void onFail(String msg) {

                        }
                    });
        }

        return newsList;
    }

    @Override
    public LiveData<List<NewsVO>> loadMoreNews() {

        LiveData<List<NewsVO>> newsList = mNewsDB.newsDao().loadNews();

        mDataAgent.loadNews(getNewsPage(),
                DUMMY_ACCESS_TOKEN,
                new GetNewsDelegate() {
                    @Override
                    public void onSuccess(List<NewsVO> newsList) {
                        setNewsPage(getNewsPage() + 1);
                        int insertedNewsCount = mNewsDB.newsDao().saveNews(newsList, mNewsDB.commentDao());

                    }
                    @Override
                    public void onFail(String msg) {

                    }
                });

        return newsList;
    }

    private int getNewsPage() {
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        return sharedPreferences.getInt(KEY_NEWS_PAGE, 1);
    }

    private void setNewsPage(int newPageNumber) {
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        sharedPreferences.edit().putInt(KEY_NEWS_PAGE, newPageNumber).apply();
    }
}
