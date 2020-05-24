package studio.bz_soft.newsreader.data.repository

import androidx.lifecycle.LiveData
import org.koin.core.KoinComponent
import studio.bz_soft.newsreader.data.http.ApiClientInterface
import studio.bz_soft.newsreader.data.http.Either
import studio.bz_soft.newsreader.data.http.safeRequest
import studio.bz_soft.newsreader.data.models.*
import studio.bz_soft.newsreader.data.models.db.News

class Repository(
    private val database: DatabaseRepositoryInterface,
    private val storage: LocalStorageInterface,
    private val client: ApiClientInterface
) : RepositoryInterface, KoinComponent {

    override suspend fun getNewsList(token: String): Either<Exception, List<NewsModel>> =
        safeRequest { client.getNews(token) }

    // DB functions
    override suspend fun insertNews(news: News) {
        database.insertNews(news)
    }

    override suspend fun deleteNews(news: News) {
        database.deleteNews(news)
    }

    override suspend fun updateNews(news: News) {
        database.updateNews(news)
    }

    override fun getAllFromNews(): LiveData<List<News>> = database.getAllFromNews()
    override suspend fun getRecords(newsId: String): List<News> = database.getRecords(newsId)
}