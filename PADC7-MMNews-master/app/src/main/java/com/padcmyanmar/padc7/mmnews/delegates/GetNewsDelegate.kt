package com.padcmyanmar.padc7.mmnews.delegates

import com.padcmyanmar.padc7.mmnews.data.vos.NewsVO

interface GetNewsDelegate : BaseNetworkDelegate {

    fun onSuccess(newsList: List<NewsVO>)
}
