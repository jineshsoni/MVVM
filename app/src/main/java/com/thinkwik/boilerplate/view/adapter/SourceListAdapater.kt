package com.thinkwik.boilerplate.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.thinkwik.boilerplate.R
import com.thinkwik.boilerplate.model.entities.sources.Sources

class SourceListAdapater(val data: ArrayList<Sources>): RecyclerView.Adapter<SourceViewHolder>() {
    override fun onBindViewHolder(holder: SourceViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.row_sources, parent, false)
        return SourceViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size
}

class SourceViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: Any) {
        binding.setVariable(BR.data, data)
        binding.executePendingBindings()
    }
}