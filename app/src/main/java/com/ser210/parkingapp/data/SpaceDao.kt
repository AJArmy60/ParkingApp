package com.ser210.parkingapp.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SpaceDao {
    @Update
    suspend fun update(space: Space)


    // parking database update. Function that updates the student id and filled status of a space based on lot name and space id.
    @Query("UPDATE spaces SET student_id = :studentId, space_full = :spaceFull WHERE id = :spaceId AND lot_name = :lotName")
    suspend fun updateSpaceStudentIdPark(studentId: Int, spaceFull: Boolean, spaceId: Int, lotName: String)


    @Query("SELECT * FROM spaces")
    fun getAllSpaces(id: Int) : Flow<Space>

    @Query("SELECT * FROM spaces WHERE lot_name = :lotName")
    fun getSpacesByLot(lotName: String): Flow<Space>

    @Query("SELECT * FROM spaces WHERE student_id = :studentId")
    fun getSpaceByStudent(studentId: Int): Space
}