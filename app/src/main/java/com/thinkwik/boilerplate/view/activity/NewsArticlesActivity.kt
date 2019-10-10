package com.thinkwik.boilerplate.view.activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.thinkwik.boilerplate.R
import com.thinkwik.boilerplate.model.entities.article.Article
import com.thinkwik.boilerplate.model.entities.article.ArticleReponse
import com.thinkwik.boilerplate.network.KEY
import com.thinkwik.boilerplate.view.adapter.ArticleListAdapater
import com.thinkwik.boilerplate.viewmodel.NewsArticleViewModel
import kotlinx.android.synthetic.main.activity_news_articles.*
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.thinkwik.boilerplate.BR


class NewsArticlesActivity : BaseActivity() {

    private lateinit var newsArticleViewMode: NewsArticleViewModel
    private lateinit var binding: ViewDataBinding
    private var articleResponse = ArticleReponse()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_news_articles)
        binding.setVariable(BR.sale, false)
        init()
    }

    override fun init() {
        val source = intent.getStringExtra(KEY.sources)
        newsArticleViewMode = ViewModelProviders.of(this).get(NewsArticleViewModel::class.java)
        newsArticleViewMode.getArticles(source)
        newsArticleViewMode.observeSourceList().observe(this, Observer {
            articleResponse = it
            binding.setVariable(BR.sale, true)
            progress.visibility = View.GONE
            setRecyclerView(articleResponse.articles as ArrayList<Article>)
        })
    }

    private fun setRecyclerView(sourceList: ArrayList<Article>) {
        val adapter = ArticleListAdapater(sourceList)
        rvArticleList.adapter = adapter
    }
}
