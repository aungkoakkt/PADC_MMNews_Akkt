package com.padcmyanmar.padc7.mmnews.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.padcmyanmar.padc7.mmnews.R
import com.padcmyanmar.padc7.mmnews.adapters.NewsDetailsImagesAdapter
import com.padcmyanmar.padc7.mmnews.data.vos.NewsVO
import com.padcmyanmar.padc7.mmnews.mvp.presenters.NewsDetailPresenter
import com.padcmyanmar.padc7.mmnews.mvp.views.NewsDetailView
import com.padcmyanmar.padc7.mmnews.utils.Constants
import kotlinx.android.synthetic.main.activity_news_details.*

class NewsDetailsActivity : BaseActivity(),NewsDetailView {

    private lateinit var mPresenter:NewsDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_news_details)
        setSupportActionBar(toolbarDetail)

        mPresenter= ViewModelProviders.of(this).get(NewsDetailPresenter::class.java)

        val newsId=intent.getStringExtra(Constants.NEWS_ID)

        mPresenter.initPresenter(this)
        mPresenter.onUIReady(this,newsId)

        val newsDetailsImagesAdapter = NewsDetailsImagesAdapter()
        vpNewsDetailsImages.adapter = newsDetailsImagesAdapter
    }

    override fun displayNewsDetail(news: NewsVO) {
        tvNewsDetails.text=news.details
        tvPublicationName.text=news.publication.title
        Glide.with(this).load(news.publication.logo).into(ivPublicationLogo)
        tvPublishedDate.text=news.postedDate
    }

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, NewsDetailsActivity::class.java)
        }
    }
}
