package studio.bz_soft.newsreader.data.db

import android.app.Application
import androidx.lifecycle.LiveData
import studio.bz_soft.newsreader.data.models.db.News

class DbClient(application: Application) : DbClientInterface {

    private val db by lazy { RoomDB.getDataBase(application) }
    private val newsDao by lazy { db.newsDao() }

    override suspend fun insertNews(news: News) {
        newsDao.insert(news)
    }

    override suspend fun deleteNews(news: News) {
        newsDao.delete(news)
    }

    override suspend fun updateNews(news: News) {
        newsDao.update(news)
    }

    override fun getAllFromNews(): LiveData<List<News>> = newsDao.getAllFromNews()

    override suspend fun getRecords(newsId: String): List<News> = newsDao.getRecords(newsId)
}