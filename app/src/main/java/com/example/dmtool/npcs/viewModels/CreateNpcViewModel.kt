package com.example.dmtool.npcs.viewModels

import android.app.Application
import android.provider.ContactsContract
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.dmtool.npcs.database.Npc
import com.example.dmtool.npcs.database.NpcDao
import kotlinx.coroutines.*

class CreateNpcViewModel (
    private val database: NpcDao,
    application: Application,
    private val campaignId: Long
): AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigateToNpc = MutableLiveData<Boolean>()
        val navigateToNpc
            get() = _navigateToNpc

    fun onNpcNavigated() {
        _navigateToNpc.value = null
    }

    fun createNewNpc(name: String, location: String, organization: String, history: String) {
        uiScope.launch {
            val n = Npc(0, campaignId, name, location, organization, history)
            create(n)
            _navigateToNpc.value = true
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