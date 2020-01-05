package com.example.dmtool.npcs.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NpcDetailViewModelFactory(
    private val application: Application,
    private val npcId: Long,
    private val campaignId: Long
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NpcDetailViewModel::class.java)) {
            return NpcDetailViewModel(application, npcId, campaignId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}