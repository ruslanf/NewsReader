package studio.bz_soft.newsreader.ui.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.cell_news.view.*
import studio.bz_soft.newsreader.R
import studio.bz_soft.newsreader.data.models.db.News
import studio.bz_soft.newsreader.root.delegated.AdapterDelegateInterface
import studio.bz_soft.newsreader.root.delegated.BaseHolder

sealed class NewsListElement {
    data class NewsListItem(val news: News): NewsListElement()
}

class SimulatorItemHolder(v: View, val onClick: (News) -> Unit): BaseHolder<NewsListElement>(v) {

    override fun bindModel(item: NewsListElement) {
        super.bindModel(item)
        when (item) {
            is NewsListElement.NewsListItem -> itemView.apply {
                newsTitleTV.text = item.news.text
                setOnClickListener { onClick(item.news) }
            }
        }
    }
}

class NewsListItemDelegate(private val onClick: (News) -> Unit):
    AdapterDelegateInterface<NewsListElement> {

    override fun isForViewType(items: List<NewsListElement>, position: Int): Boolean {
        return items[position] is NewsListElement.NewsListItem
    }

    override fun createViewHolder(parent: ViewGroup): BaseHolder<NewsListElement> {
        return SimulatorItemHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.cell_news, parent, false),
            onClick
        )
    }

    override fun bindViewHolder(items: List<NewsListElement>, position: Int, holder: BaseHolder<NewsListElement>) {
        holder.bindModel(items[position])
    }
}