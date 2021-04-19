package com.example.movieappjetpack.data

data class ContentEntity(
    var id: String,
    var title: String,
    var description: String,
    var dateRelease: String,
    //var bookmarked: Boolean = false,
    var image: String,
    var vote: String,
    var popularity: String
)
