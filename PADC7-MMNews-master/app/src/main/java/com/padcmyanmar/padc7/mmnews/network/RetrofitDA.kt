package com.padcmyanmar.padc7.mmnews.network

import com.padcmyanmar.padc7.mmnews.delegates.GetNewsDelegate
import com.padcmyanmar.padc7.mmnews.delegates.LoginDelegate
import com.padcmyanmar.padc7.mmnews.network.responses.GetNewsResponse
import com.padcmyanmar.padc7.mmnews.network.responses.LoginResponse

import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitDA : NewsDataAgent {

    private val mNewsAPI: NewsAPI

    init {
        val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl("http://padcmyanmar.com/padc-3/mm-news/apis/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

        mNewsAPI = retrofit.create(NewsAPI::class.java)
    }

    override fun loadNews(page: Int, accessToken: String, newsResponseDelegate: GetNewsDelegate) {

        val callNewsResponse = mNewsAPI.loadNews(accessToken, page)

        callNewsResponse.enqueue(object : NewsCallback<GetNewsResponse, GetNewsDelegate>(newsResponseDelegate) {

            override fun onResponse(call: Call<GetNewsResponse>, response: Response<GetNewsResponse>) {

                super.onResponse(call, response)
                val newsResponse = response.body()
                if(newsResponse!!.newsList!=null){
                    networkDelegate.onSuccess(newsResponse.newsList)
                }

            }
        })
        /*
        callNewsResponse.enqueue(new Callback<GetNewsResponse>() {
            @Override
            public void onResponse(Call<GetNewsResponse> call, Response<GetNewsResponse> response) {
                GetNewsResponse newsResponse = response.body();
                if (newsResponse != null) {
                    if (newsResponse.isResponseSuccess()) {
                        newsResponseDelegate.onSuccess(newsResponse.getNewsList());
                    } else {
                        newsResponseDelegate.onFail(newsResponse.getMessage());
                    }
                } else {
                    newsResponseDelegate.onFail("Response is null.");
                }
            }

            @Override
            public void onFailure(Call<GetNewsResponse> call, Throwable t) {
                newsResponseDelegate.onFail(t.getMessage());
            }
        });
        */
    }

    override fun login(phoneNumber: String, password: String, loginDelegate: LoginDelegate) {
        val callLoginResponse = mNewsAPI.login(phoneNumber, password)
        callLoginResponse.enqueue(object : NewsCallback<LoginResponse, LoginDelegate>(loginDelegate) {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                super.onResponse(call, response)
                val loginResponse = response.body()
                val loginUser = loginResponse!!.loginUser
                networkDelegate.onSuccess(loginUser)
            }
        })
    }

    override fun register(phoneNUmber: String, name: String, password: String) {

    }
}
