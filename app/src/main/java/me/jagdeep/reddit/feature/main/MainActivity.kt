package me.jagdeep.reddit.feature.main

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.View
import androidx.core.widget.toast
import com.skydoves.powermenu.MenuAnimation
import com.skydoves.powermenu.OnMenuItemClickListener
import com.skydoves.powermenu.PowerMenu
import com.skydoves.powermenu.PowerMenuItem
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
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

    lateinit var openRedditPostHandler: OpenRedditPostHandler

    private lateinit var viewModel: SubredditViewModel

    private lateinit var powerMenu: PowerMenu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openRedditPostHandler = OpenRedditPostHandler(this)

        viewModel = activityViewModel(viewModelFactory)

        setupListView()
        observeState()
        setupPowerMenu()
    }

    override fun onDestroy() {
        super.onDestroy()
        powerMenu.onDestroy()
    }

    @SuppressLint("SetTextI18n")
    private fun observeState() {
        viewModel.currentSubreddit().observe(this, Observer { title ->
            val item = powerMenu.itemList.find { it.title == title }
            val position = powerMenu.itemList.indexOf(item)
            powerMenu.selectedPosition = position
            subreddit.text = "r/$title"
        })

        viewModel.state().observe(this, Observer { state ->
            when (state) {
                is SubredditState.Loading -> {
                    listAdapter.submitList(emptyList())

                    shimmer_view_container.startShimmerAnimation()
                    shimmer_view_container.visibility = View.VISIBLE
                }
                is SubredditState.Error -> {
                    shimmer_view_container.stopShimmerAnimation()
                    shimmer_view_container.visibility = View.GONE

                    toast(state.message).setGravity(Gravity.CENTER, 0, 0)
                }
                is SubredditState.Success -> {
                    listAdapter.submitList(state.result)

                    shimmer_view_container.stopShimmerAnimation()
                    shimmer_view_container.visibility = View.GONE
                }
            }
        })
    }

    private fun setupListView() {
        listAdapter.clickListener = openRedditPostHandler
        posts.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private val menuItemClickListener = OnMenuItemClickListener<PowerMenuItem> { _, item ->
        viewModel.showSubreddit(item.title)
        powerMenu.dismiss()
    }

    private fun setupPowerMenu() {
        powerMenu = PowerMenu.Builder(this)
            .addItemList(subreddits())
            .setAnimation(MenuAnimation.SHOWUP_TOP_LEFT) // Animation start point (TOP | LEFT)
            .setMenuRadius(10f)
            .setMenuShadow(10f)
            .setSelectedTextColor(Color.WHITE)
            .setMenuColor(Color.WHITE)
            .setSelectedMenuColor(resources.getColor(R.color.primary))
            .setOnMenuItemClickListener(menuItemClickListener)
            .build()

        menu.setOnClickListener {
            powerMenu.showAsDropDown(menu)
        }
    }

    private fun subreddits(): List<PowerMenuItem> {
        return listOf(
            PowerMenuItem("all", true),
            PowerMenuItem("funny", false),
            PowerMenuItem("pics", false),
            PowerMenuItem("gifs", false),
            PowerMenuItem("AdviceAnimals", false),
            PowerMenuItem("cats", false),
            PowerMenuItem("Images", false),
            PowerMenuItem("photoshopbattles", false),
            PowerMenuItem("Art", false)
        )
    }

}
