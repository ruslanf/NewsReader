package studio.bz_soft.newsreader.root.state

data class LoadingState(
    val isLoading: Boolean = false,
    val isError: Boolean = false
)