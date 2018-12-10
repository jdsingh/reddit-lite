package me.jagdeep.presentation

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import me.jagdeep.domain.reddit.GetSubredditUseCase
import me.jagdeep.domain.reddit.repository.RedditRepository
import org.junit.Rule
import org.junit.Test
import java.io.IOException
import kotlin.test.assertEquals

class SubredditViewModelTest {

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val redditRepository: RedditRepository = mock()
    private val schedulerProvider = TestSchedulerProvider()
    private val getSubredditUseCase = GetSubredditUseCase(schedulerProvider, redditRepository)

    private lateinit var viewModel: SubredditViewModel

    private val initialSubreddit = "all"

    @Test
    fun init_showAllSubreddit() {
        // Given that the repository returns with success
        whenever(redditRepository.getSubreddit(any()))
            .thenReturn(Single.just(RedditPostFactory.createList()))

        // Given a viewModel
        viewModel = SubredditViewModel(getSubredditUseCase)

        // Then the currentSubreddit is "all"
        val currentSubreddit = LiveDataTestUtil.getValue(viewModel.currentSubreddit)
        assertEquals(initialSubreddit, currentSubreddit)
    }

    @Test
    fun getSubreddit_showsGivenSubredditPosts() {
        val moviePosts = RedditPostFactory.createList()

        // Given that the repository returns with success
        whenever(redditRepository.getSubreddit(any()))
            .thenReturn(Single.just(moviePosts))

        // Given a viewModel
        viewModel = SubredditViewModel(getSubredditUseCase)
        viewModel.showSubreddit("movies")

        // Then the currentSubreddit is "movies"
        val currentSubreddit = LiveDataTestUtil.getValue(viewModel.currentSubreddit)
        assertEquals("movies", currentSubreddit)

        val expectedState = SubredditState.Success(moviePosts)

        // Then the uiState should show movie posts
        val uiState = LiveDataTestUtil.getValue(viewModel.subredditState)
        assertEquals(expectedState, uiState)
    }

    @Test
    fun getSubreddit_showsErrorState() {
        // Given that the repository returns with success
        whenever(redditRepository.getSubreddit(any()))
            .thenReturn(Single.error(Throwable("error")))

        // Given a viewModel
        viewModel = SubredditViewModel(getSubredditUseCase)
        viewModel.showSubreddit("Android")

        // Then the currentSubreddit is "Android"
        val currentSubreddit = LiveDataTestUtil.getValue(viewModel.currentSubreddit)
        assertEquals("Android", currentSubreddit)

        val expectedState = SubredditState.Error("Something is not right!")

        // Then the uiState should show movie posts
        val uiState = LiveDataTestUtil.getValue(viewModel.subredditState)
        assertEquals(expectedState, uiState)
    }

    @Test
    fun getSubreddit_showsErrorStateWhenNoInternet() {
        // Given that the repository returns with success
        whenever(redditRepository.getSubreddit(any()))
            .thenReturn(Single.error(IOException("error")))

        // Given a viewModel
        viewModel = SubredditViewModel(getSubredditUseCase)
        viewModel.showSubreddit("jokes")

        // Then the currentSubreddit is "jokes"
        val currentSubreddit = LiveDataTestUtil.getValue(viewModel.currentSubreddit)
        assertEquals("jokes", currentSubreddit)

        val expectedState = SubredditState.Error("You're not connected to Internet!")

        // Then the uiState should show movie posts
        val uiState = LiveDataTestUtil.getValue(viewModel.subredditState)
        assertEquals(expectedState, uiState)
    }

}
