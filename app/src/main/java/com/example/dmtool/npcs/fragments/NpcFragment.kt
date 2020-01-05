package com.example.dmtool.npcs.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.dmtool.R
import com.example.dmtool.databinding.FragmentNpcBinding
import com.example.dmtool.npcs.NpcAdapter
import com.example.dmtool.npcs.NpcListClickListener
import com.example.dmtool.npcs.viewModels.NpcViewModel
import com.example.dmtool.npcs.viewModels.NpcViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class NpcFragment : Fragment() {

    private lateinit var viewModelFactory: NpcViewModelFactory
    private lateinit var viewModel: NpcViewModel
    private lateinit var binding: FragmentNpcBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_npc, container, false)
        val application = requireNotNull(this.activity).application

        // Get campaignId from SafeArgs
        val args = NpcFragmentArgs.fromBundle(arguments!!)
        val campaignId = args.campaignId

        // Create viewmodel
        viewModelFactory = NpcViewModelFactory(application, campaignId)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(NpcViewModel::class.java)

        binding.lifecycleOwner = this
        binding.npcViewModel = viewModel

        // Adapter voor recycler view met click listener
        val adapter = NpcAdapter(NpcListClickListener {
            npcId -> viewModel.onNpcClicked(npcId)
        })

        binding.create.setOnClickListener {
            viewModel.onCreateClicked(campaignId)
        }

        viewModel.navigateToCreate.observe(this, Observer { campaign ->
            campaign?.let {
                this.findNavController().navigate(
                    NpcFragmentDirections.actionNpcFragmentToCreateNpcFragment(campaign)
                )
                viewModel.onCreateNavigated()
            }
        })

        viewModel.navigateToDetail.observe(this, Observer { npc ->
            npc?.let {
                this.findNavController().navigate(
                    NpcFragmentDirections.actionNpcFragmentToNpcDetailFragment(npc, campaignId)
                )
                viewModel.onNpcNavigated()
            }
        })

        binding.npcList.adapter = adapter

        // NetworkError observer
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) onNetworkError()
            }
        })

        // Dynamic refreshing for recycler view
        viewModel.npcs.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }

    private fun onNetworkError() {
        Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
    }
}
