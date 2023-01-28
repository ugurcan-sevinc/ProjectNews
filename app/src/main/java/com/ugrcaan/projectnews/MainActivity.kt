package com.ugrcaan.projectnews

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ugrcaan.projectnews.adapters.NewsAdapter
import com.ugrcaan.projectnews.databinding.ActivityMainBinding
import com.ugrcaan.projectnews.model.NewsArticle
import com.ugrcaan.projectnews.model.NewsModel
import com.ugrcaan.projectnews.service.NewsAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(), NewsAdapter.Listener {

    private lateinit var binding : ActivityMainBinding
    //private lateinit var viewModel: MainVM
    private val BASE_URL = "https://newsapi.org/v2/"
    private var compositeDisposable : CompositeDisposable? = null
    //private var newsModels : ArrayList<NewsModel>? = null
    private var newsAdapter : NewsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        newsRecyclerView.layoutManager = layoutManager

        loadData()
    }



    private fun loadData() {

        compositeDisposable = CompositeDisposable()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(NewsAPI::class.java)


        compositeDisposable?.add(retrofit.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ newsModel ->
                handleResponse(newsModel)
            }, { error ->
                // handle the error here
                Log.d("MainActivity", "Error loading data: $error")
            }))

    }

    private fun handleResponse(newsModel: NewsModel){ //TODO
        newsModel.let {
            newsAdapter = NewsAdapter(it.articles, this@MainActivity)
            newsRecyclerView.adapter = newsAdapter
        }
    }

    override fun onItemClick(article: NewsArticle) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
        startActivity(browserIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()
    }
}