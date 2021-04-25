package com.example.submissionmovieapp.data.endpoint

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: String,
    val original_title: String,
    val overview: String,
    val popularity: String,
    val poster_path: String,
    val release_date: String,
    val vote_average: String
): Parcelable{
    val baseUrlImage: String
    get() = "https://image.tmdb.org/t/p/w500/"
}
