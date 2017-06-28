package br.com.missao.javavskotlin.kotlin.adapters

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.missao.javavskotlin.R
import br.com.missao.javavskotlin.kotlin.api.RedditNewsDataResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_news.view.*


/**
 * Reddit News Adapter
 */
class RedditNewsAdater(
        val topNews: List<RedditNewsDataResponse>
) : RecyclerView.Adapter<RedditNewsAdater.ViewHolder>() {

    override fun getItemCount() = topNews.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(topNews[position]) {
            holder.bindNews(this)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(
            view: View
    ) : RecyclerView.ViewHolder(view) {

        fun bindNews(news: RedditNewsDataResponse) {
            with(itemView) {
                Picasso.with(this.context).load(news.thumbnail).fit().into(this.imgThumbnail)
                this.textDescription.text = news.title
                this.textAuthor.text = news.author
                this.textComments.text = "${news.num_comments} comments"
                this.setOnClickListener {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(news.url))
                    this.context.startActivity(browserIntent)
                }
            }
        }
    }
}