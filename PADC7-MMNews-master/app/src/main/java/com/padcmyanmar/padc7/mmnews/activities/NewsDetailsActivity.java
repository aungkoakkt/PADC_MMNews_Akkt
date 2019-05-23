package com.padcmyanmar.padc7.mmnews.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.padcmyanmar.padc7.mmnews.R;
import com.padcmyanmar.padc7.mmnews.adapters.NewsDetailsImagesAdapter;

public class NewsDetailsActivity extends BaseActivity {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, NewsDetailsActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        Toolbar toolbar = findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);

        ViewPager vpNewsDetailsImages = findViewById(R.id.vp_news_details_images);

        NewsDetailsImagesAdapter newsDetailsImagesAdapter = new NewsDetailsImagesAdapter();
        vpNewsDetailsImages.setAdapter(newsDetailsImagesAdapter);
    }
}
