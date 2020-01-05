package com.example.dmtool.campaigns.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.dmtool.campaigns.database.Campaign
import com.example.dmtool.campaigns.repository.CampaignRepository
import com.example.dmtool.shared.getDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CreateCampaignViewModel(
    application: Application
) : AndroidViewModel(application) {
    private val campaignRepository = CampaignRepository(getDatabase(application))
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
            campaignRepository.create(c)
        }
        _navigateToCampaign.value = true
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}