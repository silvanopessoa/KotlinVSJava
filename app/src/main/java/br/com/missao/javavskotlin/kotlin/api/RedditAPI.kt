package br.com.missao.javavskotlin.kotlin.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Reddit Rest API
 */
interface RedditAPI {

    /**
     * Gets the most relevant news from Reddit
     * @param limit Quantity of news desired
     */
    @GET("top.json")
    fun getTopNews(@Query("limit") limit: String): Call<RedditNewsResponse>
}