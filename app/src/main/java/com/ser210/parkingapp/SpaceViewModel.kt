package com.ser210.parkingapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ser210.parkingapp.data.Space
import com.ser210.parkingapp.data.SpaceDao

class SpaceViewModel(private val spaceDao: SpaceDao): ViewModel() {

    suspend fun updateSpace(space: Space) {
        spaceDao.updateSpace(space)
    }

    fun getSpacesByLot(lotName: String) {
        spaceDao.getSpacesByLot(lotName)
    }

    fun getSpacesByStudent(studentId: Int) {
        spaceDao.getSpacesByStudent(studentId)
    }
}

class SpaceViewModelFactory (private val spaceDao: SpaceDao) : ViewModelProvider.Factory {
    fun <T: ViewModel?> create( modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SpaceViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SpaceViewModel(spaceDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }

}