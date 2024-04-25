package com.ser210.parkingapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "spaces")
data class Space(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "lot_name")
    val lotName: String,
    @ColumnInfo(name = "student_id")
    val studentId: Int,
    @ColumnInfo(name = "space_id")
    val spaceId: Int,

    )
