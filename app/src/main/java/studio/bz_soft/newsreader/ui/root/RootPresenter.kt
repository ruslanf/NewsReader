package studio.bz_soft.newsreader.ui.root

import android.content.Context
import kotlinx.coroutines.*
import org.koin.core.KoinComponent
import org.koin.core.inject
import studio.bz_soft.newsreader.R
import studio.bz_soft.newsreader.data.http.Left
import studio.bz_soft.newsreader.data.http.Right
import studio.bz_soft.newsreader.data.models.NewsModel
import studio.bz_soft.newsreader.data.models.db.News
import studio.bz_soft.newsreader.root.Constants.EMPTY_STRING
import studio.bz_soft.newsreader.root.Constants.TOKEN
import studio.bz_soft.newsreader.root.showError
import kotlin.coroutines.CoroutineContext

class RootPresenter(
    private val appContext: Context
) : CoroutineScope, KoinComponent {

    private val logTag = RootPresenter::class.java.simpleName

    private val controller by inject<RootController>()

    private var job = Job()
    override val coroutineContext: CoroutineContext get() = Dispatchers.Main + job

    private var token = EMPTY_STRING
    private var listNewsModel: List<NewsModel>? = null
    private var ex: Exception? = null

    init {
        token = TOKEN
    }

    fun synchronize() {
        launch {
            coroutineScope {
                val request = async(SupervisorJob(job) + Dispatchers.IO) {
                    when (val r = controller.getNewList(token)) {
                        is Right -> { listNewsModel = r.value }
                        is Left -> { ex = r.value }
                    }
                }
                request.await()
            }
            ex?.let {
                showError(appContext, it, R.string.root_sync_message_error, logTag)
            } ?: run {
                coroutineScope {
                    listNewsModel?.let { news ->
                        var listNews: List<News> = mutableListOf()
                        val records = async(SupervisorJob(job) + Dispatchers.IO) {
                            news.forEach { listNews = controller.getRecords(it.id!!) }
                        }
                        records.await()
                        if (listNews.isEmpty()) {
                            val dbInsert = async(SupervisorJob(job) + Dispatchers.IO) {
                                news.forEach {
                                    controller.insertNews(News(newsId = it.id!!, title = it.title!!, text = it.text!!))
                                }
                            }
                            dbInsert.await()
                        } else {
                            val dbUpdate = async(SupervisorJob(job) + Dispatchers.IO) {
                                listNews.forEach { controller.insertNews(it) }
                            }
                            dbUpdate.await()
                        }
                    }
                }
            }
        }
    }
}