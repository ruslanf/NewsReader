package studio.bz_soft.newsreader.di

import android.content.Context
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import studio.bz_soft.newsreader.data.db.DbClient
import studio.bz_soft.newsreader.data.db.DbClientInterface
import studio.bz_soft.newsreader.data.http.ApiClient
import studio.bz_soft.newsreader.data.http.ApiClientInterface
import studio.bz_soft.newsreader.data.models.viewmodels.NewsViewModel
import studio.bz_soft.newsreader.data.repository.*
import studio.bz_soft.newsreader.root.App
import studio.bz_soft.newsreader.root.Constants.API_MAIN_URL
import studio.bz_soft.newsreader.ui.news.NewsPresenter
import studio.bz_soft.newsreader.ui.root.RootController
import studio.bz_soft.newsreader.ui.root.RootPresenter

val applicationModule = module {
    single { androidApplication() as App }
}

val networkModule = module {
    single { ApiClient(API_MAIN_URL, androidContext()) as ApiClientInterface }
}

val storageModule = module {
    factory<SharedPreferences> { androidContext().getSharedPreferences("local_storage", Context.MODE_PRIVATE) }
    single { DbClient(androidApplication()) as DbClientInterface }
    single<DatabaseRepositoryInterface> { DatabaseRepository(get()) }
    single<LocalStorageInterface> { LocalStorage(get()) }
    single<RepositoryInterface> { Repository(get(), get(), get()) }
}

val presenterModule = module {
    single { RootPresenter(androidContext()) }
    single { NewsPresenter(get()) }
}

val controllerModule = module {
    single { RootController(get()) }
}

val liveDataModule = module {
    viewModel { NewsViewModel(get()) }
}