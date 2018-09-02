package me.jagdeep.database.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "subreddits")
data class Subreddit(

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,

    @ColumnInfo(index = true)
    var subreddit: String,

    var lastCachedTime: Long,

    var postIds: String

)
