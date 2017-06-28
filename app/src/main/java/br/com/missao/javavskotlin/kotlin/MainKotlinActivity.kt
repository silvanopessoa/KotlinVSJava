package br.com.missao.javavskotlin.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import br.com.missao.javavskotlin.R
import br.com.missao.javavskotlin.kotlin.adapters.RedditNewsAdater
import br.com.missao.javavskotlin.kotlin.api.RedditNewsDataResponse
import br.com.missao.javavskotlin.kotlin.domains.MainDomain
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * MainScreen Kotlin Controller
 */
class MainKotlinActivity : AppCompatActivity() {

    /**
     * Deals with Activity Requests
     */
    val domain = MainDomain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        loadViews()
    }


    /**
     * Loads views initial contents and events
     */
    private fun loadViews() {
        setupContent()
        bindEvents()
    }

    /**
     * Setup views initial contents
     */
    private fun setupContent() {
        textTitle.text = getString(R.string.instructions)
    }

    /**
     * Binds views events
     */
    private fun bindEvents() {
        btnClickMe.setOnClickListener {
            requestRedditNews()
        }
    }

    /**
     * Request Reddit's most relevant news
     */
    private fun requestRedditNews() {
        doAsync {
            val news = domain.requestRedditNews()
            uiThread {
                setupRecyclerView(news)
            }
        }
    }

    /**
     * Sets up recyclerView
     */
    private fun setupRecyclerView(data: List<RedditNewsDataResponse>?) {
        data?.let {
            textTitle.visibility = View.GONE
            btnClickMe.visibility = View.GONE
            recyclerNews.apply {
                this.layoutManager = LinearLayoutManager(recyclerNews.context)
                this.setHasFixedSize(true)
                this.adapter = RedditNewsAdater(it)
                this.visibility = View.VISIBLE
            }
        }
    }

}
