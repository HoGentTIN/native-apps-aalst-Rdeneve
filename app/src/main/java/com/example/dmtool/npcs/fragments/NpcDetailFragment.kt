package com.example.dmtool.npcs.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.dmtool.R
import com.example.dmtool.databinding.FragmentNpcDetailBinding
import com.example.dmtool.npcs.viewModels.NpcDetailViewModel
import com.example.dmtool.npcs.viewModels.NpcDetailViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class NpcDetailFragment : Fragment() {
    private lateinit var viewModelFactory: NpcDetailViewModelFactory
    private lateinit var viewModel: NpcDetailViewModel
    private lateinit var binding: FragmentNpcDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_npc_detail, container, false)
        val application = requireNotNull(this.activity).application
        val args = NpcDetailFragmentArgs.fromBundle(arguments!!)
        val npcId = args.npcId
        val campaignId = args.campaignId

        viewModelFactory = NpcDetailViewModelFactory(application, npcId, campaignId)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(NpcDetailViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }
}
