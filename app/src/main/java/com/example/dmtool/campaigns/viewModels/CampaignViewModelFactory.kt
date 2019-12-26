package com.example.dmtool.campaigns.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CampaignViewModelFactory(): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CampaignViewModel::class.java)) {
            return CampaignViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}