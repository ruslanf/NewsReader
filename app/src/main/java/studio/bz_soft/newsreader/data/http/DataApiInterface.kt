package studio.bz_soft.newsreader.data.http

import retrofit2.http.*
import studio.bz_soft.newsreader.data.models.*
import studio.bz_soft.newsreader.root.Constants.BASE_API

interface DataApiInterface {

    @GET("$BASE_API/search?query=text%3Acovid&sortBy=discoverDate&sortOrder=DESC&limit=20&language=en")
    suspend fun getNews(@Header("Authorization") token: String): List<NewsModel>
}