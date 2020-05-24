package studio.bz_soft.newsreader.ui.news

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_root.*
import kotlinx.android.synthetic.main.fragment_news.*
import org.koin.android.ext.android.inject
import studio.bz_soft.newsreader.R
import studio.bz_soft.newsreader.data.models.db.News
import studio.bz_soft.newsreader.data.models.viewmodels.NewsViewModel
import studio.bz_soft.newsreader.root.Constants.KEY_TEXT
import studio.bz_soft.newsreader.root.Constants.KEY_TITLE
import studio.bz_soft.newsreader.root.delegated.DelegateAdapter
import studio.bz_soft.newsreader.root.scrollToPosition
import studio.bz_soft.newsreader.ui.root.RootActivity
import studio.bz_soft.newsreader.ui.root.RootPresenter

class NewsFragment : Fragment() {

    private val newsListViewModel by inject<NewsViewModel>()
    private val presenter by inject<RootPresenter>()

    private val newsListAdapter = DelegateAdapter(NewsListItemDelegate { newsModel ->
        val bundle = bundleOf(
            KEY_TITLE to newsModel.title,
            KEY_TEXT to newsModel.text
        )
        view?.findNavController()?.navigate(R.id.detailedFragment, bundle)
    })

    private var recyclerViewState: Parcelable? = null
    private var position = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_news, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.apply {
            newsRV.apply {
                adapter = newsListAdapter
                layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)
                recyclerViewState?.apply {
                    layoutManager?.onRestoreInstanceState(recyclerViewState)
                    scrollToPosition(newsRV, position)
                }
            }
            swipeRefresh.setOnRefreshListener { refreshListener(this) }

            newsListViewModel.getNewsList().observe(viewLifecycleOwner, Observer {
                renderNewsList(it)
            })
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as RootActivity).mainBottomNavigationMenu.visibility = View.VISIBLE
    }

    private fun renderNewsList(news: List<News>) {
        newsListAdapter.apply {
            items.clear()
            items.addAll(
                news.map { NewsListElement.NewsListItem(it) }
            )
            notifyDataSetChanged()
        }
    }

    private fun refreshListener(v: View) {
        v.apply {
            presenter.synchronize()
            swipeRefresh.isRefreshing = false
        }
    }
}