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
import com.example.movieappjetpack.databinding.ListItemBinding
import com.example.movieappjetpack.view.DetailActivity
import java.util.ArrayList

class TvShowAdapter: RecyclerView.Adapter<TvShowAdapter.ContentViewHolder>() {
    private var listContent = ArrayList<ContentEntity>()

    fun setContent(content: List<ContentEntity>?) {
        if (content == null) return
        this.listContent.clear()
        this.listContent.addAll(content)
    }

    class ContentViewHolder(private val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(content: ContentEntity){
            with(binding){
                tvTitle.text = content.title
                tvRelease.text = content.dateRelease
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_COURSE, content.id)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(content.image)
                    .apply(RequestOptions()
                        .placeholder(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop()
                    .into(ivContent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val listItemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContentViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        val content = listContent[position]
        holder.bind(content)
    }

    override fun getItemCount(): Int = listContent.size
}