package com.example.dmtool.campaigns.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.dmtool.DmDatabase

import com.example.dmtool.R
import com.example.dmtool.campaigns.CampaignAdapter
import com.example.dmtool.campaigns.CampaignListClickListener
import com.example.dmtool.campaigns.viewModels.CampaignViewModel
import com.example.dmtool.campaigns.viewModels.CampaignViewModelFactory
import com.example.dmtool.databinding.FragmentCampaignBinding

class CampaignFragment : Fragment() {

    private lateinit var viewModelFactory: CampaignViewModelFactory
    private lateinit var viewModel: CampaignViewModel

    private lateinit var binding: FragmentCampaignBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_campaign, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = DmDatabase.getInstance(application).campaignDao

        // Create viewmodel
        viewModelFactory = CampaignViewModelFactory(dataSource, application)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CampaignViewModel::class.java)

        // Nodig voor live data updates
        binding.lifecycleOwner = this
        binding.campaignViewModel = viewModel

        //Adapter voor recycler view met click listener voor navigatie naar NpcFragment
        val adapter = CampaignAdapter(CampaignListClickListener {
            campaignId -> viewModel.onCampaignClicked(campaignId)
        })
        binding.campaignList.adapter = adapter

        viewModel.navigateToNpc.observe(this, Observer { campaign ->
            campaign?.let {
                this.findNavController().navigate(
                    CampaignFragmentDirections.actionCampaignToNpcFragment(campaign)
                )
                viewModel.onNpcNavigated()
            }
        })
        viewModel.navigateToCreate.observe(this, Observer { isClicked ->
            isClicked?.let {
                this.findNavController().navigate(
                    CampaignFragmentDirections.actionCampaignToCreateCampaignFragment()
                )
                viewModel.onCreateNavigated()
            }
        })

        // Dynamic refreshing for recycler view
        viewModel.campaigns.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }
}
