package me.jagdeep.reddit.inject.modules

import dagger.Binds
import dagger.Module
import me.jagdeep.data.reddit.repository.RedditDataRepository
import me.jagdeep.domain.reddit.repository.RedditRepository

@Module
abstract class DataModule {

    @Binds
    abstract fun bindsRedditRepository(redditRepository: RedditDataRepository): RedditRepository

}
