package studio.bz_soft.newsreader.ui.root

import studio.bz_soft.newsreader.data.http.Either
import studio.bz_soft.newsreader.data.models.NewsModel
import studio.bz_soft.newsreader.data.models.db.News
import studio.bz_soft.newsreader.data.repository.RepositoryInterface

class RootController(
    private val repository: RepositoryInterface
) : RootInterface {
    override suspend fun getNewList(token: String): Either<Exception, List<NewsModel>> =
        repository.getNewsList(token)

    override suspend fun insertNews(news: News) {
        repository.insertNews(news)
    }

    override suspend fun getRecords(newsId: String): List<News> = repository.getRecords(newsId)
}