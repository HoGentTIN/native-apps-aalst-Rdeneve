package com.example.dmtool.npcs.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NpcDao {
    @Query("SELECT * FROM Npc WHERE campaignId = :id")
    fun getAllForCampaign(id: Long): LiveData<List<Npc>>

    @Query("SELECT * FROM Npc WHERE npcId = :id")
    fun getById(id: Long): Npc?

    @Insert
    fun insert(npc: Npc)

    @Update
    fun update(npc: Npc)

    @Delete
    fun delete(npc: Npc)

    @Query("DELETE FROM Npc")
    fun clear()
}