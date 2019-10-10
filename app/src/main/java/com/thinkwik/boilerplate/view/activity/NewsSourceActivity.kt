package com.thinkwik.boilerplate.view.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.thinkwik.boilerplate.R
import com.thinkwik.boilerplate.helper.IntentHelper.redirectToSourceList
import com.thinkwik.boilerplate.helper.IntentHelper.redirectToSourceListArticleList
import com.thinkwik.boilerplate.model.entities.sources.Sources
import com.thinkwik.boilerplate.view.adapter.SourceListAdapater
import com.thinkwik.boilerplate.viewmodel.NewsSourceViewModel
import kotlinx.android.synthetic.main.activity_news_source.*


class NewsSourceActivity : BaseActivity() {

    private lateinit var newsSourceViewMode: NewsSourceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_source)
        init()
    }

    override fun init() {
        newsSourceViewMode = ViewModelProviders.of(this).get(NewsSourceViewModel::class.java)
        newsSourceViewMode.getSource()
        newsSourceViewMode.observeSourceList().observe(this, Observer {
            setRecyclerView(it.sources as ArrayList<Sources>)
        })
    }

    private fun setRecyclerView(sourceList: ArrayList<Sources>) {
        val adapter = SourceListAdapater(sourceList)
        rvSourceList.adapter = adapter
        rvSourceList.setOnItemClickListener { easyRecyclerView, view, position ->
            this@NewsSourceActivity.redirectToSourceListArticleList(sourceList[position].id)
        }
    }

}
