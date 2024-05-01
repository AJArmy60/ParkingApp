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

    /**Dao function that gets the number of spaces in the database. Used by initializeSpaces function in ParkingViewModel.
     */
    @Query("SELECT COUNT(*) FROM spaces")
    suspend fun countSpaces(): Int

    @Query("DELETE FROM spaces")
    suspend fun deleteAllSpaces()


    /**Dao function that updates the student id of a space. Used by enterSpace and leaveSpace functions in ParkingViewModel.
     * @param studentId the id of the student that is parking in the space
     * @param spaceId the id of the space that the student is parking in
     * @param lotName the name of the lot that the space is in
     */
    @Query("UPDATE spaces SET student_id = :studentId WHERE space_id = :spaceId AND lot_name = :lotName")
    suspend fun updateSpaceStudentId(studentId: Int, spaceId: Int, lotName: String)


    /**Dao function that returns a space. Used
     * @param lotName the name of the lot that the space is in
     * @param spaceId the id of the space in the given lot
     */
    @Query("SELECT * FROM spaces WHERE space_id = :spaceId AND lot_name = :lotName")
    fun getSpaceInLot(spaceId: Int, lotName: String): Flow<List<Space>>

    @Query("SELECT * FROM spaces WHERE lot_name = :lotName")
    fun getAllSpacesInLot(lotName: String): Flow<List<Space>>

    @Query("SELECT * FROM spaces WHERE student_id = :studentId")
    fun getSpaceByStudent(studentId: Int): Space

    @Query("SELECT * FROM spaces")
    fun getAllSpaces(): Flow<List<Space>>

    @Query("SELECT student_id FROM spaces WHERE lot_name = :lotName")
    fun getAllStudentIdsInLot(lotName: String): Flow<List<Int>>
}