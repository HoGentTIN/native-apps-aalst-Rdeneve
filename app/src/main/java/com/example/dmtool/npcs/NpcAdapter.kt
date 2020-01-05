package com.example.dmtool.npcs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dmtool.databinding.ListItemNpcBinding
import com.example.dmtool.npcs.database.Npc

class NpcAdapter(val clickListener: NpcListClickListener) : ListAdapter<Npc, NpcAdapter.ViewHolder>(NpcDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NpcAdapter.ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: NpcAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

    class ViewHolder private constructor(val binding: ListItemNpcBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Npc, clickListener: NpcListClickListener) {
            binding.npc = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemNpcBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class NpcDiffCallback : DiffUtil.ItemCallback<Npc>() {
    override fun areItemsTheSame(oldItem: Npc, newItem: Npc): Boolean {
        return oldItem.npcId == newItem.npcId
    }

    override fun areContentsTheSame(oldItem: Npc, newItem: Npc): Boolean {
        return oldItem == newItem
    }
}
class NpcListClickListener(val clickListener: (npcId: Long) -> Unit) {
    fun onClick(npc: Npc) = clickListener(npc.npcId)
}