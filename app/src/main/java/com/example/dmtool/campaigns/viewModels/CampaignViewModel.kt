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
    var campaigns = database.getAll()
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
