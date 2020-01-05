package com.example.dmtool.campaigns.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dmtool.campaigns.repository.CampaignRepository
import com.example.dmtool.shared.getDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException

class CampaignViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val campaignRepository = CampaignRepository(getDatabase(application))
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    var campaigns = campaignRepository.campaigns

    init {
        refreshData()
    }

    private val _eventNetworkError = MutableLiveData<Boolean>()
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private fun refreshData() = uiScope.launch {
        try {
            campaignRepository.refreshCampaigns()
            _eventNetworkError.value = false
        } catch (e: IOException) {
            _eventNetworkError.value = true
        }
    }

    // Navigation for npc fragment
    private val _navigateToNpc = MutableLiveData<Long>()
    val navigateToNpc
        get() = _navigateToNpc

    fun onCampaignClicked(id: Long) {
        _navigateToNpc.value = id
    }

    fun onNpcNavigated() {
        _navigateToNpc.value = null
    }

    // Navigation for create campaign fragment
    private val _navigateToCreate = MutableLiveData<Boolean>()
        val navigateToCreate
            get() = _navigateToCreate

    fun onCreateClicked(bool: Boolean) {
        _navigateToCreate.value = bool
    }

    fun onCreateNavigated() {
        _navigateToCreate.value = null
    }

}
