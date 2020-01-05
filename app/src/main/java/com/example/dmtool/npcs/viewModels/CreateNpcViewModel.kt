package com.example.dmtool.npcs.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.dmtool.npcs.database.Npc
import com.example.dmtool.npcs.repository.NpcRepository
import com.example.dmtool.shared.getDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CreateNpcViewModel(
    application: Application,
    private val campaignId: Long
) : AndroidViewModel(application) {
    private val npcRepository = NpcRepository(getDatabase(application), campaignId)
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigateToNpc = MutableLiveData<Boolean>()
        val navigateToNpc
            get() = _navigateToNpc

    fun onNpcNavigated() {
        _navigateToNpc.value = null
    }

    fun createNewNpc(
        npc: Npc
    ) {
        uiScope.launch {
            npcRepository.create(npc)
            _navigateToNpc.value = true
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}