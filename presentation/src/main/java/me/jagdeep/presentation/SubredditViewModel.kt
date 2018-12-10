package me.jagdeep.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import me.jagdeep.domain.reddit.GetSubredditUseCase
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class SubredditViewModel @Inject constructor(
    private val getSubredditUseCase: GetSubredditUseCase
) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _currentSubreddit = MutableLiveData<String>()
    val currentSubreddit: LiveData<String>
        get() = _currentSubreddit

    private val _subredditState = MutableLiveData<SubredditState>()
    val subredditState: LiveData<SubredditState>
        get() = _subredditState

    init {
        showSubreddit("all")
    }

    fun showSubreddit(subreddit: String) {
        _currentSubreddit.value = subreddit

        getSubredditUseCase.execute(GetSubredditUseCase.Companion.Params(subreddit))
            .doOnSubscribe {
                _subredditState.value = SubredditState.Loading
            }
            .subscribe({ results ->
                Timber.i("Got posts: ${results.size}")
                _subredditState.value = SubredditState.Success(results)
            }, { e ->
                Timber.e(e, "Failed to get Subreddit")
                handleError(e)
            })
            .addTo(disposables)
    }

    private fun handleError(e: Throwable) {
        when (e) {
            is IOException -> {
                _subredditState.value = SubredditState.Error("You're not connected to Internet!")
            }
            else -> {
                _subredditState.value = SubredditState.Error("Something is not right!")
            }
        }
    }

    override fun onCleared() {
        disposables.dispose()
    }

}
