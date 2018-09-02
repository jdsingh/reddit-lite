package me.jagdeep.remote.reddit

import io.reactivex.Single
import me.jagdeep.remote.reddit.model.SubRedditResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RedditApiService {

    @GET("r/{subreddit}.json")
    fun getSubreddit(
        @Path("subreddit") subreddit: String,
        @Query("limit") limit: Int
    ): Single<SubRedditResponse>

}
