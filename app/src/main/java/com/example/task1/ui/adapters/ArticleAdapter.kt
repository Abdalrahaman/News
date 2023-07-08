package com.example.task1.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.task1.data.model.Article
import com.example.task1.databinding.ArticleItemBinding

class ArticleAdapter(val clickListener: ArticleClickListener) :
    ListAdapter<Article, ArticleAdapter.ArticleViewHolder>(
        ArticleDiffCallback()
    ) {

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(clickListener, getItem(position))
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleViewHolder {
        return ArticleViewHolder.from(parent)
    }

    class ArticleViewHolder private constructor(private val binding: ArticleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: ArticleClickListener, article: Article) {
            binding.article = article
            binding.root.setOnClickListener {
                listener.onClick(article)
            }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ArticleViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ArticleItemBinding.inflate(layoutInflater, parent, false)

                return ArticleViewHolder(binding)
            }
        }
    }
}

class ArticleDiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(
        oldItem: Article,
        newItem: Article
    ): Boolean {
        return oldItem.source.id == newItem.source.id
    }

    override fun areContentsTheSame(
        oldItem: Article,
        newItem: Article
    ): Boolean {
        return oldItem == newItem
    }
}

class ArticleClickListener(val clickListener: (article: Article) -> Unit) {
    fun onClick(article: Article) = clickListener(article)
}