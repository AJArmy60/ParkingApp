package com.ser210.parkingapp

import android.content.Context
import androidx.fragment.app.activityViewModels
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ser210.parkingapp.data.Space
import org.junit.runner.RunWith
import org.mockito.Mockito.mock


import com.ser210.parkingapp.data.SpaceDao
import com.ser210.parkingapp.data.SpaceRoomDatabase
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import kotlin.random.Random
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class ParkingDatabaseTest {

    private lateinit var viewModel: ParkingViewModel
    private lateinit var spaceDao: SpaceDao
    private lateinit var parkingDatabase: SpaceRoomDatabase
    @Before
    fun setup(){
        viewModel = ParkingViewModel(spaceDao)
        val context: Context = ApplicationProvider.getApplicationContext()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        parkingDatabase = Room.inMemoryDatabaseBuilder(context, SpaceRoomDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        spaceDao = parkingDatabase.spaceDao()
    }

    @Test
    @Throws(Exception::class)
    fun testFillSpaces() = runBlocking{
        val expectedSpaces = listOf(
            Space(1, "Hilltop", 2600000, 1),
            Space(2, "Hilltop", 2600001, 2),
            Space(3, "Hilltop", 2600002, 3),
            Space(33, "Hogan", 2600003, 1),
            Space(34, "Hogan", 2600004, 2),
            Space(35, "Hogan", 2600005, 3),
            Space(36, "North", 2600006, 1),
            Space(37, "North", 2600007, 2),
            Space(38, "North", 2600008, 3)
        )

        for (space in expectedSpaces) {
            viewModel.parkInSpace(space.lotName, space.studentId, space.spaceId)
        }
        val actualSpaces = spaceDao.getSpaces().first()
        assertEquals(expectedSpaces, actualSpaces)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        parkingDatabase.close()
    }
}