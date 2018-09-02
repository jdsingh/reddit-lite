package me.jagdeep.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.rxkotlin.addTo
import me.jagdeep.domain.reddit.GetSubredditUseCase
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class SubredditViewModel @Inject constructor(
    private val getSubredditUseCase: GetSubredditUseCase
) : ViewModel() {

    private val subredditState = MutableLiveData<SubredditState>()

    init {
        subredditState.value = SubredditState.Uninitialized
    }

    fun state(): LiveData<SubredditState> = subredditState

    fun showSubreddit(subreddit: String) {
        getSubredditUseCase.execute(GetSubredditUseCase.Companion.Params(subreddit))
            .doOnSubscribe {
                subredditState.value = SubredditState.Loading
            }
            .subscribe({ results ->
                Timber.i("Got posts: ${results.size}")
                subredditState.value = SubredditState.Success(results)
            }, { e ->
                Timber.e(e, "Failed to get Subreddit")
                handleError(e)
            })
            .addTo(getSubredditUseCase.disposables)
    }

    private fun handleError(e: Throwable) {
        when (e) {
            is IOException -> {
                subredditState.value = SubredditState.Error("You're not connected to Internet!")
            }
            else -> {
                subredditState.value = SubredditState.Error("Something is not right!")
            }
        }
    }

    override fun onCleared() {
        getSubredditUseCase.dispose()
    }

}
