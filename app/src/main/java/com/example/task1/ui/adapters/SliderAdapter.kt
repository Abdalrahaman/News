package com.example.task1.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.task1.data.model.Article
import com.example.task1.databinding.SliderItemBinding
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderAdapter(private val clickListener: SliderClickListener) :
    SliderViewAdapter<SliderAdapter.SliderViewHolder>() {

    private var mSliderItems: MutableList<Article> = ArrayList()

    fun renewItems(sliderItems: MutableList<Article>) {
        mSliderItems = sliderItems
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        mSliderItems.removeAt(position)
        notifyDataSetChanged()
    }

    fun addItem(article: Article) {
        mSliderItems.add(article)
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        //slider view count could be dynamic size
        return mSliderItems.size
    }

    override fun onCreateViewHolder(parent: ViewGroup): SliderViewHolder {
        return SliderViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.bind(clickListener, mSliderItems[position])
    }

    class SliderViewHolder private constructor(private val binding: SliderItemBinding) :
        ViewHolder(binding.root) {

        fun bind(listener: SliderClickListener, article: Article) {
            binding.article = article
            binding.root.setOnClickListener {
                listener.onClick(article)
            }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): SliderViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SliderItemBinding.inflate(layoutInflater, parent, false)

                return SliderViewHolder(binding)
            }
        }
    }
}

class SliderClickListener(val clickListener: (article: Article) -> Unit) {
    fun onClick(article: Article) = clickListener(article)
}