package com.ugrcaan.projectnews.adapters

import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ugrcaan.projectnews.R
import com.ugrcaan.projectnews.model.NewsArticle
import kotlinx.android.synthetic.main.component_news_row.view.*
import java.util.*
import kotlin.collections.ArrayList

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

            itemView.publishedAtLabel.text = formatTime(article.publishedAt)
            itemView.descriptionLabel.text = article.description

            Glide.with(itemView.context).load(article.urlToImage).placeholder(R.color.backgroundColorDark).dontAnimate().into(itemView.header_image)
        }

        private fun formatTime(publishedAt: String): String {
            val formatInput = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            val formatOutput = SimpleDateFormat("dd/MM/yyyy - HH.mm", Locale.getDefault())
            val parsedInput = formatInput.parse(publishedAt)
            return formatOutput.format(parsedInput)
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