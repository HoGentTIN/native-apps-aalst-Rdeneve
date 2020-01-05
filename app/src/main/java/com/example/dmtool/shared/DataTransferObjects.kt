package com.example.dmtool.shared

import com.example.dmtool.campaigns.database.Campaign
import com.example.dmtool.npcs.database.Npc
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkCampaign(
    val id: Long,
    val title: String,
    val description: String
) {
    companion object {
        fun from(campaign: Campaign): NetworkCampaign {
            return NetworkCampaign(
                campaign.campaignId,
                campaign.title,
                campaign.description
            )
        }
    }
}

@JsonClass(generateAdapter = true)
data class NetworkNpc(
    val id: Long,
    val name: String,
    val type: String,
    val alignment: String,
    val size: String,
    val location: String,
    val organization: String,
    val history: String,
    val campaignId: Long
) {
    companion object {
        fun from(npc: Npc): NetworkNpc {
            return NetworkNpc(npc.npcId,
                npc.name,
                npc.type,
                npc.alignment,
                npc.size,
                npc.location,
                npc.organization,
                npc.history,
                npc.campaignId)
        }
    }
}

fun NetworkCampaign.asCampaign(): Campaign {
    return Campaign(id, title, description)
}

fun NetworkNpc.asNpc(): Npc {
    return Npc(id, campaignId, name, type, alignment, size, location, organization, history)
}

fun List<NetworkCampaign>.asCampaign(): List<Campaign> = this.map { it.asCampaign() }

fun List<NetworkNpc>.asNpc(): List<Npc> = this.map { it.asNpc() }