package com.example.dmtool.npcs.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CreateNpcViewModelFactory(
    private val application: Application,
    private val campaignId: Long
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateNpcViewModel::class.java)) {
            return CreateNpcViewModel(application, campaignId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}