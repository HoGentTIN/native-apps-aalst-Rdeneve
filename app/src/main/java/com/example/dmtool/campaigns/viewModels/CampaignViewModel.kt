package com.example.dmtool.campaigns.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dmtool.campaigns.database.Campaign
import com.example.dmtool.campaigns.database.CampaignDao
import kotlinx.coroutines.*

class CampaignViewModel(
    val database: CampaignDao,
    application: Application
) : AndroidViewModel(application) {
    var campaign = MutableLiveData<Campaign?>()
    var campaigns = database.getAll()
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        initializeCampaign()
    }

    private fun initializeCampaign() {
        uiScope.launch {
            campaign.value = getCampaignFromDatabase()
        }
    }

    private suspend fun getCampaignFromDatabase(): Campaign? {
        return withContext(Dispatchers.IO) {
            var campaign = database.getById(1)
            campaign
        }
    }

    fun createNewCampaign() {
        uiScope.launch {
            val c = Campaign(0, "Test last campaign", "Lorem ipsum dolor sit amet")
            create(c)
        }
    }

    private suspend fun create(campaign: Campaign) {
        withContext(Dispatchers.IO) {
            database.insert(campaign)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
