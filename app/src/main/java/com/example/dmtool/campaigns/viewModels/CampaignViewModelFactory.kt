package com.example.dmtool.campaigns.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CampaignViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CampaignViewModel::class.java)) {
            return CampaignViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}