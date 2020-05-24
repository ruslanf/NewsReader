package studio.bz_soft.newsreader.data.http

import studio.bz_soft.newsreader.data.models.*

interface ApiClientInterface {
    suspend fun getNews(token: String): List<NewsModel>
}