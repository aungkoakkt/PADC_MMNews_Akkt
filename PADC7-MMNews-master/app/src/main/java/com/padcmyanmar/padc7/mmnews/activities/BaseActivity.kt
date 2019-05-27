package com.padcmyanmar.padc7.mmnews.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.padcmyanmar.padc7.mmnews.mvp.views.BaseView


abstract class BaseActivity : AppCompatActivity(), BaseView {

    override fun getContext(): Context {
        return applicationContext
    }
}
