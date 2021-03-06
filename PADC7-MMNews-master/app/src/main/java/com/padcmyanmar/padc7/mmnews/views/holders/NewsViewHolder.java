package com.padcmyanmar.padc7.mmnews.views.holders;

import android.view.View;

import androidx.annotation.NonNull;

import com.padcmyanmar.padc7.mmnews.data.vos.NewsVO;
import com.padcmyanmar.padc7.mmnews.delegates.NewsItemDelegate;

public class NewsViewHolder extends BaseNewsViewHolder {

    private NewsItemDelegate mDelegate;

    public NewsViewHolder(@NonNull View itemView, @NonNull final NewsItemDelegate newsItemDelegate) {
        super(itemView);
        mDelegate=newsItemDelegate;
    }

    @Override
    public void bindData(final NewsVO data) {
        super.bindData(data);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDelegate.onTapNewsItem(data.getNewsId());
            }
        });

    }
}
