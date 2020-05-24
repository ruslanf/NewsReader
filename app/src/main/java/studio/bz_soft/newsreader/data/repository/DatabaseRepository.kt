package studio.bz_soft.newsreader.data.repository

import androidx.lifecycle.LiveData
import studio.bz_soft.newsreader.data.db.DbClientInterface
import studio.bz_soft.newsreader.data.models.db.*

class DatabaseRepository(private val dbClient: DbClientInterface) : DatabaseRepositoryInterface {

    override suspend fun insertNews(news: News) {
        dbClient.insertNews(news)
    }

    override suspend fun deleteNews(news: News) {
        dbClient.deleteNews(news)
    }

    override suspend fun updateNews(news: News) {
        dbClient.updateNews(news)
    }

    override fun getAllFromNews(): LiveData<List<News>> = dbClient.getAllFromNews()
    override suspend fun getRecords(newsId: String): List<News> = dbClient.getRecords(newsId)
}