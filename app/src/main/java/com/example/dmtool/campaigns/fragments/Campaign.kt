package com.example.dmtool.campaigns.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.dmtool.R
import com.example.dmtool.campaigns.ViewModels.CampaignViewModel

class Campaign : Fragment() {

    companion object {
        fun newInstance() = Campaign()
    }

    private lateinit var viewModel: CampaignViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(CampaignViewModel::class.java)
        return inflater.inflate(R.layout.campaign_fragment, container, false)
    }
}
