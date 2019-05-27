package com.padcmyanmar.padc7.mmnews.data.models

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

import com.padcmyanmar.padc7.mmnews.network.NewsDataAgent
import com.padcmyanmar.padc7.mmnews.network.RetrofitDA
import com.padcmyanmar.padc7.mmnews.persistence.NewsDatabase

abstract class BaseModel {

    protected val mDataAgent: NewsDataAgent=RetrofitDA
    protected lateinit var mNewsDB: NewsDatabase
    protected lateinit var sharedPreferences :SharedPreferences

    fun initNewsDB(context : Context) {
        mNewsDB = NewsDatabase.getDatabase(context)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    }

}
