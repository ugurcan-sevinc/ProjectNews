package com.ugrcaan.projectnews.service
import com.ugrcaan.projectnews.model.NewsModel
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface NewsAPI {

    @GET("top-headlines?country=us&category=business&apiKey=50085e78fbc140f085c568cd8d539e25")
    fun getData(): Observable<NewsModel>
}