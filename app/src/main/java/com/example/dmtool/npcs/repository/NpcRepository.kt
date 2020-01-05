package com.example.dmtool.npcs.repository

import com.example.dmtool.npcs.database.Npc
import com.example.dmtool.shared.DmDatabase
import com.example.dmtool.shared.DmToolApi
import com.example.dmtool.shared.NetworkNpc
import com.example.dmtool.shared.asNpc
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NpcRepository (private val database: DmDatabase, private val campaignId: Long) {

    val npcs = database.npcDao.getAllForCampaign(campaignId)

    suspend fun refreshNpcs() {
        withContext(Dispatchers.IO) {
            val npcs = DmToolApi.retrofitService.getNpcsForCampaignAsync(campaignId).await()
            database.npcDao.insert(npcs.asNpc())
        }
    }

    fun getById(npcId: Long): Npc? {
        return database.npcDao.getById(npcId)
    }

    suspend fun create(npc: Npc) {
        val networkNpc = NetworkNpc.from(npc)

        withContext(Dispatchers.IO) {
            DmToolApi.retrofitService.postNpc(networkNpc)
        }
    }
}