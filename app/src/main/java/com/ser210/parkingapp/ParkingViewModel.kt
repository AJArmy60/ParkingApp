package com.ser210.parkingapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ser210.parkingapp.data.Space
import com.ser210.parkingapp.data.SpaceDao
import kotlinx.coroutines.launch

class ParkingViewModel(private val spaceDao: SpaceDao) : ViewModel() {

    private fun getSpaceEntity(lotName: String, studentId: Int, spaceId: Int): Space {
        return (Space(lotName = lotName, studentId = studentId, spaceId = spaceId))

    }
    fun addNewSpace(lotName: String, studentId: Int, spaceId: Int) {
        val newSpace = getSpaceEntity(lotName, studentId, spaceId)
        insertSpace(newSpace)
    }

    private fun insertSpace(newSpace: Space) {
        viewModelScope.launch {
            spaceDao.insert(newSpace)
        }
    }


    fun deleteAllSpaces() {
        viewModelScope.launch {
            spaceDao.deleteAllSpaces()
        }
    }

    fun leaveSpace(lotName: String, spaceId: Int) {
        viewModelScope.launch {
            spaceDao.updateSpaceStudentId(0, spaceId, lotName)
        }
    }



    fun isEntryValid(lotName: String, studentId: Int, spaceId: Int): Boolean {
        if (lotName.isEmpty() || studentId < 0 || studentId > 9999999 || spaceId <= 0) {
            return false
        }
        return true
    }

    fun parkInSpace(lotName: String, studentId: Int, spaceId: Int) {
        viewModelScope.launch {
            spaceDao.updateSpaceStudentId(studentId, spaceId, lotName)
        }
    }



}

class ParkingViewModelFactory(private val spaceDao: SpaceDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ParkingViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ParkingViewModel(spaceDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
