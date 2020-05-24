package studio.bz_soft.newsreader.ui.root

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuBuilder
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_root.*
import org.koin.android.ext.android.inject
import studio.bz_soft.newsreader.R

class RootActivity : AppCompatActivity() {

    private val presenter by inject<RootPresenter>()

    private val navController by lazy { findNavController(R.id.nav_host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_NoActionBar)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)
        setSupportActionBar(toolbar)

        initialize()

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.menuNewsList, R.id.menuMore
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        mainBottomNavigationMenu.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    @SuppressLint("RestrictedApi")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_main_menu, menu)
        if (menu is MenuBuilder) menu.setOptionalIconsVisible(true)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        NavigationUI.onNavDestinationSelected(item, navController)
        when (item.itemId) {
            R.id.menuSync -> syncButtonListener()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initialize() {
        presenter.synchronize()
    }

    private fun syncButtonListener() {
        presenter.synchronize()
    }
}
