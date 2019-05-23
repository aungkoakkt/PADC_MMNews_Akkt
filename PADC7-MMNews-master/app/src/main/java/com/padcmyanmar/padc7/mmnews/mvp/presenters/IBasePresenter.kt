package com.padcmyanmar.padc7.mmnews.mvp.presenters

/**
 *Created by Aung Ko Ko Thet on 5/4/19
 */
interface IBasePresenter {

    fun onCreate()
    fun onStart()
    fun onStop()
    fun onDestroy()
}