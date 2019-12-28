package com.example.dmtool.npcs.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.dmtool.DmDatabase

import com.example.dmtool.R
import com.example.dmtool.databinding.FragmentCreateNpcBinding
import com.example.dmtool.npcs.viewModels.CreateNpcViewModel
import com.example.dmtool.npcs.viewModels.CreateNpcViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class CreateNpcFragment : Fragment() {
    private lateinit var viewModelFactory: CreateNpcViewModelFactory
    private lateinit var viewModel: CreateNpcViewModel
    private lateinit var binding: FragmentCreateNpcBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_npc, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = DmDatabase.getInstance(application).npcDao
        val args = CreateNpcFragmentArgs.fromBundle(arguments!!)
        val campaignId = args.campaignId

        viewModelFactory = CreateNpcViewModelFactory(dataSource, application, campaignId)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CreateNpcViewModel::class.java)

        binding.create.setOnClickListener {
            val name = binding.npcName.text.toString()
            val organization = binding.npcOrganization.text.toString()
            val location = binding.npcLocation.text.toString()
            val history = binding.npcHistory.text.toString()

            viewModel.createNewNpc(name, location, organization, history)
        }

        viewModel.navigateToNpc.observe(this, Observer { isClicked ->
            isClicked?.let {
                this.findNavController().navigate(
                    CreateNpcFragmentDirections.actionCreateNpcFragmentToNpcFragment(campaignId)
                )
                viewModel.onNpcNavigated()
            }
        })

        return binding.root
    }


}
