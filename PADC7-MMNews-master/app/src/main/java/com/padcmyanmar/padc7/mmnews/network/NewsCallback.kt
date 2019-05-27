package com.padcmyanmar.padc7.mmnews.network

import com.padcmyanmar.padc7.mmnews.delegates.BaseNetworkDelegate
import com.padcmyanmar.padc7.mmnews.network.responses.BaseResponse

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class NewsCallback<T : BaseResponse, W : BaseNetworkDelegate> internal constructor(internal var networkDelegate: W) : Callback<T> {

    override fun onResponse(call: Call<T>, response: Response<T>) {

        val newsResponse = response.body()

        if (newsResponse == null) {

            networkDelegate.onFail("Response is null.", 0)

        } else {

            if (!newsResponse.isResponseSuccess) {
                networkDelegate.onFail(newsResponse.message, newsResponse.code)
            }
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        networkDelegate.onFail(t.message!!, 100)
    }
}
