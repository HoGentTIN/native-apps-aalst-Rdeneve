package com.example.dmtool.npcs.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.dmtool.npcs.database.Npc
import com.example.dmtool.npcs.database.NpcDao
import com.example.dmtool.npcs.repository.NpcRepository
import com.example.dmtool.shared.getDatabase
import kotlinx.coroutines.*

class NpcDetailViewModel(
     application: Application,
     npcId: Long,
     campaignId: Long
): AndroidViewModel(application) {
    private val npcRepository = NpcRepository(getDatabase(application), campaignId)
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    var npc = MutableLiveData<Npc?>()

    init {
        initializeNpc(npcId)
    }

    private fun initializeNpc(npcId: Long) {
        uiScope.launch {
            npc.value = getFromDb(npcId)
        }
    }

    private suspend fun getFromDb(npcId: Long): Npc? {
        return withContext(Dispatchers.IO) {
            val npc = npcRepository.getById(npcId)
            npc
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}