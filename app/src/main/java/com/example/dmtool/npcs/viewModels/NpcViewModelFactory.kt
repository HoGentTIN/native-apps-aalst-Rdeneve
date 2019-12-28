package com.example.dmtool.npcs.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dmtool.npcs.database.NpcDao

class NpcViewModelFactory(
    private val dataSource: NpcDao,
    private val application: Application,
    private val campaignId: Long
): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NpcViewModel::class.java)) {
            return NpcViewModel(dataSource, application, campaignId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}