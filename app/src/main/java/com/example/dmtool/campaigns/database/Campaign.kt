package com.example.dmtool.campaigns.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Campaign(
    @PrimaryKey(autoGenerate = true)
    var campaignId: Long = 0L,
    var title: String,
    var description: String
)