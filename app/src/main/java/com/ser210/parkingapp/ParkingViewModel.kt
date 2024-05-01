package com.ser210.parkingapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ser210.parkingapp.data.Space
import com.ser210.parkingapp.data.SpaceDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ParkingViewModel(private val spaceDao: SpaceDao) : ViewModel() {


    //function that creates sets up the database the first time the app is ran
    fun initializeSpaces() {
        viewModelScope.launch {
            if (!isDatabaseEmpty()) {
                return@launch
            } else {
                for (i in 1..32) {
                    spaceDao.insert(Space(id = i, lotName = "Hilltop", studentId = 0, spaceId = i))
                }
                for (i in 1..32) {
                    spaceDao.insert(
                        Space(
                            id = i + 32,
                            lotName = "Hogan",
                            studentId = 0,
                            spaceId = i
                        )
                    )
                }
                for (i in 1..32) {
                    spaceDao.insert(
                        Space(
                            id = i + 64,
                            lotName = "North",
                            studentId = 0,
                            spaceId = i
                        )
                    )
                }
            }
        }
    }

    //
    fun getSpaceInLot(spaceId: Int, lotName: String) {
        viewModelScope.launch {
            spaceDao.getSpaceInLot(spaceId, lotName)
        }
    }

    //function that inserts a space into the database
    fun enterSpace(lotName: String, studentId: Int, spaceId: Int) {
        viewModelScope.launch {
            spaceDao.updateSpaceStudentId(studentId, spaceId, lotName)
        }
    }

    //function that removes a student from a space
    fun leaveSpace(lotName: String, spaceId: Int) {
        viewModelScope.launch {
            spaceDao.updateSpaceStudentId(0, spaceId, lotName)
        }
    }

    //private function that checks if the database is empty for initializeSpaces()
    private suspend fun isDatabaseEmpty(): Boolean {
        return spaceDao.countSpaces() == 0
    }

    //function that inserts a space into the database (unused)
    private fun insertSpace(newSpace: Space) {
        viewModelScope.launch {
            spaceDao.insert(newSpace)
        }
    }

    //function that updates a space in the database (unused)
    fun deleteAllSpaces() {
        viewModelScope.launch {
            spaceDao.deleteAllSpaces()
        }
    }

    //function that checks if the input is valid (unused)
    fun isEntryValid(lotName: String, studentId: Int, spaceId: Int): Boolean {
        if (lotName.isEmpty() || studentId < 0 || studentId > 9999999 || spaceId <= 0) {
            return false
        }
        return true
    }

    //function that gets a space entity (unused)
    private fun getSpaceEntity(lotName: String, studentId: Int, spaceId: Int): Space {
        return (Space(lotName = lotName, studentId = studentId, spaceId = spaceId))

    }

    //function that gets all spaces from the database (unused)
    fun getAllSpaces() {
        viewModelScope.launch {
            spaceDao.getAllSpaces()
        }
    }

    fun getAllSpacesInLot(lotName: String) : LiveData<List<Space>> {
        val spacesInLot = MutableLiveData<List<Space>>()
        viewModelScope.launch {
            spacesInLot.value = spaceDao.getAllSpacesInLot(lotName)
        }
        return spacesInLot
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
