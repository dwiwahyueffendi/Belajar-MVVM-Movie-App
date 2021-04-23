package com.example.movieappjetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieappjetpack.data.ContentEntity
import com.example.movieappjetpack.data.dummy.DataDummy

class ContentViewModel: ViewModel() {
    private lateinit var id: String
    private lateinit var endPoint: ContentEntity
    val showMovie = DataDummy.generateDummyMovie()
    val showTvShow = DataDummy.generateDummyTvShow()

    fun getContentMovie(): List<ContentEntity> = DataDummy.generateDummyMovie()

    fun getContentTvShow(): List<ContentEntity> = DataDummy.generateDummyTvShow()

    fun setContentDetail(id: String){
        this.id = id
    }

    fun getContentDetail(): ContentEntity{
        for (idLooping in showMovie){
            if (idLooping.id == id){
                endPoint = idLooping
            }
        }

        for (idLooping in showTvShow){
            if (idLooping.id == id){
                endPoint = idLooping
            }
        }

        return endPoint
    }
}