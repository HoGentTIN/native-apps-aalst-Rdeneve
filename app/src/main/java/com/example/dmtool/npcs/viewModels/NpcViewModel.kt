package com.example.dmtool.npcs.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.dmtool.npcs.database.Npc
import com.example.dmtool.npcs.database.NpcDao
import kotlinx.coroutines.*

class NpcViewModel(
    val database: NpcDao,
    application: Application,
    private val campaignId: Long
): AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    var npcs = database.getAllForCampaign(campaignId)

    fun createNewNpc() {
        uiScope.launch {
            val n = Npc(
                0,
                campaignId,
                "Test Npc",
                "Whiterun",
                "Skyrim",
                "Lorem ipsum dolor sit amet"
            )
            create(n)
        }
    }

    private suspend fun create(npc: Npc) {
        withContext(Dispatchers.IO) {
            database.insert(npc)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}