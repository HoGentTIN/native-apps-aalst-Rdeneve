package com.example.dmtool.campaigns.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CampaignViewModel : ViewModel() {
    private val _title = MutableLiveData<String>()
    private val _description = MutableLiveData<String>()

    val title: MutableLiveData<String>
        get() = _title

    val description: MutableLiveData<String>
        get() = _description
}
