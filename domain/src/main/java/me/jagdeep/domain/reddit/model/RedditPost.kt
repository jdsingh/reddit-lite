package me.jagdeep.domain.reddit.model

import android.os.Parcelable
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import java.text.DecimalFormat

/**
 * RedditPost domain entity.
 */
@Parcelize
data class RedditPost(
    val id: String,
    val subreddit: String,
    val title: String,
    val ups: Int,
    val author: String,
    val created: Long,
    val domain: String,
    val number_of_comments: Int,
    val thumbnail_url: String,
    val thumbnail_height: Int,
    val thumbnail_width: Int,
    val source_image_url: String,
    val source_image_width: Int,
    val source_image_height: Int
) : Parcelable {

    @IgnoredOnParcel
    val upString: String = ups.toSmallNumber()

    @IgnoredOnParcel
    val commentString: String = number_of_comments.toSmallNumber()

    private fun Int.toSmallNumber(): String {
        if (this < 1000) {
            return toString()
        }

        val n = div(1000.0)
        return DecimalFormat("0.0").format(n) + "k"
    }

}
