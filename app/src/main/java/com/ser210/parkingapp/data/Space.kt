package com.ser210.parkingapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "spaces")
data class Space(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "lot_name")
    val lotName: String,
    @ColumnInfo(name = "student_id")
    val studentId: Int,
    @ColumnInfo(name = "space_number")
    val lotSpaceNumber: Int,
    @ColumnInfo(name = "full")
    val spaceFull: Boolean

)
