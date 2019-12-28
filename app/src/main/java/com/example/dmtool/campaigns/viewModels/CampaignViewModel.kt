package com.example.dmtool.campaigns.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
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

    private val _navigateToNpc = MutableLiveData<Long>()
        val navigateToNpc
            get() = _navigateToNpc

    fun onCampaignClicked(id: Long) {
        _navigateToNpc.value = id
    }

    fun onNpcNavigated() {
        _navigateToNpc.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
