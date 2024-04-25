package com.ser210.parkingapp

import android.app.Application
import com.ser210.parkingapp.data.Space
import com.ser210.parkingapp.data.SpaceRoomDatabase

class ParkingApplication: Application(){
    val database : SpaceRoomDatabase by lazy { SpaceRoomDatabase.getDatabase(this) }
}