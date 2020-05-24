package studio.bz_soft.newsreader.data.repository

import androidx.lifecycle.LiveData
import studio.bz_soft.newsreader.data.models.db.News

interface DatabaseRepositoryInterface {
    suspend fun insertNews(news: News)
    suspend fun deleteNews(news: News)
    suspend fun updateNews(news: News)
    fun getAllFromNews(): LiveData<List<News>>
    suspend fun getRecords(newsId: String): List<News>
}