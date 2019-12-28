package com.example.dmtool.campaigns.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.dmtool.campaigns.database.Campaign
import com.example.dmtool.campaigns.database.CampaignDao
import kotlinx.coroutines.*

class CreateCampaignViewModel(
    val database: CampaignDao,
     application: Application
) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigateToCampaign = MutableLiveData<Boolean>()
        val navigateToCampaign
            get() = _navigateToCampaign

    fun onCampaignNavigated() {
        _navigateToCampaign.value = null
    }

    fun createNewCampaign(title: String, description: String) {
        uiScope.launch {
            val c = Campaign(0, title, description)
            create(c)
            _navigateToCampaign.value = true
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