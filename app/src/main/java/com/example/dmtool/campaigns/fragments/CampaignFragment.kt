package com.example.dmtool.campaigns.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.dmtool.DmDatabase

import com.example.dmtool.R
import com.example.dmtool.campaigns.CampaignAdapter
import com.example.dmtool.campaigns.CampaignListClickListener
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
        binding = DataBindingUtil.inflate(inflater, R.layout.campaign_fragment, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = DmDatabase.getInstance(application).campaignDao

        // Create viewmodel
        viewModelFactory = CampaignViewModelFactory(dataSource, application)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CampaignViewModel::class.java)

        // Nodig voor live data updates
        binding.setLifecycleOwner(this)
        binding.campaignViewModel = viewModel

        val adapter = CampaignAdapter(CampaignListClickListener {
            campaignId ->  Toast.makeText(context, "$campaignId", Toast.LENGTH_SHORT).show()
        })
        binding.campaignList.adapter = adapter

        viewModel.campaigns.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }
}
