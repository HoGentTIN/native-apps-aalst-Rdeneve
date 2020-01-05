package com.example.dmtool.shared

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.dmtool.campaigns.database.Campaign
import com.example.dmtool.npcs.database.Npc

@BindingAdapter("campaignTitle")
fun TextView.setCampaignTitle(item: Campaign?) {
    item?.let { text = item.title }
}

@BindingAdapter("campaignDescription")
fun TextView.setCampaignDescription(item: Campaign?) {
    item?.let { text = item.description }
}

@BindingAdapter("npcName")
fun TextView.setNpcName(item: Npc?) {
    item?.let { text = item.name }
}

@BindingAdapter("npcType")
fun TextView.setNpcType(item: Npc?) {
    item?.let { text = item.type }
}

@BindingAdapter("npcTypeFormatted")
fun TextView.setNpcTypeFormatted(item: Npc?) {
    item?.let { text = formatNpcType(item.type) }
}

@BindingAdapter("npcLocation")
fun TextView.setNpcLocation(item: Npc?) {
    item?.let { text = item.location }
}

@BindingAdapter("npcLocationFormatted")
fun TextView.setNpcLocationFormatted(item: Npc?) {
    item?.let { text =
        formatNpcLocation(item.location)
    }
}

@BindingAdapter("npcAlignment")
fun TextView.setNpcAlignment(item: Npc?) {
    item?.let { text = item.alignment }
}

@BindingAdapter("npcSize")
fun TextView.setNpcSize(item: Npc?) {
    item?.let { text = item.size }
}

@BindingAdapter("npcOrganization")
fun TextView.setNpcOrganization(item: Npc?) {
    item?.let { text = item.organization }
}

@BindingAdapter("npcHistory")
fun TextView.setNpcHistory(item: Npc?) {
    item?.let { text = item.history }
}