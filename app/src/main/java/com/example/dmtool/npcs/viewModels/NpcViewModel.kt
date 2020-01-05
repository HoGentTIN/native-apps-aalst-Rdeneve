package com.example.dmtool.npcs.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dmtool.npcs.repository.NpcRepository
import com.example.dmtool.shared.getDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException

class NpcViewModel(
    application: Application,
    campaignId: Long
): AndroidViewModel(application) {

    private val npcRepository = NpcRepository(getDatabase(application), campaignId)
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    var npcs = npcRepository.npcs

    init {
        refreshData()
    }

    private val _eventNetworkError = MutableLiveData<Boolean>()
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private fun refreshData() = uiScope.launch {
        try {
            npcRepository.refreshNpcs()
            _eventNetworkError.value = false
        } catch (e: IOException) {
            _eventNetworkError.value = true
        }
    }

    private val _navigateToCreate = MutableLiveData<Long>()
    val navigateToCreate
        get() = _navigateToCreate

    fun onCreateClicked(id: Long) {
        _navigateToCreate.value = id
    }

    fun onCreateNavigated() {
        _navigateToCreate.value = null
    }

    private val _navigateToDetail = MutableLiveData<Long>()
        val navigateToDetail
        get() = _navigateToDetail

    fun onNpcClicked(id: Long) {
        _navigateToDetail.value = id
    }

    fun onNpcNavigated() {
        _navigateToDetail.value = null
    }
}