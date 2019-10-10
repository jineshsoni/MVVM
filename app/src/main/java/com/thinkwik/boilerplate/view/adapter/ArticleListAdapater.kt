package com.thinkwik.boilerplate.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.thinkwik.boilerplate.R
import com.thinkwik.boilerplate.model.entities.article.Article

class ArticleListAdapater(val data: ArrayList<Article>): RecyclerView.Adapter<ArticleViewHolder>() {
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.row_article, parent, false)
        return ArticleViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size
}

class ArticleViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: Any) {
        binding.setVariable(BR.data, data)
        binding.executePendingBindings()
    }
}