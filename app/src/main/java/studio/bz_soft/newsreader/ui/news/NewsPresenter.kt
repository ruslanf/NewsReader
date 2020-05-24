package studio.bz_soft.newsreader.ui.news

import studio.bz_soft.newsreader.data.repository.RepositoryInterface

class NewsPresenter(
    private val repository: RepositoryInterface
) : NewsInterface