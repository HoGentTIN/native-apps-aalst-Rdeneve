package com.example.dmtool.npcs.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.dmtool.npcs.database.Npc
import com.example.dmtool.npcs.database.NpcDao
import kotlinx.coroutines.*

class NpcViewModel(
    val database: NpcDao,
    application: Application,
    private val campaignId: Long
): AndroidViewModel(application) {
    var npcs = database.getAllForCampaign(campaignId)

    private val _navigateToCreate = MutableLiveData<Long>()
    val navigateToCreate
        get() = _navigateToCreate

    fun onCreateClicked(id: Long) {
        _navigateToCreate.value = id
    }

    fun onCreateNavigated() {
        _navigateToCreate.value = null
    }
}