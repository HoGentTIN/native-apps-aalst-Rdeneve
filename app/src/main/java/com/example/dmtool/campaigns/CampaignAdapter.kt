package com.example.dmtool.campaigns

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dmtool.campaigns.database.Campaign
import com.example.dmtool.databinding.ListItemCampaignBinding

class CampaignAdapter(val clickListener: CampaignListClickListener) : ListAdapter<Campaign, CampaignAdapter.ViewHolder>(CampaignDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

    class ViewHolder private constructor(val binding: ListItemCampaignBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Campaign, clickListener: CampaignListClickListener) {
            binding.campaign = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemCampaignBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class CampaignDiffCallback : DiffUtil.ItemCallback<Campaign>() {
    override fun areItemsTheSame(oldItem: Campaign, newItem: Campaign): Boolean {
        return oldItem.campaignId == newItem.campaignId
    }

    override fun areContentsTheSame(oldItem: Campaign, newItem: Campaign): Boolean {
        return oldItem == newItem
    }
}

class CampaignListClickListener(val clickListener: (campaignId: Long) -> Unit) {
    fun onClick(campaign: Campaign) = clickListener(campaign.campaignId)
}
