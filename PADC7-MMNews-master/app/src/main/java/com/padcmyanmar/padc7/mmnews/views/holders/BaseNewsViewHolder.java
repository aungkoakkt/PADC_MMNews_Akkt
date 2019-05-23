package com.padcmyanmar.padc7.mmnews.views.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.padcmyanmar.padc7.mmnews.R;
import com.padcmyanmar.padc7.mmnews.data.vos.NewsVO;

public class BaseNewsViewHolder extends BaseViewHolder<NewsVO> {

    TextView tvBriefNews;

    ImageView ivNewsHeroImage;

    public BaseNewsViewHolder(@NonNull View itemView) {
        super(itemView);
        tvBriefNews=itemView.findViewById(R.id.tv_brief_news);
        ivNewsHeroImage=itemView.findViewById(R.id.iv_news_hero_image);
    }

    @Override
    public void bindData(NewsVO data) {
        tvBriefNews.setText(data.getBrief());

        if(ivNewsHeroImage != null) {
            Glide.with(itemView)
                    .load(data.getHeroImage())
                    .into(ivNewsHeroImage);
        }
    }
}
