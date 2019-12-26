package com.example.dmtool.campaigns.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CampaignDao  {

    @Query("SELECT * FROM Campaign WHERE campaignId = :id")
    fun getById(id: Long): Campaign?

    @Query("SELECT * FROM Campaign ORDER BY campaignId DESC")
    fun getAll(): LiveData<List<Campaign>>

    @Insert
    fun insert(campaign: Campaign)

    @Update
    fun update(campaign: Campaign)

    @Delete
    fun delete(campaign: Campaign)
}