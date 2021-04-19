package com.example.movieappjetpack.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieappjetpack.R
import com.example.movieappjetpack.adapter.MovieAdapter
import com.example.movieappjetpack.databinding.FragmentListBinding
import com.example.movieappjetpack.viewmodel.MovieViewModel

class MovieFragment: Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentListBinding.bind(view)

        if (activity != null) {
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MovieViewModel::class.java]
            val content = viewModel.getContent()

            val movieAdapter = MovieAdapter()
            movieAdapter.setContent(content)

            binding.apply {
                rvList.layoutManager = LinearLayoutManager(context)
                rvList.setHasFixedSize(true)
                rvList.adapter = movieAdapter
            }
        }
    }
}