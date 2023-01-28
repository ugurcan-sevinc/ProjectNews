package com.ugrcaan.projectnews.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ugrcaan.projectnews.adapters.NewsAdapter
import com.ugrcaan.projectnews.model.NewsModel
import androidx.recyclerview.widget.RecyclerView
import com.ugrcaan.projectnews.service.NewsAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainVM(application: Application) : AndroidViewModel(application) {



}
