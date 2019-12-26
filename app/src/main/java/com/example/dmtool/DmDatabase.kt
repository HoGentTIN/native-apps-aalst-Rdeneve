package com.example.dmtool

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dmtool.campaigns.database.Campaign
import com.example.dmtool.campaigns.database.CampaignDao

@Database(entities = [Campaign::class], version = 2, exportSchema = false)
abstract class DmDatabase : RoomDatabase() {
    abstract val campaignDao: CampaignDao

    companion object {
        @Volatile
        private var INSTANCE: DmDatabase? = null

        fun getInstance(context: Context): DmDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DmDatabase::class.java,
                        "DMTool_database"
                    )
                    .fallbackToDestructiveMigration()
                    .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}