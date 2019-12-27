package com.example.dmtool.campaigns

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.dmtool.campaigns.database.Campaign

@BindingAdapter("campaignTitle")
fun TextView.setCampaignTitle(item: Campaign?) {
    item?.let { text = item.title }
}

@BindingAdapter("campaignDescription")
fun TextView.setCampaignDescription(item: Campaign?) {
    item?.let { text = item.description }
}