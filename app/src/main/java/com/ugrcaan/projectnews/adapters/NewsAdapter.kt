package com.ugrcaan.projectnews.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.bumptech.glide.Glide
import com.ugrcaan.projectnews.MainActivity
import com.ugrcaan.projectnews.R
import com.ugrcaan.projectnews.model.NewsArticle
import com.ugrcaan.projectnews.model.NewsModel
import kotlinx.android.synthetic.main.component_news_row.view.*

class NewsAdapter(private val articlesList: ArrayList<NewsArticle>, private val listener: Listener) : RecyclerView.Adapter<NewsAdapter.NewsHolder>(){

    interface Listener {
        fun onItemClick(article: NewsArticle)
    }

    class NewsHolder(view: View) : RecyclerView.ViewHolder(view){

        fun bind(article: NewsArticle, listener: Listener){
            itemView.setOnClickListener{
                listener.onItemClick(article)
            }

            itemView.titleLabel.text = article.title
            itemView.sourceNameLabel.text = article.source.name
            itemView.publishedAtLabel.text = article.publishedAt
            itemView.descriptionLabel.text = article.description

            Glide.with(itemView.context).load(article.urlToImage).placeholder(R.color.backgroundColorDark).dontAnimate().into(itemView.header_image)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.component_news_row, parent, false)
        return NewsHolder(view)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.bind(articlesList[position], listener)
    }

    override fun getItemCount(): Int {
        return articlesList.count()
    }

}