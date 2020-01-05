package com.example.dmtool.npcs.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.dmtool.R
import com.example.dmtool.databinding.FragmentCreateNpcBinding
import com.example.dmtool.npcs.database.Npc
import com.example.dmtool.npcs.viewModels.CreateNpcViewModel
import com.example.dmtool.npcs.viewModels.CreateNpcViewModelFactory
import com.example.dmtool.shared.DmDatabase

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
        val args = CreateNpcFragmentArgs.fromBundle(arguments!!)
        val campaignId = args.campaignId

        viewModelFactory = CreateNpcViewModelFactory(application, campaignId)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CreateNpcViewModel::class.java)

        // Set size dropdown content
        val sizes: Array<String> = arrayOf("Tiny", "Small", "Medium", "Large", "Huge", "Gargantuan")
        val sizeAdapter: ArrayAdapter<String> = ArrayAdapter(requireContext(), R.layout.dropdown_item_npc, sizes)
        binding.sizeDropdown.setAdapter(sizeAdapter)

        // Set alignment dropdown content
        val alignments: Array<String> = arrayOf(
            "Lawful Good",
            "Neutral Good",
            "Chaotic Good",
            "Lawful Neutral",
            "True Neutral",
            "Chaotic Neutral",
            "Lawful Evil",
            "Neutral Evil",
            "Chaotic Evil"
        )
        val alignmentAdapter: ArrayAdapter<String> = ArrayAdapter(requireContext(), R.layout.dropdown_item_npc, alignments)
        binding.alignmentDropdown.setAdapter(alignmentAdapter)

        binding.create.setOnClickListener {
            val npc = convertValuesToNpc(campaignId)
            viewModel.createNewNpc(npc)
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

    private fun convertValuesToNpc(campaignId: Long): Npc {
        val name = binding.npcName.text.toString()
        val size = binding.sizeDropdown.text.toString()
        val alignment =  binding.alignmentDropdown.text.toString()
        val type = binding.npcType.text.toString()
        val organization = binding.npcOrganization.text.toString()
        val location = binding.npcLocation.text.toString()
        val history = binding.npcHistory.text.toString()

        return Npc(0, campaignId, name, type, alignment, size, location, organization, history)
    }



}
