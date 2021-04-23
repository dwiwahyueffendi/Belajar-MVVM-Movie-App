package com.example.movieappjetpack.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.movieappjetpack.R
import com.example.movieappjetpack.data.ContentEntity
import com.example.movieappjetpack.databinding.ActivityDetailBinding
import java.util.ArrayList

class ContentDetailAdapter: RecyclerView.Adapter<ContentDetailAdapter.ContentViewHolder>() {
    private var listContent = ArrayList<ContentEntity>()

    fun setContent(content: List<ContentEntity>?) {
        if (content == null) return

        if (content.size > 0){
            listContent.clear()
        }
        listContent.addAll(content)

        notifyDataSetChanged()
    }

    inner class ContentViewHolder(private val binding: ActivityDetailBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(content: ContentEntity){
            binding.apply{
                tvTitleDetail.text = content.title
                tvDateRelease.text = content.dateRelease
                tvRating.text = content.vote
                tvPopularity.text = content.popularity
                tvDescription.text = content.description

                val requestOptions = RequestOptions()
                requestOptions.placeholder(R.drawable.ic_loading)
                requestOptions.error(R.drawable.ic_error)

                Glide.with(itemView.context)
                    .load(content.image)
                    .apply(
                        RequestOptions()
                        .placeholder(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop()
                    .into(ivContentDetail)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val view = ActivityDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(listContent[position])
    }

    override fun getItemCount(): Int = listContent.size
}