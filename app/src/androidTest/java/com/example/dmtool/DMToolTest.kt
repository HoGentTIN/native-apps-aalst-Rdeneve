package com.example.dmtool

import androidx.room.Room
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.dmtool.campaigns.database.Campaign
import com.example.dmtool.campaigns.database.CampaignDao
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@RunWith(AndroidJUnit4::class)
class DMToolTest {
    private lateinit var campaignDao: CampaignDao
    private lateinit var db: DmDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, DmDatabase::class.java).allowMainThreadQueries().build()
        campaignDao = db.campaignDao
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun testRoomDatabaseForCampaign() {
        val c = Campaign(0, "Title", "Description")
        campaignDao.insert(c)
        val campaign = campaignDao.getById(1)
        assertEquals(campaign?.title, "Title")
        assertEquals(campaign?.description, "Description")
    }

}