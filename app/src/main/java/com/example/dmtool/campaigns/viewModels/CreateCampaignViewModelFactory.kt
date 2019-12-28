package com.example.dmtool.campaigns.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dmtool.campaigns.database.CampaignDao

class CreateCampaignViewModelFactory(
    private val dataSource: CampaignDao,
    private val application: Application
): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateCampaignViewModel::class.java)) {
            return CreateCampaignViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}