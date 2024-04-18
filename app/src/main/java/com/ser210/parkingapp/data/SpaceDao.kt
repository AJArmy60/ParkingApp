package com.ser210.parkingapp.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SpaceDao {
    @Update
    suspend fun updateSpace(space: Space)

    @Query("SELECT * FROM spaces WHERE lot_name = :lotName")
    fun getSpacesByLot(lotName: String): Flow<Space>

    @Query("SELECT * FROM spaces WHERE student_id = :studentId")
    fun getSpacesByStudent(studentId: Int): Flow<List<Space>>
}