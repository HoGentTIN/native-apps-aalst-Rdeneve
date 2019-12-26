package com.example.dmtool.campaigns.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.example.dmtool.R
import com.example.dmtool.campaigns.viewModels.CampaignViewModel
import com.example.dmtool.campaigns.viewModels.CampaignViewModelFactory
import com.example.dmtool.databinding.CampaignFragmentBinding

class Campaign : Fragment() {

    /* companion object {
        fun newInstance() = Campaign()
    }*/
    private lateinit var viewModelFactory: CampaignViewModelFactory
    private lateinit var viewModel: CampaignViewModel

    private lateinit var binding: CampaignFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModelFactory = CampaignViewModelFactory()
        viewModel = ViewModelProviders.of(this)
            .get(CampaignViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.campaign_fragment, container, false)
        binding.campaignViewModel = viewModel
        binding.setLifecycleOwner(this)

        viewModel.title.value = "init title"
        viewModel.description.value = "init desc"

        binding.button.setOnClickListener {
            viewModel.title.value = "title changed"
            viewModel.description.value = "desc changed"
            Log.i("Click","Button click method called")
        }

        return binding.root
    }
}
