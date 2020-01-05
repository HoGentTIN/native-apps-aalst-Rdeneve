package com.example.dmtool.npcs.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Delete

@Dao
interface NpcDao {
    @Query("SELECT * FROM Npc WHERE campaignId = :id")
    fun getAllForCampaign(id: Long): LiveData<List<Npc>>

    @Query("SELECT * FROM Npc WHERE npcId = :id")
    fun getById(id: Long): Npc?

    @Insert
    fun insert(npc: Npc)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(npcs: List<Npc>)

    @Delete
    fun delete(npc: Npc)

    @Query("DELETE FROM Npc")
    fun clear()
}