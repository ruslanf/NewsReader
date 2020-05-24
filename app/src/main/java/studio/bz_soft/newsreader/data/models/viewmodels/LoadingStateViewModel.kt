package studio.bz_soft.newsreader.data.models.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import studio.bz_soft.newsreader.root.LoadingState
import studio.bz_soft.newsreader.ui.root.RootPresenter

class LoadingStateViewModel : LoadingStateInterface, ViewModel() {
    val loadingState: MutableLiveData<LoadingState> = MutableLiveData()

    init {
        loadingState.value = LoadingState()
    }

    override fun loadingStarted() {
        loadingState.value = currentLoadingState().copy(isLoading = true)
    }

    override fun loadingFinished() {
        loadingState.value = currentLoadingState().copy(isLoading = false)
    }

    fun registerLoadingStateListener(presenter: RootPresenter) {
        presenter.loadingStateInterface = this
    }

    private fun currentLoadingState(): LoadingState = loadingState.value!!
}