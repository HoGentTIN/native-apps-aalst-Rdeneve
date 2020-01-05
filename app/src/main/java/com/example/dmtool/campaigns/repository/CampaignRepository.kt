package com.example.dmtool.campaigns.repository

import com.example.dmtool.campaigns.database.Campaign
import com.example.dmtool.shared.DmDatabase
import com.example.dmtool.shared.DmToolApi
import com.example.dmtool.shared.NetworkCampaign
import com.example.dmtool.shared.asCampaign
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CampaignRepository (private val database: DmDatabase) {

    val campaigns = database.campaignDao.getAll()

    suspend fun refreshCampaigns() {
        withContext(Dispatchers.IO) {
            val campaigns = DmToolApi.retrofitService.getCampaignsAsync().await()
            database.campaignDao.insert(campaigns.asCampaign())
        }
    }

    suspend fun create(campaign: Campaign) {
        val networkCampaign = NetworkCampaign.from(campaign)

        withContext(Dispatchers.IO) {
            DmToolApi.retrofitService.postCampaign(networkCampaign)
        }
    }
}