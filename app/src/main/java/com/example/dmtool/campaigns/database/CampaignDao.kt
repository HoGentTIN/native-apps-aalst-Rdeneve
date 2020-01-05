package com.example.dmtool.campaigns.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Delete

@Dao
interface CampaignDao {

    @Query("SELECT * FROM Campaign WHERE campaignId = :id")
    fun getById(id: Long): Campaign?

    @Query("SELECT * FROM Campaign ORDER BY campaignId DESC")
    fun getAll(): LiveData<List<Campaign>>

    @Insert
    fun insert(campaign: Campaign)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(campaigns: List<Campaign>)

    @Delete
    fun delete(campaign: Campaign)

    @Query("DELETE FROM Campaign")
    fun clear()
}