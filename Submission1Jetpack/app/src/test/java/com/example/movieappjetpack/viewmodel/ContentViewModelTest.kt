package com.example.movieappjetpack.viewmodel

import com.example.movieappjetpack.data.dummy.DataDummy
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ContentViewModelTest{
    private lateinit var contentViewModel: ContentViewModel
    private val dummyMovie = DataDummy.generateDummyMovie()[0]
    private val idMovie = dummyMovie.id
    private val dummyTvShow = DataDummy.generateDummyTvShow()[0]
    private val idTvShow = dummyTvShow.id
    //private val contentMovie = contentViewModel.getContentMovie()


    @Before
    fun setUp() {
        contentViewModel = ContentViewModel()
        //contentViewModel.setContentDetail(idMovie)
        //contentViewModel.setContentDetail(idTvShow)
    }

    @Test
    fun testGetContentMovie() {
        //Testing Movie
        val contentEntity = contentViewModel.getContentMovie()
        assertNotNull(contentEntity)
        assertEquals(10, contentEntity.size)
    }

    @Test
    fun testGetContentTvShow() {
        //Testing TvShow
        val contentEntity = contentViewModel.showTvShow
        assertNotNull(contentEntity)
        assertEquals(10, contentEntity.size)
    }

    @Test
    fun testSetContentDetail() {
        contentViewModel.setContentDetail(idMovie)
        contentViewModel.setContentDetail(idTvShow)
    }

    @Test
    fun testGetContentDetail() {
        //Testing Detail Movie
        contentViewModel.setContentDetail(dummyMovie.id)
        val contentMovie = contentViewModel.getContentDetail()
        Assert.assertNotNull(contentMovie)
        assertEquals(dummyMovie.id, contentMovie.id)
        assertEquals(dummyMovie.title, contentMovie.title)
        assertEquals(dummyMovie.description, contentMovie.description)
        assertEquals(dummyMovie.dateRelease, contentMovie.dateRelease)
        assertEquals(dummyMovie.image, contentMovie.image)
        assertEquals(dummyMovie.vote, contentMovie.vote)
        assertEquals(dummyMovie.popularity, contentMovie.popularity)
        assertEquals(dummyMovie.link, contentMovie.link)

        //Testing Detail TvShow
        contentViewModel.setContentDetail(dummyTvShow.id)
        val contentTvShow = contentViewModel.getContentDetail()
        Assert.assertNotNull(contentTvShow)
        assertEquals(dummyTvShow.id, contentTvShow.id)
        assertEquals(dummyTvShow.title, contentTvShow.title)
        assertEquals(dummyTvShow.description, contentTvShow.description)
        assertEquals(dummyTvShow.dateRelease, contentTvShow.dateRelease)
        assertEquals(dummyTvShow.image, contentTvShow.image)
        assertEquals(dummyTvShow.vote, contentTvShow.vote)
        assertEquals(dummyTvShow.popularity, contentTvShow.popularity)
        assertEquals(dummyTvShow.link, contentTvShow.link)
    }
}