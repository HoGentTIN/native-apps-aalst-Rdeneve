package com.example.dmtool.npcs.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.dmtool.DmDatabase

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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_npc, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = DmDatabase.getInstance(application).npcDao
        // Get campaignId from SafeArgs
        val args = NpcFragmentArgs.fromBundle(arguments!!)
        val campaignId = args.campaignId

        // Create viewmodel
        viewModelFactory = NpcViewModelFactory(dataSource, application, campaignId)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(NpcViewModel::class.java)

        binding.lifecycleOwner = this
        binding.npcViewModel = viewModel

        // Adapter voor recycler viewmet click listener
        val adapter = NpcAdapter(NpcListClickListener {
            npcId -> Toast.makeText(context, "NpcId: ${npcId}", Toast.LENGTH_SHORT).show()
        })

        binding.npcList.adapter = adapter

        // Dynamic refreshing for recycler view
        viewModel.npcs.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }


}
