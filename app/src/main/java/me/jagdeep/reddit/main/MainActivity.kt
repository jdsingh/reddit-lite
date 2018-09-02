package me.jagdeep.reddit.main

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.View
import androidx.core.widget.toast
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import me.jagdeep.domain.reddit.model.RedditPost
import me.jagdeep.presentation.SubredditState
import me.jagdeep.presentation.SubredditViewModel
import me.jagdeep.reddit.R
import me.jagdeep.reddit.base.activityViewModel
import me.jagdeep.reddit.inject.ViewModelFactory
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var listAdapter: RedditPostAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var openRedditPostHandler: OpenRedditPostHandler

    private lateinit var viewModel: SubredditViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = activityViewModel(viewModelFactory)

        setupListView()
        observeState()

        showSubreddit("funny")
    }

    @SuppressLint("SetTextI18n")
    private fun showSubreddit(text: String) {
        subreddit.text = "r/$text"
        viewModel.showSubreddit(text)
    }

    private fun observeState() {
        viewModel.state().observe(this, Observer { state ->
            when (state) {
                is SubredditState.Error -> {
                    toast(state.message)
                        .setGravity(Gravity.CENTER, 0, 0)
                }
                is SubredditState.Success -> {
                    posts.visibility = View.VISIBLE
                    listAdapter.submitList(state.result)
                }
            }
        })
    }

    private fun setupListView() {
        listAdapter.clickListener = redditPostItemListener
        posts.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private val redditPostItemListener = object : RedditPostItemListener {

        override fun onRedditPostClicked(redditPost: RedditPost) {
            openRedditPostHandler(redditPost)
        }

    }

}
