package com.example.dmtool.npcs.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.example.dmtool.campaigns.database.Campaign

@Entity(foreignKeys = [ForeignKey(entity = Campaign::class,
                        parentColumns = arrayOf("campaignId"),
                        childColumns = arrayOf("campaignId"),
                        onDelete = CASCADE)])
data class Npc(
    @PrimaryKey(autoGenerate = true)
    var npcId: Long = 0L,
    var campaignId: Long,
    var name: String,
    var type: String,
    var alignment: String,
    var size: String,
    var location: String,
    var organization: String,
    var history: String
)