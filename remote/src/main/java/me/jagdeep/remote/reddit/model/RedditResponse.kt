package me.jagdeep.remote.reddit.model

data class SubRedditResponse(
    val kind: String,
    val data: SubRedditData
)

data class SubRedditData(
    val dist: Int,
    val children: List<SubRedditPost>,
    val after: String,
    val before: String?
)

data class SubRedditPost(
    val kind: String,
    val data: SubRedditPostData
)

data class SubRedditPostData(
    val id: String,
    val subreddit: String,
    val title: String,
    val downs: Int,
    val ups: Int,
    val score: Int,
    val domain: String,
    val num_comments: Int,
    val is_video: Boolean,
    val author: String,
    val created_utc: Long,
    val thumbnail: String,
    val thumbnail_height: Int,
    val thumbnail_width: Int,
    val preview: SubRedditPreview?
)

data class SubRedditPreview(
    val images: List<SubRedditImages>,
    val enabled: Boolean
)

data class SubRedditImages(
    val id: String,
    val source: SubRedditImage,
    val resolutions: List<SubRedditImage>
)

data class SubRedditImage(
    val url: String,
    val width: Int,
    val height: Int
)
