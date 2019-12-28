package com.example.dmtool

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

@BindingAdapter("npcOrganization")
fun TextView.setNpcOrganization(item: Npc?) {
    item?.let { text = item.organization }
}

@BindingAdapter("npcLocation")
fun TextView.setNpcLocation(item: Npc?) {
    item?.let { text = item.location }
}