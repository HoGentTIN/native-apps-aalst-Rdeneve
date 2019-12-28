package com.example.dmtool.campaigns.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.dmtool.DmDatabase

import com.example.dmtool.R
import com.example.dmtool.campaigns.viewModels.CreateCampaignViewModel
import com.example.dmtool.campaigns.viewModels.CreateCampaignViewModelFactory
import com.example.dmtool.databinding.FragmentCreateCampaignBinding

/**
 * A simple [Fragment] subclass.
 */
class CreateCampaignFragment : Fragment() {

    private lateinit var viewModelFactory: CreateCampaignViewModelFactory
    private lateinit var viewModel: CreateCampaignViewModel
    private lateinit var binding: FragmentCreateCampaignBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_campaign, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = DmDatabase.getInstance(application).campaignDao

        // Create viewmodel
        viewModelFactory = CreateCampaignViewModelFactory(dataSource, application)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CreateCampaignViewModel::class.java)

        binding.create.setOnClickListener {
            val title = binding.campaignTitle.text.toString()
            val description = binding.campaignDescription.text.toString()

            viewModel.createNewCampaign(title, description)
        }

        viewModel.navigateToCampaign.observe(this, Observer { isClicked ->
            isClicked?.let {
                this.findNavController().navigate(
                    CreateCampaignFragmentDirections.actionCreateCampaignFragmentToCampaign()
                )
                viewModel.onCampaignNavigated()
            }
        })

        return binding.root
    }


}
