package com.ser210.parkingapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SpaceDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(space: Space)

    @Update
    suspend fun update(space: Space)

    @Delete()
    suspend fun delete(space: Space)

    @Query("SELECT * FROM spaces WHERE id = :id")
    fun getSpace(id: Int) : Flow<Space>

    @Query("SELECT * FROM spaces")
    fun getSpaces() : Flow<List<Space>>

    @Query("DELETE FROM spaces")
    suspend fun deleteAllSpaces()

    // parking database update. Function that updates the student id and filled status of a space based on lot name and space id.
    @Query("UPDATE spaces SET student_id = :studentId WHERE space_id = :spaceId AND lot_name = :lotName")
    suspend fun updateSpaceStudentId(studentId: Int, spaceId: Int, lotName: String)




    @Query("SELECT * FROM spaces WHERE lot_name = :lotName")
    fun getSpacesByLot(lotName: String): Flow<Space>

    @Query("SELECT * FROM spaces WHERE student_id = :studentId")
    fun getSpaceByStudent(studentId: Int): Space
}