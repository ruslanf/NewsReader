package studio.bz_soft.newsreader.ui.root

import studio.bz_soft.newsreader.data.http.Either
import studio.bz_soft.newsreader.data.models.NewsModel
import studio.bz_soft.newsreader.data.models.db.News

interface RootInterface {
    suspend fun getNewList(token: String): Either<Exception, List<NewsModel>>
    suspend fun insertNews(news: News)
    suspend fun getRecords(newsId: String): List<News>
}