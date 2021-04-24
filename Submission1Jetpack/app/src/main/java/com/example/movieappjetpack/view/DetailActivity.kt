package com.example.movieappjetpack.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.movieappjetpack.R
import com.example.movieappjetpack.data.ContentEntity
import com.example.movieappjetpack.databinding.ActivityDetailBinding
import com.example.movieappjetpack.viewmodel.ContentViewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: ContentViewModel
    private lateinit var share: String
    private lateinit var link: String
    //private lateinit var adapter: ContentAdapter
    //private lateinit var contentEntity: ContentEntity

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setDetailActionBar()
        setDeatilUI()
    }

    private fun setDetailActionBar() {
        val actionBar = supportActionBar
        actionBar!!.setTitle(R.string.detail)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setDeatilUI() {
        val id = intent.getStringExtra(EXTRA_DETAIL)
        val mBundle = Bundle()
        mBundle.putString(EXTRA_DETAIL, id)
        val state = intent.extras
        val tempId = state?.getString(EXTRA_DETAIL)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[ContentViewModel::class.java]

        if (state != null) {
            if (tempId != null) {
                viewModel.setContentDetail(tempId)
                getData(viewModel.getContentDetail(), this)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_share ->{
                val shareUser = "Username: $share"
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareUser)
                shareIntent.type = "text/html"
                startActivity(Intent.createChooser(shareIntent, "Share using"))

                /*Intent(this, DetailActivity::class.java).also {
                    startActivity(it)
                }*/
            }
            R.id.menu_web ->{
                val url = link
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(intent)

                /*Intent(this, DetailActivity::class.java).also {
                    startActivity(it)
                }*/
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getData(content: ContentEntity, context: Context) {
        binding.apply {
            tvTitleDetail.text = content.title
            tvDateRelease.text = content.dateRelease
            tvRating.text = content.vote
            tvPopularity.text = content.popularity
            tvDescription.text = content.description

            val requestOptions = RequestOptions()
            requestOptions.placeholder(R.drawable.ic_loading)
            requestOptions.error(R.drawable.ic_error)

            Glide.with(context)
                .load(content.image)
                .apply(RequestOptions()
                    .placeholder(R.drawable.ic_loading)
                    .error(R.drawable.ic_error))
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(ivContentDetail)

            share = StringBuilder("Title: ${content.title}").append("\nDate Release: ${content.dateRelease}").toString()
            link = StringBuilder("${content.link} ").toString()
        }
    }
}