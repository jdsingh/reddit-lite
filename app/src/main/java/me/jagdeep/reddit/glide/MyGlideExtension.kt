package me.jagdeep.reddit.glide

import android.content.Context
import android.util.DisplayMetrics
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

fun Context.toPx(value: Int) =
    Math.round(value * (resources.displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))

fun <T> GlideRequest<T>.roundedCorners(cornerRadius: Int) =
    apply(RequestOptions().transform(RoundedCorners(cornerRadius)))
