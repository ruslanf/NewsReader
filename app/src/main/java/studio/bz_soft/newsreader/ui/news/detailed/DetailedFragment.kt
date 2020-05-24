package studio.bz_soft.newsreader.ui.news.detailed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_root.*
import kotlinx.android.synthetic.main.fragment_detailed_news.view.*
import studio.bz_soft.newsreader.R
import studio.bz_soft.newsreader.root.Constants.EMPTY_STRING
import studio.bz_soft.newsreader.root.Constants.KEY_TEXT
import studio.bz_soft.newsreader.root.Constants.KEY_TITLE
import studio.bz_soft.newsreader.ui.root.RootActivity

class DetailedFragment : Fragment() {

    private var title = EMPTY_STRING
    private var text = EMPTY_STRING

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.apply {
            getString(KEY_TITLE)?.let { title = it }
            getString(KEY_TEXT)?.let { text = it }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_detailed_news, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.apply {
            titleTV.text = title
            textTV.text = text
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as RootActivity).mainBottomNavigationMenu.visibility = View.GONE
    }
}