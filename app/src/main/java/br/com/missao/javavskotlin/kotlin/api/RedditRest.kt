package br.com.missao.javavskotlin.kotlin.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Implementation of Reddit Rest API
 */
class RedditRest : RedditAPI {

    private val api: RedditAPI
    private val baseUrl = "https://www.reddit.com"

    init {
        val retrofit = Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        api = retrofit.create(RedditAPI::class.java)
    }

    override fun getTopNews(limit: String): Call<RedditNewsResponse>
            = api.getTopNews(limit)
}