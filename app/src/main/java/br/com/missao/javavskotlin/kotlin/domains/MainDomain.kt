package br.com.missao.javavskotlin.kotlin.domains

import br.com.missao.javavskotlin.kotlin.api.RedditNewsDataResponse
import br.com.missao.javavskotlin.kotlin.api.RedditRest

/**
 *  Domain from Main Activity
 */
object MainDomain {

    /**
     * Reddit's API
     */
    val api = RedditRest()

    /**
     * Max number of result returned by the Request
     */
    val limit = "10"

    /**
     * Request Reddit's most relevant news
     */
    fun requestRedditNews(): List<RedditNewsDataResponse>? {
        return api.getTopNews(limit).execute().body()
                ?.data?.children?.map { it.data }
    }
}