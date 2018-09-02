package me.jagdeep.remote

import me.jagdeep.remote.reddit.RedditApiService
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object ApiServiceFactory {

    open fun makeApiService(isDebug: Boolean, cacheDir: File): RedditApiService {
        val okHttpClient = makeOkHttpClient(
            makeLoggingInterceptor((isDebug)), cacheDir
        )
        return makeRedditApiService(okHttpClient)
    }

    private fun makeRedditApiService(okHttpClient: OkHttpClient): RedditApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.reddit.com/")
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(RedditApiService::class.java)
    }

    private fun makeOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        cacheDir: File
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .cache(makeCache(cacheDir))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    private fun makeCache(file: File): Cache {
        return Cache(file, 10 * 1000 * 1000) // 10 mb cache file
    }

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }

}
