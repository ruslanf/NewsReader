package studio.bz_soft.newsreader.data.models.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import studio.bz_soft.newsreader.data.models.db.News
import studio.bz_soft.newsreader.data.repository.RepositoryInterface

class NewsViewModel(
    private val repository: RepositoryInterface
) : ViewModel() {
    fun getNewsList(): LiveData<List<News>> = repository.getAllFromNews()
}